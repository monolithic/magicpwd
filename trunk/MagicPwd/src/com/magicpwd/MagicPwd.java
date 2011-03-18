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
import com.magicpwd._enum.AppView;
import com.magicpwd._comn.apps.FileLocker;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._enum.RunMode;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Skin;
import com.magicpwd.m.MpwdMdl;
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
        FileLocker fl = new FileLocker(new java.io.File("tmp", "mwpd.lck"));
        if (!fl.tryLock())
        {
            return;
        }

        // 界面启动参数读取
        if (args != null && args.length > 0)
        {
            MpwdMdl.setRunMode(args[0]);
        }

        // 系统配置信息读取
        MpwdMdl mpwdMdl = new MpwdMdl();
        mpwdMdl.loadCfg();

        // 命令模式
        if (MpwdMdl.getRunMode() == RunMode.cmd)
        {
            return;
        }

//        java.awt.KeyboardFocusManager.setCurrentKeyboardFocusManager(new KFManager());

        // 用户配置文件加载
        final UserMdl userMdl = new UserMdl();
        userMdl.setMpwdMdl(mpwdMdl);
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
                    Skin.loadLook(userMdl);

                    trayPtn.showViewPtn(AppView.mexp);
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
