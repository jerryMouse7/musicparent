package com.liu.userservice.service;

import com.liu.userservice.entity.User;

import java.util.List;

public interface UserService {

    List<User> selectUserByIds(List<Long> ids);
}
