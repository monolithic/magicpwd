/**
 * 
 */
package com.magicpwd._prop;

import com.magicpwd.MagicPwd;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.magicpwd._comp.IcoLabel;
import com.magicpwd.r.KindTN;
import com.magicpwd._comn.Kind;
import com.magicpwd.r.TreeCR;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IPropBean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;

/**
 * @author Amon
 * 
 */
public class KindProp extends JPanel implements IPropBean
{
    private boolean isUpdate;

    public KindProp()
    {
    }

    @Override
    public void initView()
    {
        initInfoView();
        initSortView();
        initBaseView();
    }

    @Override
    public void initLang()
    {
        initInfoLang();
        initSortLang();
        initBaseLang();
    }

    @Override
    public void initData()
    {
        tr_KindList.setModel(UserMdl.getTreeMdl());
    }

    @Override
    public JPanel getPanel()
    {
        return this;
    }

    private void initInfoView()
    {
        pl_ItemInfo = new javax.swing.JPanel();

        lb_ItemKind = new javax.swing.JLabel();
        tf_ItemKind = new javax.swing.JTextField();
        lb_ItemName = new javax.swing.JLabel();
        tf_ItemName = new javax.swing.JTextField();
        lb_ItemTips = new javax.swing.JLabel();
        tf_ItemTips = new javax.swing.JTextField();
        lb_ItemDesp = new javax.swing.JLabel();
        javax.swing.JScrollPane sp_ItemDesp = new javax.swing.JScrollPane();
        ta_ItemDesp = new javax.swing.JTextArea();
        bt_Delete = new javax.swing.JButton();
        bt_Update = new javax.swing.JButton();
        bt_Create = new javax.swing.JButton();

        tf_ItemKind.setColumns(16);
        tf_ItemKind.setText("0");
        tf_ItemKind.setEnabled(false);

        tf_ItemName.setColumns(16);

        tf_ItemTips.setColumns(16);

        sp_ItemDesp.setViewportView(ta_ItemDesp);

        bt_Delete.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DeleteActionPerformed(evt);
            }
        });

        bt_Update.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_UpdateActionPerformed(evt);
            }
        });

        bt_Create.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_ItemInfo);
        pl_ItemInfo.setLayout(layout);
        layout
                .setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                        layout.createSequentialGroup().addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                                        lb_ItemKind).addComponent(lb_ItemName).addComponent(lb_ItemTips))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tf_ItemKind, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                                                        tf_ItemName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                                                        tf_ItemTips, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(102,
                                        Short.MAX_VALUE)).addGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup().addContainerGap(67, Short.MAX_VALUE).addComponent(bt_Create)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
                                        bt_Update).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_Delete)).addComponent(sp_ItemDesp,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE).addGroup(
                        layout.createSequentialGroup().addComponent(lb_ItemDesp).addContainerGap(208, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_ItemKind).addComponent(tf_ItemKind,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_ItemName).addComponent(tf_ItemName,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_ItemTips).addComponent(tf_ItemTips,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lb_ItemDesp).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sp_ItemDesp,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_Delete)
                                .addComponent(bt_Update).addComponent(bt_Create))));
    }

    private void initSortView()
    {
        pl_SortItem = new javax.swing.JPanel();
        pl_SortItem.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        bt_SortLeft = new IcoLabel();
        bt_SortLeft.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_LEFT));
        bt_SortLeft.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SortLeftActionPerformed(evt);
            }
        });
        pl_SortItem.add(bt_SortLeft);

        bt_SortUp = new IcoLabel();
        bt_SortUp.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_PREV));
        bt_SortUp.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SortUpActionPerformed(evt);
            }
        });
        pl_SortItem.add(bt_SortUp);

        bt_SortDown = new IcoLabel();
        bt_SortDown.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_NEXT));
        bt_SortDown.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SortDownActionPerformed(evt);
            }
        });
        pl_SortItem.add(bt_SortDown);

        bt_SortRight = new IcoLabel();
        bt_SortRight.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_RGHT));
        bt_SortRight.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SortRightActionPerformed(evt);
            }
        });
        pl_SortItem.add(bt_SortRight);
    }

    private void initBaseView()
    {
        javax.swing.JScrollPane sp_ItemList = new javax.swing.JScrollPane();
        tr_KindList = new javax.swing.JTree();
        tr_KindList.setCellRenderer(new TreeCR());
        tr_KindList.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        javax.swing.ToolTipManager.sharedInstance().registerComponent(tr_KindList);
        sp_ItemList.setViewportView(tr_KindList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(
                                pl_SortItem, javax.swing.GroupLayout.Alignment.LEADING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE).addComponent(sp_ItemList, javax.swing.GroupLayout.Alignment.LEADING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pl_ItemInfo,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addComponent(sp_ItemList, javax.swing.GroupLayout.DEFAULT_SIZE, 174,
                        Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pl_SortItem, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(pl_ItemInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE));
    }

    private void initInfoLang()
    {
        Lang.setWText(lb_ItemKind, LangRes.P30F8361, "默认键值");
        Lang.setWText(lb_ItemName, LangRes.P30F8362, "显示名称");
        Lang.setWText(lb_ItemTips, LangRes.P30F8363, "提示信息");
        Lang.setWText(lb_ItemDesp, LangRes.P30F8365, "相关说明");

        Lang.setWText(bt_Update, LangRes.P30F8504, "保存(&S)");
        Lang.setWText(bt_Create, LangRes.P30F8505, "新增(&N)");
        Lang.setWText(bt_Delete, LangRes.P30F8506, "删除(&D)");
    }

    private void initSortLang()
    {
    }

    private void initBaseLang()
    {
    }

    private void bt_SortLeftActionPerformed(java.awt.event.ActionEvent evt)
    {
        TreePath path = tr_KindList.getSelectionPath();
        if (path == null)
        {
            return;
        }

        DefaultMutableTreeNode s = (DefaultMutableTreeNode) path.getLastPathComponent();
        DefaultMutableTreeNode p1 = (DefaultMutableTreeNode) s.getParent();
        if (p1 == null)
        {
            return;
        }

        DefaultMutableTreeNode p2 = (DefaultMutableTreeNode) p1.getParent();
        if (p2 == null)
        {
            return;
        }

        p1.remove(s);
        p2.add(s);
        UserMdl.getTreeMdl().nodeStructureChanged(p2);
        tr_KindList.setSelectionPath(new TreePath(s.getPath()));

        Kind u = (Kind) p2.getUserObject();
        Kind c = (Kind) s.getUserObject();
        c.setC2010101(p2.getChildCount());
        c.setC2010104(u.getC2010103());
        DBA3000.updateKindData(c);
    }

    private void bt_SortUpActionPerformed(java.awt.event.ActionEvent evt)
    {
        TreePath path = tr_KindList.getSelectionPath();
        if (path == null)
        {
            return;
        }

        DefaultMutableTreeNode p = (DefaultMutableTreeNode) path.getLastPathComponent();
        DefaultMutableTreeNode n = p.getPreviousSibling();
        if (n == null)
        {
            return;
        }
        DefaultMutableTreeNode o = (DefaultMutableTreeNode) p.getParent();
        if (o == null)
        {
            return;
        }

        int i = o.getIndex(p);
        o.remove(i--);
        o.insert(p, i);
        UserMdl.getTreeMdl().nodeStructureChanged(o);

        tr_KindList.setSelectionPath(path);

        Kind c = (Kind) p.getUserObject();
        c.addC2010101(1);
        DBA3000.updateKindData(c);
        c = (Kind) n.getUserObject();
        c.addC2010101(-1);
        DBA3000.updateKindData(c);
    }

    private void bt_SortDownActionPerformed(java.awt.event.ActionEvent evt)
    {
        TreePath path = tr_KindList.getSelectionPath();
        if (path == null)
        {
            return;
        }

        DefaultMutableTreeNode p = (DefaultMutableTreeNode) path.getLastPathComponent();
        DefaultMutableTreeNode n = p.getNextSibling();
        if (n == null)
        {
            return;
        }
        DefaultMutableTreeNode o = (DefaultMutableTreeNode) p.getParent();
        if (o == null)
        {
            return;
        }

        int i = o.getIndex(p);
        o.remove(i++);
        o.insert(p, i);
        UserMdl.getTreeMdl().nodeStructureChanged(o);

        tr_KindList.setSelectionPath(path);

        Kind c = (Kind) p.getUserObject();
        c.addC2010101(-1);
        DBA3000.updateKindData(c);
        c = (Kind) n.getUserObject();
        c.addC2010101(1);
        DBA3000.updateKindData(c);
    }

    private void bt_SortRightActionPerformed(java.awt.event.ActionEvent evt)
    {
        TreePath path = tr_KindList.getSelectionPath();
        if (path == null)
        {
            return;
        }

        DefaultMutableTreeNode s = (DefaultMutableTreeNode) path.getLastPathComponent();
        DefaultMutableTreeNode p = s.getPreviousSibling();
        if (p == null)
        {
            return;
        }

        DefaultMutableTreeNode o = (DefaultMutableTreeNode) p.getParent();
        o.remove(s);
        p.add(s);
        UserMdl.getTreeMdl().nodeStructureChanged(o);
        tr_KindList.setSelectionPath(new TreePath(s.getPath()));

        Kind u = (Kind) p.getUserObject();
        Kind c = (Kind) s.getUserObject();
        c.setC2010101(p.getChildCount());
        c.setC2010104(u.getC2010103());
        DBA3000.updateKindData(c);
    }

    private void bt_CreateActionPerformed(java.awt.event.ActionEvent evt)
    {
        isUpdate = false;
        TreePath path = tr_KindList.getSelectionPath();
        if (path == null)
        {
            return;
        }
        KindTN node = (KindTN) path.getLastPathComponent();
        Kind item = (Kind) node.getUserObject();

        if (item == null)
        {
            Lang.showMesg(this, "", "请选择您要更新的类别数据！");
            return;
        }

        tf_ItemName.setText(item.getC2010105());
        tf_ItemTips.setText(item.getC2010106());
        ta_ItemDesp.setText(item.getC2010108());
        isUpdate = true;
    }

    private void bt_UpdateActionPerformed(java.awt.event.ActionEvent evt)
    {
        TreePath path = tr_KindList.getSelectionPath();
        if (path == null)
        {
            Lang.showMesg(this, "", "请选择更新类别名称！");
            return;
        }

        String name = tf_ItemName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, "", "类别名称不能为空！");
            tf_ItemName.requestFocus();
            return;
        }

        Kind item;
        if (isUpdate)
        {
            KindTN node = (KindTN) path.getLastPathComponent();
            item = (Kind) node.getUserObject();
            item.setC2010105(name);
            item.setC2010106(tf_ItemTips.getText());
            item.setC2010108(ta_ItemDesp.getText());
            UserMdl.getTreeMdl().wUpdate(path, item);
        }
        else
        {
            item = new Kind();
            item.setC2010105(name);
            item.setC2010106(tf_ItemTips.getText());
            item.setC2010108(ta_ItemDesp.getText());
            UserMdl.getTreeMdl().wAppend(path, item);
        }

        tf_ItemName.setText("");
        tf_ItemTips.setText("");
        ta_ItemDesp.setText("");
        isUpdate = false;
    }

    private void bt_DeleteActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (tr_KindList.getSelectionPath() == null)
        {
            Lang.showMesg(MagicPwd.getCurrForm(), "", "请选择您要删除的类别数据！");
            tr_KindList.requestFocus();
            return;
        }

        if (Lang.showFirm(MagicPwd.getCurrForm(), LangRes.P30F7A1A, "") == JOptionPane.YES_OPTION)
        {
            UserMdl.getTreeMdl().wRemove(tr_KindList.getSelectionPath());
        }
    }

    private javax.swing.JPanel pl_ItemInfo;
    private javax.swing.JButton bt_Create;
    private javax.swing.JButton bt_Delete;
    private javax.swing.JButton bt_Update;
    private javax.swing.JLabel lb_ItemName;
    private javax.swing.JTextField tf_ItemName;
    private javax.swing.JLabel lb_ItemTips;
    private javax.swing.JTextField tf_ItemTips;
    private javax.swing.JLabel lb_ItemKind;
    private javax.swing.JTextField tf_ItemKind;
    private javax.swing.JLabel lb_ItemDesp;
    private javax.swing.JTextArea ta_ItemDesp;

    private IcoLabel bt_SortLeft;
    private IcoLabel bt_SortRight;
    private IcoLabel bt_SortUp;
    private IcoLabel bt_SortDown;

    private javax.swing.JTree tr_KindList;
    private javax.swing.JPanel pl_SortItem;
}
