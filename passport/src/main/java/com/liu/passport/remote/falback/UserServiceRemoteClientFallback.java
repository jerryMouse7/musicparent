package com.liu.passport.remote.falback;

import com.liu.passport.remote.UserServiceRemoteClient;
import org.springframework.stereotype.Component;

@Component
public class UserServiceRemoteClientFallback implements UserServiceRemoteClient {
    @Override
    public String hello() {
        return "fail";
    }
}
