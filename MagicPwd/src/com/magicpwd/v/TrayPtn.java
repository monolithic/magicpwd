/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

/**
 * @author Amon
 */
public class TrayPtn
{

    private TrayIcon trayIcon;
    private PopupMenu trayMenu;
    private MenuItem infoItem;
    private MenuItem siteItem;
    private MenuItem helpItem;
    private MenuItem mailItem;
    private MenuItem updtItem;
    private MenuItem mainItem;
    private MenuItem normItem;
    private MenuItem miniItem;
    private MenuItem exitItem;

    public TrayPtn()
    {
    }

    public boolean initView()
    {
        //检查当前系统是否支持系统托盘
        if (!SystemTray.isSupported())
        {
            return false;
        }

        //获取表示桌面托盘区的 SystemTray 实例。
        SystemTray tray = SystemTray.getSystemTray();
        trayMenu = new PopupMenu();

        infoItem = new MenuItem("关于");
        trayMenu.add(infoItem);

        try
        {
            helpItem = new MenuItem(new String("使用帮助".getBytes("utf-8"),"GB18030"));
            trayMenu.add(helpItem);
        }
        catch (Exception exp)
        {
        }

        mailItem = new MenuItem("联系作者");
        trayMenu.add(mailItem);

        trayMenu.addSeparator();

        mainItem = new MenuItem();
        trayMenu.add(mainItem);

        normItem = new MenuItem();
        trayMenu.add(normItem);

        miniItem = new MenuItem("\u2035\u2999\u2411\u2116");
        trayMenu.add(miniItem);

        trayMenu.addSeparator();

        updtItem = new MenuItem("检测更新");
        trayMenu.add(updtItem);

        trayMenu.addSeparator();

        exitItem = new MenuItem();
        exitItem.addActionListener(new ActionListener()
        {

            @Override
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
        trayMenu.add(exitItem);

        trayIcon = new TrayIcon(Util.getLogo());
        trayIcon.setPopupMenu(trayMenu);
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
            // 将 TrayIcon 添加到 SystemTray。
            tray.add(trayIcon);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        return true;
    }

    public boolean initData()
    {
        return true;
    }

    public boolean initLang()
    {
        try
        {
        mainItem.setLabel(new String(Lang.getLang(LangRes.P30F9601, "iso-8859-1").getBytes(),"gbk"));
        }catch(Exception exp)
        {
            exp.printStackTrace();
        }
//        normItem.setLabel(Lang.getLang("", ""));
//        miniItem.setLabel(Lang.getLang("", ""));
//
//        exitItem.setLabel(Lang.getLang("", ""));
        trayIcon.setToolTip("哈哈");
        return true;
    }

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        TrayPtn tp = new TrayPtn();
        tp.initView();
        tp.initLang();
    }
}
