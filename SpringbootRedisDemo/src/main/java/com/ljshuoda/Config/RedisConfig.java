package com.ljshuoda.Config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建一个模板类
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 创建String序列化器
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        // 创建Json序列化器
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //json转对象类，不设置默认的会将json转成hashmap
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 将redis连接工厂设置到模板类中
        template.setConnectionFactory(redisConnectionFactory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //key hashmap序列化
        template.setHashKeySerializer(redisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    /**
     * 目前的理解就是应用在@CachePut,@Cacheable,@CacheEvict中的管理方式。
     * 该种方式是管理注解缓存的管理器；
     * 两种方式生成注解缓存的管理器，这时一种自定义的方式;
     * 另一种是在yaml中配置redis.cache,Springboot自动生成管理器；
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        // Redis加锁的写入器
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);
        // 启动Redis缓存的默认设置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // 设置JDK序列化器
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
        // 禁用前缀
//        config = config.disableKeyPrefix();
        // 设置超时自动清空
        config = config.entryTtl(Duration.ofSeconds(10));
        // 创建Redis缓存管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager(writer,config);
        return redisCacheManager;
    }

}
