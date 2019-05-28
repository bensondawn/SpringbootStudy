package com.ljshuoda.Controller;

import com.alibaba.fastjson.JSON;
import com.ljshuoda.Model.Student;
import com.ljshuoda.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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

        redisService.set("key1",stu,60L, TimeUnit.SECONDS);
        redisService.set("key2","value2",60L, TimeUnit.SECONDS);

        return "success";
    }

    @RequestMapping("/get")
    public String show(){

        System.out.println(JSON.toJSONString(redisService.get("key1")));

        Object object = redisService.get("key1");
        System.out.println(((Student)object).getName());
        System.out.println(((Student)object).getAge());

        System.out.println(redisService.get("key2"));

        return "success";
    }

}
