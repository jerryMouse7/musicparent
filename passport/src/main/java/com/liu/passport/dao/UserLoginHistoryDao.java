package com.liu.passport.dao;

import com.liu.passport.entity.UserLoginHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLoginHistoryDao {

    void insertUserLoginHistory(@Param("userLoginHistory") UserLoginHistory userLoginHistory);
}
