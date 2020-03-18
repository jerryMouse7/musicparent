package com.liu.passport.dao;

import com.liu.passport.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
public interface UserDao {
    User selectByAccountAndState(@Param("accountName") String accountName, @Param("state") int state);
//account_name,password,email,type,name,phone,state
    void insertUser(String username,String password,String email,String name,int type,int state,String phone);

    void createUser(@Param("user") User user);
}
