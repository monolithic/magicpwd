package com.magicpwd;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
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
//        if (args != null && args.length > 0 && "webstart".equalsIgnoreCase(args[0]))
//        {
//            UserMdl.setRunMode(ConsEnv.MODE_RUN_WEB);
//        }

        // 数据完整性处理
        zipData();

        TrayPtn.getInstance().loadCfg();

        // 界面风格设置
        try
        {
            javax.swing.SwingUtilities.invokeLater(new Runnable()
            {

                @Override
                public void run()
                {
                    TrayPtn.getInstance().showViewPtn(ConsEnv.VIEW_MAIN);
                }
            });
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        TrayPtn.getInstance().loadPre();
    }

    private static void zipData()
    {
        try
        {
            Jzip.unZip(MagicPwd.class.getResourceAsStream("/res/dat.zip"), new java.io.File(ConsEnv.DIR_DAT), false);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(null, null, exp.getLocalizedMessage());
        }
    }
}
