package com.liu.song.service.impl;

import com.liu.song.entity.Singer;
import com.liu.song.mapper.SingerMapper;
import com.liu.song.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public Long insertSinger(String name, int sex, String imageName, String profileName, String location, String selfIntroduction, Long userId) {

        Singer  singer = new Singer();
        singer.setName(name);
        singer.setSelfIntroduction(selfIntroduction);
        singer.setSex(sex);
        singer.setBackgroundPicture(imageName);
        singer.setLocation(location);
        singer.setProfilePicture(profileName);
        singer.setUserId(userId);
        return singerMapper.insertSinger(singer);
    }
}
