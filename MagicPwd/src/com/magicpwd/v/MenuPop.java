/**
 * 
 */
package com.magicpwd.v;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import javax.swing.JPopupMenu;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.c.MPadEvt;
import com.magicpwd.c.MPwdEvt;
import com.magicpwd.c.MenuEvt;
import com.magicpwd.c.ToolEvt;
import com.magicpwd.m.UserMdl;
import javax.swing.ButtonGroup;

/**
 * @author Amon
 * 
 */
public class MenuPop extends JPopupMenu
{

    public static final int MENU_GRID = 0;
    public static final int MENU_TREE = 1;
    public static final int MENU_LIST = 2;
    public static final int MENU_NOTE = 3;
    private int menuType;
    private ToolEvt toolEvt;
    private MenuEvt menuEvt;

    public MenuPop(int menuType)
    {
        this.menuType = menuType;
    }

    public void initView()
    {
        switch (menuType)
        {
            case MENU_GRID:
                initGridView();
                break;
            case MENU_TREE:
                initTreeView();
                break;
            case MENU_LIST:
                initListView();
                break;
            case MENU_NOTE:
                initNoteView();
                break;
            default:
                break;
        }
    }

    public void initLang()
    {
        switch (menuType)
        {
            case MENU_GRID:
                initGridLang();
                break;
            case MENU_TREE:
                initTreeLang();
                break;
            case MENU_LIST:
                initListLang();
                break;
            case MENU_NOTE:
                initNoteLang();
                break;
            default:
                break;
        }
    }

    public void setToolEvent(ToolEvt event)
    {
        toolEvt = event;
    }

    public void setMenuEvent(MenuEvt event)
    {
        menuEvt = event;
    }

    private void initTreeView()
    {
        mi_TreeApnd = new javax.swing.JMenuItem();
        mi_TreeApnd.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.kindApndActionPerformed(evt);
            }
        });
        add(mi_TreeApnd);

        mi_TreeUpdt = new javax.swing.JMenuItem();
        mi_TreeUpdt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.kindUpdtActionPerformed(evt);
            }
        });
        add(mi_TreeUpdt);

        mi_TreeDelt = new javax.swing.JMenuItem();
        mi_TreeDelt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.kindDeltActionPerformed(evt);
            }
        });
        add(mi_TreeDelt);

        addSeparator();

        mi_TreeImpt = new javax.swing.JMenuItem();
        mi_TreeImpt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.dataImptActionPerformed(evt);
            }
        });
        add(mi_TreeImpt);
    }

    private void initTreeLang()
    {
        Lang.setWText(mi_TreeApnd, LangRes.P30F7D01, "添加类别");

        Lang.setWText(mi_TreeUpdt, LangRes.P30F7D02, "更新类别");

        Lang.setWText(mi_TreeDelt, LangRes.P30F7D03, "删除类别");

        Lang.setWText(mi_TreeImpt, LangRes.P30F7D04, "导入数据");
    }

    private void initListView()
    {
        mi_ListApnd = new javax.swing.JMenuItem();
        mi_ListApnd.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.fileApndActionPerformed(evt);
            }
        });
        add(mi_ListApnd);

        mi_ListDrop = new javax.swing.JMenuItem();
        mi_ListDrop.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.fileDeltActionPerformed(evt);
            }
        });
        add(mi_ListDrop);

        addSeparator();

        mu_ListMode = new javax.swing.JMenu();
        add(mu_ListMode);

        javax.swing.JMenuItem menuItem;
        mi_ListMode = new javax.swing.JMenuItem[10];
        for (int i = 0; i < mi_ListMode.length; i += 1)
        {
            menuItem = new javax.swing.JMenuItem();
            menuItem.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_MOD0 + i));
            menuItem.setActionCommand(Integer.toString(i));
            menuItem.addActionListener(new java.awt.event.ActionListener()
            {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    ((MPwdEvt) menuEvt).keysModeActionPerformed(evt);
                }
            });
            mu_ListMode.add(menuItem);
            mi_ListMode[i] = menuItem;
        }

        mu_ListNote = new javax.swing.JMenu();
        add(mu_ListNote);

        mi_ListNote = new javax.swing.JMenuItem[5];
        for (int i = 0; i < mi_ListNote.length; i += 1)
        {
            menuItem = new javax.swing.JCheckBoxMenuItem();
            menuItem.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_NTN2 + i));
            menuItem.setActionCommand(Integer.toString(i - 2));
            menuItem.addActionListener(new java.awt.event.ActionListener()
            {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    ((MPwdEvt) menuEvt).keysNoteActionPerformed(evt);
                }
            });
            mu_ListNote.add(menuItem);
            mi_ListNote[i] = menuItem;
        }

        addSeparator();

        mu_ListSasc = new javax.swing.JMenu();
        add(mu_ListSasc);

        javax.swing.JCheckBoxMenuItem cboxItem;
        mi_ListSasc = new javax.swing.JCheckBoxMenuItem[2];
        ButtonGroup bg = new ButtonGroup();
        String[] prop = new String[]
        {
            ConsCfg.DEF_TRUE, ConsCfg.DEF_FAIL
        };
        for (int i = 0; i < mi_ListSasc.length; i += 1)
        {
            cboxItem = new javax.swing.JCheckBoxMenuItem();
            cboxItem.setActionCommand(prop[i]);
            cboxItem.setSelected(prop[i].equals(UserMdl.getUserCfg().getCfg(ConsCfg.CFG_VIEW_LIST_ASC, ConsCfg.DEF_FAIL)));
            cboxItem.addActionListener(new java.awt.event.ActionListener()
            {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    ((MPwdEvt) menuEvt).listSascActionPerformed(evt);
                }
            });
            mu_ListSasc.add(cboxItem);
            bg.add(cboxItem);
            mi_ListSasc[i] = cboxItem;
        }

        mu_ListSkey = new javax.swing.JMenu();
        add(mu_ListSkey);

        mi_ListSkey = new javax.swing.JCheckBoxMenuItem[4];
        bg = new ButtonGroup();
        prop = new String[]
                {
                    "01", "09", "07", "0D"
                };
        for (int i = 0; i < mi_ListSkey.length; i += 1)
        {
            cboxItem = new javax.swing.JCheckBoxMenuItem();
            cboxItem.setActionCommand(prop[i]);
            cboxItem.setSelected(prop[i].equals(UserMdl.getUserCfg().getCfg(ConsCfg.CFG_VIEW_LIST_KEY, "01")));
            cboxItem.addActionListener(new java.awt.event.ActionListener()
            {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    ((MPwdEvt) menuEvt).listSkeyActionPerformed(evt);
                }
            });
            mu_ListSkey.add(cboxItem);
            mi_ListSkey[i] = cboxItem;
            bg.add(cboxItem);
        }

        addSeparator();

        mi_KindMove = new javax.swing.JMenuItem();
        mi_KindMove.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.kindMoveActionPerformed(evt);
            }
        });
        add(mi_KindMove);

        addSeparator();

        mi_HistView = new javax.swing.JMenuItem();
        mi_HistView.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.histViewActionPerformed(evt);
            }
        });
        add(mi_HistView);
    }

    private void initListLang()
    {
        Lang.setWText(mi_ListApnd, LangRes.P30F7D05, "添加记录 ");

        Lang.setWText(mi_ListDrop, LangRes.P30F7D06, "删除记录");

        int i = 0;
        Lang.setWText(mu_ListMode, LangRes.P30F7D1B, "标记为：");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D1C, "默认(&0)");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D1D, "常使用(&1)");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D1E, "待注册(&2)");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D1F, "待认证(&3)");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D20, "待激活(&4)");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D21, "仅测试(&5)");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D22, "已过期(&6)");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D23, "已丢失(&7)");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D24, "被禁用(&8)");
        Lang.setWText(mi_ListMode[i++], LangRes.P30F7D25, "已删除(&9)");

        i = 0;
        Lang.setWText(mu_ListNote, LangRes.P30F7D26, "重要性：");
        Lang.setWText(mi_ListNote[i++], LangRes.P30F7D27, "最高");
        Lang.setWText(mi_ListNote[i++], LangRes.P30F7D28, "高");
        Lang.setWText(mi_ListNote[i++], LangRes.P30F7D29, "正常");
        Lang.setWText(mi_ListNote[i++], LangRes.P30F7D2A, "低");
        Lang.setWText(mi_ListNote[i++], LangRes.P30F7D2B, "最低");

        i = 0;
        Lang.setWText(mu_ListSasc, LangRes.P30F7D2C, "排序方式");
        Lang.setWText(mi_ListSasc[i++], LangRes.P30F7D2D, "升序");
        Lang.setWText(mi_ListSasc[i++], LangRes.P30F7D2E, "降序");

        i = 0;
        Lang.setWText(mu_ListSkey, LangRes.P30F7D2F, "排序依据");
        Lang.setWText(mi_ListSkey[i++], LangRes.P30F7D30, "使用频率");
        Lang.setWText(mi_ListSkey[i++], LangRes.P30F7D31, "口令名称");
        Lang.setWText(mi_ListSkey[i++], LangRes.P30F7D32, "注册时间");
        Lang.setWText(mi_ListSkey[i++], LangRes.P30F7D33, "到期时间");

        Lang.setWText(mi_KindMove, LangRes.P30F7D1A, "把数据迁移到...(&M)");

        Lang.setWText(mi_HistView, LangRes.P30F7D07, "查看历史");
    }

    private void initGridView()
    {
        mi_GridCopy = new javax.swing.JMenuItem();
        mi_GridCopy.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.copyDataActionPerformed(evt);
            }
        });
        add(mi_GridCopy);

        addSeparator();

        mu_GridApnd = new javax.swing.JMenu();
        add(mu_GridApnd);

        mi_ApndText = new javax.swing.JMenuItem();
        mi_ApndText.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.editTextActionPerformed(evt);
            }
        });
        mu_GridApnd.add(mi_ApndText);

        mi_ApndPwds = new javax.swing.JMenuItem();
        mi_ApndPwds.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.editPwdsActionPerformed(evt);
            }
        });
        mu_GridApnd.add(mi_ApndPwds);

        mi_ApndLink = new javax.swing.JMenuItem();
        mi_ApndLink.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.editLinkActionPerformed(evt);
            }
        });
        mu_GridApnd.add(mi_ApndLink);

        mi_ApndMail = new javax.swing.JMenuItem();
        mi_ApndMail.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.editMailActionPerformed(evt);
            }
        });
        mu_GridApnd.add(mi_ApndMail);

        mi_ApndDate = new javax.swing.JMenuItem();
        mi_ApndDate.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.editDateActionPerformed(evt);
            }
        });
        mu_GridApnd.add(mi_ApndDate);

        mi_ApndArea = new javax.swing.JMenuItem();
        mi_ApndArea.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.editAreaActionPerformed(evt);
            }
        });
        mu_GridApnd.add(mi_ApndArea);

        mi_ApndFile = new javax.swing.JMenuItem();
        mi_ApndFile.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.editFileActionPerformed(evt);
            }
        });
        mu_GridApnd.add(mi_ApndFile);

        mu_GridUpdt = new javax.swing.JMenu();
        add(mu_GridUpdt);

        mi_UpdtText = new javax.swing.JMenuItem();
        mi_UpdtText.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.updtTextActionPerformed(evt);
            }
        });
        mu_GridUpdt.add(mi_UpdtText);

        mi_UpdtPwds = new javax.swing.JMenuItem();
        mi_UpdtPwds.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.updtPwdsActionPerformed(evt);
            }
        });
        mu_GridUpdt.add(mi_UpdtPwds);

        mi_UpdtLink = new javax.swing.JMenuItem();
        mi_UpdtLink.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.updtLinkActionPerformed(evt);
            }
        });
        mu_GridUpdt.add(mi_UpdtLink);

        mi_UpdtMail = new javax.swing.JMenuItem();
        mi_UpdtMail.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.updtMailActionPerformed(evt);
            }
        });
        mu_GridUpdt.add(mi_UpdtMail);

        mi_UpdtDate = new javax.swing.JMenuItem();
        mi_UpdtDate.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.updtDateActionPerformed(evt);
            }
        });
        mu_GridUpdt.add(mi_UpdtDate);

        mi_UpdtArea = new javax.swing.JMenuItem();
        mi_UpdtArea.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.updtAreaActionPerformed(evt);
            }
        });
        mu_GridUpdt.add(mi_UpdtArea);

        mi_UpdtFile = new javax.swing.JMenuItem();
        mi_UpdtFile.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.updtFileActionPerformed(evt);
            }
        });
        mu_GridUpdt.add(mi_UpdtFile);

        mi_GridDelt = new javax.swing.JMenuItem();
        mi_GridDelt.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.deltDataActionPerformed(evt);
            }
        });
        add(mi_GridDelt);
    }

    private void initGridLang()
    {
        Lang.setWText(mi_GridCopy, LangRes.P30F7D08, "复制数据");

        Lang.setWText(mu_GridApnd, LangRes.P30F7D09, "添加字段");
        Lang.setWText(mi_ApndText, LangRes.P30F7D0A, "添加文本");
        Lang.setWText(mi_ApndPwds, LangRes.P30F7D0B, "添加口令");
        Lang.setWText(mi_ApndLink, LangRes.P30F7D0C, "添加链接");
        Lang.setWText(mi_ApndMail, LangRes.P30F7D0D, "添加邮件");
        Lang.setWText(mi_ApndDate, LangRes.P30F7D0E, "添加日期");
        Lang.setWText(mi_ApndArea, LangRes.P30F7D0F, "添加附注");
        Lang.setWText(mi_ApndFile, LangRes.P30F7D10, "添加附件");

        Lang.setWText(mu_GridUpdt, LangRes.P30F7D11, "转换类型");
        Lang.setWText(mi_UpdtText, LangRes.P30F7D12, "转换为文本");
        Lang.setWText(mi_UpdtPwds, LangRes.P30F7D13, "转换为口令");
        Lang.setWText(mi_UpdtLink, LangRes.P30F7D14, "转换为链接");
        Lang.setWText(mi_UpdtMail, LangRes.P30F7D15, "转换为邮件");
        Lang.setWText(mi_UpdtDate, LangRes.P30F7D16, "转换为日期");
        Lang.setWText(mi_UpdtArea, LangRes.P30F7D17, "转换为附注");
        Lang.setWText(mi_UpdtFile, LangRes.P30F7D18, "转换为附件");

        Lang.setWText(mi_GridDelt, LangRes.P30F7D19, "删除属性");
    }

    private void initNoteView()
    {
        mi_NoteAlls = new javax.swing.JMenuItem();
        mi_NoteAlls.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ((MPadEvt) menuEvt).editAllsActionPerformed(evt);
            }
        });
        add(mi_NoteAlls);

        mi_NoteCuts = new javax.swing.JMenuItem();
        mi_NoteCuts.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ((MPadEvt) menuEvt).editCutsActionPerformed(evt);
            }
        });
        add(mi_NoteCuts);

        mi_NoteCopy = new javax.swing.JMenuItem();
        mi_NoteCopy.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ((MPadEvt) menuEvt).editCopyActionPerformed(evt);
            }
        });
        add(mi_NoteCopy);

        mi_NotePast = new javax.swing.JMenuItem();
        mi_NotePast.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ((MPadEvt) menuEvt).editPastActionPerformed(evt);
            }
        });
        add(mi_NotePast);

        addSeparator();

        mi_NoteUndo = new javax.swing.JMenuItem();
        mi_NoteUndo.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ((MPadEvt) menuEvt).editUndoActionPerformed(evt);
            }
        });
        add(mi_NoteUndo);

        mi_NoteRedo = new javax.swing.JMenuItem();
        mi_NoteRedo.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ((MPadEvt) menuEvt).editRedoActionPerformed(evt);
            }
        });
        add(mi_NoteRedo);
    }

    private void initNoteLang()
    {
        Lang.setWText(mi_NoteAlls, LangRes.P30F7D34, "全选");
        Lang.setWText(mi_NoteCuts, LangRes.P30F7D35, "剪切");
        Lang.setWText(mi_NoteCopy, LangRes.P30F7D36, "复制");
        Lang.setWText(mi_NotePast, LangRes.P30F7D37, "粘贴");
        Lang.setWText(mi_NoteUndo, LangRes.P30F7D38, "撤消");
        Lang.setWText(mi_NoteRedo, LangRes.P30F7D39, "重做");
    }

    public void setNoteUndoEnabled(boolean enabled)
    {
        mi_NoteUndo.setEnabled(enabled);
    }

    public void setNoteRedoEnabled(boolean enabled)
    {
        mi_NoteRedo.setEnabled(enabled);
    }
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // 目录菜单
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private javax.swing.JMenuItem mi_TreeApnd;
    private javax.swing.JMenuItem mi_TreeUpdt;
    private javax.swing.JMenuItem mi_TreeDelt;
    private javax.swing.JMenuItem mi_TreeImpt;
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // 列表菜单
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private javax.swing.JMenuItem mi_ListApnd;// 新增
    private javax.swing.JMenuItem mi_ListDrop;// 删除
    private javax.swing.JMenu mu_ListMode;// 标记为
    private javax.swing.JMenuItem[] mi_ListMode;
    private javax.swing.JMenu mu_ListNote;// 重要性
    private javax.swing.JMenuItem[] mi_ListNote;
    private javax.swing.JMenu mu_ListSasc; // 排序方式
    private javax.swing.JCheckBoxMenuItem[] mi_ListSasc;
    private javax.swing.JMenu mu_ListSkey;// 排序依据
    private javax.swing.JCheckBoxMenuItem[] mi_ListSkey;// 排序依据
    private javax.swing.JMenuItem mi_KindMove;
    private javax.swing.JMenuItem mi_HistView;
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // 属性菜单
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private javax.swing.JMenuItem mi_GridCopy;
    private javax.swing.JMenu mu_GridUpdt;
    private javax.swing.JMenuItem mi_UpdtText;
    private javax.swing.JMenuItem mi_UpdtPwds;
    private javax.swing.JMenuItem mi_UpdtLink;
    private javax.swing.JMenuItem mi_UpdtMail;
    private javax.swing.JMenuItem mi_UpdtDate;
    private javax.swing.JMenuItem mi_UpdtArea;
    private javax.swing.JMenuItem mi_UpdtFile;
    private javax.swing.JMenu mu_GridApnd;
    private javax.swing.JMenuItem mi_ApndText;
    private javax.swing.JMenuItem mi_ApndPwds;
    private javax.swing.JMenuItem mi_ApndLink;
    private javax.swing.JMenuItem mi_ApndMail;
    private javax.swing.JMenuItem mi_ApndDate;
    private javax.swing.JMenuItem mi_ApndArea;
    private javax.swing.JMenuItem mi_ApndFile;
    private javax.swing.JMenuItem mi_GridDelt;
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // 记事菜单
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private javax.swing.JMenuItem mi_NoteAlls;
    private javax.swing.JMenuItem mi_NoteCuts;
    private javax.swing.JMenuItem mi_NoteCopy;
    private javax.swing.JMenuItem mi_NotePast;
    private javax.swing.JMenuItem mi_NoteUndo;
    private javax.swing.JMenuItem mi_NoteRedo;
}
