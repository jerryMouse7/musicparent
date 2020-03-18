package com.liu.userservice.service;

import com.liu.userservice.entity.UserBaseInfo;

import java.time.LocalDateTime;

public interface UserBaseInfoService {

    /**
     * 根据用户id查询用户的基本信息
     *
     * @param userId 用户id
     * @return
     */
    UserBaseInfo selectUserBaseInfoByUserId(long userId);

    String insertUserBaseInfo(long userId, String accountName, LocalDateTime createTime,LocalDateTime updateTime);
}
