package com.liu.song.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AlbumService {

    Long insertAlbum(Long singerId, String name, LocalDateTime createTime, String image, BigDecimal price,String inttroduction);
}
