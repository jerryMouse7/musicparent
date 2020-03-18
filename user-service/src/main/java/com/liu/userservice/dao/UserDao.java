package com.liu.userservice.dao;

import com.liu.userservice.entity.User;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
//    User selectByAccountAndState(@Param("accountName") String accountName, @Param("state") int state);
////account_name,password,email,type,name,phone,state
//    void insertUser(String username, String password, String email, String name, int type, int state, String phone);

    List<User> selectUserByIds(@Param("ids") List<Long> ids);
}
