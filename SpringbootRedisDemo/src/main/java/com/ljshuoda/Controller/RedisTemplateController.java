package com.ljshuoda.Controller;

import com.ljshuoda.Model.Student;
import com.ljshuoda.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/redis")
public class RedisTemplateController {

//    @Autowired
//    StringRedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/set")
    public String set() {

        Student stu = new Student("zhang",20);

        redisService.set("key1",stu,60L);
        redisService.set("key2","value2",60L);

        return "success";
    }

    @RequestMapping("/get")
    public String show(){

        Object object = redisService.get("key1");
        System.out.println(((Student)object).getName());
        System.out.println(((Student)object).getAge());

        System.out.println(redisService.get("key2"));

        return "success";
    }

    @RequestMapping("/get-list")
    public void listTest(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","zhang");
        map.put("age",32);
        System.out.println(map.get("name") + "+++++++++" + map.get("age"));
        redisService.lPush("redis:list:test",map,100L);

        Long len = redisService.lSize("redis:list:test");
        System.out.println("list size:" + len);
        Object obj1 = redisService.lIndex("redis:list:test",0);
        HashMap<String,Object> map1 = (HashMap<String, Object>) obj1;
        map1.put("name","binbin");
        map1.put("age",21);
        Long timeout = redisService.getExpire("redis:list:test");
        redisService.lRemove("redis:list:test",0,map);
        redisService.lPush("redis:list:test",map1,timeout);

        len = redisService.lSize("redis:list:test");
        System.out.println("list size:" + len);
        Object obj2 = redisService.lIndex("redis:list:test",0);
        HashMap<String,Object> map2 = (HashMap<String, Object>) obj2;
        System.out.println(map2.get("name") + "+++++++++" + map2.get("age"));
    }

}
