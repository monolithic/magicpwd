package com.magicpwd;

import java.util.EventListener;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._mail.MailDlg;
import com.magicpwd._util.Lang;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBAccess;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.FindBar;
import com.magicpwd.v.InfoBar;
import com.magicpwd.v.MainPtn;
import com.magicpwd.v.MenuBar;
import com.magicpwd.v.MiniPtn;
import com.magicpwd.v.ToolBar;

/**
 * @author Amon
 * 
 */
public class MagicPwd extends javax.swing.JFrame
{

    private static javax.swing.JFrame mf_MainForm;
    private static javax.swing.JDialog md_UcfgForm;
    private static java.awt.Dimension mf_LastSize;
    private static int viewPtn;

    private MagicPwd()
    {
        setIconImage(Util.getImage(ConsEnv.ICON_LOGO_0016));
    }

    public void init()
    {
    }

    public static javax.swing.JFrame getFrame()
    {
        return mf_MainForm;
    }

    public static MenuBar getWMenuBar()
    {
        return mb_MenuBar;
    }

    public static ToolBar getWToolBar()
    {
        return tb_ToolBar;
    }

    public static MailDlg getMailDlg()
    {
        return md_MailDlg;
    }

    public static void setMailDlg(MailDlg mailDlg)
    {
        md_MailDlg = mailDlg;
    }

    public static InfoBar getWInfoBar()
    {
        return ib_InfoBar;
    }

    public static FindBar getWFindBar()
    {
        return fb_FindBar;
    }

    public static void showCfgDlg()
    {
        if (md_UcfgForm == null || !md_UcfgForm.isVisible())
        {
            md_UcfgForm = new javax.swing.JDialog(mf_MainForm);
        }
    }

    public static void showMainPtn()
    {
        if (mf_MainForm != null)
        {
            mf_MainForm.getContentPane().removeAll();
        }
        else
        {
            mf_MainForm = new MagicPwd();
            mf_MainForm.setIconImage(Util.getLogo());
        }

        if (mb_MenuBar == null)
        {
            mb_MenuBar = new MenuBar();
            mb_MenuBar.initView();
            mb_MenuBar.initLang();
        }
        mb_MenuBar.setVisible(UserMdl.getCfg().isMenuViw());
        mf_MainForm.setJMenuBar(mb_MenuBar);

        if (tb_ToolBar == null)
        {
            tb_ToolBar = new ToolBar();
            tb_ToolBar.initView();
            tb_ToolBar.initLang();
        }
        tb_ToolBar.setVisible(UserMdl.getCfg().isToolViw());
        mf_MainForm.getContentPane().add(tb_ToolBar, UserMdl.getCfg().getToolLoc());

        if (ib_InfoBar == null)
        {
            ib_InfoBar = new InfoBar();
            ib_InfoBar.initView();
            ib_InfoBar.initLang();
        }
        ib_InfoBar.setVisible(UserMdl.getCfg().isInfoViw());
        mf_MainForm.getContentPane().add(ib_InfoBar, java.awt.BorderLayout.SOUTH);

        if (fb_FindBar == null)
        {
            fb_FindBar = new FindBar();
            fb_FindBar.initView();
            fb_FindBar.initLang();
        }
        fb_FindBar.setVisible(UserMdl.getCfg().isFindViw());

        if (mp_MainPtn == null)
        {
            mp_MainPtn = new MainPtn();
            mp_MainPtn.initView();
            mp_MainPtn.initLang();
        }
        mf_MainForm.getContentPane().add(mp_MainPtn, java.awt.BorderLayout.CENTER);

        mb_MenuBar.setActionEvent(mp_MainPtn);
        tb_ToolBar.setActionEvent(mp_MainPtn);
        ib_InfoBar.setActionEvent(mp_MainPtn);
        fb_FindBar.setActionEvent(mp_MainPtn);

        mf_MainForm.setTitle(Lang.getLang(LangRes.P30F7201, "魔方密码"));
        if (mf_LastSize != null)
        {
            java.awt.Dimension d = mf_MainForm.getSize();
            mf_MainForm.setSize(mf_LastSize);
            mf_LastSize = d;
        }
        else
        {
            mf_MainForm.pack();
            Util.centerForm(mf_MainForm, null);
        }

        viewPtn = VIEW_MAIN;
    }

    public static void initMainPtn()
    {
        mp_MainPtn.initData();
    }

    public static void showNormPtn()
    {
        viewPtn = VIEW_NORM;
    }

    public static void showMiniPtn()
    {
        if (mf_MainForm != null)
        {
            mf_MainForm.getContentPane().removeAll();
            mb_MenuBar.setVisible(false);
        }
        else
        {
            mf_MainForm = new MagicPwd();
        }

        if (mp_MiniPtn == null)
        {
            mp_MiniPtn = new MiniPtn();
            mp_MiniPtn.initView();
            mp_MiniPtn.initLang();
            mp_MiniPtn.initData();
        }
        mf_MainForm.getContentPane().add(mp_MiniPtn);

        mf_MainForm.setTitle(Lang.getLang(LangRes.P30F5201, "记事本"));
        if (mf_LastSize != null)
        {
            java.awt.Dimension d = mf_MainForm.getSize();
            mf_MainForm.setSize(mf_LastSize);
            mf_LastSize = d;
        }
        else
        {
            mf_LastSize = mf_MainForm.getSize();
            mf_MainForm.pack();
            Util.centerForm(mf_MainForm, null);
        }

        viewPtn = VIEW_MINI;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // 设置软件界面风格
        try
        {
            String osnm = System.getProperty("os.name").toLowerCase();
            if (osnm.indexOf("windows") >= 0)
            {
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            }
            else if (osnm.indexOf("linux") >= 0)
            {
                javax.swing.UIManager.installLookAndFeel("GTK+", "com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        // 用户配置文件加载
        UserMdl.loadCfg();

        // 界面启动参数读取
        if (args != null && args.length > 1 && "webstart".equalsIgnoreCase(args[1]))
        {
            UserMdl.setRunMode(ConsEnv.MODE_RUN_WEB);
        }
        else
        {
            try
            {
                Class.forName("org.hsqldb.jdbcDriver");
            }
            catch (ClassNotFoundException ex)
            {
                Logs.exception(ex);
            }
        }

        // 显示登录或注册界面
        UserSign us = new UserSign(UserMdl.getCfg().getCfg(ConsCfg.CFG_USER, "").trim().length() > 0 ? ConsEnv.SIGN_IN : ConsEnv.SIGN_UP);
        us.setConfrmBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, EventListener event, String... params)
            {
                return viewFrm();
            }
        });
        us.init();

        // 启动后台预加载线程
        Thread t = new Thread()
        {

            @Override
            public void run()
            {
                preLoad();
            }
        };
        t.start();
    }

    public static void exit(int status)
    {
        mf_MainForm.setVisible(false);
        endSave();
        System.exit(status);
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e)
    {
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
        {
            if (viewPtn == VIEW_MAIN)
            {
                mp_MainPtn.fileExitActionPerformed(null);
            }
            else if (viewPtn == VIEW_MINI)
            {
                mp_MiniPtn.fileExitActionPerformed(null);
            }
            return;
        }
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_ICONIFIED)
        {
            if (viewPtn == VIEW_MAIN)
            {
                mp_MainPtn.fileHideActionPerformed(null);
            }
            // else if (viewPtn == VIEW_MINI)
            // {
            // mp_MiniPtn.fileHideActionPerformed(null);
            // }
            // validate();
        }
        super.processWindowEvent(e);
    }

    private static void preLoad()
    {
        Util.preLoad();
        UserMdl.preLoad();

        mb_MenuBar = new MenuBar();
        mb_MenuBar.initView();
        mb_MenuBar.initLang();
        tb_ToolBar = new ToolBar();
        tb_ToolBar.initView();
        tb_ToolBar.initLang();
        ib_InfoBar = new InfoBar();
        ib_InfoBar.initView();
        ib_InfoBar.initLang();
        fb_FindBar = new FindBar();
        fb_FindBar.initView();
        fb_FindBar.initLang();

        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
        }
        catch (ClassNotFoundException exp)
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

            java.io.File backFile = Util.nextBackupFile(UserMdl.getCfg().getBackNum());
            Jzip.zip(backFile, new java.io.File(ConsEnv.DIR_DAT));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    private static boolean viewFrm()
    {
        showMainPtn();
        if (!getFrame().isVisible())
        {
            getFrame().setVisible(true);
        }
        initMainPtn();
        return true;
    }
    private static MenuBar mb_MenuBar;
    private static ToolBar tb_ToolBar;
    private static InfoBar ib_InfoBar;
    private static FindBar fb_FindBar;
    private static MiniPtn mp_MiniPtn;
    private static MainPtn mp_MainPtn;
    private static MailDlg md_MailDlg;
    private static final int VIEW_MAIN = 0;
    private static final int VIEW_NORM = 1;
    private static final int VIEW_MINI = 2;
}
