package com.liu.clouddisk.service.impl;

import com.liu.clouddisk.entity.DiskShare;
import com.liu.clouddisk.dao.DiskShareDao;
import com.liu.clouddisk.service.DiskShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DiskShare)表服务实现类
 *
 * @author makejava
 * @since 2020-02-22 23:08:00
 */
@Service("diskShareService")
public class DiskShareServiceImpl implements DiskShareService {
    @Resource
    private DiskShareDao diskShareDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DiskShare queryById(Long id) {
        return this.diskShareDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DiskShare> queryAllByLimit(int offset, int limit) {
        return this.diskShareDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param diskShare 实例对象
     * @return 实例对象
     */
    @Override
    public DiskShare insert(DiskShare diskShare) {
        this.diskShareDao.insert(diskShare);
        return diskShare;
    }

    /**
     * 修改数据
     *
     * @param diskShare 实例对象
     * @return 实例对象
     */
    @Override
    public DiskShare update(DiskShare diskShare) {
        this.diskShareDao.update(diskShare);
        return this.queryById(diskShare.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.diskShareDao.deleteById(id) > 0;
    }
}