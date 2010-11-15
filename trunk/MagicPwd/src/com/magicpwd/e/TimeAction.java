/*
 *  Copyright (C) 2010 Amon
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.e;

import com.magicpwd._util.Logs;

/**
 * Application: MagicPwd
 * Author     : Amon
 * Encoding   : UTF-8
 * Created    : 2010-11-14 20:51:57
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class TimeAction implements java.awt.event.ActionListener
{

    private javax.swing.text.JTextComponent component;
    private java.text.DateFormat dtFormat;

    public TimeAction(javax.swing.text.JTextComponent component)
    {
        this.component = component;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        String cmd = e.getActionCommand();
        if (!com.magicpwd._util.Char.isValidate(cmd))
        {
            return;
        }

        String tmp = cmd.toLowerCase();

        if (tmp.startsWith("fix:"))
        {
            processFix(tmp.substring(4));
            return;
        }

        if (tmp.startsWith("now:"))
        {
            processNow(tmp.substring(4));
            return;
        }

        if (tmp.startsWith("var:"))
        {
            processVar(tmp.substring(4));
            return;
        }

        component.setText(cmd);
    }

    /**
     * 固定日期及日期，格式要求：
     * fix:yyyy-MM-dd HH:mm:ss
     * @param txt
     */
    private void processFix(String txt)
    {
        if (!com.magicpwd._util.Char.isValidateDate(txt, true))
        {
            return;
        }

        try
        {
            java.util.Calendar cal = com.magicpwd._util.Date.stringToDate(txt, '-', ':', ' ');
            component.setText(dtFormat.format(cal.getTime()));
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    /**
     * 当前日期及时间，格式要求：
     * now:date 11:30:00
     * now:2010-11-15 time
     * @param txt
     */
    private void processNow(String txt)
    {
        if (txt.indexOf("date") >= 0)
        {
            component.setText(new java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Calendar.getInstance().getTime()));
            return;
        }

        if (txt.indexOf("time") >= 0)
        {
            component.setText(new java.text.SimpleDateFormat("HH:mm:ss").format(java.util.Calendar.getInstance().getTime()));
            return;
        }

        component.setText(txt);
    }

    /**
     * 可变日期及时间，格式要求：
     * var:1minute
     * var:2hour
     * var:1day
     * ...
     * @param txt
     */
    private void processVar(String txt)
    {
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("[-+]\\d+").matcher(txt);
        if (!matcher.find())
        {
            return;
        }

        int step = Integer.parseInt(matcher.group());

        java.util.Calendar cal = java.util.Calendar.getInstance();
        if (txt.endsWith("second"))
        {
            cal.add(java.util.Calendar.SECOND, step);
            component.setText(dtFormat.format(cal.getTime()));
            return;
        }
        if (txt.endsWith("minute"))
        {
            cal.add(java.util.Calendar.MINUTE, step);
            component.setText(dtFormat.format(cal.getTime()));
            return;
        }
        if (txt.endsWith("hour"))
        {
            cal.add(java.util.Calendar.HOUR_OF_DAY, step);
            component.setText(dtFormat.format(cal.getTime()));
            return;
        }
        if (txt.endsWith("day"))
        {
            cal.add(java.util.Calendar.DAY_OF_MONTH, step);
            component.setText(dtFormat.format(cal.getTime()));
            return;
        }
        if (txt.endsWith("week"))
        {
            cal.add(java.util.Calendar.WEEK_OF_MONTH, step);
            component.setText(dtFormat.format(cal.getTime()));
            return;
        }
        if (txt.endsWith("month"))
        {
            cal.add(java.util.Calendar.MONTH, step);
            component.setText(dtFormat.format(cal.getTime()));
            return;
        }
        if (txt.endsWith("year"))
        {
            cal.add(java.util.Calendar.YEAR, step);
            component.setText(dtFormat.format(cal.getTime()));
            return;
        }
    }

    /**
     * @return the dtFormat
     */
    public java.text.DateFormat getFormat()
    {
        return dtFormat;
    }

    /**
     * @param format the dtFormat to set
     */
    public void setFormat(java.text.DateFormat format)
    {
        this.dtFormat = format;
    }
}
