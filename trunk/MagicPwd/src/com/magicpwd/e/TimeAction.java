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

import com.magicpwd._cons.ConsEnv;

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
        if (tmp.startsWith("time:"))
        {
            tmp = tmp.substring(5);

            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(tmp);
            int step = Integer.parseInt(matcher.find() ? matcher.group() : "1");

            tmp = tmp.replaceAll("^\\s*\\d+", "").trim();

            java.util.Calendar cal = java.util.Calendar.getInstance();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
            if ("second".equals(tmp))
            {
                cal.add(java.util.Calendar.SECOND, step);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if ("minute".equals(tmp))
            {
                cal.add(java.util.Calendar.MINUTE, step);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if ("hour".equals(tmp))
            {
                cal.add(java.util.Calendar.HOUR_OF_DAY, step);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if ("day".equals(tmp))
            {
                cal.add(java.util.Calendar.DAY_OF_MONTH, step);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if ("week".equals(tmp))
            {
                cal.add(java.util.Calendar.WEEK_OF_MONTH, step);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if ("month".equals(tmp))
            {
                cal.add(java.util.Calendar.MONTH, step);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if ("year".equals(tmp))
            {
                cal.add(java.util.Calendar.YEAR, step);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
        }
        component.setText(cmd);
    }
}
