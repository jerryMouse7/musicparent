package com.liu.song.controller;

import com.liu.song.remote.PassportRemoteClient;
import com.liu.song.service.SingerService;
import com.liu.song.utils.FileTypeUtils;
import com.liu.utils.api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @Autowired
    private PassportRemoteClient passportRemoteClient;

    public ApiResponse<String> insertSinger(@RequestParam("name") String name,
                                            @RequestParam("image") MultipartFile image,
                                            @RequestParam("profile") MultipartFile profile,
                                            @RequestParam("sex") int sex,
                                            @RequestParam("location") String location,
                                            @RequestParam("selfIntroduction") String selfIntroduction,
                                            Principal principal) throws IOException {
        Map<String, Object> userInfo = passportRemoteClient.userInfo(principal.getName());
        String imageName = image.getOriginalFilename();
        InputStream imageIn = image.getInputStream();
        String imageType = FileTypeUtils.getFileType(imageIn);

        String profileName = profile.getOriginalFilename();
        if(!imageType.equals("mp3")){
            return new ApiResponse<>("文件格式出错");
        }

        Long id = singerService.insertSinger(name,sex,imageName,profileName,location,selfIntroduction, (Long) userInfo.get("id"));

        return new ApiResponse<>("成功");
    }
}


