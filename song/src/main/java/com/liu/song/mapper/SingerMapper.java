package com.liu.song.mapper;

import com.liu.song.entity.Singer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingerMapper {
    Long insertSinger(Singer singer);
}
