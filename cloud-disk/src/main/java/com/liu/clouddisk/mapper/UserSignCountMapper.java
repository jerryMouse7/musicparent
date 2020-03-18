package com.liu.clouddisk.mapper;

import com.liu.clouddisk.entity.UserSignCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserSignCountMapper {

    /**
     * @param userSignCount 添加用户签到
     * @return
     */
    Integer initUserSignCount(@Param("userSignCount") UserSignCount userSignCount);

    /**
     * @param userId 用户id
     * @return
     */
    Optional<UserSignCount> selectByUserId(@Param("userId") Long userId);

    /**
     * 用户签到更新
     *
     * @param userSignCount
     */
    void update(@Param("userSignCount") UserSignCount userSignCount);
}
