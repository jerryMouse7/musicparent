package com.liu.userservice.controller;

import com.liu.userservice.entity.UserBaseInfo;
import com.liu.userservice.service.UserBaseInfoService;
import com.liu.utils.api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
public class UserBaseInfoController {

    @Autowired
    private UserBaseInfoService userBaseInfoService;

    @GetMapping("/{userId}")
    public ApiResponse<UserBaseInfo> getUserBaseInfo(@PathVariable("userId") long userId){
        UserBaseInfo userBaseInfo =  userBaseInfoService.selectUserBaseInfoByUserId(userId);
        return new ApiResponse<>(userBaseInfo);
    }

    @PostMapping("/login")
    public ApiResponse<String> login(){
        return null;
    }
}
