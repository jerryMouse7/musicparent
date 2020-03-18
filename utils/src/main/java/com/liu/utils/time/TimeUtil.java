package com.liu.utils.time;


import java.time.LocalDate;
import java.time.Period;

public class TimeUtil {

    public static int getTimeDifference(LocalDate date1, LocalDate date2){

        //date2 - date1
        Period period = Period.between(date1,date2);
        return period.getDays();
    }
}
