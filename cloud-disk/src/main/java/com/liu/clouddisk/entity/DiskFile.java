package com.liu.clouddisk.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (DiskFile)实体类
 *
 * @author makejava
 * @since 2020-02-24 09:53:05
 */
@Data
public class DiskFile implements Serializable {
    private static final long serialVersionUID = 336773220461640107L;

    private String fileUuid;
    private Long id;
    
    private String fileName;
    
    private String filePath;
    
    private Integer fileSize;
    
    private Integer fileType;
    
    private String fileMd5;
    
    private Date uploadTime;
    
    private Long uploaderId;
    
    private Integer state;
    
    private Long folderId;
    
    private String fileSuffix;


}