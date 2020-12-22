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
        // Json序列号器
        RedisSerializer<Object> serializer = redisSerializer();
        // 创建一个模板类
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 将redis连接工厂设置到模板类中
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //配置key String序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //配置value序列化器
        redisTemplate.setValueSerializer(serializer);
        //配置hash key String序列化器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //配置hash value 序列化器
        redisTemplate.setHashValueSerializer(serializer);
        return redisTemplate;
    }

    /**
     * 序列化器
     * @return
     */
    @Bean
    public RedisSerializer<Object> redisSerializer() {
        //创建JSON序列化器
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(objectMapper);
        return serializer;
    }

    /**
     * 缓存管理器，配合@Cacheable、@CachePut、@CacheEvict使用。
     * 1、@Cacheable使用该注解的方法当缓存存在时，会从缓存中获取数据而不执行方法，当缓存不存在时，
     *   会执行方法并把返回结果存入缓存中。一般使用在查询方法上。
     * 2、@CachePut使用该注解的方法每次执行时都会把返回结果存入缓存中。一般使用在新增方法上。
     * 3、@CacheEvict使用该注解的方法执行时会清空指定的缓存。一般使用在更新或删除方法上。
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        // Redis加锁的写入器
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置Redis缓存有效期为1天
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer()))
                .entryTtl(Duration.ofSeconds(10L));
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

}
