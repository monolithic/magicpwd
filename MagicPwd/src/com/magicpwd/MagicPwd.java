package com.magicpwd;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._face.IBackCall;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBAccess;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.TrayPtn;
import java.io.File;

/**
 * @author Amon
 * 
 */
public class MagicPwd
{

    private MagicPwd()
    {
    }

    public void init()
    {
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // 界面启动参数读取
        if (args != null && args.length > 0 && "webstart".equalsIgnoreCase(args[0]))
        {
            UserMdl.setRunMode(ConsEnv.MODE_RUN_WEB);
            try
            {
                Jzip.unZip(MagicPwd.class.getResourceAsStream("/res/dat.zip"), new File(ConsEnv.DIR_DAT), false);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                Lang.showMesg(null, null, exp.getLocalizedMessage());
            }
        }

        // 用户配置文件加载
        UserMdl.loadUserCfg();

        // 界面风格设置
        try
        {
            javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
            javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);
            javax.swing.SwingUtilities.invokeLater(new Runnable()
            {

                @Override
                public void run()
                {
                    // 用户偏好风格设置
                    try
                    {
                        String lafClass = UserMdl.getUserCfg().getCfg(ConsCfg.CFG_SKIN, ConsCfg.DEF_SKIN).trim();
                        if (lafClass.length() < 1 || ConsCfg.DEF_SKIN.equalsIgnoreCase(lafClass))
                        {
                            lafClass = javax.swing.UIManager.getSystemLookAndFeelClassName();
                        }
                        javax.swing.UIManager.setLookAndFeel(lafClass);
                    }
                    catch (Exception e)
                    {
                        Logs.exception(e);
                    }

                    // 显示登录或注册界面
                    UserSign us = new UserSign();
                    us.setBackCall(new IBackCall()
                    {

                        @Override
                        public boolean callBack(Object sender, java.util.EventListener event, String... params)
                        {
                            if (params == null || params.length != 1)
                            {
                                return false;
                            }
                            if (ConsEnv.STR_SIGN_IN.equalsIgnoreCase(params[0]) || ConsEnv.STR_SIGN_UP.equalsIgnoreCase(params[0]))
                            {
                                return viewFrm();
                            }
                            return false;
                        }
                    });
                    us.initView(UserMdl.getUserCfg().getCfg(ConsCfg.CFG_USER, "").trim().length() > 0 ? ConsEnv.INT_SIGN_IN : ConsEnv.INT_SIGN_UP);
                    us.initLang();
                    us.initData();
                }
            });
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        // 启动后台预加载线程
        UserMdl.preLoad();

        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        Util.getIcon(0);
        Util.getNone();
        Util.getLogo(16);
    }

    public static java.io.File endSave()
    {
        try
        {
            UserMdl.saveCfg();

            DBAccess.exit();

            java.io.File backFile = Util.nextBackupFile(UserMdl.getUserCfg().getBackNum());
            Jzip.doZip(backFile, new java.io.File(ConsEnv.DIR_DAT));
            return backFile;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return null;
        }
    }

    private static boolean viewFrm()
    {
        // 设置软件界面风格
        TrayPtn.showMainPtn();
        if (!TrayPtn.getCurrForm().isVisible())
        {
            TrayPtn.getCurrForm().setVisible(true);
        }
        return true;
    }
}
