package com.liu.song.service;

public interface SingerService {

    Long insertSinger(String name, int sex, String imageName, String profileName, String location, String selfIntroduction, Long userId);
}
