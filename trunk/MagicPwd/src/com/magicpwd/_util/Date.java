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
        return Util.lPad(Long.toHexString(System.currentTimeMillis()), 16, '0');
    }
}
