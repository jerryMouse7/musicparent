package com.liu.clouddisk.service.impl;

import com.liu.clouddisk.entity.UserSignCount;
import com.liu.clouddisk.mapper.UserSignCountMapper;
import com.liu.clouddisk.service.UserSignCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserSignCountServiceImpl implements UserSignCountService {

    @Autowired
    private UserSignCountMapper userSignCountMapper;

    @Override
    public Integer initUserSignCount(Long userId) {
        UserSignCount userSignCount = new UserSignCount();
        userSignCount.setId(userId);
        userSignCount.setLastSign(LocalDate.of(2000,1,1));
        userSignCount.setSignCount(0);
        return userSignCountMapper.initUserSignCount(userSignCount);
    }

    @Override
    public Optional<UserSignCount> selectByUserId(Long userId) {
        return userSignCountMapper.selectByUserId(userId);
    }

    @Override
    public void update(UserSignCount userSignCount) {
        userSignCountMapper.update(userSignCount);
    }
}
