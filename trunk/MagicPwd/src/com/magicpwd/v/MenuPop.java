/**
 * 
 */
package com.magicpwd.v;

import javax.swing.JPopupMenu;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.c.ToolEvt;

/**
 * @author Amon
 * 
 */
public class MenuPop extends JPopupMenu
{
    private static final long serialVersionUID = -4427688180617357960L;

    public static final int MENU_GRID = 0;
    public static final int MENU_TREE = 1;
    public static final int MENU_LIST = 2;

    private int menuType;
    private ToolEvt toolEvt;

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
            default:
                break;
        }
    }

    public void setActionEvent(ToolEvt event)
    {
        toolEvt = event;
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

        mi_ListDelt = new javax.swing.JMenuItem();
        mi_ListDelt.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                toolEvt.fileDeltActionPerformed(evt);
            }
        });
        add(mi_ListDelt);

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

        Lang.setWText(mi_ListDelt, LangRes.P30F7D06, "删除记录");

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

    private javax.swing.JMenuItem mi_TreeApnd;
    private javax.swing.JMenuItem mi_TreeUpdt;
    private javax.swing.JMenuItem mi_TreeDelt;
    private javax.swing.JMenuItem mi_TreeImpt;

    private javax.swing.JMenuItem mi_ListApnd;
    private javax.swing.JMenuItem mi_ListDelt;
    private javax.swing.JMenuItem mi_KindMove;
    private javax.swing.JMenuItem mi_HistView;

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
}
