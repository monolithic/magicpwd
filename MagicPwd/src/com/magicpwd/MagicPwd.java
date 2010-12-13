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
package com.magicpwd;

import com.magicpwd.__a.AFrame;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.AmonFF;
import com.magicpwd.v.tray.TrayPtn;

/**
 * @author Amon
 */
public class MagicPwd
{

    private MagicPwd()
    {
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // 界面启动参数读取
        if (args != null && args.length > 0)
        {
            String arg = args[0];
            if ("webstart".equalsIgnoreCase(arg) || "web".equalsIgnoreCase(arg))
            {
                UserMdl.setRunMode(ConsEnv.RUN_MODE_WEB);
            }
            else if ("command".equalsIgnoreCase(arg) || "cmd".equalsIgnoreCase(arg))
            {
                UserMdl.setRunMode(ConsEnv.RUN_MODE_CMD);
            }
            else if ("dev".equalsIgnoreCase(arg))
            {
                UserMdl.setRunMode(ConsEnv.RUN_MODE_DEV);
            }
        }

        // 数据完整性处理
        zipData();

        // 用户配置文件加载
        final UserMdl userMdl = new UserMdl();
        userMdl.loadCfg();

        final TrayPtn trayPtn = new TrayPtn(userMdl);

        try
        {
            javax.swing.SwingUtilities.invokeLater(new Runnable()
            {

                @Override
                public void run()
                {
                    // 语言资源加载
                    Lang.loadLang(userMdl);

                    // 扩展皮肤加载
                    Bean.loadLnF(userMdl);

                    trayPtn.showViewPtn(ConsEnv.APP_MODE_MPWD);
                }
            });
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        loadPre();
    }

    private static void zipData()
    {
        try
        {
            Jzip.unZip(MagicPwd.class.getResourceAsStream("/res/res.zip"), new java.io.File("."), false);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(null, null, exp.getLocalizedMessage());
        }
    }

    private static void loadPre()
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        Bean.getNone();
        Bean.getLogo(16);
        AFrame.loadPre();

        // 扩展库加载
        java.io.File file = new java.io.File(ConsEnv.DIR_EXT);
        if (file != null && file.exists() && file.isDirectory() && file.canRead())
        {
            java.io.File jars[] = file.listFiles(new AmonFF(".+\\.jar$", true));
            if (jars != null && jars.length > 0)
            {
                try
                {
                    // 加载扩展库
                    Bean.loadJar(jars);
                }
                catch (Exception exp)
                {
                    Logs.exception(exp);
                }
            }
        }
    }
}
