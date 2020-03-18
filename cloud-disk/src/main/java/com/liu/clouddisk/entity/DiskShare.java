package com.liu.clouddisk.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (DiskShare)实体类
 *
 * @author makejava
 * @since 2020-02-22 23:07:47
 */
public class DiskShare implements Serializable {
    private static final long serialVersionUID = 525019864904963357L;
    
    private Long id;
    
    private String shareId;
    
    private String theme;
    
    private Integer lockWhether;
    
    private String sharePassword;
    
    private Integer visitCount;
    
    private Integer saveCount;
    
    private Integer downloadCount;
    
    private Date expiration;
    
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getLockWhether() {
        return lockWhether;
    }

    public void setLockWhether(Integer lockWhether) {
        this.lockWhether = lockWhether;
    }

    public String getSharePassword() {
        return sharePassword;
    }

    public void setSharePassword(String sharePassword) {
        this.sharePassword = sharePassword;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(Integer saveCount) {
        this.saveCount = saveCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}