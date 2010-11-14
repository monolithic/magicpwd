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
            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(tmp);
            if (!matcher.find())
            {
                return;
            }
            int time = Integer.parseInt(matcher.group());

            java.util.Calendar cal = java.util.Calendar.getInstance();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
            if (tmp.endsWith("second"))
            {
                cal.add(java.util.Calendar.SECOND, time);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if (tmp.endsWith("minute"))
            {
                cal.add(java.util.Calendar.MINUTE, time);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if (tmp.endsWith("hour"))
            {
                cal.add(java.util.Calendar.HOUR_OF_DAY, time);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if (tmp.endsWith("day"))
            {
                cal.add(java.util.Calendar.DAY_OF_MONTH, time);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if (tmp.endsWith("week"))
            {
                cal.add(java.util.Calendar.WEEK_OF_MONTH, time);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if (tmp.endsWith("month"))
            {
                cal.add(java.util.Calendar.MONTH, time);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
            if (tmp.endsWith("year"))
            {
                cal.add(java.util.Calendar.YEAR, time);
                component.setText(sdf.format(cal.getTime()));
                return;
            }
        }
        component.setText(cmd);
    }
}
