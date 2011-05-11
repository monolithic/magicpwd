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

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._util.Bean;
import com.magicpwd.m.UserMdl;
import java.awt.geom.RoundRectangle2D;

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
public class TrayWnd extends javax.swing.JWindow implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener
{

    // 中间变量，用于控制绘制过程
    private int wndDim;
    private int wndDdd;
    private int arcDim;
    private int arcDdd;
    /**
     * 窗口最大宽度
     */
    private int maxWidth = 72;
    /**
     * 窗口最小宽度
     */
    private int minWidth = 24;
    /**
     * 圆角最大宽度
     */
    private int arcWidth = 48;
    /**
     * 动画时长
     */
    private int aniTime = 100;
    /**
     * 动画侦数
     */
    private int aniStep = 10;
    private boolean aniDir = true;
    private javax.swing.Timer timer;
    private TrayPtn trayPtn;
    private UserMdl userMdl;

    TrayWnd(TrayPtn trayPtn, UserMdl userMdl)
    {
        this.trayPtn = trayPtn;
        this.userMdl = userMdl;
    }

    public void initView()
    {
        javax.swing.JLabel iconLbl = new javax.swing.JLabel();
        iconLbl.setIcon(new javax.swing.ImageIcon(Bean.getLogo(24)));
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(iconLbl);
        setAlwaysOnTop(true);
    }

    public void initLang()
    {
    }

    public void initData()
    {
        java.awt.Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        String loc = userMdl.getCfg(ConsCfg.CFG_TRAY_LOC, "");
        if (com.magicpwd._util.Char.isValidate(loc))
        {
            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(loc);
            int x = -1;
            if (matcher.find())
            {
                x = Integer.parseInt(matcher.group());
            }
            int y = -1;
            if (matcher.find())
            {
                y = Integer.parseInt(matcher.group());
            }
            if (x >= 0 && x < size.width && y >= 0 && y < size.height)
            {
                formLoc = new java.awt.Point(x, y);
            }
        }

        if (formLoc == null)
        {
            formLoc = new java.awt.Point(size.width - 120 - getWidth(), 80);
        }

        setLocation(formLoc);
        addMouseListener(this);
        addMouseMotionListener(this);

        // 矩形步增量
        wndDdd = (maxWidth - minWidth) / aniStep;
        // 圆角步增量
        arcDdd = arcWidth / aniStep;
        timer = new javax.swing.Timer(aniTime / aniStep, new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                timerActionPerformed(e);
            }
        });
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt)
    {
        if (evt.isPopupTrigger())
        {
            trayPtn.showJPopupMenu(evt);
        }
        else if (evt.getClickCount() > 1)
        {
            trayPtn.showLastPtn();
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent evt)
    {
        if (evt.isPopupTrigger())
        {
            trayPtn.showJPopupMenu(evt);
        }
        dragLoc = getScreenLocation(evt);
        formLoc = getLocation();
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent evt)
    {
        if (evt.isPopupTrigger())
        {
            trayPtn.showJPopupMenu(evt);
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent evt)
    {
        aniDir = true;
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent evt)
    {
        aniDir = false;
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent evt)
    {
        java.awt.Point tmp = getScreenLocation(evt);
        tmp.x += formLoc.x - dragLoc.x;
        tmp.y += formLoc.y - dragLoc.y;
        java.awt.Dimension scrSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dlgSize = getSize();
        if (tmp.x < 10)
        {
            tmp.x = 1;
        }
        if (tmp.y < 10)
        {
            tmp.y = 1;
        }
        int x = scrSize.width - dlgSize.width;
        if (tmp.x + 10 > x)
        {
            tmp.x = x;
        }
        int y = scrSize.height - dlgSize.height;
        if (tmp.y + 10 > y)
        {
            tmp.y = y;
        }
        setLocation(tmp);
        userMdl.setCfg(ConsCfg.CFG_TRAY_LOC, "[" + tmp.x + "," + tmp.y + "]");
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent evt)
    {
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent evt)
    {
        if (evt.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
        {
            return;
        }
        if (evt.getID() == java.awt.event.WindowEvent.WINDOW_DEACTIVATED)
        {
            setVisible(false);
        }
        super.processWindowEvent(evt);
    }

    private void timerActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (aniDir)
        {
            if (wndDim >= maxWidth)
            {
                timer.stop();
                wndDim = maxWidth;
                return;
            }

            wndDim += wndDdd;
            arcDim += arcDdd;
            int p = (maxWidth - wndDim) >> 1;
            com.sun.awt.AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(p, p, wndDim, wndDim, arcDim, arcDim));
            return;
        }

        if (wndDim <= minWidth)
        {
            timer.stop();
            wndDim = minWidth;
            return;
        }
        wndDim -= wndDdd;
        arcDim -= arcDdd;
        int p = (maxWidth - wndDim) >> 1;
        com.sun.awt.AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(p, p, wndDim, wndDim, arcDim, arcDim));
    }

    private java.awt.Point getScreenLocation(java.awt.event.MouseEvent evt)
    {
        java.awt.Point cur = evt.getPoint();
        java.awt.Point dlg = getLocationOnScreen();
        return new java.awt.Point(dlg.x + cur.x, dlg.y + cur.y);
    }
    private java.awt.Point dragLoc;
    private java.awt.Point formLoc;
}
