package com.liu.passport.service.impl;

import com.liu.passport.dao.UserLoginHistoryDao;
import com.liu.passport.entity.UserLoginHistory;
import com.liu.passport.service.UserLoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginHistoryServiceImpl implements UserLoginHistoryService {

    @Autowired
    private UserLoginHistoryDao userLoginHistoryDao;
    @Override
    public void insertUserLoginHistory(UserLoginHistory userLoginHistory) {
        userLoginHistoryDao.insertUserLoginHistory(userLoginHistory);
    }
}
