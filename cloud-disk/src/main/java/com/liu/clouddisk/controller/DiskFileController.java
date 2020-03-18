package com.liu.clouddisk.controller;

import com.liu.clouddisk.entity.DiskFile;
import com.liu.clouddisk.service.DiskFileService;
import com.liu.clouddisk.vo.FileForm;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * (DiskFile)表控制层
 *
 * @author makejava
 * @since 2020-02-24 09:53:05
 */
@RestController
@RequestMapping("/file")
public class DiskFileController {
    /**
     * 服务对象
     */
    @Resource
    private DiskFileService diskFileService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public DiskFile selectOne(Long id) {
        return this.diskFileService.queryById(id);
    }

    @GetMapping("/open")
    public ModelAndView open() {

        return new ModelAndView("upload");
    }
    @PostMapping("/isUpload")
    @ResponseBody
    public Map<String, Object> isUpload(FileForm fileForm){
        return diskFileService.findByFileMd5(fileForm.getMd5());
    }
    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(@Valid FileForm form,
                                      @RequestParam(value = "data", required = false) MultipartFile multipartFile) {
        Map<String, Object> map = null;

        try {
            map = diskFileService.realUpload(form, multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @PostMapping("/upload/up")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, fileToSave);
        return fileToSave.getAbsolutePath();
    }
}