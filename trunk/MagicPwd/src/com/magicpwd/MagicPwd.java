package com.magicpwd;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._face.IBackCall;
import com.magicpwd._mail.MailDlg;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBAccess;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MainPtn;
import com.magicpwd.v.MiniPtn;
import com.magicpwd.v.NormPtn;
import java.io.File;

/**
 * @author Amon
 * 
 */
public class MagicPwd
{

    private static javax.swing.JFrame mf_CurrForm;
    private static javax.swing.JDialog md_UcfgForm;
    private static int viewPtn;

    private MagicPwd()
    {
    }

    public void init()
    {
    }

    public static javax.swing.JFrame getCurrForm()
    {
        return mf_CurrForm;
    }

    public static MailDlg getMailDlg()
    {
        return md_MailDlg;
    }

    public static void setMailDlg(MailDlg mailDlg)
    {
        md_MailDlg = mailDlg;
    }

    public static void showCfgDlg()
    {
        if (md_UcfgForm == null || !md_UcfgForm.isVisible())
        {
            md_UcfgForm = new javax.swing.JDialog(mf_CurrForm);
        }
    }

    public static void showMainPtn()
    {
        if (mp_MainPtn == null)
        {
            mp_MainPtn = new MainPtn();
            mp_MainPtn.initView();
            mp_MainPtn.initLang();
            mp_MainPtn.initData();
        }

        mf_CurrForm = mp_MainPtn;
        viewPtn = VIEW_MAIN;
    }

    public static void showNormPtn()
    {
        if (mp_NormPtn == null)
        {
            mp_NormPtn = new NormPtn();
            mp_NormPtn.initView();
            mp_NormPtn.initLang();
            mp_NormPtn.initData();
        }

        mf_CurrForm = mp_NormPtn;
        viewPtn = VIEW_NORM;
    }

    public static void showMiniPtn()
    {
        if (mp_MiniPtn == null)
        {
            mp_MiniPtn = new MiniPtn();
            mp_MiniPtn.initView();
            mp_MiniPtn.initLang();
            mp_MiniPtn.initData();
        }

        mf_CurrForm = mp_MiniPtn;
        viewPtn = VIEW_MINI;
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
                Lang.showMesg(null, null, exp.getLocalizedMessage());
                Logs.exception(exp);
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
                    UserSign us = new UserSign(UserMdl.getUserCfg().getCfg(ConsCfg.CFG_USER, "").trim().length() > 0 ? ConsEnv.SIGN_IN : ConsEnv.SIGN_UP);
                    us.setConfrmBackCall(new IBackCall()
                    {

                        @Override
                        public boolean callBack(Object sender, java.util.EventListener event, String... params)
                        {
                            return viewFrm();
                        }
                    });
                    us.init();
                }
            });
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        // 启动后台预加载线程
        preLoad();
    }

    public static void exit(int status)
    {
        if (mf_CurrForm != null)
        {
            mf_CurrForm.setVisible(false);
        }
        endSave();
        System.exit(status);
    }

    private static void preLoad()
    {
        Util.getNone();
        Util.getLogo();
        Util.getIcon(0);
        UserMdl.preLoad();

        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    private static void endSave()
    {
        try
        {
            UserMdl.saveCfg();

            DBAccess.exit();

            java.io.File backFile = Util.nextBackupFile(UserMdl.getUserCfg().getBackNum());
            Jzip.zip(backFile, new java.io.File(ConsEnv.DIR_DAT));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    private static boolean viewFrm()
    {
        // 设置软件界面风格
        showMainPtn();
        if (!getCurrForm().isVisible())
        {
            getCurrForm().setVisible(true);
        }
        return true;
    }
    private static MiniPtn mp_MiniPtn;
    private static NormPtn mp_NormPtn;
    private static MainPtn mp_MainPtn;
    private static MailDlg md_MailDlg;
    private static final int VIEW_MAIN = 0;
    private static final int VIEW_NORM = 1;
    private static final int VIEW_MINI = 2;
}
