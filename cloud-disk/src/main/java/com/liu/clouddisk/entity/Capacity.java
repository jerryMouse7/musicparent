package com.liu.clouddisk.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Capacity {

    private Long id;

    private Long userId;

    private Long totalCapacity;

    private Long usedCapacity;

    private Long tempCapacity;

    private LocalDateTime expireDate;
}
