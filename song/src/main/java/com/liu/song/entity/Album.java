package com.liu.song.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Album {
    private long id;

    private long singerId;

    private String name;

    private LocalDateTime creatTime;

    //专辑的背景图片
    private String backgroundPicture;

    private BigDecimal price;

    //专辑的介绍
    private String introduction;

    private int state;




}
