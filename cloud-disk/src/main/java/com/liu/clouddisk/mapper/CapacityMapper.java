package com.liu.clouddisk.mapper;

import com.liu.clouddisk.entity.Capacity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

@Mapper
public interface CapacityMapper {
    void initCapacity(@Param("capacity") Capacity capacity);


    /**
     * @param userId 用户id
     * @param size   增加的大小
     */
    void addCapacity(@Param("userId") Long userId, @Param("size") Long size);

    /**
     * 根据用户id查询其网盘容量
     *
     * @param userId 用户id
     * @return
     */
    Capacity getCapacityByUserId(@Param("userId") Long userId);

    /**
     * 开通会员  添加临时容量
     *
     * @param capacity
     */
    void addTempCapacity(Capacity capacity);
}
