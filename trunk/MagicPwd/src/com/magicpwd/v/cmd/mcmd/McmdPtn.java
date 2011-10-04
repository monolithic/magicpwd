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
package com.magicpwd.v.cmd.mcmd;

import com.magicpwd._cons.ConsDat;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.m.MpwdMdl;
import com.magicpwd.m.UserMdl;

/**
 * 命令行模式
 * @author Aven
 */
public class McmdPtn
{
    private MpwdMdl mpwdMdl;
    private UserMdl userMdl;
    private String currKind;
    private String currKeys;
    private int currPage;

    public McmdPtn(MpwdMdl mpwdMdl)
    {
        this.mpwdMdl = mpwdMdl;
    }

    public void init()
    {
        console = System.console();
        if (console == null)
        {
            scanner = new java.util.Scanner(System.in);
        }
        userMdl = new UserMdl(mpwdMdl);

        int errCnt = 0;
        while (errCnt < 3)
        {
            if (signIn())
            {
                break;
            }
            errCnt += 1;
            if (errCnt > 2)
            {
                Lang.showMesg(console, null, "您操作的错误次太多，请确认您是否为合法用户！\n为了保障用户数据安全，软件将自动关闭。");
            }
            else
            {
                Lang.showMesg(console, null, "身份验证错误，请确认您的用户名及口令是否正确！");
            }
        }

        showHelp();

        String cmd;
        String tmp;
        String sep = " ";
        while (true)
        {
            cmd = readText();
            if (cmd == null)
            {
                continue;
            }
            cmd = cmd.trim();
            tmp = cmd.toLowerCase();
            int idx = tmp.indexOf(sep);
            if (idx > 0)
            {
                cmd = cmd.substring(idx);
                tmp = tmp.substring(0, idx);
            }

            if ("ls".equals(tmp))
            {
                listKeys();
                continue;
            }
            if ("pwd".equals(tmp))
            {
                continue;
            }
            if ("cat".equals(tmp))
            {
                viewKeys();
                continue;
            }
            if ("help".equals(tmp))
            {
                showHelp();
                continue;
            }
            if ("exit".equals(tmp))
            {
                break;
            }
        }
    }

    private boolean signIn()
    {
        print("用户：");
        String name = readText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(console, null, "请输入用户名称！");
            return false;
        }
        name = name.trim();

        // 获得数据路径
        String path = mpwdMdl.getDatPath(name);
        if (!Char.isValidate(path))
        {
            Lang.showMesg(console, null, "系统无法定位当前用户的数据文件，请尝试以下操作：\n1、打开文件：定位您之前的数据文件；\n2、用户注册：注册名称为 {0} 的用户。", name);
            return false;
        }

        print("口令：");
        String pwds = readPass();
        if (!com.magicpwd._util.Char.isValidate(pwds))
        {
            Lang.showMesg(console, null, "请输入登录口令！");
            return false;
        }

        if (!userMdl.loadCfg(path))
        {
            Lang.showMesg(console, null, "用户数据加载异常！");
            return false;
        }

        try
        {
            boolean b = userMdl.signIn(name, pwds);
            if (b)
            {
                if (!ConsDat.VERSIONS.equals(DBA4000.readConfig(userMdl, "versions")))
                {
                    return false;
                }
                mpwdMdl.setUserLast(name);
                mpwdMdl.setViewLast("mcmd");
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(console, null, "身份验证错误，请确认您的用户名及口令是否正确！");
            return false;
        }

        return true;
    }

    private void listKeys()
    {
    }

    private void viewKeys()
    {
    }

    private void showHelp()
    {
        StringBuilder buf = new StringBuilder();
        buf.append("cd 数字\t- 切换目录\n");
        buf.append("ls \t- 查看当前目录下数据信息\n");
        buf.append("pwd \t- 查看当前所处目录路径\n");
        buf.append("cat \t- 查看指定记录的口令\n");
        buf.append("<< \t- 转到首屏\n");
        buf.append("< \t- 转到上一屏\n");
        buf.append("> \t- 转到下一屏\n");
        buf.append(">> \t- 转到未屏\n");
        buf.append("& 数字 \t- 复制当前属性的值到剪贴板\n");
        buf.append("@ 数字 \t- 复制当前属性的键到剪贴板\n");

        Lang.showMesg(console, null, buf.toString());
    }

    public boolean endSave()
    {
        return true;
    }

    public void print(String cmd)
    {
        System.out.print(cmd);
    }

    public void print(String cmd, Object... obj)
    {
        console.format(cmd, obj);
    }

    public void printLine(String cmd)
    {
        System.out.println(cmd);
    }

    public String readText()
    {
        if (console != null)
        {
            return console.readLine();
        }
        return scanner.next();
    }

    public String readPass()
    {
        if (console != null)
        {
            return new String(console.readPassword());
        }
        return scanner.next();
    }

    public Integer readInt()
    {
        return scanner.nextInt();
    }

    public Long readLong()
    {
        return scanner.nextLong();
    }

    public Double readDouble()
    {
        return scanner.nextDouble();
    }
    private java.io.Console console;
    private java.util.Scanner scanner;
}
