package com.magicpwd.v;

import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._user.UserPtn;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBAccess;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.AmonFF;
import com.magicpwd.v.mpay.NormPtn;
import com.magicpwd.v.mpad.MiniPtn;
import com.magicpwd.v.mpwd.MainPtn;

/**
 * 系统托盘
 * @author Amon
 */
public class TrayPtn implements IBackCall, java.awt.event.MouseListener, java.awt.event.MouseMotionListener
{

    private static boolean dbLocked;
    private static boolean isOsTray;
    private static int currPtn;
    private static int nextPtn;
    private UserMdl userMdl;
    private UserPtn userPtn;
    private javax.swing.JFrame mfCurrForm;
    private javax.swing.JWindow mwTrayForm;
    private javax.swing.event.PopupMenuListener listener;
    private MenuPtn menuPtn;

    public TrayPtn()
    {
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

        // 右键菜单初始化
        menuPtn = new MenuPtn(null);
        try
        {
            menuPtn.loadData(new java.io.File(ConsEnv.DIR_DAT, "tray.xml"));
            trayMenu = menuPtn.getPopMenu("tray");
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }

        return true;
    }

    public boolean initLang()
    {
        trayIcon.setToolTip(ConsEnv.SOFTNAME + ' ' + ConsEnv.VERSIONS);
        return true;
    }

    public boolean initData()
    {
        changeView(userMdl.getCfg(ConsCfg.CFG_TRAY_PTN, "guid"));
        return true;
    }

    @Override
    public boolean callBack(Object sender, java.util.EventListener event, String... params)
    {
        if (params == null || params.length != 1)
        {
            return false;
        }

        // 用户登录
        if (ConsEnv.STR_SIGN_IN.equalsIgnoreCase(params[0]) || ConsEnv.STR_SIGN_UP.equalsIgnoreCase(params[0]))
        {
            // 设置软件界面风格
            showMainPtn();
            mfCurrForm.setVisible(true);
            if (userMdl.isEditVisible())
            {
                mp_MainPtn.showPropInfo();
            }
            mp_MainPtn.setEditIsolate(userMdl.isEditIsolate());
            mp_MainPtn.setEditVisible(userMdl.isEditVisible());
            mp_MainPtn.requestFocus();
            initView();
            initLang();
            initData();
            mfCurrForm.toFront();
            return true;
        }

        // 身份认证
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
        getCurrForm().toFront();
        return true;
    }

    public void setVisible(boolean visible)
    {
        if (isOsTray)
        {
            mwTrayForm.setVisible(visible);
        }
    }

    public javax.swing.JFrame getCurrForm()
    {
        return mfCurrForm;
    }

    public MainPtn getMainPtn()
    {
        if (mp_MainPtn == null)
        {
            mp_MainPtn = new MainPtn(this, userMdl);
            mp_MainPtn.initView();
            mp_MainPtn.initLang();
            mp_MainPtn.initData();
        }
        return mp_MainPtn;
    }

    private void showMainPtn()
    {
        getMainPtn();

        mfCurrForm = mp_MainPtn;
        currPtn = ConsEnv.VIEW_MAIN;
    }

    public static NormPtn getNormPtn()
    {
        return null;
    }

    private void showNormPtn()
    {
        if (mp_NormPtn == null)
        {
            mp_NormPtn = new NormPtn();
            mp_NormPtn.initView();
            mp_NormPtn.initLang();
            mp_NormPtn.initData();
        }

        mfCurrForm = mp_NormPtn;
        currPtn = ConsEnv.VIEW_NORM;
    }

    public MiniPtn getMiniPtn()
    {
        if (mp_MiniPtn == null)
        {
            mp_MiniPtn = new MiniPtn(this, userMdl);
            mp_MiniPtn.initView();
            mp_MiniPtn.initLang();
            mp_MiniPtn.initData();
        }
        return mp_MiniPtn;
    }

    private void showMiniPtn()
    {
        getMiniPtn();

        mfCurrForm = mp_MiniPtn;
        currPtn = ConsEnv.VIEW_MINI;
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
            if (mp_MainPtn != null)
            {
//                mp_MainPtn.setVisible(false);
                mp_MainPtn.endSave();
            }
            if (mp_NormPtn != null)
            {
//                mp_NormPtn.setVisible(false);
                mp_NormPtn.endSave();
            }
            if (mp_MiniPtn != null)
            {
//                mp_MiniPtn.setVisible(false);
                mp_MiniPtn.endSave();
            }

            userMdl.saveCfg();

            DBAccess.exit();

            java.io.File backFile = Util.nextBackupFile(userMdl.getBackDir(), userMdl.getBackNum());
            Jzip.doZip(backFile, new java.io.File(userMdl.getDataDir()));
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

    public void loadCfg()
    {
        if (userMdl == null)
        {
            userMdl = new UserMdl();

            // 用户配置文件加载
            userMdl.loadCfg();
        }

        // 语言资源加载
        Lang.loadLang(userMdl);

        // 扩展皮肤加载
        Bean.loadLnF(userMdl);
    }

    public void loadPre()
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

        // 扩展库加载
        java.io.File file = new java.io.File(ConsEnv.DIR_EXT);
        if (file != null && file.exists() && file.isDirectory() && file.canRead())
        {
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
    }

    public void showViewPtn(int nextPtn)
    {
        // 显示登录
        if (getCurrForm() == null)
        {
            userPtn = new UserPtn(this, userMdl, null);
            userPtn.setBackCall(this);
            userPtn.initView(userMdl.getCfg(ConsCfg.CFG_USER, "").trim().length() > 0 ? ConsEnv.INT_SIGN_IN : ConsEnv.INT_SIGN_UP);
            userPtn.initLang();
            userPtn.initData();
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
        getUserPtn(ConsEnv.INT_SIGN_RS, this);
    }

    public UserPtn getUserPtn(int view, IBackCall call)
    {
        if (userPtn == null)
        {
            userPtn = new UserPtn(this, userMdl, null);
        }
        UserPtn ptn = (getCurrForm() != null && getCurrForm().isVisible()) ? new UserPtn(userMdl, null, getCurrForm()) : userPtn;
        ptn.setBackCall(call);
        ptn.initView(view);
        ptn.initLang();
        ptn.initData();
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
            userMdl.setCfg(ConsCfg.CFG_SKIN_LOOK, isSystem ? ConsCfg.DEF_SKIN_SYS : lafClass);
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

    public void showTips(String title, String tips)
    {
        if (trayIcon != null)
        {
            trayIcon.displayMessage(title, tips, java.awt.TrayIcon.MessageType.INFO);
        }
    }
    private static MiniPtn mp_MiniPtn;
    private static NormPtn mp_NormPtn;
    private static MainPtn mp_MainPtn;
    private java.awt.Point dragLoc;
    private java.awt.Point formLoc;
    private java.awt.TrayIcon trayIcon;
    private javax.swing.JPopupMenu trayMenu;
}
