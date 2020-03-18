package com.liu.clouddisk.service;

import com.liu.clouddisk.entity.DiskShare;
import java.util.List;

/**
 * (DiskShare)表服务接口
 *
 * @author makejava
 * @since 2020-02-22 23:07:56
 */
public interface DiskShareService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DiskShare queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DiskShare> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param diskShare 实例对象
     * @return 实例对象
     */
    DiskShare insert(DiskShare diskShare);

    /**
     * 修改数据
     *
     * @param diskShare 实例对象
     * @return 实例对象
     */
    DiskShare update(DiskShare diskShare);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}