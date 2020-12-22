package redis;

import com.ljshuoda.Service.RedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@SpringBootApplication(scanBasePackages = {
        "com.ljshuoda"
})
class BootConfig {

}
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootConfig.class)
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private RedisServiceImpl redisService;

    @Test
    public void test1(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","zhang");
        map.put("age",32);
        System.out.println(map.get("name") + "+++++++++" + map.get("age"));
        redisService.lPushAll("redis:list:test3",map);
    }

    @Test
    public void test2(){

        Long ll = redisService.getExpire("redis:list:test1");
        System.out.println(ll);

        HashMap<String,Object> map = new HashMap<>();

        long len = redisService.lSize("redis:list:test");
        if (len > 0){
            for (int i=0;i<len;i++){
                redisService.lRemove("redis:list:test",i,map);
            }
        }
    }
}

