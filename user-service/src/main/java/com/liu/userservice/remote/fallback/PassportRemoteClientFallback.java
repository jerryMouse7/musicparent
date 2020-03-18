package com.liu.userservice.remote.fallback;

import com.liu.userservice.remote.PassportRemoteClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;
@Component
public class PassportRemoteClientFallback implements PassportRemoteClient {
    @Override
    public Map<String, Object> userInfo(String name) {
        return null;
    }
}
