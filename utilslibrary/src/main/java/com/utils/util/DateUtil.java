package com.utils.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * 时间工具类
 *
 * @author
 */
public class DateUtil {

    public final static String DATE_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_OMIT_TIME_FORMAT = "yyyy-MM-dd";

    private static SimpleDateFormat dateFormater;

    public static final String differentMonth(String date, int months) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            return null;
        }
        cal.add(Calendar.MONTH, months);
        return simpleDateFormat.format(cal.getTime());
    }

    public static final String different(String date, int days) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            return null;
        }
        cal.add(Calendar.DAY_OF_MONTH, days);
        return simpleDateFormat.format(cal.getTime());
    }

    public static final Date different(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }


    public static int intervalDay(String startTime,String endTime){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        int day=0;
        try {
            Date startDate=simpleDateFormat.parse(startTime);
            Date endDate=simpleDateFormat.parse(endTime);
            long startMillis=startDate.getTime();
            long endMillis=endDate.getTime();
            long sub=endMillis-startMillis;
            day=(int)(sub/(1000*60*60*24));
        } catch (ParseException e) {
            return day;
        }
        return day;
    }

    public static boolean isBeforeTime(String time){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(DATE_OMIT_TIME_FORMAT);
        Date lastDate=null;
        try {
            lastDate = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            return false;
        }
        return  lastDate.before(new Date());
    }

    public static boolean afterTime(String startTime,String endTime){
        if (startTime.equals(endTime)){
            return true;
        }

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date startDate=simpleDateFormat.parse(startTime);
                Date endDate=simpleDateFormat.parse(endTime);
                if (endDate.after(startDate)){
                    return true;
                }

            } catch (ParseException e) {
               return false;
            }
        return false;
    }

    public static String getLongDate(String timeNum){
        return  getLongDate(timeNum,null);
    }

    public static String getLongDate(String timeNum,String format){
        String time=null;
        if (timeNum.length()==10){
            time=timeNum+"000";
        }
        else {
            time=timeNum;
        }

        if (format==null){
            format=DATE_DEFAULT_FORMAT;
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        try {
            Date date=new Date(Long.parseLong(time));
            time = simpleDateFormat.format(date);
        } catch (NumberFormatException e) {
            LogUtil.e("NumberFormatException");
            time = simpleDateFormat.format(new Date());
//        } catch (ParseException e) {
//            LogUtil.e("ParseException:"+e.getMessage());
//            time = simpleDateFormat.format(new Date());
    }
        return time;
    }

    public static String getSysdate(String format) {

        if (format == null || format.equals("")) {
            format = DATE_DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        return sdf.format(new Date());
    }

    public static String getSysdate() {
        return getSysdate(DATE_DEFAULT_FORMAT);
    }

    public static String getFormatDateString(String format, Date date) {

        if (format == null || format.equals("")) {
            format = DATE_DEFAULT_FORMAT;
        }
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08"));

        return sdf.format(date);
    }

    public static String getFormatDateString(String format) {
        return getFormatDateString(format, null);
    }

    public static String getFormatDateString(Date date) {
        return getFormatDateString(null, date);
    }

    public static Date parseString(String format, String strDate) {

        if (strDate == null || strDate.equals("")) {
            return new Date();
        }

        if (format == null || format.equals("")) {
            format = DATE_DEFAULT_FORMAT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08"));

        Date date = new Date();
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date parseString(String strDate) {
        return parseString(null, strDate);
    }

    public static Date getDateBefore(Date date, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    public static Date getDateBefore(Date date, int field, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(field, now.get(field) - value);
        return now.getTime();
    }

    public static Date getDateAfter(Date date, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static Date getDateAfter(Date date, int field, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(field, value);
        return now.getTime();
    }

    public static String getFriendlyTime(String sdate) {
        if (dateFormater == null) {
            dateFormater = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        }

        Date time = parseString(sdate);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        long timeDifference = cal.getTimeInMillis() - time.getTime();
        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((timeDifference) / 3600000);
            if (hour == 0) {
                // if (timeDifference / 1000 < 30) {
                // ftime = "30秒前";
                // } else {
                ftime = Math.max((timeDifference) / 60000, 1) + "分钟前";
                // }
            } else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 31) {
            ftime = days + "天前";
        } else if (days > 31) {
            ftime = dateFormater.format(time);
        }
        return ftime;
    }

    public static long getHowMonthsLaterDays(Date date, int months) {
        return getLaterDays(date, Calendar.MONTH, months);
    }

    public static long getHowYearLaterDays(Date date, int months) {
        return getLaterDays(date, Calendar.YEAR, months);
    }

    /**
     * @param date
     * @param field Calendar.MONTH...
     * @param value
     * @return
     */
    public static long getLaterDays(Date date, int field, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(field, value);
        return now.getTimeInMillis() / 86400000 - date.getTime() / 86400000;
    }

    /**
     * @param date       当前时间
     * @param selectDate 选择的时间
     * @return
     */
    public static boolean isDateOut(Date date, Date selectDate) {

        return (selectDate.getTime() - date.getTime()) / 86400000 < 0 ? true
                : false;
    }

    /**
     * @param date       当前时间
     * @param selectDate 选择的时间
     * @return
     */
    public static boolean isDateHourOut(Date date, Date selectDate) {
        return (selectDate.getTime() - date.getTime()) / 60000 < 0 ? true
                : false;
    }

    public static int getCurrentMonthStart(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat format = new SimpleDateFormat("E");
        LogUtil.e("当前时间:", format.format(calendar.getTime()) + "====" + calendar.get(Calendar.DAY_OF_WEEK));
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1)
            day = 7;
        else
            day = day - 1;
        return day;
    }

    public static  String getTime(long time){

        SimpleDateFormat format  = new SimpleDateFormat("yyyy年MM月");
        String str= (String) format.format(time);
        return str;
    }

    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static int getDayOfMonth(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 当前时间转换
     *
     * @return
     */
    public static String getCurrentTime()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
                .getInstance().getTime());
    }

    /**
     * 将日期转换成指定格式
     * @param date 日期
     * @param pattern 转换格式 eg：'yyyy-MM-dd HH:mm:ss' 2014-12-22 10:16:13
     * @return
     */
    public static final String formatDate(Date date, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 在字符串中插入时间格式
     * @param str 时间字符串 eg:20141222101613->2014-12-22 10:16:13
     * @return
     */
    public static String converDateString(String str)
    {
        if (StringUtil.isEmptyOrNull(str))
        {
            return "时间格式错误";
        }

        StringBuffer sb = new StringBuffer(str.trim());
        if (str.length() > 3)
        {
            sb.insert(4, '-');
        }
        if (str.length() > 6)
        {
            sb.insert(7, '-');
        }
        if (str.length() > 8)
        {
            sb.insert(10, ' ');
        }
        if (str.length() > 10)
        {
            sb.insert(13, ':');
        }
        if (str.length() > 12)
        {
            sb.insert(16, ':');
        }
        return sb.toString();
    }
}
