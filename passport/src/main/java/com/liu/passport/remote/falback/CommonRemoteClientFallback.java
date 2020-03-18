package com.liu.passport.remote.falback;

import com.liu.passport.remote.CommonRemoteClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CommonRemoteClientFallback implements CommonRemoteClient {
    @Override
    public String sendEmail(String to, String subject, String content) {
        return "发送邮件失败";
    }
}
