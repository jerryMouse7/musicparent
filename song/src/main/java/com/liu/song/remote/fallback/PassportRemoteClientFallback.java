package com.liu.song.remote.fallback;

import com.liu.song.remote.PassportRemoteClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PassportRemoteClientFallback implements PassportRemoteClient {
    @Override
    public Map<String, Object> userInfo(String name) {
        return null;
    }
}
