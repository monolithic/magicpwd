/**
 * 
 */
package com.magicpwd._prop;

import com.magicpwd._comn.S1S2;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeSelectionModel;

import com.magicpwd._comp.IcoLabel;
import com.magicpwd._comn.Tplt;
import com.magicpwd.r.TreeCR;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IPropBean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;

/**
 * @author amon
 * 
 */
public class TpltProp extends javax.swing.JPanel implements IPropBean
{
    private javax.swing.tree.DefaultTreeModel tm_TpltList;
    private javax.swing.tree.DefaultMutableTreeNode rootNode;
    private javax.swing.tree.DefaultMutableTreeNode currNode;
    private Tplt currTplt;
    private boolean isUpdate = true;

    public TpltProp()
    {
    }

    public void initView()
    {
        initInfoView();
        initSortView();
        initBaseView();
    }

    public void initLang()
    {
        initInfoLang();
        initSortLang();
        initBaseLang();
    }

    public void initData()
    {
        rootNode = new javax.swing.tree.DefaultMutableTreeNode("/");
        java.util.List<Tplt> kindList = UserMdl.getCboxMdl().getAllItems();

        java.util.List<Tplt> dataList;
        javax.swing.tree.DefaultMutableTreeNode node;
        for (Tplt kind : kindList)
        {
            node = new javax.swing.tree.DefaultMutableTreeNode(kind);

            dataList = DBA3000.selectTpltData(kind.getP30F1103());
            for (Tplt item : dataList)
            {
                node.add(new javax.swing.tree.DefaultMutableTreeNode(item));
            }

            rootNode.add(node);
        }

        tm_TpltList = new javax.swing.tree.DefaultTreeModel(rootNode);
        tr_TpltList.setModel(tm_TpltList);
    }

    public javax.swing.JPanel getPanel()
    {
        return this;
    }

    private void initInfoView()
    {
        pl_ItemInfo = new javax.swing.JPanel();

        lb_TpltKind = new javax.swing.JLabel();
        cb_TpltKind = new javax.swing.JComboBox(new String[]{"类别", "文本", "口令", "链接", "邮件", "日期", "附注", "附件"});
        lb_TpltName = new javax.swing.JLabel();
        tf_TpltName = new javax.swing.JTextField();
        lb_TpltTips = new javax.swing.JLabel();
        tf_TpltTips = new javax.swing.JTextField();
        lb_TpltDesp = new javax.swing.JLabel();
        javax.swing.JScrollPane sp_ItemDesp = new javax.swing.JScrollPane();
        ta_TpltDesp = new javax.swing.JTextArea();
        bt_Delete = new javax.swing.JButton();
        bt_Update = new javax.swing.JButton();
        bt_Create = new javax.swing.JButton();

        tf_TpltName.setColumns(16);

        tf_TpltTips.setColumns(16);

        sp_ItemDesp.setViewportView(ta_TpltDesp);

        bt_Delete.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DeleteActionPerformed(evt);
            }
        });

        bt_Update.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_UpdateActionPerformed(evt);
            }
        });

        bt_Create.addActionListener(new java.awt.event.ActionListener()
        {
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
                                        lb_TpltKind).addComponent(lb_TpltName).addComponent(lb_TpltTips))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cb_TpltKind, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                                                        tf_TpltName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                                                        tf_TpltTips, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(102,
                                        Short.MAX_VALUE)).addGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup().addContainerGap(67, Short.MAX_VALUE).addComponent(bt_Create)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
                                        bt_Update).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_Delete)).addComponent(sp_ItemDesp,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE).addGroup(
                        layout.createSequentialGroup().addComponent(lb_TpltDesp).addContainerGap(208, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_TpltKind).addComponent(cb_TpltKind,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_TpltName).addComponent(tf_TpltName,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_TpltTips).addComponent(tf_TpltTips,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lb_TpltDesp).addPreferredGap(
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

        bt_SortUp = new IcoLabel();
        bt_SortUp.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_ITEM_PREV)));
        bt_SortUp.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SortUpActionPerformed(evt);
            }
        });
        pl_SortItem.add(bt_SortUp);

        bt_SortDown = new IcoLabel();
        bt_SortDown.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_ITEM_NEXT)));
        bt_SortDown.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SortDownActionPerformed(evt);
            }
        });
        pl_SortItem.add(bt_SortDown);
    }

    private void initBaseView()
    {
        javax.swing.JScrollPane sp_ItemList = new javax.swing.JScrollPane();
        tr_TpltList = new javax.swing.JTree();
        tr_TpltList.setRootVisible(false);
        tr_TpltList.setCellRenderer(new TreeCR());
        tr_TpltList.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tr_TpltList.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener()
        {
            public void valueChanged(TreeSelectionEvent arg0)
            {
                tr_TpltListValueChanged(arg0);
            }
        });
        javax.swing.ToolTipManager.sharedInstance().registerComponent(tr_TpltList);
        sp_ItemList.setViewportView(tr_TpltList);

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
        Lang.setWText(lb_TpltKind, LangRes.P30F8341, "属性类型");
        Lang.setWText(lb_TpltName, LangRes.P30F8342, "属性名称");
        Lang.setWText(lb_TpltTips, LangRes.P30F8343, "默认数据");
        Lang.setWText(lb_TpltDesp, LangRes.P30F8344, "相关说明");
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

    private void bt_SortUpActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (currNode == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode n = currNode.getPreviousSibling();
        if (n == null)
        {
            return;
        }
        javax.swing.tree.DefaultMutableTreeNode o = (javax.swing.tree.DefaultMutableTreeNode) currNode.getParent();
        if (o == null)
        {
            return;
        }

        int i = o.getIndex(currNode);
        o.remove(i--);
        o.insert(currNode, i);
        tm_TpltList.nodeStructureChanged(o);

        //tr_TpltList.setSelectionPath(currNode.get);

        // 上移
        Tplt c = (Tplt) currNode.getUserObject();
        c.addP30F1101(-1);
        DBA3000.updateTpltData(c);

        // 下移
        c = (Tplt) n.getUserObject();
        c.addP30F1101(1);
        DBA3000.updateTpltData(c);
    }

    private void bt_SortDownActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_TpltList.getSelectionPath();
        if (path == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode p = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
        javax.swing.tree.DefaultMutableTreeNode n = p.getNextSibling();
        if (n == null)
        {
            return;
        }
        javax.swing.tree.DefaultMutableTreeNode o = (javax.swing.tree.DefaultMutableTreeNode) p.getParent();
        if (o == null)
        {
            return;
        }

        int i = o.getIndex(p);
        o.remove(i++);
        o.insert(p, i);
        tm_TpltList.nodeStructureChanged(o);

        tr_TpltList.setSelectionPath(path);

        Tplt c = (Tplt) p.getUserObject();
        c.addP30F1101(-1);
        DBA3000.updateTpltData(c);
        c = (Tplt) n.getUserObject();
        c.addP30F1101(1);
        DBA3000.updateTpltData(c);
    }

    private void bt_CreateActionPerformed(java.awt.event.ActionEvent evt)
    {
        currTplt = new Tplt();
        viewInfo(currTplt);
        isUpdate = false;
    }

    private void bt_UpdateActionPerformed(java.awt.event.ActionEvent evt)
    {
        int indx = cb_TpltKind.getSelectedIndex();
        if (indx < 0)
        {
            Lang.showMesg(this, "", "请选择属性类别！");
            cb_TpltKind.requestFocus();
            return;
        }

        String name = tf_TpltName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, "", "类别名称不能为空！");
            tf_TpltName.requestFocus();
            return;
        }

        if (!name.startsWith(ConsDat.SP_TPL_LS))
        {
            name = ConsDat.SP_TPL_LS + name;
        }
        if (!name.endsWith(ConsDat.SP_TPL_RS))
        {
            name = name + ConsDat.SP_TPL_RS;
        }
        javax.swing.tree.TreePath path = tr_TpltList.getSelectionPath();
        if (path == null)
        {
            Lang.showMesg(this, "", "请选择属性对应的模板！");
            tr_TpltList.requestFocus();
            return;
        }
        javax.swing.tree.DefaultMutableTreeNode node = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();

        if (isUpdate)
        {
            currTplt.setP30F1102(indx);
            currTplt.setP30F1105(name);
            currTplt.setP30F1106(tf_TpltTips.getText());
            currTplt.setP30F1107(ta_TpltDesp.getText());
            DBA3000.updateTpltData(currTplt);
            tm_TpltList.nodeChanged(node);
            UserMdl.getCboxMdl().wUpdate();

            isUpdate = false;
        }
        else
        {
            Tplt kind = (Tplt) node.getUserObject();

            currTplt.setP30F1101(node.getChildCount());
            currTplt.setP30F1102(indx);
            currTplt.setP30F1104(indx == 0 ? "0" : kind.getP30F1103());
            currTplt.setP30F1105(name);
            currTplt.setP30F1106(tf_TpltTips.getText());
            currTplt.setP30F1107(ta_TpltDesp.getText());
            DBA3000.updateTpltData(currTplt);
            node.add(new javax.swing.tree.DefaultMutableTreeNode(currTplt));
            tm_TpltList.nodeStructureChanged(node);

            if (indx == 0)
            {
                UserMdl.getCboxMdl().wAppend(currTplt);
            }
        }

        tf_TpltName.setText("");
        tf_TpltTips.setText("");
        ta_TpltDesp.setText("");
    }

    private void bt_DeleteActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_TpltList.getSelectionPath();
        if (path == null)
        {
            Lang.showMesg(this, "", "请选择您要删除的类别数据！");
            tr_TpltList.requestFocus();
            return;
        }

        if (Lang.showFirm(this, "", "确认要删除此数据吗，此操作将不可恢复？") == JOptionPane.YES_OPTION)
        {
            javax.swing.tree.DefaultMutableTreeNode node = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
            DBA3000.deleteTpltData((S1S2) node.getUserObject());
            tm_TpltList.removeNodeFromParent((javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent());
        }
    }

    public void tr_TpltListValueChanged(javax.swing.event.TreeSelectionEvent evt)
    {
        if (!isUpdate)
        {
            return;
        }
        javax.swing.tree.TreePath path = tr_TpltList.getSelectionPath();
        if (path == null)
        {
            return;
        }
        currNode = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
        if (currNode == null)
        {
            return;
        }
        Object obj = currNode.getUserObject();
        if (obj instanceof Tplt)
        {
            currTplt = (Tplt) currNode.getUserObject();
            viewInfo(currTplt);
            isUpdate = true;
        }
    }

    private void viewInfo(Tplt item)
    {
        String text = item.getP30F1105();
        if (text.startsWith(ConsDat.SP_TPL_LS))
        {
            text = text.substring(1);
        }
        if (text.endsWith(ConsDat.SP_TPL_RS))
        {
            text = text.substring(0, text.length() - 1);
        }
        tf_TpltName.setText(text);

        text = item.getP30F1106();
        if (text.startsWith(ConsDat.SP_TPL_LS))
        {
            text = text.substring(1);
        }
        if (text.endsWith(ConsDat.SP_TPL_RS))
        {
            text = text.substring(0, text.length() - 1);
        }
        tf_TpltTips.setText(text);

        ta_TpltDesp.setText(item.getP30F1107());
    }

    private javax.swing.JPanel pl_ItemInfo;
    private javax.swing.JButton bt_Create;
    private javax.swing.JButton bt_Delete;
    private javax.swing.JButton bt_Update;
    private javax.swing.JLabel lb_TpltKind;
    private javax.swing.JComboBox cb_TpltKind;
    private javax.swing.JLabel lb_TpltName;
    private javax.swing.JTextField tf_TpltName;
    private javax.swing.JLabel lb_TpltTips;
    private javax.swing.JTextField tf_TpltTips;
    private javax.swing.JLabel lb_TpltDesp;
    private javax.swing.JTextArea ta_TpltDesp;

    private IcoLabel bt_SortUp;
    private IcoLabel bt_SortDown;

    private javax.swing.JTree tr_TpltList;
    private javax.swing.JPanel pl_SortItem;
}