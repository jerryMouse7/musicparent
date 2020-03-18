package com.liu.song.mapper;

import com.liu.song.entity.Album;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlbumMapper {
    Long insertAlbum(Album album);
}
