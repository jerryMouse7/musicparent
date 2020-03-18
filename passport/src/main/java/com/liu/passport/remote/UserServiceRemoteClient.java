package com.liu.passport.remote;

import com.liu.passport.config.FeignConfiguration;
import com.liu.passport.remote.falback.UserServiceRemoteClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-service", configuration = FeignConfiguration.class,fallback = UserServiceRemoteClientFallback.class)
public interface UserServiceRemoteClient {

    @GetMapping("/user")
    String hello();
}
