package com.liu.clouddisk.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDateTime1 = localDateTime.plusDays(1).toLocalDate();
        LocalDate localDateTime2 = localDateTime.plusHours(1).toLocalDate();
        //localDateTime1 - localDateTime2
        Period period = Period.between(localDateTime2,localDateTime1);
        System.out.println(period.getDays());



        //localDatetime计算时间差
//        Duration duration = Duration.between(localDateTime1,localDateTime2);
//        System.out.println(duration.toDays());
    }
}
