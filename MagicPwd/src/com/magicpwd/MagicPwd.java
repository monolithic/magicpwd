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

import com.magicpwd._enum.RunMode;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.MpwdMdl;
import com.magicpwd.v.cmd.mcmd.McmdPtn;
import com.magicpwd.v.gui.tray.TrayPtn;

/**
 * @author Amon
 */
public class MagicPwd
{

    private MpwdMdl mpwdMdl;

    private MagicPwd()
    {
    }

    private void init()
    {
        // 系统配置信息读取
        mpwdMdl = new MpwdMdl();
        mpwdMdl.loadCfg();

        // 命令模式
        if (MpwdMdl.getRunMode() == RunMode.cmd)
        {
            McmdPtn mcmdPtn = new McmdPtn();
            mcmdPtn.init();
            return;
        }

        if (MpwdMdl.getRunMode() == RunMode.app)
        {
            TrayPtn trayPtn = new TrayPtn(mpwdMdl);
            trayPtn.init();
            return;
        }

        if (MpwdMdl.getRunMode() == RunMode.dev)
        {
            TrayPtn trayPtn = new TrayPtn(mpwdMdl);
            trayPtn.init();
            return;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // 界面启动参数读取
        if (args != null && args.length > 0)
        {
            MpwdMdl.setRunMode(args[0]);
        }

        new MagicPwd().init();
    }

    private static void zipData(String dir)
    {
        try
        {
            Jzip.unZip(MagicPwd.class.getResourceAsStream("/res/res.zip"), new java.io.File(dir), false);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(null, null, exp.getLocalizedMessage());
        }
    }
}
