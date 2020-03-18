package com.liu.passport.remote;

import com.liu.passport.config.FeignConfiguration;
import com.liu.passport.remote.falback.CommonRemoteClientFallback;
import com.liu.passport.remote.falback.UserServiceRemoteClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common",configuration = FeignConfiguration.class,fallback = CommonRemoteClientFallback.class)
public interface CommonRemoteClient {

    @PostMapping("/api/common/email")
    String sendEmail(@RequestParam("to") String to, @RequestParam("subject") String subject,@RequestParam("content") String content);

}
