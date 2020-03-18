package com.liu.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${username:qwer}")
    private String username;

    @GetMapping("/config/getusername")
    public String getUsername(){
        return username;
    }
}
