package com.liu.song.controller;

import com.liu.song.service.AlbumService;
import com.liu.song.utils.FileTypeUtils;
import com.liu.utils.api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public ApiResponse<String> insertAblum(@RequestParam("singerId") Long singerId,
                                           @RequestParam("name")String name,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("createTime")LocalDateTime createTime,
                                           @RequestParam("backgorundPicture")MultipartFile image,
                                           @RequestParam("price")BigDecimal price,
                                           @RequestParam("introduction")String introduction){

        albumService.insertAlbum(singerId,name,createTime,image.getOriginalFilename(),price,introduction);

        return new ApiResponse<>("chenggong");
    }
}
