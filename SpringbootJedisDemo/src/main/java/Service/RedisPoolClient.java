package Service;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisPoolClient {

    /*
    // Redis服务器地址
    @Value("${lj.redis.list-host.host}")
    private String host;
    // Redis服务器端口号
    @Value("${lj.redis.list-host.port}")
    private int port;
    // 控制一个pool可分配最大jedis实例数
    @Value("${lj.redis.jedis.pool.max-total}")
    private int maxTotal;
    // 控制一个pool最多有多少个状态为空闲的jedis实例
    @Value("${lj.redis.jedis.pool.max-idle}")
    private int maxIdle;
    // 当池内没有返回对象时，最大等待时间，单位毫秒
    @Value("${lj.redis.jedis.pool.max-wait}")
    private long maxWait;
    */
    private Logger logger = LoggerFactory.getLogger(RedisPoolClient.class);

    // 切片链接池
    private ShardedJedisPool shardedJedisPool;

    // 需采用构造器注入，Spring在生成实例的时候，上面的私有变量没有值，Spring是先产生实例，然后用在set值，采用构造器注入就可以了。
    public RedisPoolClient(@Value("${lj.redis.list-host.host}") String host, @Value("${lj.redis.list-host.port}")int port,
                           @Value("${lj.redis.jedis.pool.max-total}")int maxTotal,@Value("${lj.redis.jedis.pool.max-idle}")int maxIdle,
                           @Value("${lj.redis.jedis.pool.max-wait}")long maxWait) {

        logger.info("host:" + host);

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        //控制一个pool可分配最大jedis实例数，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool分配已满，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(maxTotal);
        //控制一个pool最多有多少个状态为空闲的jedis实例。
        config.setMaxIdle(maxIdle);
        //当池内没有返回对象时，最大等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        //小于零:阻塞不确定的时间,  默认-1；单位毫秒；
        config.setMaxWaitMillis(maxWait);
        //在引入一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(true);
        //return一个jedis实例给pool时，是否检查连接可用性ping()；
        config.setTestOnReturn(true);

        // 链接
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo(host,port));
        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    // 方法描述，获取Jedis实例
    public ShardedJedis getRedisClient() {
        ShardedJedis shardJedis = null;
        try {
            shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            logger.error("getRedisClent error:" + e.getMessage());
            if (null != shardJedis)
                shardJedis.close();
        }
        return null;
    }

    // 释放连接资源
    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedis.close();
    }
}
