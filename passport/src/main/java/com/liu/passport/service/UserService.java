package com.liu.passport.service;

import com.liu.passport.entity.User;

import java.util.Optional;

public interface UserService {
    User findByAccountNameAndState(String accountName, int state);
    void insertUser(String username,String password,String email);
    void insertUser(User user);
}
