package com.magicpwd;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.TrayPtn;

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
        }

        // 数据完整性处理
        zipData();

        final TrayPtn trayPtn = new TrayPtn();

        try
        {
            javax.swing.SwingUtilities.invokeLater(new Runnable()
            {

                @Override
                public void run()
                {
                    // 加载用户配置
                    trayPtn.loadCfg();

                    trayPtn.showViewPtn(ConsEnv.VIEW_MAIN);
                }
            });
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        trayPtn.loadPre();
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
}
