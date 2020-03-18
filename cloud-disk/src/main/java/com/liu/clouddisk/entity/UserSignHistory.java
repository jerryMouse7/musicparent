package com.liu.clouddisk.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author liu
 */
@Data
public class UserSignHistory {
    private Long id;

    private Long userId;

    private LocalDate signMonth;

    private Integer signRecord;
}
