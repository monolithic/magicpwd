/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

import com.magicpwd.MagicPwd;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Desk;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.x.MdiDialog;
import java.awt.SystemTray;
import java.awt.TrayIcon;

/**
 * @author Amon
 */
public class TrayPtn extends TrayIcon
{

    private static TrayPtn trayPtn;
    private static boolean isOsTray;
    private static javax.swing.JDialog trayForm;
    private static javax.swing.event.PopupMenuListener listener;

    private TrayPtn()
    {
        super(Util.getLogo());
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
        if (trayForm == null)
        {
            trayForm = new javax.swing.JDialog();
            trayForm.setUndecorated(true);
            trayForm.setAlwaysOnTop(true);
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
                    trayForm.setVisible(false);
                }

                @Override
                public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt)
                {
                    trayForm.setVisible(false);
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
                if (evt.getClickCount() == 2)
                {
                }
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
            trayForm.getContentPane().setLayout(new java.awt.BorderLayout());
            trayForm.getContentPane().add(iconLbl);
            trayForm.pack();

            java.awt.Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            trayForm.setLocation(size.width - 120 - trayForm.getWidth(), 80);
            trayForm.setVisible(true);
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

        setToolTip("哈哈");
        return true;
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
            java.awt.Dimension size = trayMenu.getPreferredSize();
            trayForm.setLocation(evt.getX() - size.width, evt.getY() - size.height);
            // trayMenu.setInvoker(trayMenu);
            trayForm.setVisible(true);
            trayMenu.show(trayForm.getContentPane(), 0, 0);
            trayForm.toFront();
        }
        else
        {
            trayMenu.show(trayForm, 0, trayForm.getHeight());
        }
    }

    private void infoItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        MdiDialog.getInstance(null).showProp(ConsEnv.PROP_INFO, true);
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
        UserSign us = new UserSign(MagicPwd.getCurrForm());
        us.setConfrmBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                MagicPwd.showMainPtn();
                MagicPwd.getCurrForm().setVisible(true);
                MagicPwd.getCurrForm().setState(javax.swing.JFrame.NORMAL);
                return true;
            }
        });
        us.initView(ConsEnv.SIGN_RS);
        us.initLang();
        us.initData();
    }

    private void normItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        UserSign us = new UserSign(MagicPwd.getCurrForm());
        us.setConfrmBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                MagicPwd.showNormPtn();
                MagicPwd.getCurrForm().setVisible(true);
                MagicPwd.getCurrForm().setState(javax.swing.JFrame.NORMAL);
                return true;
            }
        });
        us.initView(ConsEnv.SIGN_RS);
        us.initLang();
        us.initData();
    }

    private void miniItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        UserSign us = new UserSign(MagicPwd.getCurrForm());
        us.setConfrmBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                MagicPwd.showMiniPtn();
                MagicPwd.getCurrForm().setVisible(true);
                MagicPwd.getCurrForm().setState(javax.swing.JFrame.NORMAL);
                return true;
            }
        });
        us.initView(ConsEnv.SIGN_RS);
        us.initLang();
        us.initData();
    }

    private void updtItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        try
        {
            boolean b = Util.checkUpdate(ConsEnv.SOFTHASH, ConsEnv.VERSIONS);
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
}
