package com.liu.clouddisk.service.impl;

import com.liu.clouddisk.entity.DiskFile;
import com.liu.clouddisk.dao.DiskFileDao;
import com.liu.clouddisk.service.DiskFileService;
import com.liu.clouddisk.utils.Constant;
import com.liu.clouddisk.utils.FileMd5Util;
import com.liu.clouddisk.utils.KeyUtil;
import com.liu.clouddisk.utils.NameUtil;
import com.liu.clouddisk.vo.FileForm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (DiskFile)表服务实现类
 *
 * @author makejava
 * @since 2020-02-24 09:53:05
 */
@Service("diskFileService")
public class DiskFileServiceImpl implements DiskFileService {
    @Resource
    private DiskFileDao diskFileDao;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    public Map<String, Object> realUpload(FileForm fileForm, MultipartFile multipartFile) throws IOException {
        String action = fileForm.getAction();
        String fileUuid = fileForm.getUuid();
        int index = Integer.parseInt(fileForm.getIndex());
        String partMd5 = fileForm.getPartMd5();
        String md5 = fileForm.getMd5();
        int total = Integer.parseInt(fileForm.getTotal());
        String fileName = fileForm.getName();
        int size = Integer.parseInt(fileForm.getSize());
        String suffix = NameUtil.getExtensionName(fileName);

        String saveDirectory = Constant.PATH + File.separator + fileUuid;
        String filePath = saveDirectory + File.separator + fileUuid + "." + suffix;
        File path = new File(saveDirectory);
        if (!path.exists()) {
            //新建目录
            path.mkdirs();
        }
        //文件位置分片
        File file = new File(saveDirectory, fileUuid + "_" + index);

        //根据action的不同执行不同的操作，check：校验分片是否上传过
        Map<String, Object> map = new HashMap<>();
        if("check".equalsIgnoreCase(action)){
            String md5String = FileMd5Util.getFileMD5(file);
            if(md5String != null && md5String.length() == 31){
                System.out.println("check length =" + partMd5.length() + " md5Str length" + md5String.length() + "   " + partMd5 + " " + md5String);
                md5String = "0" + md5String;
            }
            if(md5String != null && md5String.equalsIgnoreCase(partMd5)) {
                //分片已经上传过
                map.put("flag", 1);
                map.put("fileId", fileUuid);
                if (index != total) {
                    return map;
                }
            }else{
                    map.put("flag", "0");
                    map.put("fileId", fileUuid);
                    return map;
                }
            }else if("upload".equalsIgnoreCase(action)){
                //上传过程中出错，删除分块，重新上传
                if(file.exists()){
                    file.delete();
                }
                multipartFile.transferTo(new File(saveDirectory,fileUuid+"_"+index));
                map.put("flag", "1");
                map.put("fileId", fileUuid);
                if(index != total){
                    return map;
                }

            }

        if(path.isDirectory()){
            File[] fileArray = path.listFiles();
            if (fileArray != null) {
                if (fileArray.length == total) {
                    //分块全部上传完毕,合并

                    File newFile = new File(saveDirectory, fileUuid + "." + suffix);
                    FileOutputStream outputStream = new FileOutputStream(newFile, true);//文件追加写入
                    byte[] byt = new byte[10 * 1024 * 1024];
                    int len;
                    FileInputStream temp = null;//分片文件
                    for (int i = 0; i < total; i++) {
                        int j = i + 1;
                        temp = new FileInputStream(new File(saveDirectory, fileUuid + "_" + j));
                        while ((len = temp.read(byt)) != -1) {
                            outputStream.write(byt, 0, len);
                        }
                    }
                    //关闭流
                    temp.close();
                    outputStream.close();
                    //修改FileRes记录为上传成功
                    DiskFile uploadFile = new DiskFile();
                    uploadFile.setFileUuid(fileUuid);
                    uploadFile.setState(2);
                    uploadFile.setFileName(fileName);
                    uploadFile.setFileMd5(md5);
                    uploadFile.setFileSuffix(suffix);
                    uploadFile.setFilePath(filePath);
                    uploadFile.setFileSize(size);

                    diskFileDao.insert(uploadFile);

                    map=new HashMap<>();
                    map.put("fileId", fileUuid);
                    map.put("flag", "2");

                    return map;
                } else if(index == 1) {
                    //文件第一个分片上传时记录到数据库
                    DiskFile uploadFile = new DiskFile();
                    uploadFile.setFileMd5(md5);
                    String name = NameUtil.getFileNameNoEx(fileName);
                    if (name.length() > 32) {
                        name = name.substring(0, 32);
                    }
                    uploadFile.setFileName(name);
                    uploadFile.setFileSuffix(suffix);
                    uploadFile.setFileUuid(fileUuid);
                    uploadFile.setFilePath(filePath);
                    uploadFile.setFileSize(size);
                    uploadFile.setState(1);

                    diskFileDao.insert(uploadFile);
                }
            }
        }
        return map;
    }




    @Override
    public Map<String, Object> findByFileMd5(String md5) {
        DiskFile diskFile = diskFileDao.selectByFileMd5(md5);
        Map<String, Object> result = new HashMap<>();
        if (diskFile == null) {
            //没有上传过文件
            result.put("flag", 0);
            result.put("fileId", KeyUtil.genUniqueKey());
            result.put("date", simpleDateFormat.format(new Date()));
        } else {
            //上传过文件，判断文件还在不在
            File file = new File(diskFile.getFilePath());
            if (file.exists()) {
                if (diskFile.getState() == 1) {
                    //文件只上传了一部分
                    result.put("flag", 1);
                    result.put("fileId", diskFile.getId());
                    result.put("data", simpleDateFormat.format(new Date()));
                } else if (diskFile.getState() == 2) {
                    //文件早已上传完成
                    result.put("flag", 2);
                }
            } else {
                //文件不存在
                result.put("flag", 0);
                result.put("fileId", diskFile.getId());
                result.put("date", simpleDateFormat.format(new Date()));
            }
        }
        return result;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DiskFile queryById(Long id) {
        return this.diskFileDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DiskFile> queryAllByLimit(int offset, int limit) {
        return this.diskFileDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param diskFile 实例对象
     * @return 实例对象
     */
    @Override
    public DiskFile insert(DiskFile diskFile) {
        this.diskFileDao.insert(diskFile);
        return diskFile;
    }

    /**
     * 修改数据
     *
     * @param diskFile 实例对象
     * @return 实例对象
     */
    @Override
    public DiskFile update(DiskFile diskFile) {
        this.diskFileDao.update(diskFile);
        return this.queryById(diskFile.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.diskFileDao.deleteById(id) > 0;
    }
}