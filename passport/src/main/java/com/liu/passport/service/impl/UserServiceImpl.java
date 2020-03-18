package com.liu.passport.service.impl;

import com.liu.passport.dao.UserDao;
import com.liu.passport.entity.User;
import com.liu.passport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByAccountNameAndState(String accountName, int state) {
        return userDao.selectByAccountAndState(accountName, state);
    }

    @Override
    public void insertUser(String username,String password,String email){
        String encodePassword = passwordEncoder.encode(password);
        userDao.insertUser(username,encodePassword,email,"刘",1,1,"15970239542");

    }

    @Override
    public void insertUser(User user) {
        userDao.createUser(user);
    }
}
