package com.liu.userservice.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserBaseInfo {

    private long id;

    //用户id
    private long userId;

    //用户名
    private String accountName;

    //性别
    private int sex;

    //签名
    private String signature;

    //生日
    private LocalDateTime birthday;

    //地区
    private String location;

    //大学
    private String university;

    //个人介绍
    private String selfIntroduction;

    // 头像路径
    private String avatar;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
