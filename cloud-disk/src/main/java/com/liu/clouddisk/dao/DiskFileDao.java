package com.liu.clouddisk.dao;

import com.liu.clouddisk.entity.DiskFile;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (DiskFile)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-24 09:53:05
 */
public interface DiskFileDao {

    DiskFile selectByFileMd5(@Param("fileMd5") String fileMD5);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DiskFile queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DiskFile> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param diskFile 实例对象
     * @return 对象列表
     */
    List<DiskFile> queryAll(DiskFile diskFile);

    /**
     * 新增数据
     *
     * @param diskFile 实例对象
     * @return 影响行数
     */
    int insert(DiskFile diskFile);

    /**
     * 修改数据
     *
     * @param diskFile 实例对象
     * @return 影响行数
     */
    int update(DiskFile diskFile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}