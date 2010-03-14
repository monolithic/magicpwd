/**
 * 
 */
package com.magicpwd._prop;

import com.magicpwd.MagicPwd;
import com.magicpwd._comn.S1S2;

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
 * @author Amon
 * 
 */
public class TpltProp extends javax.swing.JPanel implements IPropBean
{

    private javax.swing.DefaultComboBoxModel cm_TpltList;
    private javax.swing.tree.DefaultTreeModel tm_TpltList;
    private javax.swing.tree.DefaultMutableTreeNode rootNode;
    private javax.swing.tree.DefaultMutableTreeNode currNode;
    private Tplt currTplt;
    private boolean isUpdate;

    public TpltProp()
    {
    }

    @Override
    public void initView()
    {
        initSortView();
        initInfoView();
        initBaseView();
    }

    @Override
    public void initLang()
    {
        initSortLang();
        initInfoLang();
        initBaseLang();
    }

    @Override
    public void initData()
    {
        if (tm_TpltList == null)
        {
            rootNode = new javax.swing.tree.DefaultMutableTreeNode("/");

            java.util.List<Tplt> dataList;
            javax.swing.tree.DefaultMutableTreeNode node;
            for (Tplt kind : UserMdl.getCboxMdl().getAllItems())
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
        if (cm_TpltList == null)
        {
            cm_TpltList = new javax.swing.DefaultComboBoxModel();
            cm_TpltList.addElement("类别");
            cm_TpltList.addElement("文本");
            cm_TpltList.addElement("口令");
            cm_TpltList.addElement("链接");
            cm_TpltList.addElement("邮件");
            cm_TpltList.addElement("日期");
            cm_TpltList.addElement("附注");
            cm_TpltList.addElement("附件");
            cb_TpltKind.setModel(cm_TpltList);
        }
    }

    @Override
    public void saveData()
    {
    }

    @Override
    public javax.swing.JPanel getPanel()
    {
        return this;
    }

    private void initInfoView()
    {
        pl_ItemInfo = new javax.swing.JPanel();

        lb_TpltKind = new javax.swing.JLabel();
        cb_TpltKind = new javax.swing.JComboBox();
        lb_TpltName = new javax.swing.JLabel();
        tf_TpltName = new javax.swing.JTextField();
        lb_TpltTips = new javax.swing.JLabel();
        tf_TpltTips = new javax.swing.JTextField();
        lb_TpltDesp = new javax.swing.JLabel();
        javax.swing.JScrollPane sp_ItemDesp = new javax.swing.JScrollPane();
        ta_TpltDesp = new javax.swing.JTextArea();

        tf_TpltName.setColumns(16);

        tf_TpltTips.setColumns(16);

        sp_ItemDesp.setViewportView(ta_TpltDesp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_ItemInfo);
        pl_ItemInfo.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_TpltDesp);
        hpg1.addComponent(lb_TpltTips);
        hpg1.addComponent(lb_TpltName);
        hpg1.addComponent(lb_TpltKind);
        hpg1.addComponent(pl_ItemSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(cb_TpltKind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_TpltName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_TpltTips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(sp_ItemDesp, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_TpltKind);
        vpg1.addComponent(cb_TpltKind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_TpltName);
        vpg2.addComponent(tf_TpltName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lb_TpltTips);
        vpg3.addComponent(tf_TpltTips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addComponent(lb_TpltDesp);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(pl_ItemSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg4.addGroup(vsg1);
        vpg4.addComponent(sp_ItemDesp, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup vsg2 = layout.createSequentialGroup();
        vsg2.addGroup(vpg1);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addGroup(vpg2);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addGroup(vpg3);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addGroup(vpg4);
        layout.setVerticalGroup(vsg2);
    }

    private void initSortView()
    {
        pl_ItemSort = new javax.swing.JPanel();

        bt_DropData = new IcoLabel();
        bt_DropData.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_DELT));
        bt_DropData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dropDataActionPerformed(evt);
            }
        });

        bt_SaveData = new IcoLabel();
        bt_SaveData.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_SAVE));
        bt_SaveData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveDataActionPerformed(evt);
            }
        });

        bt_ApndData = new IcoLabel();
        bt_ApndData.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_APND));
        bt_ApndData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                apndDataActionPerformed(evt);
            }
        });
        bt_SortU = new IcoLabel();
        bt_SortU.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_PREV));
        bt_SortU.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sortUActionPerformed(evt);
            }
        });
        add(bt_SortU);

        bt_SortD = new IcoLabel();
        bt_SortD.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_NEXT));
        bt_SortD.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sortDActionPerformed(evt);
            }
        });
        add(bt_SortD);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_ItemSort);
        pl_ItemSort.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(bt_SortU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg1.addComponent(bt_SortD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(bt_ApndData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(bt_SaveData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(bt_DropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap(1, Short.MAX_VALUE);
        vsg1.addComponent(bt_ApndData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(bt_SaveData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(bt_DropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg2 = layout.createSequentialGroup();
        vsg2.addContainerGap(1, Short.MAX_VALUE);
        vsg2.addComponent(bt_SortU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addComponent(bt_SortD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(vsg1);
        vpg.addGroup(vsg2);
        layout.setVerticalGroup(vpg);
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

            @Override
            public void valueChanged(TreeSelectionEvent arg0)
            {
                tr_TpltListValueChanged(arg0);
            }
        });
        javax.swing.ToolTipManager.sharedInstance().registerComponent(tr_TpltList);
        sp_ItemList.setViewportView(tr_TpltList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(sp_ItemList, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg.addComponent(pl_ItemSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(pl_ItemInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setHorizontalGroup(hsg);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addComponent(sp_ItemList, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE);
        vpg.addComponent(pl_ItemSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE);
        vpg.addComponent(pl_ItemInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE);
        layout.setVerticalGroup(vpg);
    }

    private void initInfoLang()
    {
        Lang.setWText(lb_TpltKind, LangRes.P30F8341, "属性类型");
        Lang.setWText(lb_TpltName, LangRes.P30F8342, "属性名称");
        Lang.setWText(lb_TpltTips, LangRes.P30F8343, "默认数据");
        Lang.setWText(lb_TpltDesp, LangRes.P30F8344, "相关说明");
    }

    private void initSortLang()
    {
        Lang.setWText(bt_ApndData, LangRes.P30F8509, "&N");
        Lang.setWTips(bt_ApndData, LangRes.P30F850A, "新增(Alt + N)");

        Lang.setWText(bt_SaveData, LangRes.P30F8507, "&S");
        Lang.setWTips(bt_SaveData, LangRes.P30F8507, "保存(Alt + S)");

        Lang.setWText(bt_DropData, LangRes.P30F850B, "&D");
        Lang.setWTips(bt_DropData, LangRes.P30F850B, "删除(Alt + D)");
    }

    private void initBaseLang()
    {
    }

    private void sortUActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (currNode == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode p = currNode;
        javax.swing.tree.DefaultMutableTreeNode n = p.getPreviousSibling();
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
        o.remove(i--);
        o.insert(p, i);
        tm_TpltList.nodeStructureChanged(o);

        tr_TpltList.setSelectionPath(new javax.swing.tree.TreePath(p.getPath()));

        // 上移
        Tplt c = (Tplt) p.getUserObject();
        c.addP30F1101(-1);
        DBA3000.saveTpltData(c);

        // 下移
        c = (Tplt) n.getUserObject();
        c.addP30F1101(1);
        DBA3000.saveTpltData(c);
    }

    private void sortDActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (currNode == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode p = currNode;
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

        tr_TpltList.setSelectionPath(new javax.swing.tree.TreePath(p.getPath()));

        Tplt c = (Tplt) p.getUserObject();
        c.addP30F1101(1);
        DBA3000.saveTpltData(c);
        c = (Tplt) n.getUserObject();
        c.addP30F1101(-1);
        DBA3000.saveTpltData(c);
    }

    private void apndDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        currTplt = new Tplt();
        viewInfo(currTplt);
        isUpdate = false;
    }

    private void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        int indx = cb_TpltKind.getSelectedIndex();
        if (indx < 0)
        {
            Lang.showMesg(this, LangRes.P30F8A05, "请选择属性类别！");
            cb_TpltKind.requestFocus();
            return;
        }

        String name = tf_TpltName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30F8A06, "类别名称不能为空！");
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

        if (currTplt == null)
        {
            currTplt = new Tplt();
        }

        currTplt.setP30F1102(indx);
        currTplt.setP30F1105(name);
        currTplt.setP30F1106(tf_TpltTips.getText());
        currTplt.setP30F1107(ta_TpltDesp.getText());

        if (isUpdate)
        {
            DBA3000.saveTpltData(currTplt);
            tm_TpltList.nodeChanged(currNode);
            UserMdl.getCboxMdl().wUpdate();

            isUpdate = false;
        }
        else
        {
            javax.swing.tree.DefaultMutableTreeNode node;
            if (indx == 0)
            {
                node = rootNode;
                currTplt.setP30F1104("0");
            }
            else
            {
                if (currNode == null)
                {
                    Lang.showMesg(this, LangRes.P30F8A07, "请选择属性对应的模板！");
                    tr_TpltList.requestFocus();
                    return;
                }
                node = (javax.swing.tree.DefaultMutableTreeNode) tr_TpltList.getSelectionPath().getPath()[1];
                currTplt.setP30F1104(((Tplt) node.getUserObject()).getP30F1103());
            }
            currTplt.setP30F1101(node.getChildCount());
            DBA3000.saveTpltData(currTplt);
            node.add(new javax.swing.tree.DefaultMutableTreeNode(currTplt));
            tm_TpltList.nodeStructureChanged(node);

            if (indx == 0)
            {
                UserMdl.getCboxMdl().wAppend(currTplt);
            }
        }

        currTplt = new Tplt();
        viewInfo(currTplt);
        isUpdate = false;
    }

    private void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_TpltList.getSelectionPath();
        if (path == null)
        {
            Lang.showMesg(MagicPwd.getCurrForm(), LangRes.P30F8A08, "请选择您要删除的数据！");
            tr_TpltList.requestFocus();
            return;
        }

        if (Lang.showFirm(MagicPwd.getCurrForm(), LangRes.P30F8A09, "确认要删除此数据吗，此操作将不可恢复？") == JOptionPane.YES_OPTION)
        {
            javax.swing.tree.DefaultMutableTreeNode node = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
            DBA3000.deleteTpltData((S1S2) node.getUserObject());
            tm_TpltList.removeNodeFromParent((javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent());
        }
    }

    public void tr_TpltListValueChanged(javax.swing.event.TreeSelectionEvent evt)
    {
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
        if (!(obj instanceof Tplt))
        {
            return;
        }
        currTplt = (Tplt) currNode.getUserObject();
        viewInfo(currTplt);
        isUpdate = true;
    }

    private void viewInfo(Tplt item)
    {
        cb_TpltKind.setSelectedIndex(item.getP30F1102());
        cb_TpltKind.setEnabled(item.getP30F1102() != 0);

        String text = item.getP30F1105();
        if (text != null)
        {
            text = text.replaceAll('^' + ConsDat.SP_TPL_LS + '|' + ConsDat.SP_TPL_RS + '$', "");
        }
        tf_TpltName.setText(text);

        text = item.getP30F1106();
        if (text != null)
        {
            text = text.replaceAll('^' + ConsDat.SP_TPL_LS + '|' + ConsDat.SP_TPL_RS + '$', "");
        }
        tf_TpltTips.setText(text);

        ta_TpltDesp.setText(item.getP30F1107());
    }
    private javax.swing.JPanel pl_ItemInfo;
    private javax.swing.JLabel lb_TpltKind;
    private javax.swing.JComboBox cb_TpltKind;
    private javax.swing.JLabel lb_TpltName;
    private javax.swing.JTextField tf_TpltName;
    private javax.swing.JLabel lb_TpltTips;
    private javax.swing.JTextField tf_TpltTips;
    private javax.swing.JLabel lb_TpltDesp;
    private javax.swing.JTextArea ta_TpltDesp;
    private IcoLabel bt_ApndData;
    private IcoLabel bt_SaveData;
    private IcoLabel bt_DropData;
    private IcoLabel bt_SortU;
    private IcoLabel bt_SortD;
    private javax.swing.JTree tr_TpltList;
    private javax.swing.JPanel pl_ItemSort;
}
