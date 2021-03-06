package com.liu.userservice.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@FeignClient(value = "passport")
public interface PassportRemoteClient {

    @RequestMapping("/oauth/info")
    Map<String, Object> userInfo(@RequestParam("name") String name);
}
