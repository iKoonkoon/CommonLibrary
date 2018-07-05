package com.exsun.commonlibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author yuyh.
 * @date 16/4/9.
 */
public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String getTime(long time) {
        return format(time, DEFAULT_DATE_FORMAT);
    }

    /**
     * 格式化时间,自定义标签
     *
     * @param time    时间
     * @param pattern 格式化时间用的标签 yyyy-MM-dd hh:MM:ss  HH为24小时
     * @return
     */
    public static String format(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(time));
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurDate() {
        return new Date(System.currentTimeMillis());
    }
    
    public static String getCurDateStr() {
    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(getCurDate());
        return str;
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getCurMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前是几号
     *
     * @return
     */
    public static int getCurDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    
    
    public static String getStageTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
    
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天："+day);
//
//        //过去一月
//        c.setTime(new Date());
//        c.add(Calendar.MONTH, -1);
//        Date m = c.getTime();
//        String mon = format.format(m);
//        System.out.println("过去一个月："+mon);
//
//        //过去三个月
//        c.setTime(new Date());
//        c.add(Calendar.MONTH, -3);
//        Date m3 = c.getTime();
//        String mon3 = format.format(m3);
//        System.out.println("过去三个月："+mon3);
//
//        //过去一年
//        c.setTime(new Date());
//        c.add(Calendar.YEAR, -1);
//        Date y = c.getTime();
//        String year = format.format(y);
//        System.out.println("过去一年："+year);
        
        return day;
    }
    
   
    
}
