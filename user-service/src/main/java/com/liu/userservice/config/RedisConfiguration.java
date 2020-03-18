package com.liu.userservice.config;

import com.liu.utils.redis.RedisAdapter;
import com.liu.utils.redis.StringRedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfiguration {
    @Bean
    public StringRedisClient stringRedisClient(){
        return new StringRedisClient();
    }

    @Bean
    public RedisAdapter redisAdapter(){
        return new RedisAdapter();
    }
}
