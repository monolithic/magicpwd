/**
 * 
 */
package com.magicpwd._prop;

import com.magicpwd.__i.IPropBean;
import com.magicpwd._comp.IcoLabel;
import com.magicpwd._comn.prop.Kind;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.r.TreeCR;
import com.magicpwd.v.mpwd.MainPtn;

/**
 * @author Amon
 * 
 */
public class KindProp extends javax.swing.JPanel implements IPropBean
{

    private javax.swing.tree.DefaultMutableTreeNode treeNode;
    private Kind kindItem;
    private boolean isUpdate;
    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public KindProp(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
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
        tr_KindList.setModel(mainPtn.getTreeMdl());

        kindItem = new Kind();
        viewInfo(kindItem);
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

        tf_KindKind = new javax.swing.JTextField(16);
        lb_KindKind = new javax.swing.JLabel();
        lb_KindKind.setLabelFor(tf_KindKind);

        tf_KindName = new javax.swing.JTextField(16);
        lb_KindName = new javax.swing.JLabel();
        lb_KindName.setLabelFor(tf_KindName);

        tf_KindTips = new javax.swing.JTextField(16);
        lb_KindTips = new javax.swing.JLabel();
        lb_KindTips.setLabelFor(tf_KindTips);

        ta_KindDesp = new javax.swing.JTextArea();
        ta_KindDesp.setLineWrap(true);
        javax.swing.JScrollPane sp_ItemDesp = new javax.swing.JScrollPane(ta_KindDesp);
        lb_KindDesp = new javax.swing.JLabel();
        lb_KindDesp.setLabelFor(ta_KindDesp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_ItemInfo);
        pl_ItemInfo.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_KindDesp);
        hpg1.addComponent(lb_KindTips);
        hpg1.addComponent(lb_KindName);
        hpg1.addComponent(lb_KindKind);
        hpg1.addComponent(pl_ItemSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_KindName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_KindTips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_KindKind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(sp_ItemDesp, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_KindName);
        vpg1.addComponent(tf_KindName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_KindTips);
        vpg2.addComponent(tf_KindTips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lb_KindKind);
        vpg3.addComponent(tf_KindKind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addComponent(lb_KindDesp);
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
        bt_DropData.setIcon(coreMdl.getUserCfg().readIcon(ConsEnv.FEEL_PATH + "file-delete.png"));
        bt_DropData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dropDataActionPerformed(evt);
            }
        });

        bt_SaveData = new IcoLabel();
        bt_SaveData.setIcon(coreMdl.getUserCfg().readIcon(ConsEnv.FEEL_PATH + "file-save.png"));
        bt_SaveData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveDataActionPerformed(evt);
            }
        });

        bt_ApndData = new IcoLabel();
        bt_ApndData.setIcon(coreMdl.getUserCfg().readIcon(ConsEnv.FEEL_PATH + "file-new.png"));
        bt_ApndData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                apndDataActionPerformed(evt);
            }
        });
        bt_SortU = new IcoLabel();
        bt_SortU.setIcon(coreMdl.getUserCfg().readIcon(ConsEnv.FEEL_PATH + "edit-move-up.png"));
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
        bt_SortD.setIcon(coreMdl.getUserCfg().readIcon(ConsEnv.FEEL_PATH + "edit-move-down.png"));
        bt_SortD.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sortDActionPerformed(evt);
            }
        });
        add(bt_SortD);

        bt_SortL = new IcoLabel();
        bt_SortL.setIcon(coreMdl.getUserCfg().readIcon(ConsEnv.FEEL_PATH + "edit-move-left.png"));
        bt_SortL.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sortLActionPerformed(evt);
            }
        });
        add(bt_SortL);

        bt_SortR = new IcoLabel();
        bt_SortR.setIcon(coreMdl.getUserCfg().readIcon(ConsEnv.FEEL_PATH + "edit-move-right.png"));
        bt_SortR.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sortRActionPerformed(evt);
            }
        });
        add(bt_SortR);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_ItemSort);
        pl_ItemSort.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(bt_SortU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg1.addComponent(bt_SortD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg1.addComponent(bt_SortL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg1.addComponent(bt_SortR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addComponent(bt_SortL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addComponent(bt_SortR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(vsg1);
        vpg.addGroup(vsg2);
        layout.setVerticalGroup(vpg);
    }

    private void initBaseView()
    {
        javax.swing.JScrollPane sp_ItemList = new javax.swing.JScrollPane();
        tr_KindList = new javax.swing.JTree();
        tr_KindList.setCellRenderer(new TreeCR());
        tr_KindList.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
        tr_KindList.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener()
        {

            @Override
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt)
            {
                tr_KindListValueChanged(evt);
            }
        });
        javax.swing.ToolTipManager.sharedInstance().registerComponent(tr_KindList);
        sp_ItemList.setViewportView(tr_KindList);

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
        Lang.setWText(lb_KindKind, LangRes.P30F8361, "默认键值");
        Lang.setWText(lb_KindName, LangRes.P30F8362, "显示名称");
        Lang.setWText(lb_KindTips, LangRes.P30F8363, "提示信息");
        Lang.setWText(lb_KindDesp, LangRes.P30F8365, "相关说明");
    }

    private void initSortLang()
    {
        Lang.setWText(bt_ApndData, LangRes.P30F8509, "@N");
        Lang.setWTips(bt_ApndData, LangRes.P30F850A, "新增(Alt + N)");

        Lang.setWText(bt_SaveData, LangRes.P30F8507, "@S");
        Lang.setWTips(bt_SaveData, LangRes.P30F8508, "保存(Alt + S)");

        Lang.setWText(bt_DropData, LangRes.P30F850B, "@D");
        Lang.setWTips(bt_DropData, LangRes.P30F850C, "删除(Alt + D)");
    }

    private void initBaseLang()
    {
    }

    private void sortLActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_KindList.getSelectionPath();
        if (path == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode s = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
        javax.swing.tree.DefaultMutableTreeNode p1 = (javax.swing.tree.DefaultMutableTreeNode) s.getParent();
        if (p1 == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode p2 = (javax.swing.tree.DefaultMutableTreeNode) p1.getParent();
        if (p2 == null)
        {
            return;
        }

        p1.remove(s);
        p2.add(s);
        mainPtn.getTreeMdl().nodeStructureChanged(p2);
        tr_KindList.setSelectionPath(new javax.swing.tree.TreePath(s.getPath()));

        Kind u = (Kind) p2.getUserObject();
        Kind c = (Kind) s.getUserObject();
        c.setC2010101(p2.getChildCount());
        c.setC2010104(u.getC2010103());
        DBA3000.updateKindData(c);
    }

    private void sortUActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_KindList.getSelectionPath();
        if (path == null)
        {
            return;
        }

        treeNode = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
        javax.swing.tree.DefaultMutableTreeNode p = treeNode;
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
        mainPtn.getTreeMdl().nodeStructureChanged(o);

        tr_KindList.setSelectionPath(path);

        Kind c = (Kind) p.getUserObject();
        c.addC2010101(-1);
        DBA3000.updateKindData(c);
        c = (Kind) n.getUserObject();
        c.addC2010101(1);
        DBA3000.updateKindData(c);
    }

    private void sortDActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_KindList.getSelectionPath();
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
        mainPtn.getTreeMdl().nodeStructureChanged(o);

        tr_KindList.setSelectionPath(path);

        Kind c = (Kind) p.getUserObject();
        c.addC2010101(1);
        DBA3000.updateKindData(c);
        c = (Kind) n.getUserObject();
        c.addC2010101(-1);
        DBA3000.updateKindData(c);
    }

    private void sortRActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_KindList.getSelectionPath();
        if (path == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode s = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
        javax.swing.tree.DefaultMutableTreeNode p = s.getPreviousSibling();
        if (p == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode o = (javax.swing.tree.DefaultMutableTreeNode) p.getParent();
        o.remove(s);
        p.add(s);
        mainPtn.getTreeMdl().nodeStructureChanged(o);
        tr_KindList.setSelectionPath(new javax.swing.tree.TreePath(s.getPath()));

        Kind u = (Kind) p.getUserObject();
        Kind c = (Kind) s.getUserObject();
        c.setC2010101(p.getChildCount());
        c.setC2010104(u.getC2010103());
        DBA3000.updateKindData(c);
    }

    private void apndDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        kindItem = new Kind();
        viewInfo(kindItem);
        isUpdate = false;
    }

    private void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_KindName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30F8A06, "类别名称不能为空！");
            tf_KindName.requestFocus();
            return;
        }

        javax.swing.tree.TreePath path = tr_KindList.getSelectionPath();
        javax.swing.tree.DefaultMutableTreeNode node = (javax.swing.tree.DefaultMutableTreeNode) (path != null ? path.getLastPathComponent() : mainPtn.getTreeMdl().getRoot());

        if (kindItem == null)
        {
            kindItem = new Kind();
        }
        kindItem.setC2010105(name);
        kindItem.setC2010106(tf_KindTips.getText());
        String prop = tf_KindKind.getText();
        if (Char.isValidate(prop))
        {
            prop = prop.replaceAll("[^:\\w]+", " ").trim();
        }
        kindItem.setC2010107(prop);
        kindItem.setC2010108(ta_KindDesp.getText());
        if (isUpdate)
        {
            mainPtn.getTreeMdl().wUpdate(path, kindItem);
        }
        else
        {
            mainPtn.getTreeMdl().wAppend(path, kindItem);
        }

        kindItem = new Kind();
        viewInfo(kindItem);
        isUpdate = false;
    }

    private void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (tr_KindList.getSelectionPath() == null)
        {
            Lang.showMesg(this, LangRes.P30F8A04, "请选择您要删除的类别数据！");
            tr_KindList.requestFocus();
            return;
        }

        if (Lang.showFirm(this, LangRes.P30F7A1A, "执行此操作后，此类别下的其它类别将会移动到根类别下，\n确认要删除此类别么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            mainPtn.getTreeMdl().wRemove(tr_KindList.getSelectionPath());
        }
    }

    private void tr_KindListValueChanged(javax.swing.event.TreeSelectionEvent evt)
    {
        javax.swing.tree.TreePath treePath = tr_KindList.getSelectionPath();
        if (treePath == null)
        {
            return;
        }
        treeNode = (javax.swing.tree.DefaultMutableTreeNode) treePath.getLastPathComponent();
        kindItem = (Kind) treeNode.getUserObject();
        viewInfo(kindItem);
        isUpdate = true;
    }

    private void viewInfo(Kind node)
    {
        tf_KindName.setText(node.getC2010105());
        tf_KindTips.setText(node.getC2010106());
        tf_KindKind.setText(node.getC2010107());
        ta_KindDesp.setText(node.getC2010108());
    }
    private javax.swing.JPanel pl_ItemInfo;
    private javax.swing.JLabel lb_KindName;
    private javax.swing.JTextField tf_KindName;
    private javax.swing.JLabel lb_KindTips;
    private javax.swing.JTextField tf_KindTips;
    private javax.swing.JLabel lb_KindKind;
    private javax.swing.JTextField tf_KindKind;
    private javax.swing.JLabel lb_KindDesp;
    private javax.swing.JTextArea ta_KindDesp;
    private IcoLabel bt_ApndData;
    private IcoLabel bt_SaveData;
    private IcoLabel bt_DropData;
    private IcoLabel bt_SortU;
    private IcoLabel bt_SortD;
    private IcoLabel bt_SortL;
    private IcoLabel bt_SortR;
    private javax.swing.JTree tr_KindList;
    private javax.swing.JPanel pl_ItemSort;
}
