package com.liu.clouddisk.service;

import com.liu.clouddisk.entity.Capacity;

import java.time.LocalDateTime;

public interface CapacityService {
    /**
     * 初始化网盘容量
     *
     * @param userId 用户id
     */
    void initCapacity(Long userId);


    /**
     * 增加网盘容量
     *
     * @param userId 用户id
     * @param size   增加的大小
     */
    void addCapacity(Long userId, Long size);

    /**
     * 根据用户id查询其容量
     *
     * @param userId
     * @return
     */
    Capacity getCapacityByUserId(Long userId);

    /**
     * 开通会员增加的临时容量
     *
     * @param userId       用户id
     * @param tempCapacity 临时容量
     * @param expireTime   到期时间
     */
    void addTempCapacity(Long userId, Long tempCapacity, LocalDateTime expireTime);


    /**
     * 续费会员 增加会员时间
     *
     * @param userId
     * @param expireTime
     */
    void addDate(Long userId, LocalDateTime expireTime);
}
