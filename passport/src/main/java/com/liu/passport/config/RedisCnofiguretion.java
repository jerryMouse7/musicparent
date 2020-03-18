package com.liu.passport.config;

import com.liu.utils.redis.StringRedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisCnofiguretion {

    @Bean
    public StringRedisClient stringRedisClient(){
        return new StringRedisClient();
    }
}
