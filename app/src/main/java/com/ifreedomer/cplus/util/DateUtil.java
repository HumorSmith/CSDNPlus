package com.ifreedomer.cplus.util;

import android.content.Context;
import android.text.format.DateFormat;

import com.ifreedomer.cplus.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:eavawu
 * @since: 25/10/2017.
 * TODO:
 */

public class DateUtil {
    private static final String TAG = DateUtil.class.getSimpleName();

    public static String timeStamp2DateString(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date.setTime(time);
        simpleDateFormat.format(date);
        return simpleDateFormat.format(date);
    }


    public static String timeStamp2DateStringWithMonth(Context context, long time) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        simpleDateFormat.format(date);


        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        String day = (String) DateFormat.format("dd", date); // 20
        String monthString = (String) DateFormat.format("MMM", date); // Jun
        String monthNumber = (String) DateFormat.format("MM", date); // 06
        String year = (String) DateFormat.format("yyyy", date); // 2013
        if (Integer.parseInt(year) != Calendar.getInstance().get(Calendar.YEAR)) {
            year = (Integer.parseInt(year) % 100) + context.getString(R.string.year);
        } else {
            year = "";
        }
        LogUtil.d(TAG, "timestamp =" + time);
        return year + monthNumber + context.getString(R.string.month) + day + context.getString(R.string.day);
    }


    public static String timeStamp2DayString(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d天H时mm分ss秒");
        Date date = new Date();
        date.setTime(time);
        simpleDateFormat.format(date);
        return simpleDateFormat.format(date);
    }

    public static long string2Timestamp(Context context, String timeStr) {
        String dateStr = "2010-05-04 12:34:23";

        //注意format的格式要与日期String的格式相匹配
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date.getTime();
        return time;
    }

    public static String convertToMonth(Context context, String timeStr) {
        Timestamp ts = Timestamp.valueOf(timeStr);
        return timeStamp2DateStringWithMonth(context,ts.getTime());
    }
}
