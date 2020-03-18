package com.liu.passport.controller;

import com.liu.passport.entity.User;
import com.liu.passport.enums.UserDef;
import com.liu.passport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.security.Security;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class UserIdentityController {

    private final String AUTHORTIES = "authorities";

    @Autowired
    private UserService userService;

    @RequestMapping("/info")
    public Map<String, Object> userInfo(@RequestParam("name") String name){
        SecurityContextHolder.getContext().getAuthentication();
//        if (principal instanceof OAuth2Authentication){
//            OAuth2Authentication auth2Authentication = (OAuth2Authentication) principal;
//            if(!auth2Authentication.isClientOnly()){
//                String username = principal.getName();
//                String clientId = auth2Authentication.getOAuth2Request().getClientId();
//                User user = userService.findByAccountNameAndState(username, UserDef.State.NORMAL.getCode());
//
//                Map<String,Object> userInfo = new HashMap<>();
//                userInfo.put("email",user.getEmail());
//                userInfo.put("name",user.getName());
//                userInfo.put("id",String.valueOf(user.getId()));
//                userInfo.put("type",String.valueOf(user.getType()));
//
//                return userInfo;
//            }
//        }
//        return Collections.emptyMap();
//        String username = principal.getName();
        User user = userService.findByAccountNameAndState(name, UserDef.State.NORMAL.getCode());

        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("email",user.getEmail());
        userInfo.put("name",user.getName());
        userInfo.put("id",String.valueOf(user.getId()));
        userInfo.put("type",String.valueOf(user.getType()));
        userInfo.put("singerId","1");

        return userInfo;
    }
}
