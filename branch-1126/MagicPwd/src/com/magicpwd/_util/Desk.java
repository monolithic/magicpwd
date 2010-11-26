/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.net.URI;

/**
 *
 * @author Amon
 */
public class Desk
{

    public static boolean browse(String url)
    {
        // 桌面支持性判断
        if (!Desktop.isDesktopSupported())
        {
            return false;
        }

        // 指定事件可用性判断
        Desktop desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Action.BROWSE))
        {
            return false;
        }

        // 浏览指定地址
        try
        {
            desktop.browse(new URI(url));
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    public static boolean open(java.io.File file)
    {
        // 桌面支持性判断
        if (!Desktop.isDesktopSupported())
        {
            return false;
        }

        // 指定事件可用性判断
        Desktop desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Action.OPEN))
        {
            return false;
        }

        // 浏览指定地址
        try
        {
            desktop.open(file);
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    public static boolean mail(String mailto)
    {
        Logs.log(mailto);

        // 桌面支持性判断
        if (!Desktop.isDesktopSupported())
        {
            return false;
        }

        // 指定事件可用性判断
        Desktop desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Action.MAIL))
        {
            return false;
        }

        // 发送邮件
        try
        {
            if (!mailto.toLowerCase().startsWith("mailto:"))
            {
                mailto = "mailto:" + mailto;
            }
            desktop.mail(new URI(mailto));
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }
}
