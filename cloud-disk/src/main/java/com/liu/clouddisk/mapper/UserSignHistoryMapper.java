package com.liu.clouddisk.mapper;

import com.liu.clouddisk.entity.UserSignCount;
import com.liu.clouddisk.entity.UserSignHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSignHistoryMapper {

    void initSignHistory(UserSignHistory userSignHistory);

}
