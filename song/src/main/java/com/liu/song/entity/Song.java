package com.liu.song.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Song {
    private long id;

    private long singerId;

    private long albumId;

    private String name;

    //歌曲的背景图片
    private String backgroundPicture;

    private LocalDateTime createTime;

    //歌词
    private  String lyric;

    //歌曲路径
    private  String path;

    private int state;
}
