package com.ljshuoda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate; //stringRedisTemplate只能缓存key-value的String类型

    @Override
    public void set(String key, Object value,long timeout, TimeUnit unit){
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value,timeout, unit);//设定key的缓存时间为60秒；第三个参数是时间长度，第四个参数是时间单位。
    }

    @Override
    public Object get(String key) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public void setListLeft(String key, Object value,long timeout, TimeUnit unit) {
        ListOperations<String, Object> lo = redisTemplate.opsForList();
        lo.leftPush(key,value);
        redisTemplate.expire(key,timeout,unit);
    }

    @Override
    public Object getListLeft(String key) {
        ListOperations<String, Object> lo = redisTemplate.opsForList();
        return lo.leftPop(key);
    }

    @Override
    public Object getElement(String key, int index) {
        ListOperations<String, Object> lo = redisTemplate.opsForList();
        return lo.index(key,index);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void setTimeout(String key,long timeout, TimeUnit unit){
        redisTemplate.expire(key,timeout,unit);
    }
}
