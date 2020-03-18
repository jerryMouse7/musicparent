package com.liu.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisAdapter {

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    public boolean zadd(String key, String value,double score){
        return redisTemplate.opsForZSet().add(key,value,score);
    }

    public long zrem(String key, String value){
        return redisTemplate.opsForZSet().remove(key,value);
    }




}
