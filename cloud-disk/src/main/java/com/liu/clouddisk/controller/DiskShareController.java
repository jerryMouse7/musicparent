package com.liu.clouddisk.controller;

import com.liu.clouddisk.entity.DiskShare;
import com.liu.clouddisk.service.DiskShareService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DiskShare)表控制层
 *
 * @author makejava
 * @since 2020-02-22 23:08:00
 */
@RestController
@RequestMapping("/api/diskShare")
public class DiskShareController {
    /**
     * 服务对象
     */
    @Resource
    private DiskShareService diskShareService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public DiskShare selectOne(Long id) {
        return this.diskShareService.queryById(id);
    }

}