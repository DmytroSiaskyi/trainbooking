package com.trainbooking.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date addDays(String date, int days) throws ParseException {
        Date oldDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        cal.add(Calendar.DATE, days);
        oldDate = cal.getTime();
        Date newDate = oldDate;
        return newDate;
    }
}
