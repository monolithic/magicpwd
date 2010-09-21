package com.magicpwd.v;

import com.magicpwd.v.pad.MiniPtn;
import com.magicpwd.v.pwd.MailPtn;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd._comn.I1S2;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._mail.Connect;
import com.magicpwd._mail.MailDlg;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Desk;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBAccess;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.UserCfg;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.m.SafeMdl;

/**
 * 系统托盘
 * @author Amon
 */
public class TrayPtn extends java.awt.TrayIcon implements IBackCall, java.awt.event.MouseListener, java.awt.event.MouseMotionListener
{

    private static boolean dbLocked;
    private static boolean isOsTray;
    private static int currPtn;
    private static int nextPtn;
    private static CoreMdl coreMdl;
    private static SafeMdl safeMdl;
    private static MailDlg mailDlg;
    private static TrayPtn trayPtn;
    private static UserSign userSign;
    private static javax.swing.JFrame mf_CurrForm;
    private static javax.swing.JDialog md_TrayForm;
    private static javax.swing.event.PopupMenuListener listener;

    private TrayPtn()
    {
        super(Util.getLogo(16));
        int size = getSize().height;
        if (size != 16)
        {
            setImage(Util.getLogo(size));
        }
        setImageAutoSize(true);
    }

    public static TrayPtn getInstance()
    {
        if (trayPtn == null)
        {
            trayPtn = new TrayPtn();
            trayPtn.initView();
            trayPtn.initLang();
            trayPtn.initData();
        }
        return trayPtn;
    }

    public boolean initView()
    {
        if (md_TrayForm == null)
        {
            md_TrayForm = new javax.swing.JDialog();
            md_TrayForm.setUndecorated(true);
            md_TrayForm.setAlwaysOnTop(true);
//            trayForm.addWindowListener(new java.awt.event.WindowAdapter()
//            {
//                @Override
//                public void windowDeactivated(java.awt.event.WindowEvent evt)
//                {
//                      md_TrayForm.setVisible(false);
//                }
//            });
        }

        if (listener == null)
        {
            listener = new javax.swing.event.PopupMenuListener()
            {

                @Override
                public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt)
                {
                }

                @Override
                public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt)
                {
                    //md_TrayForm.setVisible(false);
                }

                @Override
                public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt)
                {
                    //md_TrayForm.setVisible(false);
                }
            };
        }

        addMouseListener(this);

        javax.swing.JLabel iconLbl = new javax.swing.JLabel();
        iconLbl.setIcon(new javax.swing.ImageIcon(Util.getLogo(24)));
        iconLbl.addMouseListener(this);
        iconLbl.addMouseMotionListener(this);
        //iconLbl.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.cyan));
        md_TrayForm.getContentPane().setLayout(new java.awt.BorderLayout());
        md_TrayForm.getContentPane().add(iconLbl);
        md_TrayForm.setVisible(true);

        trayMenu = new javax.swing.JPopupMenu();

        infoItem = new javax.swing.JMenuItem();
        infoItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                infoItemActionPerformed(evt);
            }
        });
        trayMenu.add(infoItem);

        helpItem = new javax.swing.JMenuItem();
        helpItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                helpItemActionPerformed(evt);
            }
        });
        trayMenu.add(helpItem);

        trayMenu.addSeparator();

        viewItem = new javax.swing.JMenuItem();
        viewItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                viewItemActionPerformed(evt);
            }
        });
        trayMenu.add(viewItem);

        trayMenu.addSeparator();

        mainItem = new javax.swing.JMenuItem();
        mainItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mainItemActionPerformed(evt);
            }
        });
        trayMenu.add(mainItem);

        normItem = new javax.swing.JMenuItem();
        normItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                normItemActionPerformed(evt);
            }
        });
        trayMenu.add(normItem);

        miniItem = new javax.swing.JMenuItem();
        miniItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                miniItemActionPerformed(evt);
            }
        });
        trayMenu.add(miniItem);

        trayMenu.addSeparator();

        updtItem = new javax.swing.JMenuItem();
        updtItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                updtItemActionPerformed(evt);
            }
        });
        trayMenu.add(updtItem);

        mailItem = new javax.swing.JMenuItem();
        mailItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mailItemActionPerformed(evt);
            }
        });
        trayMenu.add(mailItem);

        trayMenu.addSeparator();

        siteItem = new javax.swing.JMenuItem();
        siteItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                siteItemActionPerformed(evt);
            }
        });
        trayMenu.add(siteItem);

        mlogItem = new javax.swing.JMenuItem();
        mlogItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mlogItemActionPerformed(evt);
            }
        });
        trayMenu.add(mlogItem);

        trayMenu.addSeparator();

        exitItem = new javax.swing.JMenuItem();
        exitItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                TrayPtn.getCurrForm().setVisible(false);
                endSave();
                System.exit(0);
            }
        });
        trayMenu.add(exitItem);

        return true;
    }

    public boolean initData()
    {
        viewItem.setEnabled(java.awt.SystemTray.isSupported());

        changeView(coreMdl.getUserCfg().getCfg(ConsCfg.CFG_TRAY_PTN, "guid"));

        return true;
    }

    public boolean initLang()
    {
        normItem.setVisible(false);

        Lang.setWText(infoItem, LangRes.P30F9601, "关于软件");
        Lang.setWText(helpItem, LangRes.P30F9602, "使用帮助");

        Lang.setWText(viewItem, LangRes.P30F960D, "显示为托盘图标");

        Lang.setWText(mainItem, LangRes.P30F9603, "魔方密码");
        Lang.setWText(normItem, LangRes.P30F9604, "迷你账簿");
        Lang.setWText(miniItem, LangRes.P30F9605, "记事便签");

        Lang.setWText(updtItem, LangRes.P30F9606, "检测更新");
        Lang.setWText(mailItem, LangRes.P30F9607, "联系作者");
        Lang.setWText(siteItem, LangRes.P30F9608, "网站");
        Lang.setWText(mlogItem, LangRes.P30F960B, "微博");

        Lang.setWText(exitItem, LangRes.P30F960C, "退出");

        setToolTip(ConsEnv.SOFTNAME + ' ' + ConsEnv.VERSIONS);
        return true;
    }

    @Override
    public boolean callBack(Object sender, java.util.EventListener event, String... params)
    {
        if (params == null || params.length != 1)
        {
            return false;
        }

        if (!ConsEnv.STR_SIGN_RS.equalsIgnoreCase(params[0]))
        {
            return false;
        }

        javax.swing.JFrame currForm = getCurrForm();
        switch (nextPtn)
        {
            case ConsEnv.VIEW_MAIN:
                showMainPtn();
                break;
            case ConsEnv.VIEW_NORM:
                showNormPtn();
                break;
            case ConsEnv.VIEW_MINI:
                showMiniPtn();
                break;
            default:
                break;
        }

        currForm.setVisible(false);
        getCurrForm().setVisible(true);
        if (getCurrForm().getState() != java.awt.Frame.NORMAL)
        {
            getCurrForm().setState(java.awt.Frame.NORMAL);
        }
        //getCurrForm().toFront();
        return true;
    }

    public static javax.swing.JFrame getCurrForm()
    {
        return mf_CurrForm;
    }

    public static MainPtn getMainPtn()
    {
        if (mp_MainPtn == null)
        {
            mp_MainPtn = new MainPtn(coreMdl, safeMdl);
            mp_MainPtn.initView();
            mp_MainPtn.initLang();
            mp_MainPtn.initData();
        }
        return mp_MainPtn;
    }

    public static void showMainPtn()
    {
        getMainPtn();

        mf_CurrForm = mp_MainPtn;
        currPtn = ConsEnv.VIEW_MAIN;
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
        currPtn = ConsEnv.VIEW_NORM;
    }

    public static MiniPtn getMiniPtn()
    {
        if (mp_MiniPtn == null)
        {
            mp_MiniPtn = new MiniPtn(coreMdl, safeMdl);
            mp_MiniPtn.initView();
            mp_MiniPtn.initLang();
            mp_MiniPtn.initData();
        }
        return mp_MiniPtn;
    }

    public static void showMiniPtn()
    {
        getMiniPtn();

        mf_CurrForm = mp_MiniPtn;
        currPtn = ConsEnv.VIEW_MINI;
    }

    public static void showMailPtn()
    {
        if (mailDlg == null)
        {
            mailDlg = new MailDlg();
            mailDlg.initView();
            mailDlg.initLang();
            mailDlg.initData();
            Util.centerForm(mailDlg, TrayPtn.getCurrForm());
        }

        GridMdl gm = null;//coreMdl.getGridMdl();

        MailPtn mailPtn = new MailPtn();
        mailPtn.initView();
        mailPtn.initLang();
        java.util.List<I1S2> mailList = gm.wSelect(ConsDat.INDX_MAIL);
        mailPtn.initMail(mailList);
        if (mailList.size() < 1)
        {
            Lang.showMesg(mailDlg, null, "没有可用的邮件类型数据！");
            return;
        }
        java.util.List<I1S2> userList = gm.wSelect(ConsDat.INDX_TEXT);
        mailPtn.initUser(userList);
        if (userList.size() < 1)
        {
            Lang.showMesg(mailDlg, null, "没有可用的文本类型数据！");
            return;
        }
        java.util.List<I1S2> pwdsList = gm.wSelect(ConsDat.INDX_PWDS);
        mailPtn.initPwds(pwdsList);
        if (pwdsList.size() < 1)
        {
            Lang.showMesg(mailDlg, null, "没有可用的口令类型数据！");
            return;
        }
        if (javax.swing.JOptionPane.OK_OPTION != javax.swing.JOptionPane.showConfirmDialog(TrayPtn.getCurrForm(), mailPtn, "登录确认", javax.swing.JOptionPane.OK_CANCEL_OPTION))
        {
            return;
        }

        String mail = mailList.get(mailPtn.getMail()).getK();
        String user = userList.get(mailPtn.getUser()).getK();
        String pwds = pwdsList.get(mailPtn.getPwds()).getK();

        String host = mail.substring(mail.indexOf('@') + 1);
        if (!com.magicpwd._util.Char.isValidate(host))
        {
            return;
        }

        final Connect connect = new Connect(mail, pwds);
        connect.setUsername(user);
        if (!connect.useDefault())
        {
            Lang.showMesg(mailDlg, null, "查找不到对应的服务信息，如有疑问请与作者联系！");
            return;
        }

        mailDlg.setVisible(true);
        new Thread()
        {

            @Override
            public void run()
            {
                mailDlg.append(connect, "");
            }
        }.start();
    }

    public static void setMailDlgVisible(boolean visible)
    {
        mailDlg.setVisible(visible);
    }

    public javax.swing.JPopupMenu getJPopupMenu()
    {
        return trayMenu;
    }

    public void setJPopupMenu(javax.swing.JPopupMenu menu)
    {
        if (trayMenu != null)
        {
            trayMenu.removePopupMenuListener(listener);
        }
        trayMenu = menu;
        trayMenu.addPopupMenuListener(listener);
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt)
    {
        if (evt.isPopupTrigger())
        {
            showJPopupMenu(evt);
        }
        else
        {
            showLastActionPerformed(evt);
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent evt)
    {
        if (evt.isPopupTrigger())
        {
            showJPopupMenu(evt);
        }
        if (!isOsTray)
        {
            dragLoc = getScreenLocation(evt);
            formLoc = md_TrayForm.getLocation();
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent evt)
    {
        if (evt.isPopupTrigger())
        {
            showJPopupMenu(evt);
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent evt)
    {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent evt)
    {
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent evt)
    {
        if (!isOsTray)
        {
            java.awt.Point tmp = getScreenLocation(evt);
            tmp.x += formLoc.x - dragLoc.x;
            tmp.y += formLoc.y - dragLoc.y;
            java.awt.Dimension scrSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            java.awt.Dimension dlgSize = md_TrayForm.getSize();
            if (tmp.x < 10)
            {
                tmp.x = 1;
            }
            if (tmp.y < 10)
            {
                tmp.y = 1;
            }
            int x = scrSize.width - dlgSize.width;
            if (tmp.x + 10 > x)
            {
                tmp.x = x;
            }
            int y = scrSize.height - dlgSize.height;
            if (tmp.y + 10 > y)
            {
                tmp.y = y;
            }
            md_TrayForm.setLocation(tmp);
            coreMdl.getUserCfg().setCfg(ConsCfg.CFG_TRAY_LOC, "[" + tmp.x + "," + tmp.y + "]");
        }
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent evt)
    {
    }

    public static java.io.File endSave()
    {
        try
        {
            coreMdl.saveCfg();

            DBAccess.exit();

            java.io.File backFile = Util.nextBackupFile(coreMdl.getUserCfg().getBackDir(), coreMdl.getUserCfg().getBackNum());
            Jzip.doZip(backFile, new java.io.File(ConsEnv.DIR_DAT));
            return backFile;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return null;
        }
    }

    private void showJPopupMenu(java.awt.event.MouseEvent evt)
    {
        if (trayMenu == null)
        {
            return;
        }

        if (isOsTray)
        {
            java.awt.Dimension window = trayMenu.getPreferredSize();
            java.awt.Dimension screan = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            int x = evt.getX();
            if (x + window.width > screan.width && x > window.width)
            {
                x -= window.width;
            }
            int y = evt.getY();
            if (y + window.height > screan.height && y > window.height)
            {
                y -= window.height;
            }
            md_TrayForm.setLocation(x, y);

            // trayMenu.setInvoker(trayMenu);
            trayMenu.show(md_TrayForm.getContentPane(), 0, 0);
            md_TrayForm.toFront();
        }
        else
        {
            trayMenu.show(md_TrayForm, 0, md_TrayForm.getHeight());
        }
    }

    private void infoItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        StringBuilder buf = new StringBuilder();
        buf.append(Lang.getLang(LangRes.P30F7201, "魔方密码")).append("\n");
        buf.append(ConsEnv.VERSIONS).append(" Build ").append(ConsEnv.BUILDER);
        javax.swing.JFrame form = null;
        if (getCurrForm().isVisible())
        {
            form = getCurrForm();
        }
        javax.swing.JOptionPane.showMessageDialog(form, buf.toString(), Lang.getLang(LangRes.P30F1208, "关于软件"), javax.swing.JOptionPane.INFORMATION_MESSAGE, new javax.swing.ImageIcon(Util.getLogo(32)));
    }

    private void helpItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(null, LangRes.P30F7A0F, "");
        }

        java.io.File help = new java.io.File("help", "index.html");
        if (!help.exists())
        {
            Lang.showMesg(null, LangRes.P30F7A10, "");
            return;
        }
        try
        {
            java.awt.Desktop.getDesktop().browse(help.toURI());
        }
        catch (java.io.IOException exp)
        {
            Logs.exception(exp);
        }
    }

    private void viewItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        changeView(isOsTray ? "guid" : "icon");
    }

    private void mainItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        showViewPtn(ConsEnv.VIEW_MAIN);
    }

    private void normItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        showViewPtn(ConsEnv.VIEW_NORM);
    }

    private void miniItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        showViewPtn(ConsEnv.VIEW_MINI);
    }

    private void updtItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.JFrame form = null;
        if (getCurrForm().isVisible())
        {
            form = getCurrForm();
        }
        try
        {
            boolean b = Util.checkUpdate(ConsEnv.SOFTCODE, ConsEnv.VERSIONS);
            if (b)
            {
                if (Lang.showFirm(form, LangRes.P30F7A12, "检测到新版本，现在要下载吗？") == javax.swing.JOptionPane.YES_OPTION)
                {
                    Desk.browse(ConsEnv.HOMEPAGE);
                }
            }
            else
            {
                Lang.showMesg(form, LangRes.P30F7A13, "您使用的已是最新版本。");
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(null, LangRes.P30F7A14, "");
        }
    }

    private void mailItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(null, LangRes.P30F7A11, "");
        }

        try
        {
            java.awt.Desktop.getDesktop().mail(new java.net.URI("mailto:" + ConsEnv.SOFTMAIL));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    private void siteItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(null, LangRes.P30F7A0F, "");
        }

        try
        {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(ConsEnv.HOMEPAGE));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    private void mlogItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(null, LangRes.P30F7A0F, "");
        }

        try
        {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(ConsEnv.MLOGSITE));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    private void showLastActionPerformed(java.awt.event.MouseEvent evt)
    {
        if (evt.getClickCount() < 2)
        {
            return;
        }

        showViewPtn(currPtn);
    }

    public void showViewPtn(int nextPtn)
    {
        if (getCurrForm().isVisible())
        {
            if (currPtn == nextPtn)
            {
                getCurrForm().toFront();
                return;
            }

            getCurrForm().setVisible(false);
            switch (nextPtn)
            {
                case ConsEnv.VIEW_MAIN:
                    showMainPtn();
                    break;
                case ConsEnv.VIEW_NORM:
                    showNormPtn();
                    break;
                case ConsEnv.VIEW_MINI:
                    showMiniPtn();
                    break;
                default:
                    break;
            }
            getCurrForm().setVisible(true);
            currPtn = nextPtn;
            return;
        }

        TrayPtn.nextPtn = nextPtn;
        getUserSign(ConsEnv.INT_SIGN_RS);
    }

    public static UserSign getUserSign(int view)
    {
        if (userSign == null)
        {
            userSign = new UserSign(coreMdl.getUserCfg(), safeMdl);
        }
        userSign.initView(view);
        userSign.initLang();
        userSign.initData();
        userSign.toFront();
        return userSign;
    }

    private void changeView(String ptn)
    {
        UserCfg uc = coreMdl.getUserCfg();
        // 下一步：显示为托盘图标
        if (ConsCfg.DEF_TRAY.equalsIgnoreCase(ptn))
        {
            try
            {
                java.awt.SystemTray.getSystemTray().add(this);
                md_TrayForm.setSize(1, 1);
                Lang.setWText(viewItem, LangRes.P30F960E, "显示为导航罗盘");
                uc.setCfg(ConsCfg.CFG_TRAY_PTN, "icon");
                isOsTray = true;
                return;
            }
            catch (java.awt.AWTException ex)
            {
                Logs.exception(ex);
            }
        }

        // 下一步：显示为导航罗盘
        md_TrayForm.pack();
        java.awt.SystemTray.getSystemTray().remove(this);
        Lang.setWText(viewItem, LangRes.P30F960D, "显示为托盘图标");
        uc.setCfg(ConsCfg.CFG_TRAY_PTN, "guid");

        if (formLoc == null)
        {
            String loc = uc.getCfg(ConsCfg.CFG_TRAY_LOC);
            if (com.magicpwd._util.Char.isValidate(loc))
            {
                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(loc);
                int x = -1;
                if (matcher.find())
                {
                    x = Integer.parseInt(matcher.group());
                }
                int y = -1;
                if (matcher.find())
                {
                    y = Integer.parseInt(matcher.group());
                }
                if (x >= 0 && y >= 0)
                {
                    formLoc = new java.awt.Point(x, y);
                }
            }

            if (formLoc == null)
            {
                java.awt.Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                formLoc = new java.awt.Point(size.width - 120 - md_TrayForm.getWidth(), 80);
            }
        }

        md_TrayForm.setLocation(formLoc);
        isOsTray = false;
    }

    public static void changeSkin(String lafClass)
    {
        boolean wasDecoratedByOS = !(TrayPtn.getCurrForm().isUndecorated());
        try
        {
            boolean isSystem = !com.magicpwd._util.Char.isValidate(lafClass) || ConsCfg.DEF_SKIN_SYS.equalsIgnoreCase(lafClass);
            if (isSystem)
            {
                lafClass = javax.swing.UIManager.getSystemLookAndFeelClassName();
            }
            javax.swing.UIManager.setLookAndFeel(lafClass);
            for (java.awt.Window window : java.awt.Window.getWindows())
            {
                javax.swing.SwingUtilities.updateComponentTreeUI(window);
            }

            boolean canBeDecoratedByLAF = javax.swing.UIManager.getLookAndFeel().getSupportsWindowDecorations();

            if (canBeDecoratedByLAF == wasDecoratedByOS)
            {
                boolean wasVisible = TrayPtn.getCurrForm().isVisible();

                TrayPtn.getCurrForm().setVisible(false);
                TrayPtn.getCurrForm().dispose();
                if (!canBeDecoratedByLAF || wasDecoratedByOS)
                {
                    TrayPtn.getCurrForm().setUndecorated(false);
                    TrayPtn.getCurrForm().getRootPane().setWindowDecorationStyle(0);
                }
                else
                {
                    TrayPtn.getCurrForm().setUndecorated(true);
                    TrayPtn.getCurrForm().getRootPane().setWindowDecorationStyle(1);
                }

                TrayPtn.getCurrForm().setVisible(wasVisible);
            }
            coreMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN_LOOK, isSystem ? ConsCfg.DEF_SKIN_SYS : lafClass);
        }
        catch (Exception exc)
        {
            Lang.showMesg(TrayPtn.getCurrForm(), null, exc.getLocalizedMessage());
        }
    }

    private static java.awt.Point getScreenLocation(java.awt.event.MouseEvent evt)
    {
        java.awt.Point cur = evt.getPoint();
        java.awt.Point dlg = md_TrayForm.getLocationOnScreen();
        return new java.awt.Point(dlg.x + cur.x, dlg.y + cur.y);
    }

    /**
     * @return the dbLocked
     */
    public static boolean isDbLocked()
    {
        return dbLocked;
    }

    /**
     * @param aDbLocked the dbLocked to set
     */
    public static void setDbLocked(boolean aDbLocked)
    {
        dbLocked = aDbLocked;
    }

    public static void setUserCfg(UserCfg userCfg)
    {
        if (coreMdl == null)
        {
            coreMdl = new CoreMdl();
        }
        coreMdl.setUserCfg(userCfg);
        coreMdl.preLoad();

        safeMdl = new SafeMdl(userCfg);
    }
    private java.awt.Point dragLoc;
    private java.awt.Point formLoc;
    private javax.swing.JPopupMenu trayMenu;
    private javax.swing.JMenuItem infoItem;
    private javax.swing.JMenuItem siteItem;
    private javax.swing.JMenuItem mlogItem;
    private javax.swing.JMenuItem helpItem;
    private javax.swing.JMenuItem mailItem;
    private javax.swing.JMenuItem updtItem;
    private javax.swing.JMenuItem mainItem;
    private javax.swing.JMenuItem normItem;
    private javax.swing.JMenuItem miniItem;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JMenuItem viewItem;
    private static MiniPtn mp_MiniPtn;
    private static NormPtn mp_NormPtn;
    private static MainPtn mp_MainPtn;
}
