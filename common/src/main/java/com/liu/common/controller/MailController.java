package com.liu.common.controller;

import com.liu.common.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/email")
    public String sendEmail(@RequestParam("to") String to,
                          @RequestParam("subject") String subject,
                          @RequestParam("content") String content){
        mailService.sendSimpleMail(to,subject,content);
        return "发送邮件成功";

    }

}
