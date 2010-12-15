/*
 *  Copyright (C) 2010 Amon
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.v.maoc;

import com.magicpwd.__a.AFrame;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import com.magicpwd.m.maoc.MaocMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.tray.TrayPtn;

/**
 * 计算器
 * 
 * @author Amon
 */
public class MaocPtn extends AFrame
{

    private MaocMdl maocMdl;
    private CalcPtn calcPtn;
    private java.util.ArrayList<com.magicpwd._comn.Math> list;

    public MaocPtn(TrayPtn trayPtn, UserMdl userMdl)
    {
        super(trayPtn, userMdl);
    }

    public void initView()
    {
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane();
        tr_StepList = new javax.swing.JTree();
        tf_MathText = new javax.swing.JTextField();
        bt_KeyBoard = new javax.swing.JButton();
        bt_MathText = new javax.swing.JButton();
//        calcPtn = new CalcPtn();
//        calcPtn.initView();

        jsp.setViewportView(tr_StepList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(tf_MathText, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(bt_MathText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(bt_KeyBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
//        hpg.addComponent(calcPtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg1);
        hpg.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addGroup(hpg);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg2));

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(tf_MathText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg.addComponent(bt_KeyBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg.addComponent(bt_MathText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg);
//        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
//        vsg.addComponent(calcPtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg));

        this.pack();
        Bean.centerForm(this, null);
        this.setVisible(true);
    }

    public void initLang()
    {
    }

    public void initData()
    {
        maocMdl = new MaocMdl(userMdl);
        maocMdl.init();

        java.awt.event.ActionListener listener = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                bt_MathTextActionPerformed(e);
            }
        };
        tf_MathText.addActionListener(listener);
        bt_MathText.addActionListener(listener);

        list = new java.util.ArrayList<com.magicpwd._comn.Math>();

        tn_StepRoot = new javax.swing.tree.DefaultMutableTreeNode();
        tr_StepList.setModel(new javax.swing.tree.DefaultTreeModel(tn_StepRoot));
        tr_StepList.setRootVisible(false);
    }

    @Override
    public MenuPtn getMenuPtn()
    {
        return null;
    }

    @Override
    public boolean endSave()
    {
        return true;
    }

    @Override
    public void requestFocus()
    {
    }

    private void bt_MathTextActionPerformed(java.awt.event.ActionEvent e)
    {
        String math = tf_MathText.getText().trim();
        if (!Char.isValidate(math))
        {
            return;
        }

        try
        {
            list.clear();
            maocMdl.getComputer().calculate(math, 8, list);
            showStep(math, list);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, null, exp.getLocalizedMessage());
        }
    }

    private void showStep(String math, java.util.List<com.magicpwd._comn.Math> list)
    {
        javax.swing.tree.DefaultMutableTreeNode root = new javax.swing.tree.DefaultMutableTreeNode(new com.magicpwd._comn.Math(math));
        javax.swing.tree.DefaultMutableTreeNode node;
        for (com.magicpwd._comn.Math item : list)
        {
            node = new javax.swing.tree.DefaultMutableTreeNode(item);
            root.add(node);
        }
        tn_StepRoot.add(root);
        tr_StepList.fireTreeExpanded(new javax.swing.tree.TreePath(root.getPath()));
    }
    private javax.swing.JButton bt_KeyBoard;
    private javax.swing.JButton bt_MathText;
    private javax.swing.JTree tr_StepList;
    private javax.swing.tree.DefaultMutableTreeNode tn_StepRoot;
    private javax.swing.JTextField tf_MathText;
}
