package com.liu.passport.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    public User(String accountName, String email, Collection<? extends GrantedAuthority> authorities) {
        this.accountName = accountName;
        this.email = email;
        this.authorities = authorities;
    }

    private Long id;

    private String accountName;

    private int type;

    private String password;

    private String name;

    private String phone;

    private String email;

    private int state;

    private long createdBy;

    private LocalDateTime createdDate;

    private long updatedBy;

    private LocalDateTime updatedDate;
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
    //等级
    private int level;

    private Collection<? extends GrantedAuthority> authorities;

}
