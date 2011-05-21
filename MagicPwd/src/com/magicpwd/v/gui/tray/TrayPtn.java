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
package com.magicpwd.v.gui.tray;

import com.magicpwd.__a.AMpwdPtn;
import com.magicpwd.__i.IBackCall;
import com.magicpwd.__i.ITrayView;
import com.magicpwd._comn.apps.FileLocker;
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
import com.magicpwd._util.Skin;
import com.magicpwd._util.Util;
import com.magicpwd.d.db.DBAccess;
import com.magicpwd.m.MpwdMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.AmonFF;
import com.magicpwd.v.gui.MenuPtn;
import com.magicpwd.v.gui.maoc.MaocPtn;
import com.magicpwd.v.gui.mgtd.MgtdPtn;
import com.magicpwd.v.gui.mpad.MpadPtn;
import com.magicpwd.v.gui.mexp.MexpPtn;
import com.magicpwd.v.gui.mruc.MrucPtn;
import com.magicpwd.v.gui.mwiz.MwizPtn;

/**
 * 系统托盘
 * @author Amon
 */
public class TrayPtn implements IBackCall<AuthLog, UserDto>
{

    private static boolean isOsTray;
    private static AppView currPtn;
    private static AppView nextPtn;
    private MpwdMdl mpwdMdl;
    private UserMdl userMdl;
    private UserPtn userPtn;
    private MenuPtn menuPtn;
    private AMpwdPtn mpwdPtn;
    private ITrayView trayPtn;
    private javax.swing.JWindow trayForm;
    private javax.swing.event.PopupMenuListener listener;
    private java.util.Map<AppView, AMpwdPtn> ptnList;

    public TrayPtn(MpwdMdl mpwdMdl)
    {
        this.mpwdMdl = mpwdMdl;
    }

    @Override
    public boolean callBack(AuthLog options, UserDto object)
    {
        switch (options)
        {
            // 用户登录
            case signIn:
            {
                initView();
                initLang();
                initData();

                // 设置软件界面风格
                showNextPtn(userMdl.getAppView());
                break;
            }

            // 屏幕解锁
            case signLs:
            {
                break;
            }

            // 口令找回
            case signFp:
            {
                break;
            }

            // 身份认证
            case signRs:
            {
                break;
            }

            default:
            {
                break;
            }
        }

        if (mpwdPtn.getState() != java.awt.Frame.NORMAL)
        {
            mpwdPtn.setState(java.awt.Frame.NORMAL);
        }
        mpwdPtn.toFront();
        return true;
    }

    public boolean init()
    {
//        java.awt.KeyboardFocusManager.setCurrentKeyboardFocusManager(new KFManager());
        userMdl = new UserMdl(mpwdMdl);

        try
        {
            javax.swing.SwingUtilities.invokeLater(new Runnable()
            {

                @Override
                public void run()
                {
                    signIn();
                }
            });
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        loadPre();
        return true;
    }

    private void signIn()
    {
        // 语言资源加载
        Lang.loadLang(mpwdMdl.getAppLang());

        // 扩展皮肤加载
        Skin.loadLook(mpwdMdl.getAppSkin());

        userPtn = new UserPtn(userMdl);
        userPtn.setBackCall(this);
        userPtn.initView(AuthLog.signIn);
        userPtn.initLang();
        userPtn.initData();
        userPtn.setVisible(true);
    }

    private static void loadPre()
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        Bean.getNone();
        Bean.getLogo(16);
        AMpwdPtn.loadPre();

        // 扩展库加载
        loadExt();
    }

    private static void loadExt()
    {
        java.io.File file = new java.io.File(ConsEnv.DIR_EXT);
        if (file == null || !file.exists() || !file.isDirectory() || !file.canRead())
        {
            return;
        }

        java.io.File jars[] = file.listFiles(new AmonFF(".+\\.jar$", true));
        if (jars != null && jars.length > 0)
        {
            try
            {
                // 加载扩展库
                Bean.loadJar(jars);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
    }

    private boolean initView()
    {
        // 启动实例判断
        FileLocker fl = new FileLocker(new java.io.File("tmp", "mwpd.lck"));
        if (!fl.tryLock())
        {
            javax.swing.JOptionPane.showMessageDialog(null, "已经有一个《魔方密码》实例处于运行状态！", "友情提示", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

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
                    if (isOsTray)
                    {
                        trayForm.setVisible(false);
                    }
                }

                @Override
                public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt)
                {
                    if (isOsTray)
                    {
                        trayForm.setVisible(false);
                    }
                }
            };
        }

        return true;
    }

    private boolean initLang()
    {
        return true;
    }

    private boolean initData()
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

        mpwdPtn = ptn;
        currPtn = nextPtn;
    }

    public AMpwdPtn getMpwdPtn()
    {
        return mpwdPtn;
    }

    public void setMpwdPtn(AMpwdPtn mpwdPtn)
    {
        this.mpwdPtn = mpwdPtn;
    }

    public AMpwdPtn getMpwdPtn(AppView appView)
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
        if (mpwdPtn == null)
        {
            return;
        }

        if (mpwdPtn.isVisible())
        {
            if (currPtn == nextPtn)
            {
                mpwdPtn.toFront();
                return;
            }

            mpwdPtn.setVisible(false);
            showNextPtn(nextPtn);
            return;
        }

        getUserPtn(AuthLog.signRs, this);
    }

    public UserPtn getUserPtn(AuthLog view, IBackCall<AuthLog, UserDto> call)
    {
        if (userPtn == null)
        {
            userPtn = new UserPtn(userMdl);
        }
        UserPtn ptn = (getMpwdPtn() != null && getMpwdPtn().isVisible()) ? new UserPtn(userMdl, getMpwdPtn()) : userPtn;
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
            if (trayIco == null)
            {
                trayIco = new TrayIco();
                trayIco.initView();
                trayIco.initLang();
                trayIco.initData();
            }
            trayPtn = trayIco;
            trayPtn.setVisible(true);

            if (button != null)
            {
                Lang.setWText(button, LangRes.P30F960E, "显示为导航罗盘");
            }
            userMdl.setCfg(ConsCfg.CFG_TRAY_PTN, "icon");
            isOsTray = true;
            return;
        }

        // 下一步：显示为导航罗盘
        if (trayWnd == null)
        {
            trayWnd = new TrayWnd(this, userMdl);
            trayWnd.initView();
            trayWnd.initLang();
            trayWnd.initData();
        }
        trayPtn = trayWnd;
        trayPtn.setVisible(true);

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
        boolean wasDecoratedByOS = !(mpwdPtn.isUndecorated());
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
                boolean wasVisible = mpwdPtn.isVisible();

                mpwdPtn.setVisible(false);
                mpwdPtn.dispose();
                if (!canBeDecoratedByLAF || wasDecoratedByOS)
                {
                    mpwdPtn.setUndecorated(false);
                    mpwdPtn.getRootPane().setWindowDecorationStyle(0);
                }
                else
                {
                    mpwdPtn.setUndecorated(true);
                    mpwdPtn.getRootPane().setWindowDecorationStyle(1);
                }

                mpwdPtn.setVisible(wasVisible);
            }
            userMdl.setLook(isSystem ? ConsCfg.DEF_SKIN_LOOK_SYS : lafClass);
        }
        catch (Exception exc)
        {
            Lang.showMesg(mpwdPtn, null, exc.getLocalizedMessage());
        }
    }
    private TrayIco trayIco;
    private TrayWnd trayWnd;
    private javax.swing.JPopupMenu trayMenu;
}
