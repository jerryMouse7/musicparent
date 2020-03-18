package com.liu.userservice.service.impl;

import com.liu.userservice.dao.UserDao;
import com.liu.userservice.entity.User;
import com.liu.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectUserByIds(List<Long> ids) {
        return userDao.selectUserByIds(ids);
    }
}
