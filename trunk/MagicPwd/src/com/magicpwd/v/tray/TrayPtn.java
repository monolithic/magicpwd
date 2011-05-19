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

import com.magicpwd.__a.AMpwdPtn;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._enum.AppView;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.LangRes;
import com.magicpwd._enum.AuthLog;
import com.magicpwd._user.UserDto;
import com.magicpwd._user.UserPtn;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.db.DBAccess;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.maoc.MaocPtn;
import com.magicpwd.v.mgtd.MgtdPtn;
import com.magicpwd.v.mpad.MpadPtn;
import com.magicpwd.v.mexp.MexpPtn;
import com.magicpwd.v.mruc.MrucPtn;
import com.magicpwd.v.mwiz.MwizPtn;

/**
 * 系统托盘
 * @author Amon
 */
public class TrayPtn implements IBackCall<AuthLog, UserDto>
{

    private static boolean isOsTray;
    private static AppView currPtn;
    private static AppView nextPtn;
    private UserMdl userMdl;
    private UserPtn userPtn;
    private AMpwdPtn mfCurrForm;
    private javax.swing.JWindow trayForm;
//    private TrayWnd mwTrayForm;
    private javax.swing.event.PopupMenuListener listener;
    private MenuPtn menuPtn;
    private java.util.Map<AppView, AMpwdPtn> ptnList;

    public TrayPtn(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public boolean initView()
    {
        trayForm = new javax.swing.JWindow();
        trayMenu = new javax.swing.JPopupMenu();

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

        return true;
    }

    public boolean initLang()
    {
        return true;
    }

    public boolean initData()
    {
        ptnList = new java.util.EnumMap<AppView, AMpwdPtn>(AppView.class);

        // 右键菜单初始化
        try
        {
            menuPtn = new MenuPtn(this, userMdl);
            menuPtn.loadData(new java.io.File(userMdl.getDataDir(), "tray.xml"));
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
    public boolean callBack(AuthLog options, UserDto object)
    {
        switch (options)
        {
            // 用户登录
            case signIn:
            {
                initLang();
                initData();

                // 设置软件界面风格
                showNextPtn(userMdl.getAppView());

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
                showNextPtn(userMdl.getAppView());

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

        showNextPtn(nextPtn);

        javax.swing.JFrame currForm = getCurrPtn();
        if (currForm.getState() != java.awt.Frame.NORMAL)
        {
            currForm.setState(java.awt.Frame.NORMAL);
        }
        currForm.toFront();
        return true;
    }

    private void showNextPtn(AppView nextPtn)
    {
        AMpwdPtn ptn = ptnList.get(nextPtn);
        if (ptn == null)
        {
            switch (nextPtn)
            {
                case mexp:
                    ptn = new MexpPtn(this, userMdl);
                    break;
                case mwiz:
                    ptn = new MwizPtn(this, userMdl);
                    break;
                case mpad:
                    ptn = new MpadPtn(this, userMdl);
                    break;
                case maoc:
                    ptn = new MaocPtn(this, userMdl);
                    break;
                case mruc:
                    ptn = new MrucPtn(this, userMdl);
                    break;
                case mgtd:
                    ptn = new MgtdPtn(this, userMdl);
                    break;
            }
            ptn.initView();
            ptn.initLang();
            ptn.initData();
        }
        else
        {
            ptn.setVisible(true);
        }
        ptn.showData();

        mfCurrForm = ptn;
        currPtn = nextPtn;
    }

    public void setVisible(boolean visible)
    {
        if (isOsTray)
        {
            trayForm.setVisible(visible);
        }
    }

    public AMpwdPtn getCurrPtn()
    {
        return mfCurrForm;
    }

    public AMpwdPtn getPtn(AppView appView)
    {
        return ptnList.get(appView);
    }

    public java.io.File endSave()
    {
        try
        {
            DBAccess.locked = true;

            AMpwdPtn ptn;
            for (AppView appView : ptnList.keySet())
            {
                ptn = ptnList.get(appView);
                if (ptn != null)
                {
                    ptn.endSave();
                }
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

    public void hideJPopupMenu()
    {
        trayMenu.setVisible(false);
    }

    public void showJPopupMenu(java.awt.event.MouseEvent evt)
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
            trayForm.setLocation(x, y);

            // trayMenu.setInvoker(trayMenu);
            trayMenu.show(trayForm.getContentPane(), 0, 0);
            trayForm.toFront();
        }
        else
        {
            trayMenu.show(trayForm, 0, trayForm.getHeight());
        }
    }

    public void showLastPtn()
    {
        showViewPtn(currPtn);
    }

    public void showViewPtn(AppView nextPtn)
    {
        // 显示登录
        if (nextPtn == AppView.user)
        {
            userPtn = new UserPtn(userMdl, this);
            userPtn.initView(AuthLog.signIn);
            userPtn.initLang();
            userPtn.initData();
            userPtn.setVisible(true);
            userPtn.setBackCall(this);
            return;
        }

        //TrayPtn.setUserCfg(cfg);
        if (mfCurrForm.isVisible())
        {
            if (currPtn == nextPtn)
            {
                mfCurrForm.toFront();
                return;
            }

            mfCurrForm.setVisible(false);
            showNextPtn(nextPtn);
            return;
        }

        getUserPtn(AuthLog.signRs, this);
    }

    public UserPtn getUserPtn(AuthLog view, IBackCall<AuthLog, UserDto> call)
    {
        if (userPtn == null)
        {
            userPtn = new UserPtn(userMdl, this);
        }
        UserPtn ptn = (getCurrPtn() != null && getCurrPtn().isVisible()) ? new UserPtn(userMdl, getCurrPtn()) : userPtn;
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
            trayForm.setSize(1, 1);
            if (button != null)
            {
                Lang.setWText(button, LangRes.P30F960E, "显示为导航罗盘");
            }
            userMdl.setCfg(ConsCfg.CFG_TRAY_PTN, "icon");
            isOsTray = true;
            return;
        }

        // 下一步：显示为导航罗盘
        trayForm.pack();
        if (button != null)
        {
            Lang.setWText(button, LangRes.P30F960D, "显示为托盘图标");
        }
        userMdl.setCfg(ConsCfg.CFG_TRAY_PTN, "guid");

        isOsTray = false;
    }

    public void showTips(String title, String message)
    {
    }

    public void changeSkin(String lafClass)
    {
        boolean wasDecoratedByOS = !(getCurrPtn().isUndecorated());
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
                boolean wasVisible = getCurrPtn().isVisible();

                getCurrPtn().setVisible(false);
                getCurrPtn().dispose();
                if (!canBeDecoratedByLAF || wasDecoratedByOS)
                {
                    getCurrPtn().setUndecorated(false);
                    getCurrPtn().getRootPane().setWindowDecorationStyle(0);
                }
                else
                {
                    getCurrPtn().setUndecorated(true);
                    getCurrPtn().getRootPane().setWindowDecorationStyle(1);
                }

                getCurrPtn().setVisible(wasVisible);
            }
            userMdl.setLook(isSystem ? ConsCfg.DEF_SKIN_LOOK_SYS : lafClass);
        }
        catch (Exception exc)
        {
            Lang.showMesg(getCurrPtn(), null, exc.getLocalizedMessage());
        }
    }
    private javax.swing.JPopupMenu trayMenu;
}
