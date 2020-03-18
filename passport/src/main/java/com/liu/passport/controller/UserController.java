package com.liu.passport.controller;

import com.liu.passport.dto.AccessTokenDTO;
import com.liu.passport.provider.GithubProvider;
import com.liu.passport.remote.CommonRemoteClient;
import com.liu.passport.remote.UserServiceRemoteClient;
import com.liu.passport.service.UserService;
import com.liu.utils.redis.StringRedisClient;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceRemoteClient userServiceRemoteClient;

    @Autowired
    private StringRedisClient stringRedisClient;

    @Autowired
    private CommonRemoteClient commonRemoteClient;


    public Object getUser(Authentication authentication){
        return authentication;
    }
    @GetMapping(value = "/user")
    public String user(Principal principal) {
        return "sdfasd";
    }

    @PostMapping(value = "/regist")
    public String regist(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password")String password,
                         @RequestParam(value = "email")String email,
                         @RequestParam(value = "verifyCode") String verifyCode ) {
        String code = stringRedisClient.get(email);
        if(verifyCode.equals(code)){
            userService.insertUser(username,password,email);
            stringRedisClient.delete(email);
            // todo 注册成功后新增user_base_info

            return "注册成功";
        }else{
            return "验证码错误";
        }

    }

    @PostMapping("/email")
    public String email(@RequestParam("to") String to){
        return sendEmail(to);
    }

    private String sendEmail(String to){
        int verifgCode = new Random().nextInt(899999)+100000;
        String content  = "你的验证码为:"+verifgCode;
        String title = "welcome to music";
        stringRedisClient.setEx(to,String.valueOf(verifgCode),5, TimeUnit.MINUTES);
        return commonRemoteClient.sendEmail(to,title,content);
    }

    public String updatePassword(String accountName,String oldPassword,String newPassword1, String newPassword2){
        if(!newPassword1.equals(newPassword2)){
            return "两次密码不一致";
        }
        String old = null;


        return null;
    }

    @PostMapping("/github/callback")
    public String loginFromGithub(@RequestParam("code") String code) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("87ceddd7c837811b428c");
        accessTokenDTO.setClient_secret("965825256e0bb70016a33e68a707528846cb5ce8");
        accessTokenDTO.setCode(code);
        String result = githubProvider.getGithubAccessToken(accessTokenDTO);
        return result;
    }


    @GetMapping("/user/hello")
    public String getHello() {
        return userServiceRemoteClient.hello();
    }
}
