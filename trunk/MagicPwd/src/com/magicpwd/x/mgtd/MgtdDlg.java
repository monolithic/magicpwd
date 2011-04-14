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
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._util.Bean;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.v.mgtd.MgtdPtn;

/**
 *
 * @author Amon
 */
public class MgtdDlg extends ADialog
{

    private MgtdPtn mgtdPtn;

    public MgtdDlg(MgtdPtn mgtdPtn, boolean modal)
    {
        super(mgtdPtn, modal);
        this.mgtdPtn = mgtdPtn;
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

        plLayout.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dd();

        javax.swing.JScrollPane spRemark = new javax.swing.JScrollPane(taRemark);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
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
        hpg3.addGroup(hsg1);
        hpg3.addComponent(plLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg3.addComponent(lbRemark);
        hpg3.addComponent(spRemark, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE);
        hpg3.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg2);
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

        lbRemark.setText("备注(M)");
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

        cbLevel.addItem("无");
        cbLevel.addItem("低");
        cbLevel.addItem("中");
        cbLevel.addItem("高");

        cbCycle.addItem("定时提醒");
        cbCycle.addItem("间隔提醒");
        cbCycle.addItem("周期提醒");
        cbCycle.addItem("特殊提醒");
        cbCycle.addItem("其它提醒");

        this.pack();
        Bean.centerForm(this, mgtdPtn);
        this.setVisible(true);
    }

    @Override
    protected boolean hideDialog()
    {
        return true;
    }

    private void dd()
    {
        lbUTime = new javax.swing.JLabel();
        spUTime = new javax.swing.JSpinner();
        btUTime = new javax.swing.JButton();
        lbUTime.setLabelFor(spUTime);
        lbUTime.setText("指定时间");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plLayout);
        plLayout.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(lbUTime);
        hsg.addComponent(spUTime);
        hsg.addComponent(btUTime);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup();
        vpg.addComponent(lbUTime);
        vpg.addComponent(spUTime);
        vpg.addComponent(btUTime);
        layout.setVerticalGroup(vpg);
    }

    private void btApplyActionPerformed(java.awt.event.ActionEvent evt)
    {
        Mgtd mgtd = new Mgtd();
        mgtd.setP30F0701(ConsDat.MGTD_TYPE_DATETIME);
        mgtd.setP30F0702(ConsDat.MGTD_STATUS_INIT);
        mgtd.setP30F0703(cbLevel.getSelectedIndex());
        mgtd.setP30F0704(cbCycle.getSelectedIndex());
        mgtd.setP30F0705(ConsDat.MGTD_NOTE_MSG);
        mgtd.setP30F0706(1);
        mgtd.setP30F0707(0);
        mgtd.setP30F070B(tfTitle.getText());
        mgtd.setP30F070C(0L);
        mgtd.setP30F070D(0L);
        mgtd.setP30F070E(0L);
        mgtd.setP30F070F(0L);
        mgtd.setP30F0710(0);
        mgtd.setP30F0711("");
        mgtd.setP30F0712("");
        mgtd.setP30F0713(0);
        mgtd.setP30F0714(0);
        mgtd.setP30F0715(taRemark.getText());
        DBA4000.saveHintData(mgtd);
    }

    private void btAbortActionPerformed(java.awt.event.ActionEvent evt)
    {
        this.dispose();
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
    /**指定时间*/
    private javax.swing.JLabel lbUTime;
    private javax.swing.JSpinner spUTime;
    private javax.swing.JButton btUTime;
    /**起始时间*/
    private javax.swing.JLabel lbFTime;
    private javax.swing.JSpinner spFTime;
    private javax.swing.JButton btFTime;
    /**结束时间*/
    private javax.swing.JLabel lbTTime;
    private javax.swing.JSpinner spTTime;
    private javax.swing.JButton btTTime;
}
