/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

import com.magicpwd._util.Util;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 *
 * @author Administrator
 */
public class TrayPtn
{
    private TrayIcon trayIcon = null;

    public TrayPtn()
    {
        init();
    }

    public boolean init()
    {
        //检查当前系统是否支持系统托盘
        if (!SystemTray.isSupported())
        {
            return false;
        }

        //获取表示桌面托盘区的 SystemTray 实例。
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Util.getLogo();
        PopupMenu popupMenu = new PopupMenu();
        MenuItem exitItem = new MenuItem("关闭");
        MenuItem menuItema = new MenuItem("menu a");
        MenuItem menuItemb = new MenuItem("menu b");
        MenuItem menuItemc = new MenuItem("menu c");
        MenuItem menuItemd = new MenuItem("menu d");
        exitItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    System.exit(0);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        popupMenu.add(menuItema);
        popupMenu.add(menuItemb);
        popupMenu.add(menuItemc);
        popupMenu.add(menuItemd);
        popupMenu.add(exitItem);
        trayIcon = new TrayIcon(image, "系统托盘{kissjava}", popupMenu);
        trayIcon.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                }
            }
        });
        try
        {
            tray.add(trayIcon);  // 将 TrayIcon 添加到 SystemTray。
        }
        catch (AWTException e)
        {
            System.err.println(e);
        }
        return true;
    }

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new TrayPtn();
            }
        });
    }
}
