package com.liu.userservice.dao;

import com.liu.userservice.entity.UserBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface UserBaseInfoDao {
    //根据用户id查询用户的基本信息
    UserBaseInfo selectUserBaseInfoByUserId(long userId);

    void insertUserBaseInfo(@Param("userBaseInfo") UserBaseInfo userBaseInfo);
}
