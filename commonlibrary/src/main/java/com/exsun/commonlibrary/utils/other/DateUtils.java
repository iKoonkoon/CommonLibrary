package com.exsun.commonlibrary.utils.other;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 *
 * @author yuyh.
 * @date 16/4/9.
 */
public class DateUtils
{

    public static final String DEFAULT_DATE = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    public static final DateFormat String_DATE_FORMAT = new SimpleDateFormat("yyMMddHHmmss");
    public static final DateFormat String_DATE_FORMAT = new SimpleDateFormat("yyMMdd");





    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String getTime(long time)
    {
        return format(time, DEFAULT_DATE);
    }

    /**
     * 格式化时间,自定义标签
     *
     * @param time    时间
     * @param pattern 格式化时间用的标签 yyyy-MM-dd hh:MM:ss  HH为24小时
     * @return
     */
    public static String format(long time, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(time));
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurDate()
    {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前日期的前一段时间
     *
     * @return
     */
    public static Date getBeforeDate()
    {
        return getBeforeDate(1);
    }

    public static Date getBeforeDate(int day)
    {
        Calendar calendar = Calendar.getInstance();//得到一个Calendar的实例
        calendar.setTime(new Date()); //设置时间为当前时间
        calendar.add(Calendar.DATE, -day); //年份减1
        Date date = calendar.getTime();//结果
        return date;
    }

    public static String getBeforeDate2String(int day)
    {
        return getBeforeDate2String(day, DEFAULT_DATE_FORMAT);
    }


    public static String getBeforeDate2String(int day, DateFormat dateFormat)
    {
        Calendar calendar = Calendar.getInstance();//得到一个Calendar的实例
        calendar.setTime(new Date()); //设置时间为当前时间
        calendar.add(Calendar.DATE, -day); //年份减1
        Date date = calendar.getTime();//结果


        return date2String(date, dateFormat);
    }




    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurYear()
    {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getCurMonth()
    {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前是几号
     *
     * @return
     */
    public static int getCurDay()
    {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static String date2String(final Date date)
    {
        return date2String(date, FORMAT);
    }

    public static String date2String(final Date date, final DateFormat format)
    {
        return format.format(date);
    }

    public static String dateStringTransform(String dateStr)
    {
        // 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，HH表示小时24小时制，小写的话是12小时制
        // mm，小写，表示分钟，ss表示秒
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
            {
                // 用parse方法，可能会异常，所以要try-catch
                Date date = simpleDateFormat.parse(dateStr);
                // 获取日期实例
                Calendar calendar = Calendar.getInstance();
                // 将日历设置为指定的时间
                calendar.setTime(date);
                // 获取年
                int year = calendar.get(Calendar.YEAR);
                // 这里要注意，月份是从0开始。
                int month = calendar.get(Calendar.MONTH);
                // 获取天
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                return Integer.toBinaryString(month);
            }
        catch (ParseException e)
            {
                e.printStackTrace();
            }
        return "";

    }



    /**
     * date 转 毫秒级字符串
     *
     * @param date The date.
     * @return the milliseconds
     */
    public static long date2Millis(final Date date) {
        return date.getTime();
    }


    /**
     * 时间戳转字符串
     *
     * @param millis
     * @return
     */
    public static String millis2String(final long millis) {
        return millis2String(millis, DEFAULT_DATE_FORMAT);
    }

    public static String millis2String(final long millis, @NonNull final DateFormat format) {
        return format.format(new Date(millis));
    }
}