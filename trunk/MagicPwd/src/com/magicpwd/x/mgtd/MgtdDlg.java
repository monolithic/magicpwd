/*
 *  Copyright (C) 2011 Amon
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
package com.magicpwd.x.mgtd;

import com.magicpwd.__a.ADialog;
import com.magicpwd.__a.AMpwdPtn;

/**
 *
 * @author Amon
 */
public class MgtdDlg extends ADialog
{

    public MgtdDlg(AMpwdPtn owner, boolean modal)
    {
        super(owner, modal);
    }

    public void initView()
    {
        lbTitle = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        lbLevel = new javax.swing.JLabel();
        cbLevel = new javax.swing.JComboBox();
        lbCycle = new javax.swing.JLabel();
        cbCycle = new javax.swing.JComboBox();
        plLayout = new javax.swing.JPanel();
        btAbort = new javax.swing.JButton();
        btApply = new javax.swing.JButton();
        lbRemark = new javax.swing.JLabel();
        taRemark = new javax.swing.JTextArea();

        javax.swing.JScrollPane spRemark = new javax.swing.JScrollPane(taRemark);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lbCycle, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbLevel, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbTitle, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg2.addComponent(tfTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE);
        hpg2.addComponent(cbLevel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(cbCycle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addGroup(hpg1);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addGroup(hpg2);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(btApply);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(btAbort);
        javax.swing.GroupLayout.ParallelGroup hpg3 = layout.createParallelGroup();
        hpg3.addComponent(spRemark, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE);
        hpg3.addGroup(hsg1);
        hpg3.addComponent(lbRemark);
        hpg3.addComponent(plLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg3.addGroup(hsg2);
        javax.swing.GroupLayout.SequentialGroup hsg3 = layout.createSequentialGroup();
        hsg3.addContainerGap();
        hsg3.addGroup(hpg3);
        hsg3.addContainerGap();
        layout.setHorizontalGroup(hsg3);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lbTitle);
        vpg1.addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lbLevel);
        vpg2.addComponent(cbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lbCycle);
        vpg3.addComponent(cbCycle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg4.addComponent(btAbort);
        vpg4.addComponent(btApply);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg3);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(plLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(lbRemark);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(spRemark, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg4);
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);
    }

    public void initLang()
    {
        lbTitle.setText("标题(T)");

        tfTitle.setText("jTextField1");

        lbLevel.setText("优先级(L)");

        lbCycle.setText("提醒周期(P)");

        btAbort.setText("取消(C)");

        btApply.setText("确认(O)");

        lbRemark.setText("jLabel1");
    }

    public void initData()
    {
        btAbort.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btAbortActionPerformed(evt);
            }
        });
        btApply.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btApplyActionPerformed(evt);
            }
        });
    }

    @Override
    protected boolean hideDialog()
    {
        return true;
    }

    private void btApplyActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void btAbortActionPerformed(java.awt.event.ActionEvent evt)
    {
    }
    private javax.swing.JButton btAbort;
    private javax.swing.JButton btApply;
    private javax.swing.JComboBox cbCycle;
    private javax.swing.JComboBox cbLevel;
    private javax.swing.JLabel lbCycle;
    private javax.swing.JLabel lbLevel;
    private javax.swing.JLabel lbRemark;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel plLayout;
    private javax.swing.JTextArea taRemark;
    private javax.swing.JTextField tfTitle;
}
