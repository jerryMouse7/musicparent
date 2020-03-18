package com.liu.song.service.impl;

import com.liu.song.entity.Album;
import com.liu.song.mapper.AlbumMapper;
import com.liu.song.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AlbumSerivceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;
    @Override
    public Long insertAlbum(Long singerId, String name, LocalDateTime createTime, String image, BigDecimal price, String introduction) {
        Album album = new Album();
        album.setSingerId(singerId);
        album.setName(name);
        album.setCreatTime(createTime);
        album.setBackgroundPicture(image);
        album.setPrice(price);
        album.setIntroduction(introduction);
        album.setState(1);
        return albumMapper.insertAlbum(album);
    }
}
