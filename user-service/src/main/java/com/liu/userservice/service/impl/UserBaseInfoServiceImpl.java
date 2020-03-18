package com.liu.userservice.service.impl;

import com.liu.userservice.dao.UserBaseInfoDao;
import com.liu.userservice.entity.UserBaseInfo;
import com.liu.userservice.service.UserBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserBaseInfoServiceImpl implements UserBaseInfoService {

    @Autowired
    private UserBaseInfoDao userBaseInfoDao;

    @Override
    public UserBaseInfo selectUserBaseInfoByUserId(long userId) {
        return userBaseInfoDao.selectUserBaseInfoByUserId(userId);
    }

    @Override
    public String insertUserBaseInfo(long userId, String accountName, LocalDateTime createTime, LocalDateTime updateTime) {
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setUserId(userId);
        userBaseInfo.setAccountName(accountName);
        userBaseInfo.setCreateTime(createTime);
        userBaseInfo.setUpdateTime(updateTime);

        userBaseInfoDao.insertUserBaseInfo(userBaseInfo);

        return "注册成功";
    }
}
