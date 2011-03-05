/*
 *  Copyright (C) 2011 Amon
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
package com.magicpwd._comp.date;

import com.magicpwd.__i.IBackCall;
import com.magicpwd._comp.WPanel;
import java.util.HashMap;

public class WDateChooser
{

    private javax.swing.text.JTextComponent txtCmp;
    private java.text.DateFormat format = java.text.DateFormat.getDateInstance();
    private java.util.HashMap<Integer, java.util.HashMap<Integer, java.util.HashMap<Integer, Integer>>> specialDays;

    public WDateChooser()
    {
    }

    public void setDateFormat(java.text.DateFormat format)
    {
        this.format = format;
    }

    public void setDateVisible(boolean visible)
    {
    }

    public void setTimeVisible(boolean visible)
    {
    }

    public void show(final javax.swing.text.JTextComponent cmp, int x, int y)
    {
        showMenu(new IBackCall<java.util.Calendar>()
        {

            @Override
            public boolean callBack(String options, java.util.Calendar object)
            {
                cmp.setText(getDate(object));
                return true;
            }
        });
        pm_DateMenu.show(cmp, x, y);
    }

    public void show(final javax.swing.AbstractButton btn, int x, int y)
    {
        showMenu(new IBackCall<java.util.Calendar>()
        {

            @Override
            public boolean callBack(String options, java.util.Calendar object)
            {
                btn.setText(getDate(object));
                return true;
            }
        });
        pm_DateMenu.show(btn, x, y);
    }

    public void show(final javax.swing.JLabel lbl, int x, int y)
    {
        showMenu(new IBackCall<java.util.Calendar>()
        {

            @Override
            public boolean callBack(String options, java.util.Calendar object)
            {
                lbl.setText(getDate(object));
                return true;
            }
        });
        pm_DateMenu.show(lbl, x, y);
    }

    public void show(int x, int y, IBackCall<java.util.Calendar> backCall)
    {
        showMenu(backCall);
        pm_DateMenu.show(null, x, y);
    }

    private void showMenu(IBackCall<java.util.Calendar> backCall)
    {
        if (dp_DatePanel == null)
        {
            dp_DatePanel = new DatePanel();
            dp_DatePanel.initView();
            WPanel panel = new WPanel();
            panel.setLayout(new java.awt.BorderLayout());
            panel.add(dp_DatePanel);
            pm_DateMenu.add(panel);
        }
        dp_DatePanel.initLang();
        dp_DatePanel.showDate();
        dp_DatePanel.setBackCall(backCall);
    }

    private String getDate(java.util.Calendar calendar)
    {
        if (calendar == null)
        {
            return "";
        }
        if (format == null)
        {
            format = java.text.DateFormat.getDateInstance();
        }
        return format.format(calendar.getTime());
    }

    public void appendDisabledDay(int day)
    {
        appendDisabledDay(0, day);
    }

    public void appendDisabledDay(int month, int day)
    {
        appendDisabledDay(0, 0, day);
    }

    public void appendDisabledDay(int year, int month, int day)
    {
        if (day < 1 || day > 31)
        {
            return;
        }
        if (specialDays == null)
        {
            specialDays = new java.util.HashMap<Integer, java.util.HashMap<Integer, java.util.HashMap<Integer, Integer>>>();
        }
        specialDays.put(year, null);
    }
    private javax.swing.JPopupMenu pm_DateMenu = new javax.swing.JPopupMenu();
    private DatePanel dp_DatePanel;
}
