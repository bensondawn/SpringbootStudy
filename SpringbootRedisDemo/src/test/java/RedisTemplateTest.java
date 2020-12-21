import com.ljshuoda.Service.RedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

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
//        redisService.set("key1", "value1");
        System.out.println(redisService.get("key1"));

        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys){
            System.out.println(key);
        }
    }

    @Test
    public void test2(){

//        redisService.setListLeft("listkey1","a1",600L, TimeUnit.SECONDS);
//        redisService.setListLeft("listkey1","a2",600L, TimeUnit.SECONDS);
//        redisService.setListLeft("listkey1","a3",600L, TimeUnit.SECONDS);
//        redisService.setListLeft("listkey1","a4",600L, TimeUnit.SECONDS);

//        System.out.println("resultList1:"+redisService.getListLeft("listkey1"));
        System.out.println("Last element:"+redisService.getElement("listkey1",0));
    }
}

