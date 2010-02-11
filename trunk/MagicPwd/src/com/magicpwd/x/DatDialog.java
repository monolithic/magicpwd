package com.magicpwd.x;

import javax.swing.JDialog;
import javax.swing.tree.TreePath;

import com.magicpwd.r.KindTN;
import com.magicpwd._comn.S1S2;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;

public class DatDialog extends JDialog
{
    private IBackCall backCall;

    public DatDialog(IBackCall backCall)
    {
        this.backCall = backCall;
    }

    public void initView()
    {
        tr_KindList = new javax.swing.JTree();
        bt_Cancel = new javax.swing.JButton();
        bt_Update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        tr_KindList.setModel(UserMdl.getTreeMdl());
        sp1.setViewportView(tr_KindList);

        bt_Cancel.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CancelActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap().addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(sp1,
                                javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220,
                                Short.MAX_VALUE).addGroup(
                                layout.createSequentialGroup().addComponent(bt_Update).addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_Cancel)))
                        .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap().addComponent(sp1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_Cancel)
                                .addComponent(bt_Update)).addContainerGap()));

        setIconImage(Util.getLogo());
        pack();
        Util.centerForm(this, null);
        setVisible(true);
    }

    public void initLang()
    {
        Lang.setWText(bt_Update, LangRes.P30FA50A, "确定(&O)");

        Lang.setWText(bt_Cancel, LangRes.P30FA50B, "取消(&C)");
    }

    void bt_UpdateActionPerformed(java.awt.event.ActionEvent evt)
    {
        TreePath tp = tr_KindList.getSelectionPath();
        if (tp == null)
        {
            Lang.showMesg(this, LangRes.P30FAA19, "", "请选择您要移动的目标类别！");
            return;
        }

        Object obj = tp.getLastPathComponent();
        if (obj instanceof KindTN)
        {
            KindTN item = (KindTN) obj;
            S1S2 kv = (S1S2) item.getUserObject();
            backCall.callBack(null, null, kv.getK());
        }
        this.setVisible(false);
        this.dispose();
    }

    void bt_CancelActionPerformed(java.awt.event.ActionEvent evt)
    {
        this.setVisible(false);
        this.dispose();
    }

    private javax.swing.JTree tr_KindList;
    private javax.swing.JButton bt_Update;
    private javax.swing.JButton bt_Cancel;
}
