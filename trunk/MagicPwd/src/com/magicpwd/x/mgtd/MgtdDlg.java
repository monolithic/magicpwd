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
import com.magicpwd.__i.mgtd.IMgtdBean;
import com.magicpwd._comn.S1S1;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._util.Bean;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.v.mgtd.MgtdPtn;
import com.magicpwd.x.mgtd.method.Apps;
import com.magicpwd.x.mgtd.method.Audio;
import com.magicpwd.x.mgtd.method.File;
import com.magicpwd.x.mgtd.method.Mail;
import com.magicpwd.x.mgtd.method.Note;
import com.magicpwd.x.mgtd.schedule.Cycle;
import com.magicpwd.x.mgtd.schedule.FixTime;
import com.magicpwd.x.mgtd.schedule.Formula;
import com.magicpwd.x.mgtd.schedule.Interval;
import com.magicpwd.x.mgtd.schedule.Special;

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
        jCheckBox1 = new javax.swing.JCheckBox();
        btAbort = new javax.swing.JButton();
        btApply = new javax.swing.JButton();

        initA();
        initB();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(plA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(plB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(jCheckBox1);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE);
        hsg2.addComponent(btApply);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(btAbort);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addGroup(hsg1);
        hpg1.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg2);
        javax.swing.GroupLayout.SequentialGroup hsg3 = layout.createSequentialGroup();
        hsg3.addContainerGap();
        hsg3.addGroup(hpg1);
        hsg3.addContainerGap();
        layout.setHorizontalGroup(hsg3);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg1.addComponent(plB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(plA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(btAbort);
        vpg2.addComponent(btApply);
        vpg2.addComponent(jCheckBox1);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vsg.addGroup(vpg2);
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);
    }

    private void initA()
    {
        plA = new javax.swing.JPanel();

        lbTitle = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        lbLevel = new javax.swing.JLabel();
        cbLevel = new javax.swing.JComboBox();
        lbCycle = new javax.swing.JLabel();
        cbCycle = new javax.swing.JComboBox();
        plLayout = new javax.swing.JPanel();

        intvalLayout = new java.awt.CardLayout();
        intvalList = new java.util.ArrayList<IMgtdBean>(5);
        plLayout.setLayout(intvalLayout);

        FixTime fixTime = new FixTime(this);
        fixTime.initView();
        plLayout.add(fixTime.getName(), fixTime);
        intvalList.add(fixTime);

        Cycle sycle = new Cycle(this);
        sycle.initView();
        plLayout.add(sycle.getName(), sycle);
        intvalList.add(sycle);

        Interval interval = new Interval(this);
        interval.initView();
        plLayout.add(interval.getName(), interval);
        intvalList.add(interval);

        Special special = new Special(this);
        special.initView();
        plLayout.add(special.getName(), special);
        intvalList.add(special);

        Formula formula = new Formula(this);
        formula.initView();
        plLayout.add(formula.getName(), formula);
        intvalList.add(formula);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plA);
        plA.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lbTitle, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbLevel, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbCycle, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tfTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
        hpg2.addComponent(cbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(cbCycle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addGroup(hpg1);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addGroup(hpg2);
        javax.swing.GroupLayout.ParallelGroup hpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg3.addGroup(hsg1);
        hpg3.addComponent(plLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addGroup(hpg3);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(hsg2);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lbTitle);
        vpg1.addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lbLevel);
        vpg2.addComponent(cbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lbCycle);
        vpg3.addComponent(cbCycle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg3);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(plLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addContainerGap(0, Short.MAX_VALUE);
        layout.setVerticalGroup(vsg);
    }

    private void initB()
    {
        plB = new javax.swing.JPanel();
        lbABC = new javax.swing.JLabel();
        cbABC = new javax.swing.JComboBox();
        plABC = new javax.swing.JPanel();
        lbRemark = new javax.swing.JLabel();
        taRemark = new javax.swing.JTextArea();

        methodLayout = new java.awt.CardLayout();
        methodList = new java.util.ArrayList<IMgtdBean>(5);
        plABC.setLayout(methodLayout);

        Note note = new Note(this);
        note.initView();
        plABC.add(note.getName(), note);
        methodList.add(note);

        Mail mail = new Mail(this);
        mail.initView();
        plABC.add(mail.getName(), mail);
        methodList.add(mail);

        Apps apps = new Apps(this);
        apps.initView();
        plABC.add(apps.getName(), apps);
        methodList.add(apps);

        File file = new File(this);
        file.initView();
        plABC.add(file.getName(), file);
        methodList.add(file);

        Audio audio = new Audio(this);
        audio.initView();
        plABC.add(audio.getName(), audio);
        methodList.add(audio);

        taRemark.setRows(5);
        javax.swing.JScrollPane spRemark = new javax.swing.JScrollPane(taRemark);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plB);
        plB.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(lbABC);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(cbABC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(spRemark, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE);
        hpg1.addComponent(plABC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg1.addGroup(hsg1);
        hpg1.addComponent(lbRemark);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
//        hsg2.addContainerGap();
        hsg2.addGroup(hpg1);
//        hsg2.addContainerGap();
        layout.setHorizontalGroup(hsg2);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lbABC);
        vpg1.addComponent(cbABC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(plABC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE);
        vsg.addComponent(lbRemark);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(spRemark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
//        vsg.addContainerGap();
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

        jCheckBox1.setText("保存为公用模板");

        for (IMgtdBean bean : intvalList)
        {
            bean.initLang();
            cbCycle.addItem(new S1S1(bean.getName(), bean.getTitle()));
        }

        for (IMgtdBean bean : methodList)
        {
            bean.initLang();
            cbABC.addItem(new S1S1(bean.getName(), bean.getTitle()));
        }
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

        cbCycle.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                cbCycleActionPerformed(e);
            }
        });

        cbABC.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                cbABCActionPerformed(e);
            }
        });

        cbLevel.addItem("无");
        cbLevel.addItem("低");
        cbLevel.addItem("中");
        cbLevel.addItem("高");

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

    private void cbCycleActionPerformed(java.awt.event.ActionEvent e)
    {
        Object obj = cbCycle.getSelectedItem();
        if (obj == null || !(obj instanceof S1S1))
        {
            return;
        }
        S1S1 item = (S1S1) obj;
        intvalLayout.show(plLayout, item.getK());
    }

    private void cbABCActionPerformed(java.awt.event.ActionEvent e)
    {
        Object obj = cbABC.getSelectedItem();
        if (obj == null || !(obj instanceof S1S1))
        {
            return;
        }
        S1S1 item = (S1S1) obj;
        methodLayout.show(plABC, item.getK());
    }

    private void btApplyActionPerformed(java.awt.event.ActionEvent evt)
    {
        Mgtd mgtd = new Mgtd();
        mgtd.setP30F0701(ConsDat.MGTD_TYPE_DATETIME);
        mgtd.setP30F0702(ConsDat.MGTD_STATUS_INIT);
        mgtd.setP30F0703(cbLevel.getSelectedIndex());
        mgtd.setP30F0704(cbCycle.getSelectedIndex());
        mgtd.setP30F0705(ConsDat.MGTD_METHOD_NOTE);
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
    private javax.swing.JPanel plA;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox cbCycle;
    private javax.swing.JComboBox cbLevel;
    private javax.swing.JLabel lbCycle;
    private javax.swing.JLabel lbLevel;
    private javax.swing.JLabel lbRemark;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel plLayout;
    private javax.swing.JTextArea taRemark;
    private javax.swing.JTextField tfTitle;
    private javax.swing.JPanel plB;
    private javax.swing.JLabel lbABC;
    private javax.swing.JComboBox cbABC;
    private javax.swing.JPanel plABC;
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
    private java.awt.CardLayout methodLayout;
    private java.util.List<IMgtdBean> methodList;
    private java.awt.CardLayout intvalLayout;
    private java.util.List<IMgtdBean> intvalList;
}
