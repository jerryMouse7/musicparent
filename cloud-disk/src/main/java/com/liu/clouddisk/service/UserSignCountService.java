package com.liu.clouddisk.service;

import com.liu.clouddisk.entity.UserSignCount;

import java.util.Optional;

public interface UserSignCountService {
    /**
     * @param userId 用户id
     * @return
     */
    Integer initUserSignCount(Long userId);

    /**
     * @param userId 用户id
     * @return
     */
    Optional<UserSignCount> selectByUserId(Long userId);

    /**
     * 更新操作
     */
    void update(UserSignCount userSignCount);

}
