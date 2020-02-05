package com.gmail.zlotnikova.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatUtil {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String formatLongToDateTime(Long time) {
        Date date = new Date(time);
        DateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        return df.format(date);
    }

}