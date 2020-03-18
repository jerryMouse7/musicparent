package com.liu.passport.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserLoginHistory {
    private long id;

    private long userId;

    private String userAgent;

    private String remoteAddr;

    private String cookies;

    private LocalDateTime loginTime;

    private int state;

    private long createdBy;

    private LocalDateTime createdDate;

    private long updatedBy;

    private LocalDateTime updatedDate;


}
