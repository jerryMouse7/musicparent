package com.liu.song.entity;

import lombok.Data;

@Data
public class Singer {

    private long id;

    private String name;

    private int sex;

    //歌手的背景图片路径
    private String backgroundPicture;

    //歌手的头像
    private String profilePicture;

    //地区 华语 日韩等
    private String location;

    //自我介绍
    private String selfIntroduction;

    //关联的用户id
    private long userId;


}
