package com.liu.song.controller;

import com.liu.song.entity.Song;
import com.liu.song.remote.PassportRemoteClient;
import com.liu.song.service.SongService;
import com.liu.song.utils.FileTypeUtils;
import com.liu.utils.api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private PassportRemoteClient passportRemoteClient;

    @GetMapping(value = "")
    public ApiResponse<String> getSongPath(@RequestParam("songId") long songId){
        String songName = songService.getSongNameById(songId);
        return new ApiResponse<>(songName);
    }

    @PostMapping(value = "")
    public ApiResponse<String> insertSong(@RequestParam("singerId")long singerId,
                                          @RequestParam("image") MultipartFile image,
                                          @RequestParam("song") MultipartFile song,
                                          @RequestParam(value = "lyric",required = false)String lyric,
                                          Principal principal) throws IOException {
        Map<String, Object> userInfo = passportRemoteClient.userInfo(principal.getName());
        //文件名
        String imageName = image.getOriginalFilename();
        InputStream imageIn = image.getInputStream();
        String imageType = FileTypeUtils.getFileType(imageIn);

        String songName = song.getOriginalFilename();
        InputStream songIn = song.getInputStream();
        String songType = FileTypeUtils.getFileType(songIn);
//        if(!imageType.equals("jpg") || !songType.equals("mp3")){
//            return new ApiResponse<>("文件格式出错");
//        }
        Long songId = songService.insertSong(singerId,songName,imageName,lyric);
        if(songId == null){
            return new ApiResponse<>("重名了");
        }

        String imageFileName = "E:\\temp\\"+songName+".jpg";
        String songFileName = "E:\\temp\\"+songName+".mp3";
        byte[] imageBytes = image.getBytes();
        byte[] songBytes = song.getBytes();
        File imageFileToSave = new File(imageFileName);
        File songFileToSave = new File(songFileName);
        FileCopyUtils.copy(imageBytes,imageFileToSave);
        FileCopyUtils.copy(songBytes,songFileToSave);



        return new ApiResponse<>("发布成功");
    }

    public ApiResponse<List<Song>> getSongsByAlbumId(@RequestParam("albumId") Long albumId){


        return null;
    }
}
