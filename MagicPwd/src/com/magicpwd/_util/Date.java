/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

import java.util.Calendar;

/**
 *
 * @author Amon
 */
public class Date
{

    public static Calendar stringToDate(String datetime, char datesp, char timesp, char dtsp) throws Exception
    {
        // 若指定日期时间字符串为空，则直接返回当前时间
        if (!Char.isValidate(datetime))
        {
            return Calendar.getInstance();
        }

        // 日期时间字符串分隔
        int dtspIndx = datetime.indexOf(dtsp);
        String date = datetime;
        String time = "";
        if (dtspIndx >= 0 && dtspIndx < datetime.length())
        {
            date = datetime.substring(0, dtspIndx);
            time = datetime.substring(dtspIndx + 1);
        }

        // 日期对象
        Calendar cal = Calendar.getInstance();

        // 日期信息解析
        if (Char.isValidate(date))
        {
            // 读取日期分隔符在日期字符串中位置信息
            int f = date.indexOf(datesp);
            int e = date.lastIndexOf(datesp);

            String y = null;// 年份
            String m = null;// 月份
            String d = null;// 日期
            // 没有日期分隔符号的情况下，日期字符串均按年份处理
            if (f < 0)
            {
                y = date;
            }
            // 存在日期分隔符号的情况下的处理
            else if (f >= 0)
            {
                // 只存在一个日期分隔符号的情况下，日期字符串按年月格式处理
                if (f == e)
                {
                    y = date.substring(0, f);
                    m = date.substring(e + 1);
                }
                // 存在两个日期分隔符号的情况下，日期字符串按年月日格式处理
                else
                {
                    y = date.substring(0, f);
                    m = date.substring(f + 1, e);
                    d = date.substring(e + 1);
                }
            }

            // 年份信息读取
            if (Char.isValidate(y))
            {
                cal.set(Calendar.YEAR, Integer.parseInt(y));
            }

            // 月份信息读取
            if (Char.isValidate(m))
            {
                cal.set(Calendar.MONTH, Integer.parseInt(m) - 1);
            }

            // 日期信息读取
            if (Char.isValidate(d))
            {
                cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d));
            }
        }

        // 时间信息解析
        if (Char.isValidate(time))
        {
            // 读取日期分隔符在日期字符串中位置信息
            int f = time.indexOf(timesp);
            int e = time.lastIndexOf(timesp);

            String h = null;// 小时
            String m = null;// 分钟
            String s = null;// 秒钟
            // 没有时间分隔符号的情况下，时间字符串均按小时处理
            if (f < 0)
            {
                h = time;
            }
            // 存在日期分隔符号的情况下的处理
            else if (f >= 0)
            {
                // 只存在一个日期分隔符号的情况下，日期字符串按年月格式处理
                if (f == e)
                {
                    h = time.substring(0, f);
                    m = time.substring(e + 1);
                }
                // 存在两个日期分隔符号的情况下，日期字符串按年月日格式处理
                else
                {
                    h = time.substring(0, f);
                    m = time.substring(f + 1, e);
                    s = time.substring(e + 1);
                }
            }

            // 年份信息读取
            if (Char.isValidate(h))
            {
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h));
            }

            // 月份信息读取
            if (Char.isValidate(m))
            {
                cal.set(Calendar.MINUTE, Integer.parseInt(m));
            }

            // 日期信息读取
            if (Char.isValidate(s))
            {
                cal.set(Calendar.SECOND, Integer.parseInt(s));
            }
        }

        return cal;
    }

    /**
     * 获取UTC时间
     * @return
     */
    public static Calendar utcDate()
    {
        // 万年历对象
        java.util.Calendar cal = java.util.Calendar.getInstance();
        // 当前时区偏差
        int offset = cal.get(java.util.Calendar.DST_OFFSET) + cal.get(java.util.Calendar.ZONE_OFFSET);
        // 校正时区偏差到GMT时区
        cal.add(java.util.Calendar.MILLISECOND, -offset);
        return cal;
    }

    public static String curTime()
    {
        return Char.lPad(Long.toHexString(System.currentTimeMillis()), 16, '0');
    }
}
