package com.magicpwd.v;

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
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.UserMdl;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * 系统托盘
 * @author Amon
 */
public class TrayPtn extends TrayIcon implements IBackCall
{

    private static boolean isOsTray;
    private static int currPtn;
    private static int nextPtn;
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
            //trayForm.addWindowListener(new java.awt.event.WindowAdapter()
//            {
//                @Override
//                public void windowDeactivated(java.awt.event.WindowEvent evt)
//                {
//                      dialog.setVisible(false);
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
                    md_TrayForm.setVisible(false);
                }

                @Override
                public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt)
                {
                    md_TrayForm.setVisible(false);
                }
            };
        }

        //检查当前系统是否支持系统托盘
        if (SystemTray.isSupported())
        {
            try
            {
                //获取表示桌面托盘区的 SystemTray 实例。
                SystemTray.getSystemTray().add(this);
                isOsTray = true;
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                isOsTray = false;
            }
        }
        else
        {
            isOsTray = false;
        }

        java.awt.event.MouseListener ml = new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                showLastActionPerformed(evt);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                showJPopupMenu(evt);
            }
        };
        if (isOsTray)
        {
            addMouseListener(ml);
        }
        else
        {
            javax.swing.JLabel iconLbl = new javax.swing.JLabel();
            iconLbl.setIcon(Util.getIcon(0));
            iconLbl.addMouseListener(ml);
            iconLbl.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
            md_TrayForm.getContentPane().setLayout(new java.awt.BorderLayout());
            md_TrayForm.getContentPane().add(iconLbl);
            md_TrayForm.pack();

            java.awt.Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            md_TrayForm.setLocation(size.width - 120 - md_TrayForm.getWidth(), 80);
            md_TrayForm.setVisible(true);
        }

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

        trayMenu.addSeparator();

        exitItem = new javax.swing.JMenuItem();
        exitItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                //MagicPwd.exit(0);
                System.exit(0);
            }
        });
        trayMenu.add(exitItem);

        return true;
    }

    public boolean initData()
    {
        return true;
    }

    public boolean initLang()
    {
        Lang.setWText(infoItem, LangRes.P30F9601, "");
        Lang.setWText(helpItem, LangRes.P30F9602, "");

        Lang.setWText(mainItem, LangRes.P30F9603, "");
        Lang.setWText(normItem, LangRes.P30F9604, "");
        Lang.setWText(miniItem, LangRes.P30F9605, "");

        Lang.setWText(updtItem, LangRes.P30F9606, "");
        Lang.setWText(mailItem, LangRes.P30F9607, "");
        Lang.setWText(siteItem, LangRes.P30F9608, "");

        Lang.setWText(exitItem, LangRes.P30F9609, "");

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

        if ("cancel".equalsIgnoreCase(params[0]))
        {
            userSign = null;
            return false;
        }
        if (!ConsEnv.STR_SIGN_RS.equalsIgnoreCase(params[0]))
        {
            return false;
        }

        javax.swing.JFrame currForm = getCurrForm();
        switch (nextPtn)
        {
            case VIEW_MAIN:
                showMainPtn();
                break;
            case VIEW_NORM:
                showNormPtn();
                break;
            case VIEW_MINI:
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
        currPtn = VIEW_MAIN;
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
        currPtn = VIEW_NORM;
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
        currPtn = VIEW_MINI;
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

        GridMdl gm = UserMdl.getGridMdl();

        MailPtn mailPtn = new MailPtn();
        mailPtn.initView();
        mailPtn.initLang();
        List<I1S2> mailList = gm.wSelect(ConsDat.INDX_MAIL);
        mailPtn.initMail(mailList);
        if (mailList.size() < 1)
        {
            Lang.showMesg(mailDlg, null, "没有可用的邮件类型数据！");
            return;
        }
        List<I1S2> userList = gm.wSelect(ConsDat.INDX_TEXT);
        mailPtn.initUser(userList);
        if (userList.size() < 1)
        {
            Lang.showMesg(mailDlg, null, "没有可用的文本类型数据！");
            return;
        }
        List<I1S2> pwdsList = gm.wSelect(ConsDat.INDX_PWDS);
        mailPtn.initPwds(pwdsList);
        if (pwdsList.size() < 1)
        {
            Lang.showMesg(mailDlg, null, "没有可用的口令类型数据！");
            return;
        }
        if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(TrayPtn.getCurrForm(), mailPtn, "登录确认", JOptionPane.OK_CANCEL_OPTION))
        {
            return;
        }

        String mail = mailList.get(mailPtn.getMail()).getK();
        String user = userList.get(mailPtn.getUser()).getK();
        String pwds = pwdsList.get(mailPtn.getPwds()).getK();

        String host = mail.substring(mail.indexOf('@') + 1);
        if (!Util.isValidate(host))
        {
            return;
        }
        String type = UserMdl.getMailCfg().getCfg(host + ".type");
        if (!Util.isValidate(type))
        {
            Lang.showMesg(mailDlg, null, "查找不到对应的服务信息，如有疑问请与作者联系！");
            return;
        }

        final Connect connect = new Connect(type, mail, pwds);
        connect.setUsername(user);

        // 读取服务器配置
        String cfg = UserMdl.getMailCfg().getCfg(type + '.' + host);
        if (!Util.isValidate(cfg))
        {
            return;
        }

        // 服务器地址
        String[] arr = (cfg + ":::false").split("[:]");
        connect.setHost(arr[0]);

        // 服务器端口
        cfg = arr[1].trim();
        if (Util.isValidateInteger(cfg))
        {
            connect.setPort(Integer.parseInt(cfg));
        }

        // 是否需要身份认证
        connect.setAuth("true".equalsIgnoreCase(arr[2].trim().toLowerCase()));
        // 是否需要安全认证
        connect.setJssl("true".equalsIgnoreCase(arr[3].trim().toLowerCase()));

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

    private void showJPopupMenu(java.awt.event.MouseEvent evt)
    {
        if (!evt.isPopupTrigger() || trayMenu == null)
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
            md_TrayForm.setVisible(true);
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
        Lang.showMesg(null, "", "");
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

    private void mainItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        nextPtn = VIEW_MAIN;
        showViewPtn();
    }

    private void normItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        nextPtn = VIEW_NORM;
        showViewPtn();
    }

    private void miniItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        nextPtn = VIEW_MINI;
        showViewPtn();
    }

    private void updtItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        try
        {
            boolean b = Util.checkUpdate(ConsEnv.SOFTCODE, ConsEnv.VERSIONS);
            if (b)
            {
                if (Lang.showFirm(null, LangRes.P30F7A12, "检测到新版本，现在要下载吗？") == javax.swing.JOptionPane.YES_OPTION)
                {
                    Desk.browse(ConsEnv.HOMEPAGE);
                }
            }
            else
            {
                Lang.showMesg(null, LangRes.P30F7A13, "您使用的已是最新版本。");
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

    private void showLastActionPerformed(java.awt.event.MouseEvent evt)
    {
        if (evt.getClickCount() < 2)
        {
            return;
        }

        showViewPtn();
    }

    private void showViewPtn()
    {
        if (currPtn == nextPtn)
        {
            getCurrForm().toFront();
            return;
        }
        if (getCurrForm().isVisible())
        {
            getCurrForm().setVisible(false);
            switch (nextPtn)
            {
                case VIEW_MAIN:
                    showMainPtn();
                    break;
                case VIEW_NORM:
                    showNormPtn();
                    break;
                case VIEW_MINI:
                    showMiniPtn();
                    break;
                default:
                    break;
            }
            getCurrForm().setVisible(true);
            currPtn = nextPtn;
            return;
        }

        userSign = new UserSign();
        userSign.setBackCall(this);
        userSign.initView(ConsEnv.INT_SIGN_RS);
        userSign.initLang();
        userSign.initData();
        userSign.toFront();
    }

    public static void changeSkin(String lafClass)
    {
        boolean wasDecoratedByOS = !(TrayPtn.getCurrForm().isUndecorated());
        try
        {
            boolean isSystem = !Util.isValidate(lafClass) || ConsCfg.DEF_SKIN.equalsIgnoreCase(lafClass);
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
            UserMdl.getUserCfg().setCfg(ConsCfg.CFG_SKIN, isSystem ? ConsCfg.DEF_SKIN : lafClass);
        }
        catch (Exception exc)
        {
            Lang.showMesg(TrayPtn.getCurrForm(), null, exc.getLocalizedMessage());
        }
    }
    private javax.swing.JPopupMenu trayMenu;
    private javax.swing.JMenuItem infoItem;
    private javax.swing.JMenuItem siteItem;
    private javax.swing.JMenuItem helpItem;
    private javax.swing.JMenuItem mailItem;
    private javax.swing.JMenuItem updtItem;
    private javax.swing.JMenuItem mainItem;
    private javax.swing.JMenuItem normItem;
    private javax.swing.JMenuItem miniItem;
    private javax.swing.JMenuItem exitItem;
    private static MiniPtn mp_MiniPtn;
    private static NormPtn mp_NormPtn;
    private static MainPtn mp_MainPtn;
    private static final int VIEW_MAIN = 0;
    private static final int VIEW_NORM = 1;
    private static final int VIEW_MINI = 2;
}
