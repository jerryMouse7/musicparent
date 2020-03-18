package com.liu.clouddisk.dao;

import com.liu.clouddisk.entity.DiskShare;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (DiskShare)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-22 23:07:47
 */
public interface DiskShareDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DiskShare queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DiskShare> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param diskShare 实例对象
     * @return 对象列表
     */
    List<DiskShare> queryAll(DiskShare diskShare);

    /**
     * 新增数据
     *
     * @param diskShare 实例对象
     * @return 影响行数
     */
    int insert(DiskShare diskShare);

    /**
     * 修改数据
     *
     * @param diskShare 实例对象
     * @return 影响行数
     */
    int update(DiskShare diskShare);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}