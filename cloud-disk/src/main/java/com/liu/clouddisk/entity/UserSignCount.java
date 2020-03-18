package com.liu.clouddisk.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author liu
 */
@Data
public class UserSignCount {
    private Long id;

    private Long userId;

    private LocalDate lastSign;

    private Integer signCount;

    public void addSignCount(){
        signCount++;
    }
}
