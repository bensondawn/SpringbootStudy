import Service.RedisPoolClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.ShardedJedis;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisPoolClient.class)
public class RedisTest {

    @Autowired
    private RedisPoolClient redisPoolClient;

    @Test
    public void test2() {
//        String jsonPublicMsg = JSON.toJSONString(new PublicMsgModel("11111","22222","333333","44444","55555"));
//        redisPoolClient.ConstructionRedisPool();
        ShardedJedis shardedJedis = redisPoolClient.getRedisClient();
//        shardedJedis.set("key7",jsonPublicMsg);
        System.out.println(shardedJedis.get("mykey1"));
    }

}