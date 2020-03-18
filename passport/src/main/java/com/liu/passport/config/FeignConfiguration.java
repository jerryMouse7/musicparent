package com.liu.passport.config;

import com.liu.passport.interceptor.FeignBasicAuthRequestInterceptor;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    /**
     * 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    //Basic认证配置  也可以自定义拦截器实现认证
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user","password");
    }

//    //自定义拦截器实现认证
//    @Bean
//    public FeignBasicAuthRequestInterceptor feignBasicAuthRequestInterceptor(){
//        return new FeignBasicAuthRequestInterceptor();
//    }


    //配置超时时间
    @Bean
    public Request.Options options(){
        return  new Request.Options(5000,10000);
    }
}
