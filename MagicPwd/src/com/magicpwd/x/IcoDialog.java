/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.x;

import com.magicpwd.MagicPwd;
import com.magicpwd._face.IBackCall;
import com.magicpwd._util.Util;
import com.magicpwd.r.AmonFF;

/**
 *
 * @author amon
 */
public class IcoDialog extends javax.swing.JDialog
{

    private java.io.File icoPath;
    private IBackCall backCall;

    public IcoDialog(IBackCall backCall)
    {
        super(MagicPwd.getCurrForm(), true);
        this.backCall = backCall;
    }

    public void initView()
    {

        javax.swing.JScrollPane sp_IconList = new javax.swing.JScrollPane();
        pl_IconList = new javax.swing.JPanel();
        bt_Append = new javax.swing.JButton();
        bt_Select = new javax.swing.JButton();

        pl_IconList.setLayout(new java.awt.GridLayout(0, 10));
        pl_IconList.setBackground(java.awt.Color.WHITE);
        sp_IconList.setViewportView(pl_IconList);

        bt_Append.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_AppendActionPerformed(evt);
            }
        });

        bt_Select.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(bt_Select);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(bt_Append);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg.addComponent(sp_IconList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE);
        hpg.addGroup(hsg1);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(bt_Append);
        vpg.addComponent(bt_Select);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(sp_IconList, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg);
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);

        this.pack();
        this.setResizable(false);
        Util.centerForm(this, MagicPwd.getCurrForm());
    }

    public void initLang()
    {
        bt_Append.setText("jButton1");
        bt_Select.setText("jButton2");
    }

    public void initData()
    {
        if (icoPath == null)
        {
            icoPath = Util.icoPath;
            if (!icoPath.exists())
            {
                icoPath.mkdirs();
            }
        }

        java.io.File[] fileList = icoPath.listFiles(new AmonFF("[0-9a-z]{16}\\.png", false));
        if (fileList == null)
        {
            return;
        }

        javax.swing.JLabel label;
        for (java.io.File file : fileList)
        {
            if (!file.isFile())
            {
                continue;
            }
            label = new javax.swing.JLabel(Util.getIcon(file));
            pl_IconList.add(label);
        }
    }

    private synchronized void getIcon()
    {
    }

    private void bt_SelectActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void bt_AppendActionPerformed(java.awt.event.ActionEvent evt)
    {
    }
    private javax.swing.JButton bt_Append;
    private javax.swing.JButton bt_Select;
    private javax.swing.JPanel pl_IconList;
}
