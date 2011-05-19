/*
 *  Copyright (C) 2011 Aven
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
package com.magicpwd.v.tray;

import com.magicpwd.__i.ITrayView;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Logs;
import java.awt.event.MouseEvent;

/**
 * Application: MagicPwd
 * Author     : Aven
 * Encoding   : UTF-8
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class TrayIco extends java.awt.TrayIcon implements ITrayView, java.awt.event.MouseListener
{

    TrayIco()
    {
        super(null);
    }

    public boolean initView()
    {
        if (!java.awt.SystemTray.isSupported())
        {
            return false;
        }
        setImageAutoSize(true);
        return true;
    }

    public void initLang()
    {
        setToolTip(ConsEnv.SOFTNAME + ' ' + ConsEnv.VERSIONS);
    }

    public void initData()
    {
        addMouseListener(this);
    }

    @Override
    public void displayMessage(String title, String message)
    {
        super.displayMessage(title, message, java.awt.TrayIcon.MessageType.INFO);
    }

    @Override
    public void setVisible(boolean visible)
    {
        try
        {
            if (visible)
            {
                java.awt.SystemTray.getSystemTray().add(this);
            }
            else
            {
                java.awt.SystemTray.getSystemTray().remove(this);
            }
        }
        catch (java.awt.AWTException ex)
        {
            Logs.exception(ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
}
