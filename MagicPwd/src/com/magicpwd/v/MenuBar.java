/**
 * 
 */
package com.magicpwd.v;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.c.MenuEvt;
import com.magicpwd.m.UserMdl;
import com.magicpwd.x.MdiDialog;

/**
 * @author Amon
 * 
 */
public class MenuBar extends JMenuBar
{

    private MenuEvt me_MenuEvent;
    private javax.swing.JMenuItem mi_lastItem;

    public MenuBar()
    {
    }

    public void initView()
    {
        initFileMenu();
        initEditMenu();
        initViewMenu();
        initDataMenu();
        initUserMenu();
        initSkinMenu();
        initHelpMenu();
    }

    public void initLang()
    {
        initFileLang();
        initEditLang();
        initViewLang();
        initDataLang();
        initUserLang();
        initSkinLang();
        initHelpLang();
    }

    public void initData()
    {
    }

    public void setMenuEvent(MenuEvt event)
    {
        me_MenuEvent = event;
    }

    public void setMenuItemStatus(javax.swing.JMenuItem item, boolean enabled)
    {
        if (item != null)
        {
            item.setEnabled(enabled);
        }
    }

    public void setViewPropSelected(boolean selected)
    {
        mi_ViewProp.setSelected(selected);
    }

    public void setViewSideSelected(boolean selected)
    {
        mi_ViewSide.setSelected(selected);
    }

    public void setViewFindSelected(boolean selected)
    {
        if (mi_ViewFind.isSelected() != selected)
        {
            mi_ViewFind.setSelected(selected);
        }
    }

    public void setViewMenuSelected(boolean selected)
    {
        if (mi_ViewMenu.isSelected() != selected)
        {
            mi_ViewMenu.setSelected(selected);
        }
    }

    public void setViewToolSelected(boolean selected)
    {
        if (mi_ViewTool.isSelected() != selected)
        {
            mi_ViewTool.setSelected(selected);
        }
    }

    public void setViewInfoSelected(boolean selected)
    {
        if (mi_ViewInfo.isSelected() != selected)
        {
            mi_ViewInfo.setSelected(selected);
        }
    }

    public void setUserSecretEnabled()
    {
        String skey = UserMdl.getCfg().getCfg(ConsCfg.CFG_USER_SKEY);
        mi_UserSecret.setEnabled(skey == null || skey.length() != 224);
    }

    private void initFileMenu()
    {
        mu_FileMenu = new javax.swing.JMenu();
        add(mu_FileMenu);

        mi_FileApnd = new javax.swing.JMenuItem();
        mi_FileApnd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        mi_FileApnd.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.fileApndActionPerformed(evt);
            }
        });
        mu_FileMenu.add(mi_FileApnd);

        mi_FileSave = new javax.swing.JMenuItem();
        mi_FileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        mi_FileSave.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.fileSaveActionPerformed(evt);
            }
        });
        mu_FileMenu.add(mi_FileSave);

        mu_FileMenu.addSeparator();

        mi_FileHide = new javax.swing.JMenuItem();
        mi_FileHide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK));
        mi_FileHide.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.fileHideActionPerformed(evt);
            }
        });
        mu_FileMenu.add(mi_FileHide);

        mu_FileMenu.addSeparator();

        mi_FileExit = new javax.swing.JMenuItem();
        mi_FileExit.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.fileExitActionPerformed(evt);
            }
        });
        mu_FileMenu.add(mi_FileExit);
    }

    private void initEditMenu()
    {
        mu_EditMenu = new javax.swing.JMenu();
        add(mu_EditMenu);

        mi_EditFind = new javax.swing.JMenuItem();
        mi_EditFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        mi_EditFind.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editFindActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditFind);

        mu_EditMenu.addSeparator();

        mi_EditText = new javax.swing.JMenuItem();
        mi_EditText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
        mi_EditText.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editTextActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditText);

        mi_EditPwds = new javax.swing.JMenuItem();
        mi_EditPwds.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));
        mi_EditPwds.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editPwdsActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditPwds);

        mi_EditLink = new javax.swing.JMenuItem();
        mi_EditLink.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK));
        mi_EditLink.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editLinkActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditLink);

        mi_EditMail = new javax.swing.JMenuItem();
        mi_EditMail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_MASK));
        mi_EditMail.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editMailActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditMail);

        mi_EditDate = new javax.swing.JMenuItem();
        mi_EditDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_MASK));
        mi_EditDate.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editDateActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditDate);

        mi_EditArea = new javax.swing.JMenuItem();
        mi_EditArea.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.CTRL_MASK));
        mi_EditArea.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editAreaActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditArea);

        mi_EditFile = new javax.swing.JMenuItem();
        mi_EditFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, InputEvent.CTRL_MASK));
        mi_EditFile.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editFileActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditFile);

        mu_EditMenu.addSeparator();

        mi_EditPrev = new javax.swing.JMenuItem();
        mi_EditPrev.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_MASK));
        mi_EditPrev.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editPrevActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditPrev);

        mi_EditNext = new javax.swing.JMenuItem();
        mi_EditNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_MASK));
        mi_EditNext.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.editNextActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_EditNext);

        mu_EditMenu.addSeparator();

        mi_KindApnd = new javax.swing.JMenuItem();
        mi_KindApnd.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.kindApndActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_KindApnd);

        mi_KindUpdt = new javax.swing.JMenuItem();
        mi_KindUpdt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.kindUpdtActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_KindUpdt);

        mi_KindDelt = new javax.swing.JMenuItem();
        mi_KindDelt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.kindDeltActionPerformed(evt);
            }
        });
        mu_EditMenu.add(mi_KindDelt);
    }

    private void initViewMenu()
    {
        mu_ViewMenu = new javax.swing.JMenu();
        add(mu_ViewMenu);

        mi_ViewTop1 = new javax.swing.JCheckBoxMenuItem();
        mi_ViewTop1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        mi_ViewTop1.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.viewTop1ActionPerformed(evt);
            }
        });
        mu_ViewMenu.add(mi_ViewTop1);

        mu_ViewMenu.addSeparator();

        mi_ViewProp = new javax.swing.JCheckBoxMenuItem();
        mi_ViewProp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, InputEvent.ALT_MASK));
        mi_ViewProp.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.viewEditActionPerformed(evt);
            }
        });
        mi_ViewProp.setSelected(UserMdl.getCfg().isEditViw());
        mu_ViewMenu.add(mi_ViewProp);

        mi_ViewSide = new javax.swing.JCheckBoxMenuItem();
        mi_ViewSide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, InputEvent.ALT_MASK));
        mi_ViewSide.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.viewSideActionPerformed(evt);
            }
        });
        mi_ViewSide.setSelected(UserMdl.getCfg().isEditWnd());
        mu_ViewMenu.add(mi_ViewSide);

        mu_ViewMenu.addSeparator();

        mi_ViewFind = new javax.swing.JCheckBoxMenuItem();
        mi_ViewFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, InputEvent.ALT_MASK));
        mi_ViewFind.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.viewFindActionPerformed(evt);
            }
        });
        mi_ViewFind.setSelected(UserMdl.getCfg().isFindViw());
        mu_ViewMenu.add(mi_ViewFind);

        mi_ViewMenu = new javax.swing.JCheckBoxMenuItem();
        mi_ViewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.ALT_MASK));
        mi_ViewMenu.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.viewMenuActionPerformed(evt);
            }
        });
        mi_ViewMenu.setSelected(UserMdl.getCfg().isMenuViw());
        mu_ViewMenu.add(mi_ViewMenu);

        mi_ViewTool = new javax.swing.JCheckBoxMenuItem();
        mi_ViewTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, InputEvent.ALT_MASK));
        mi_ViewTool.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.viewToolActionPerformed(evt);
            }
        });
        mi_ViewTool.setSelected(UserMdl.getCfg().isToolViw());
        mu_ViewMenu.add(mi_ViewTool);

        mi_ViewInfo = new javax.swing.JCheckBoxMenuItem();
        mi_ViewInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, InputEvent.ALT_MASK));
        mi_ViewInfo.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.viewInfoActionPerformed(evt);
            }
        });
        mi_ViewInfo.setSelected(UserMdl.getCfg().isInfoViw());
        mu_ViewMenu.add(mi_ViewInfo);
    }

    private void initDataMenu()
    {
        mu_DataMenu = new javax.swing.JMenu();
        add(mu_DataMenu);

        mi_DataImpt = new javax.swing.JMenuItem();
        mi_DataImpt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.dataImptActionPerformed(evt);
            }
        });
        mu_DataMenu.add(mi_DataImpt);

        mi_DataExpt = new javax.swing.JMenuItem();
        mi_DataExpt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.dataExptActionPerformed(evt);
            }
        });
        mu_DataMenu.add(mi_DataExpt);

        mu_DataMenu.addSeparator();

        mi_DataUSet = new javax.swing.JMenuItem();
        mi_DataUSet.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_DataUSetActionPerformed(evt);
            }
        });
        mu_DataMenu.add(mi_DataUSet);

        mi_DataChar = new javax.swing.JMenuItem();
        mi_DataChar.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_DataCharActionPerformed(evt);
            }
        });
        mu_DataMenu.add(mi_DataChar);

        mi_DataTplt = new javax.swing.JMenuItem();
        mi_DataTplt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_DataTpltActionPerformed(evt);
            }
        });
        mu_DataMenu.add(mi_DataTplt);

        mi_DataKind = new javax.swing.JMenuItem();
        mi_DataKind.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_DataKindActionPerformed(evt);
            }
        });
        mu_DataMenu.add(mi_DataKind);
    }

    private void initUserMenu()
    {
        mu_UserMenu = new javax.swing.JMenu();
        add(mu_UserMenu);

        mi_UserSwitch = new javax.swing.JMenuItem();
        mi_UserSwitch.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.userSwitchActionPerformed(evt);
            }
        });
        mu_UserMenu.add(mi_UserSwitch);

        mi_UserCreate = new javax.swing.JMenuItem();
        mi_UserCreate.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.userSwitchActionPerformed(evt);
            }
        });
        mu_UserMenu.add(mi_UserCreate);

        mu_UserMenu.addSeparator();

        mi_UserUpdate = new javax.swing.JMenuItem();
        mi_UserUpdate.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.userUpdateActionPerformed(evt);
            }
        });
        mu_UserMenu.add(mi_UserUpdate);

        mi_UserSecret = new javax.swing.JMenuItem();
        mi_UserSecret.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.userSecretActionPerformed(evt);
            }
        });
        mu_UserMenu.add(mi_UserSecret);
    }

    private void initSkinMenu()
    {
        mu_SkinMenu = new javax.swing.JMenu();
        add(mu_SkinMenu);

        String userSkin = UserMdl.getCfg().getCfg(ConsCfg.CFG_SKIN, ConsCfg.DEF_SKIN);
        boolean checked = ConsCfg.DEF_SKIN.equalsIgnoreCase(userSkin);
        java.awt.event.ActionListener listener = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                if (mi_lastItem != null)
                {
                    mi_lastItem.setSelected(false);
                }
                mi_lastItem = (javax.swing.JMenuItem) evt.getSource();
                me_MenuEvent.skinChangeActionPerformed(evt);
            }
        };

        mi_SkinSystem = new javax.swing.JCheckBoxMenuItem();
        mi_SkinSystem.setActionCommand(ConsCfg.DEF_SKIN);
        mi_SkinSystem.addActionListener(listener);
        mu_SkinMenu.add(mi_SkinSystem);

        java.io.File skinFile = new java.io.File(ConsEnv.DIR_DAT, "skin.config");
        if (skinFile.isFile() && skinFile.canRead())
        {
            try
            {
                javax.swing.JMenu lastMenu = null;
                java.util.regex.Pattern family = java.util.regex.Pattern.compile("^skin_[^._]+$");
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(skinFile), ConsEnv.FILE_ENCODING));
                String line;
                String[] arr;
                while ((line = reader.readLine()) != null)
                {
                    arr = line.split("=");
                    if (arr == null || arr.length != 2)
                    {
                        continue;
                    }
                    String k = arr[0];
                    String v = arr[1];
                    if (family.matcher(k).matches())
                    {
                        lastMenu = new javax.swing.JMenu(v);
                        mu_SkinMenu.add(lastMenu);
                        continue;
                    }

                    if (lastMenu == null)
                    {
                        continue;
                    }

                    if ("-".equals(v))
                    {
                        lastMenu.addSeparator();
                        continue;
                    }

                    arr = v.split(":");
                    if (arr == null || arr.length != 2)
                    {
                        continue;
                    }

                    k = arr[0];
                    v = arr[1];
                    javax.swing.JCheckBoxMenuItem item = new javax.swing.JCheckBoxMenuItem(k);
                    if (!checked)
                    {
                        checked = userSkin.equalsIgnoreCase(v);
                        if (checked)
                        {
                            item.setSelected(true);
                            mi_lastItem = item;
                        }
                    }
                    item.setActionCommand(v);
                    item.addActionListener(listener);
                    lastMenu.add(item);
                }
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }

        if (!checked)
        {
            mi_SkinSystem.setSelected(true);
            mi_lastItem = mi_SkinSystem;
        }
    }

    private void initHelpMenu()
    {
        mu_HelpMenu = new javax.swing.JMenu();
        add(mu_HelpMenu);

        mi_HelpHelp = new javax.swing.JMenuItem();
        mi_HelpHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        mi_HelpHelp.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.helpHelpActionPerformed(evt);
            }
        });
        mu_HelpMenu.add(mi_HelpHelp);

        mu_HelpMenu.addSeparator();

        mi_HelpSite = new javax.swing.JMenuItem();
        mi_HelpSite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        mi_HelpSite.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.helpSiteActionPerformed(evt);
            }
        });
        mu_HelpMenu.add(mi_HelpSite);

        mi_HelpMail = new javax.swing.JMenuItem();
        mi_HelpMail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        mi_HelpMail.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.helpMailActionPerformed(evt);
            }
        });
        mu_HelpMenu.add(mi_HelpMail);

        mi_HelpUpdt = new javax.swing.JMenuItem();
        mi_HelpUpdt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        mi_HelpUpdt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.helpUpdtActionPerformed(evt);
            }
        });
        mu_HelpMenu.add(mi_HelpUpdt);

        mu_HelpMenu.addSeparator();

        mi_HelpSKey = new javax.swing.JMenuItem();
        mi_HelpSKey.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
        mi_HelpSKey.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_DataSKeyActionPerformed(evt);
            }
        });
        mu_HelpMenu.add(mi_HelpSKey);

        mi_HelpJava = new javax.swing.JMenuItem();
        mi_HelpJava.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_DataJavaActionPerformed(evt);
            }
        });
        mu_HelpMenu.add(mi_HelpJava);

        mu_HelpMenu.addSeparator();

        mi_HelpInfo = new javax.swing.JMenuItem();
        mi_HelpInfo.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                me_MenuEvent.helpInfoActionPerformed(evt);
            }
        });
        mu_HelpMenu.add(mi_HelpInfo);
    }

    private void initFileLang()
    {
        Lang.setWText(mu_FileMenu, LangRes.P30F7601, "文件");

        Lang.setWText(mi_FileApnd, LangRes.P30F7602, "新增");

        Lang.setWText(mi_FileSave, LangRes.P30F7603, "保存");

        Lang.setWText(mi_FileHide, LangRes.P30F7604, "隐藏");

        Lang.setWText(mi_FileExit, LangRes.P30F7605, "退出");
    }

    private void initEditLang()
    {
        Lang.setWText(mu_EditMenu, LangRes.P30F7606, "编辑");

        Lang.setWText(mi_EditFind, LangRes.P30F7607, "查找");

        Lang.setWText(mi_EditText, LangRes.P30F7608, "添加文本属性");
        Lang.setWText(mi_EditPwds, LangRes.P30F7609, "添加口令属性");
        Lang.setWText(mi_EditLink, LangRes.P30F760A, "添加链接属性");
        Lang.setWText(mi_EditMail, LangRes.P30F760B, "添加邮件属性");
        Lang.setWText(mi_EditDate, LangRes.P30F760C, "添加日期属性");
        Lang.setWText(mi_EditArea, LangRes.P30F760D, "添加附注属性");
        Lang.setWText(mi_EditFile, LangRes.P30F760E, "添加文件属性");

        Lang.setWText(mi_EditPrev, LangRes.P30F760F, "上移");
        Lang.setWText(mi_EditNext, LangRes.P30F7610, "下移");

        Lang.setWText(mi_KindApnd, LangRes.P30F7611, "添加类别");
        Lang.setWText(mi_KindUpdt, LangRes.P30F7612, "更新类别");
        Lang.setWText(mi_KindDelt, LangRes.P30F7613, "删除类别");
    }

    private void initViewLang()
    {
        Lang.setWText(mu_ViewMenu, LangRes.P30F7614, "视图");

        Lang.setWText(mi_ViewTop1, LangRes.P30F7615, "总在最上");
        Lang.setWText(mi_ViewProp, LangRes.P30F7616, "显示属性栏");
        Lang.setWText(mi_ViewSide, LangRes.P30F7617, "分离属性栏");
        Lang.setWText(mi_ViewFind, LangRes.P30F7618, "显示搜索栏");
        Lang.setWText(mi_ViewMenu, LangRes.P30F7619, "显示菜单栏");
        Lang.setWText(mi_ViewTool, LangRes.P30F761A, "显示工具栏");
        Lang.setWText(mi_ViewInfo, LangRes.P30F761B, "显示状态栏");
    }

    private void initDataLang()
    {
        Lang.setWText(mu_DataMenu, LangRes.P30F761C, "数据");

        Lang.setWText(mi_DataImpt, LangRes.P30F761D, "导入数据");

        Lang.setWText(mi_DataExpt, LangRes.P30F761E, "导出数据");

        Lang.setWText(mi_DataUSet, LangRes.P30F761F, "常规设置");

        Lang.setWText(mi_DataChar, LangRes.P30F7620, "口令设置");

        Lang.setWText(mi_DataTplt, LangRes.P30F7621, "模板管理");

        Lang.setWText(mi_DataKind, LangRes.P30F7622, "类别管理");

        Lang.setWText(mi_HelpSKey, LangRes.P30F7623, "快捷键");
    }

    private void initUserLang()
    {
        mi_UserSwitch.setEnabled(false);
        mi_UserCreate.setEnabled(false);
        setUserSecretEnabled();

        Lang.setWText(mu_UserMenu, LangRes.P30F7624, "用户");

        Lang.setWText(mi_UserSwitch, LangRes.P30F7625, "切换用户");

        Lang.setWText(mi_UserCreate, LangRes.P30F7626, "添加用户");

        Lang.setWText(mi_UserUpdate, LangRes.P30F7627, "更改登录口令");

        Lang.setWText(mi_UserSecret, LangRes.P30F7628, "设置安全口令");
    }

    private void initSkinLang()
    {
        Lang.setWText(mu_SkinMenu, LangRes.P30F762F, "风格(&L)");

        Lang.setWText(mi_SkinSystem, LangRes.P30F7630, "系统风格(&S)");
    }

    private void initHelpLang()
    {
        Lang.setWText(mu_HelpMenu, LangRes.P30F7629, "帮助");

        Lang.setWText(mi_HelpHelp, LangRes.P30F762A, "帮助");

        Lang.setWText(mi_HelpSite, LangRes.P30F762B, "软件首页");

        Lang.setWText(mi_HelpMail, LangRes.P30F762C, "联系作者");

        Lang.setWText(mi_HelpUpdt, LangRes.P30F762D, "检测更新");

        Lang.setWText(mi_HelpJava, LangRes.P30F7631, "Java环境");

        Lang.setWText(mi_HelpInfo, LangRes.P30F762E, "关于");
    }

    private void mi_DataUSetActionPerformed(java.awt.event.ActionEvent evt)
    {
        MdiDialog.getInstance(me_MenuEvent).showProp(ConsEnv.PROP_USET, true);
    }

    private void mi_DataCharActionPerformed(java.awt.event.ActionEvent evt)
    {
        MdiDialog.getInstance(me_MenuEvent).showProp(ConsEnv.PROP_CHAR, true);
    }

    private void mi_DataTpltActionPerformed(java.awt.event.ActionEvent evt)
    {
        MdiDialog.getInstance(me_MenuEvent).showProp(ConsEnv.PROP_TPLT, true);
    }

    private void mi_DataKindActionPerformed(java.awt.event.ActionEvent evt)
    {
        MdiDialog.getInstance(me_MenuEvent).showProp(ConsEnv.PROP_KIND, true);
    }

    private void mi_DataSKeyActionPerformed(java.awt.event.ActionEvent evt)
    {
        me_MenuEvent.helpSKeyActionPerformed(evt);
    }

    private void mi_DataJavaActionPerformed(java.awt.event.ActionEvent evt)
    {
        me_MenuEvent.helpJavaActionPerformed(evt);
    }
    private javax.swing.JMenu mu_FileMenu;
    private javax.swing.JMenuItem mi_FileApnd;
    private javax.swing.JMenuItem mi_FileSave;
    private javax.swing.JMenuItem mi_FileHide;
    private javax.swing.JMenuItem mi_FileExit;
    private javax.swing.JMenu mu_EditMenu;
    private javax.swing.JMenuItem mi_EditText;
    private javax.swing.JMenuItem mi_EditPwds;
    private javax.swing.JMenuItem mi_EditMail;
    private javax.swing.JMenuItem mi_EditLink;
    private javax.swing.JMenuItem mi_EditDate;
    private javax.swing.JMenuItem mi_EditFile;
    private javax.swing.JMenuItem mi_EditArea;
    private javax.swing.JMenuItem mi_EditPrev;
    private javax.swing.JMenuItem mi_KindApnd;
    private javax.swing.JMenuItem mi_KindUpdt;
    private javax.swing.JMenuItem mi_KindDelt;
    private javax.swing.JMenuItem mi_EditNext;
    private javax.swing.JMenuItem mi_EditFind;
    private javax.swing.JMenu mu_ViewMenu;
    private javax.swing.JCheckBoxMenuItem mi_ViewTop1;
    private javax.swing.JCheckBoxMenuItem mi_ViewProp;
    private javax.swing.JCheckBoxMenuItem mi_ViewSide;
    private javax.swing.JCheckBoxMenuItem mi_ViewMenu;
    private javax.swing.JCheckBoxMenuItem mi_ViewTool;
    private javax.swing.JCheckBoxMenuItem mi_ViewFind;
    private javax.swing.JCheckBoxMenuItem mi_ViewInfo;
    private javax.swing.JMenu mu_DataMenu;
    private javax.swing.JMenuItem mi_DataImpt;
    private javax.swing.JMenuItem mi_DataExpt;
    private javax.swing.JMenuItem mi_DataUSet;
    private javax.swing.JMenuItem mi_DataChar;
    private javax.swing.JMenuItem mi_DataTplt;
    private javax.swing.JMenuItem mi_DataKind;
    private javax.swing.JMenu mu_UserMenu;
    private javax.swing.JMenuItem mi_UserSwitch;
    private javax.swing.JMenuItem mi_UserCreate;
    private javax.swing.JMenuItem mi_UserUpdate;
    private javax.swing.JMenuItem mi_UserSecret;
    private javax.swing.JMenu mu_SkinMenu;
    private javax.swing.JCheckBoxMenuItem mi_SkinSystem;
    private javax.swing.JMenu mu_HelpMenu;
    private javax.swing.JMenuItem mi_HelpHelp;
    private javax.swing.JMenuItem mi_HelpSite;
    private javax.swing.JMenuItem mi_HelpMail;
    private javax.swing.JMenuItem mi_HelpUpdt;
    private javax.swing.JMenuItem mi_HelpSKey;
    private javax.swing.JMenuItem mi_HelpJava;
    private javax.swing.JMenuItem mi_HelpInfo;
}
