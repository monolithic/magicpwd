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
package com.magicpwd.v.tray;

import com.magicpwd.__a.AFrame;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._enum.AppView;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._enum.AuthLog;
import com.magicpwd._user.UserDto;
import com.magicpwd._user.UserPtn;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.db.DBAccess;
import com.magicpwd.m.MpwdMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.maoc.MaocPtn;
import com.magicpwd.v.mgtd.MgtdPtn;
import com.magicpwd.v.mpad.MpadPtn;
import com.magicpwd.v.mpwd.MpwdPtn;
import com.magicpwd.v.mruc.MrucPtn;
import com.magicpwd.v.mwiz.MwizPtn;

/**
 * 系统托盘
 * @author Amon
 */
public class TrayPtn implements IBackCall<UserDto>, java.awt.event.MouseListener, java.awt.event.MouseMotionListener
{

    private static boolean isOsTray;
    private static AppView currPtn;
    private static AppView nextPtn;
    private UserMdl userMdl;
    private UserPtn userPtn;
    private AFrame mfCurrForm;
    private javax.swing.JWindow mwTrayForm;
    private javax.swing.event.PopupMenuListener listener;
    private MenuPtn menuPtn;

    public TrayPtn(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public boolean initView()
    {
        if (java.awt.SystemTray.isSupported())
        {
            // 托盘视图初始化
            trayIcon = new java.awt.TrayIcon(Bean.getLogo(java.awt.SystemTray.getSystemTray().getTrayIconSize().height));
            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(this);
        }

        // 罗盘视图初始化
        if (mwTrayForm == null)
        {
            mwTrayForm = new javax.swing.JWindow()
            {

                @Override
                protected void processWindowEvent(java.awt.event.WindowEvent evt)
                {
                    if (evt.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
                    {
                        return;
                    }
                    if (evt.getID() == java.awt.event.WindowEvent.WINDOW_DEACTIVATED)
                    {
                        setVisible(false);
                    }
                    super.processWindowEvent(evt);
                }
            };
            mwTrayForm.setAlwaysOnTop(true);
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
                    setVisible(false);
                }

                @Override
                public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt)
                {
                    setVisible(false);
                }
            };
        }

        javax.swing.JLabel iconLbl = new javax.swing.JLabel();
        iconLbl.setIcon(new javax.swing.ImageIcon(Bean.getLogo(24)));
        iconLbl.addMouseListener(this);
        iconLbl.addMouseMotionListener(this);
        mwTrayForm.getContentPane().setLayout(new java.awt.BorderLayout());
        mwTrayForm.getContentPane().add(iconLbl);
        mwTrayForm.setVisible(true);

        trayMenu = new javax.swing.JPopupMenu();

        return true;
    }

    public boolean initLang()
    {
        trayIcon.setToolTip(ConsEnv.SOFTNAME + ' ' + ConsEnv.VERSIONS);
        return true;
    }

    public boolean initData()
    {
        // 右键菜单初始化
        try
        {
            menuPtn = new MenuPtn(this, userMdl);
            menuPtn.loadData(new java.io.File(ConsEnv.DIR_DAT, "tray.xml"));
            menuPtn.getPopMenu("tray", trayMenu);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }

        changeView(userMdl.getCfg(ConsCfg.CFG_TRAY_PTN, "guid"));
        return true;
    }

    @Override
    public boolean callBack(String options, UserDto object)
    {
        switch (AuthLog.valueOf(options))
        {
            // 用户登录
            case signIn:
            {
                // 设置软件界面风格
                showPtn(MpwdMdl.getAppView());

                initView();
                initLang();
                initData();
                mfCurrForm.toFront();
                mfCurrForm.requestFocus();
                return true;
            }

            // 用户注册
            case signUp:
            {
                // 设置软件界面风格
                showPtn(MpwdMdl.getAppView());

                initView();
                initLang();
                initData();
                mfCurrForm.toFront();
                mfCurrForm.requestFocus();

//            mfCurrForm.initDemo();
                return true;
            }

            // 屏幕解锁
            case signLs:
            {
                return true;
            }

            // 口令找回
            case signFp:
            {
                return true;
            }

            // 身份认证
            case signRs:
            {
                return false;
            }
        }

        showPtn(nextPtn);

        javax.swing.JFrame currForm = getCurrForm();
        if (currForm.getState() != java.awt.Frame.NORMAL)
        {
            currForm.setState(java.awt.Frame.NORMAL);
        }
        currForm.toFront();
        return true;
    }

    private void showPtn(AppView view)
    {
        switch (view)
        {
            case mpwd:
                showMpwdPtn();
                return;
            case mwiz:
                showMwizPtn();
                return;
            case mpad:
                showMpadPtn();
                return;
            case maoc:
                showMaocPtn();
                return;
            case mruc:
                showMrucPtn();
                return;
            case mgtd:
                showMgtdPtn();
                return;
        }
    }

    public void setVisible(boolean visible)
    {
        if (isOsTray)
        {
            mwTrayForm.setVisible(visible);
        }
    }

    public AFrame getCurrForm()
    {
        return mfCurrForm;
    }

    public MpwdPtn getMpwdPtn()
    {
        return mp_MpwdPtn;
    }

    private void showMpwdPtn()
    {
        if (mp_MpwdPtn == null)
        {
            mp_MpwdPtn = new MpwdPtn(this, userMdl);
            mp_MpwdPtn.initView();
            mp_MpwdPtn.initLang();
            mp_MpwdPtn.initData();
        }
        else
        {
            mp_MpwdPtn.setVisible(true);
        }
        mp_MpwdPtn.showData();

        mfCurrForm = mp_MpwdPtn;
        currPtn = AppView.mpwd;
    }

    public MwizPtn getMwizPtn()
    {
        return mp_MwizPtn;
    }

    private void showMwizPtn()
    {
        if (mp_MwizPtn == null)
        {
            mp_MwizPtn = new MwizPtn(this, userMdl);
            mp_MwizPtn.initView();
            mp_MwizPtn.initLang();
            mp_MwizPtn.initData();
        }
        else
        {
            mp_MwizPtn.setVisible(true);
        }
        mp_MwizPtn.showData();

        mfCurrForm = mp_MwizPtn;
        currPtn = AppView.mwiz;
    }

    public MpadPtn getMpadPtn()
    {
        return mp_MpadPtn;
    }

    private void showMpadPtn()
    {
        if (mp_MpadPtn == null)
        {
            mp_MpadPtn = new MpadPtn(this, userMdl);
            mp_MpadPtn.initView();
            mp_MpadPtn.initLang();
            mp_MpadPtn.initData();
        }
        else
        {
            mp_MpadPtn.setVisible(true);
        }
        mp_MpadPtn.showData();

        mfCurrForm = mp_MpadPtn;
        currPtn = AppView.mpad;
    }

    public MaocPtn getMaocPtn()
    {
        return mp_MaocPtn;
    }

    private void showMaocPtn()
    {
        if (mp_MaocPtn == null)
        {
            mp_MaocPtn = new MaocPtn(this, userMdl);
            mp_MaocPtn.initView();
            mp_MaocPtn.initLang();
            mp_MaocPtn.initData();
        }
        else
        {
            mp_MaocPtn.setVisible(true);
        }
        mp_MaocPtn.showData();

        mfCurrForm = mp_MaocPtn;
        currPtn = AppView.maoc;
    }

    public MrucPtn getMrucPtn()
    {
        return mp_MrucPtn;
    }

    private void showMrucPtn()
    {
        if (mp_MrucPtn == null)
        {
            mp_MrucPtn = new MrucPtn(this, userMdl);
            mp_MrucPtn.initView();
            mp_MrucPtn.initLang();
            mp_MrucPtn.initData();
        }
        else
        {
            mp_MrucPtn.setVisible(true);
        }
        mp_MrucPtn.showData();

        mfCurrForm = mp_MrucPtn;
        currPtn = AppView.mruc;
    }

    public MgtdPtn getMgtdPtn()
    {
        return mp_MgtdPtn;
    }

    private void showMgtdPtn()
    {
        if (mp_MgtdPtn == null)
        {
            mp_MgtdPtn = new MgtdPtn(this, userMdl);
            mp_MgtdPtn.initView();
            mp_MgtdPtn.initLang();
            mp_MgtdPtn.initData();
        }
        else
        {
            mp_MgtdPtn.setVisible(true);
        }
        mp_MgtdPtn.showData();

        mfCurrForm = mp_MgtdPtn;
        currPtn = AppView.mgtd;
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
            formLoc = mwTrayForm.getLocation();
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
            java.awt.Dimension dlgSize = mwTrayForm.getSize();
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
            mwTrayForm.setLocation(tmp);
            userMdl.setCfg(ConsCfg.CFG_TRAY_LOC, "[" + tmp.x + "," + tmp.y + "]");
        }
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent evt)
    {
    }

    public java.io.File endSave()
    {
        try
        {
            DBAccess.locked = true;

            if (mp_MpwdPtn != null)
            {
//                mp_MainPtn.setVisible(false);
                mp_MpwdPtn.endSave();
            }
            if (mp_MwizPtn != null)
            {
//                mp_NormPtn.setVisible(false);
                mp_MwizPtn.endSave();
            }
            if (mp_MpadPtn != null)
            {
//                mp_MiniPtn.setVisible(false);
                mp_MpadPtn.endSave();
            }

            userMdl.saveCfg();

            new DBAccess().shutdown();

            java.io.File backFile = Util.nextBackupFile(userMdl.getDumpDir(), userMdl.getDumpCnt());
            Jzip.doZip(backFile, new java.io.File(userMdl.getDataDir()));
            return backFile;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return null;
        }
        finally
        {
            DBAccess.locked = false;
        }
    }

    public void endExit(int status)
    {
        endSave();
        Logs.end();
        System.exit(status);
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
            mwTrayForm.setLocation(x, y);

            // trayMenu.setInvoker(trayMenu);
            trayMenu.show(mwTrayForm.getContentPane(), 0, 0);
            mwTrayForm.toFront();
        }
        else
        {
            trayMenu.show(mwTrayForm, 0, mwTrayForm.getHeight());
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

    public void showViewPtn(AppView nextPtn)
    {
        // 显示登录
        if (getCurrForm() == null)
        {
            userPtn = new UserPtn(userMdl, this);
            userPtn.setBackCall(this);
            userPtn.initView(userMdl.getCfg(ConsCfg.CFG_USER, "").trim().length() > 0 ? AuthLog.signIn : AuthLog.signUp);
            userPtn.initLang();
            userPtn.initData();
            userPtn.setVisible(true);
            return;
        }

        //TrayPtn.setUserCfg(cfg);
        if (getCurrForm().isVisible())
        {
            if (currPtn == nextPtn)
            {
                getCurrForm().toFront();
                return;
            }

            getCurrForm().setVisible(false);
            showViewPtn(nextPtn);
            currPtn = nextPtn;
            return;
        }

        TrayPtn.nextPtn = nextPtn;
        getUserPtn(AuthLog.signRs, this);
    }

    public UserPtn getUserPtn(AuthLog view, IBackCall<UserDto> call)
    {
        if (userPtn == null)
        {
            userPtn = new UserPtn(userMdl, this);
        }
        UserPtn ptn = (getCurrForm() != null && getCurrForm().isVisible()) ? new UserPtn(userMdl, getCurrForm()) : userPtn;
        ptn.setBackCall(call);
        ptn.initView(view);
        ptn.initLang();
        ptn.initData();
        ptn.setVisible(true);
        ptn.toFront();
        return ptn;
    }

    public void changeView()
    {
        String view = userMdl.getCfg(ConsCfg.CFG_TRAY_PTN, "guid");
        changeView(ConsCfg.DEF_TRAY.equals(view) ? "guid" : ConsCfg.DEF_TRAY);
    }

    public void changeView(String view)
    {
        javax.swing.AbstractButton button = menuPtn.getButton("viewPtn");
        if (!java.awt.SystemTray.isSupported())
        {
            if (button != null)
            {
                button.setEnabled(false);
            }
            return;
        }

        // 下一步：显示为托盘图标
        if (ConsCfg.DEF_TRAY.equalsIgnoreCase(view))
        {
            try
            {
                java.awt.SystemTray.getSystemTray().add(trayIcon);
                mwTrayForm.setSize(1, 1);
                if (button != null)
                {
                    Lang.setWText(button, LangRes.P30F960E, "显示为导航罗盘");
                }
                userMdl.setCfg(ConsCfg.CFG_TRAY_PTN, "icon");
                isOsTray = true;
                return;
            }
            catch (java.awt.AWTException ex)
            {
                Logs.exception(ex);
            }
        }

        // 下一步：显示为导航罗盘
        mwTrayForm.pack();
        java.awt.SystemTray.getSystemTray().remove(trayIcon);
        if (button != null)
        {
            Lang.setWText(button, LangRes.P30F960D, "显示为托盘图标");
        }
        userMdl.setCfg(ConsCfg.CFG_TRAY_PTN, "guid");

        if (formLoc == null)
        {
            java.awt.Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

            String loc = userMdl.getCfg(ConsCfg.CFG_TRAY_LOC);
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
                if (x >= 0 && x < size.width && y >= 0 && y < size.height)
                {
                    formLoc = new java.awt.Point(x, y);
                }
            }

            if (formLoc == null)
            {
                formLoc = new java.awt.Point(size.width - 120 - mwTrayForm.getWidth(), 80);
            }
        }

        mwTrayForm.setLocation(formLoc);
        isOsTray = false;
    }

    public void changeSkin(String lafClass)
    {
        boolean wasDecoratedByOS = !(getCurrForm().isUndecorated());
        try
        {
            boolean isSystem = !com.magicpwd._util.Char.isValidate(lafClass) || ConsCfg.DEF_SKIN_LOOK_SYS.equalsIgnoreCase(lafClass);
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
                boolean wasVisible = getCurrForm().isVisible();

                getCurrForm().setVisible(false);
                getCurrForm().dispose();
                if (!canBeDecoratedByLAF || wasDecoratedByOS)
                {
                    getCurrForm().setUndecorated(false);
                    getCurrForm().getRootPane().setWindowDecorationStyle(0);
                }
                else
                {
                    getCurrForm().setUndecorated(true);
                    getCurrForm().getRootPane().setWindowDecorationStyle(1);
                }

                getCurrForm().setVisible(wasVisible);
            }
            userMdl.setLook(isSystem ? ConsCfg.DEF_SKIN_LOOK_SYS : lafClass);
        }
        catch (Exception exc)
        {
            Lang.showMesg(getCurrForm(), null, exc.getLocalizedMessage());
        }
    }

    private java.awt.Point getScreenLocation(java.awt.event.MouseEvent evt)
    {
        java.awt.Point cur = evt.getPoint();
        java.awt.Point dlg = mwTrayForm.getLocationOnScreen();
        return new java.awt.Point(dlg.x + cur.x, dlg.y + cur.y);
    }

    public void showTips(String title, String tips)
    {
        if (trayIcon != null)
        {
            trayIcon.displayMessage(title, tips, java.awt.TrayIcon.MessageType.INFO);
        }
    }
    private static MpwdPtn mp_MpwdPtn;
    private static MwizPtn mp_MwizPtn;
    private static MpadPtn mp_MpadPtn;
    private static MrucPtn mp_MrucPtn;
    private static MaocPtn mp_MaocPtn;
    private static MgtdPtn mp_MgtdPtn;
    private java.awt.Point dragLoc;
    private java.awt.Point formLoc;
    private java.awt.TrayIcon trayIcon;
    private javax.swing.JPopupMenu trayMenu;
}
