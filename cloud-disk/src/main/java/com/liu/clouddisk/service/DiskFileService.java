package com.liu.clouddisk.service;

import com.liu.clouddisk.entity.DiskFile;
import com.liu.clouddisk.vo.FileForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * (DiskFile)表服务接口
 *
 * @author makejava
 * @since 2020-02-24 09:53:05
 */
public interface DiskFileService {
    Map<String, Object> realUpload(FileForm fileForm, MultipartFile multipartFile)throws IOException;

    Map<String, Object> findByFileMd5(String md5);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DiskFile queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DiskFile> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param diskFile 实例对象
     * @return 实例对象
     */
    DiskFile insert(DiskFile diskFile);

    /**
     * 修改数据
     *
     * @param diskFile 实例对象
     * @return 实例对象
     */
    DiskFile update(DiskFile diskFile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}