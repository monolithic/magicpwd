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
    private javax.swing.SpinnerNumberModel smAhead;

    public MgtdDlg(MgtdPtn mgtdPtn, boolean modal)
    {
        super(mgtdPtn, modal);
        this.mgtdPtn = mgtdPtn;
    }

    public void initView()
    {
        cbPublic = new javax.swing.JCheckBox();
        btAbort = new javax.swing.JButton();
        btApply = new javax.swing.JButton();

        initA();
        initB();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(plPanelA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(plPanelB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(cbPublic);
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
        vpg1.addComponent(plPanelB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(plPanelA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(btAbort);
        vpg2.addComponent(btApply);
        vpg2.addComponent(cbPublic);
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
        plPanelA = new javax.swing.JPanel();

        tfTitle = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        lbTitle.setLabelFor(tfTitle);

        cbLevel = new javax.swing.JComboBox();
        lbLevel = new javax.swing.JLabel();
        lbLevel.setLabelFor(cbLevel);

        cbMethod = new javax.swing.JComboBox();
        lbMethod = new javax.swing.JLabel();
        lbMethod.setLabelFor(cbMethod);

        plMethod = new javax.swing.JPanel();
        methodLayout = new java.awt.CardLayout();
        plMethod.setLayout(methodLayout);
        methodList = new java.util.ArrayList<IMgtdBean>(5);

        Note note = new Note(this);
        note.initView();
        plMethod.add(note.getName(), note);
        methodList.add(note);

        Mail mail = new Mail(this);
        mail.initView();
        plMethod.add(mail.getName(), mail);
        methodList.add(mail);

        Apps apps = new Apps(this);
        apps.initView();
        plMethod.add(apps.getName(), apps);
        methodList.add(apps);

        File file = new File(this);
        file.initView();
        plMethod.add(file.getName(), file);
        methodList.add(file);

        Audio audio = new Audio(this);
        audio.initView();
        plMethod.add(audio.getName(), audio);
        methodList.add(audio);

        lbRemark = new javax.swing.JLabel();
        taRemark = new javax.swing.JTextArea();
        taRemark.setRows(3);
        javax.swing.JScrollPane spRemark = new javax.swing.JScrollPane(taRemark);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plPanelA);
        plPanelA.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lbTitle, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbLevel, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbMethod, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tfTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
        hpg2.addComponent(cbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(cbMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addGroup(hpg1);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addGroup(hpg2);
        javax.swing.GroupLayout.ParallelGroup hpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg3.addGroup(hsg1);
        hpg3.addComponent(plMethod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg3.addComponent(lbRemark);
        hpg3.addComponent(spRemark, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE);
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
        vpg3.addComponent(lbMethod);
        vpg3.addComponent(cbMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg3);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(plMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE);
        vsg.addComponent(lbRemark);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(spRemark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);
    }

    private void initB()
    {
        plPanelB = new javax.swing.JPanel();

        cbAhead = new javax.swing.JComboBox();
        spAhead = new javax.swing.JSpinner();
        smAhead = new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1));
        spAhead.setModel(smAhead);
        lbAhead = new javax.swing.JLabel();
        lbAhead.setLabelFor(spAhead);

        cbIntval = new javax.swing.JComboBox();
        lbIntval = new javax.swing.JLabel();

        plIntval = new javax.swing.JPanel();
        intvalLayout = new java.awt.CardLayout();
        plIntval.setLayout(intvalLayout);
        intvalList = new java.util.ArrayList<IMgtdBean>(5);

        FixTime fixTime = new FixTime(this);
        fixTime.initView();
        plIntval.add(fixTime.getName(), fixTime);
        intvalList.add(fixTime);

        Cycle sycle = new Cycle(this);
        sycle.initView();
        plIntval.add(sycle.getName(), sycle);
        intvalList.add(sycle);

        Interval interval = new Interval(this);
        interval.initView();
        plIntval.add(interval.getName(), interval);
        intvalList.add(interval);

        Special special = new Special(this);
        special.initView();
        plIntval.add(special.getName(), special);
        intvalList.add(special);

        Formula formula = new Formula(this);
        formula.initView();
        plIntval.add(formula.getName(), formula);
        intvalList.add(formula);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plPanelB);
        plPanelB.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(spAhead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(cbAhead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lbAhead, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbIntval, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addGroup(hsg1);
        hpg2.addComponent(cbIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addGroup(hpg1);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addGroup(hpg2);
        javax.swing.GroupLayout.ParallelGroup hpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg3.addGroup(hsg2);
        hpg3.addComponent(plIntval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setHorizontalGroup(hpg3);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lbAhead);
        vpg1.addComponent(spAhead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(cbAhead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lbIntval);
        vpg2.addComponent(cbIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(plIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
//        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);
    }

    public void initLang()
    {
        lbTitle.setText("标题(T)");

        lbLevel.setText("优先级(L)");

        lbMethod.setText("提醒方式(P)");

        lbIntval.setText("提醒周期(P)");

        lbRemark.setText("备注(M)");

        cbPublic.setText("保存为公用模板");

        btAbort.setText("取消(C)");

        btApply.setText("确认(O)");

        lbAhead.setText("提前提醒(A)");
        cbAhead.addItem("无");
        cbAhead.addItem("秒");
        cbAhead.addItem("分");
        cbAhead.addItem("时");
        cbAhead.addItem("周");
        cbAhead.addItem("日");
        cbAhead.addItem("月");
        cbAhead.addItem("年");

        for (IMgtdBean bean : methodList)
        {
            bean.initLang();
            cbMethod.addItem(new S1S1(bean.getName(), bean.getTitle()));
        }

        for (IMgtdBean bean : intvalList)
        {
            bean.initLang();
            cbIntval.addItem(new S1S1(bean.getName(), bean.getTitle()));
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

        cbMethod.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                cbMethodActionPerformed(e);
            }
        });

        cbIntval.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                cbIntvalActionPerformed(e);
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

    private void cbIntvalActionPerformed(java.awt.event.ActionEvent e)
    {
        Object obj = cbIntval.getSelectedItem();
        if (obj == null || !(obj instanceof S1S1))
        {
            return;
        }
        S1S1 item = (S1S1) obj;
        intvalLayout.show(plIntval, item.getK());
    }

    private void cbMethodActionPerformed(java.awt.event.ActionEvent e)
    {
        Object obj = cbMethod.getSelectedItem();
        if (obj == null || !(obj instanceof S1S1))
        {
            return;
        }
        S1S1 item = (S1S1) obj;
        methodLayout.show(plMethod, item.getK());
    }

    private void btApplyActionPerformed(java.awt.event.ActionEvent evt)
    {
        Mgtd mgtd = new Mgtd();
        mgtd.setP30F0301(ConsDat.MGTD_TYPE_DATETIME);
        mgtd.setP30F0302(ConsDat.MGTD_STATUS_INIT);
        mgtd.setP30F0703(cbLevel.getSelectedIndex());
        mgtd.setP30F0704(cbMethod.getSelectedIndex());
        mgtd.setP30F0305(ConsDat.MGTD_METHOD_NOTE);
        mgtd.setP30F0306(1);
        mgtd.setP30F0307(0);
        mgtd.setP30F030B(tfTitle.getText());
        mgtd.setP30F030C(0L);
        mgtd.setP30F030D(0L);
        mgtd.setP30F030E(0L);
        methodList.get(cbMethod.getSelectedIndex()).saveData(mgtd);
        mgtd.setP30F0311(cbAhead.getSelectedIndex());
        mgtd.setP30F0312(smAhead.getNumber().intValue());
        intvalList.get(cbIntval.getSelectedIndex()).saveData(mgtd);
        mgtd.setP30F0313(taRemark.getText());
        DBA4000.saveHintData(mgtd);
    }

    private void btAbortActionPerformed(java.awt.event.ActionEvent evt)
    {
        this.dispose();
    }
    private javax.swing.JPanel plPanelA;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField tfTitle;
    private javax.swing.JLabel lbLevel;
    private javax.swing.JComboBox cbLevel;
    private javax.swing.JLabel lbMethod;
    private javax.swing.JComboBox cbMethod;
    private javax.swing.JPanel plMethod;
    private javax.swing.JLabel lbRemark;
    private javax.swing.JTextArea taRemark;
    private javax.swing.JPanel plPanelB;
    private javax.swing.JLabel lbAhead;
    private javax.swing.JSpinner spAhead;
    private javax.swing.JComboBox cbAhead;
    private javax.swing.JLabel lbIntval;
    private javax.swing.JComboBox cbIntval;
    private javax.swing.JPanel plIntval;
    private javax.swing.JCheckBox cbPublic;
    private javax.swing.JButton btAbort;
    private javax.swing.JButton btApply;
    private java.awt.CardLayout methodLayout;
    private java.util.List<IMgtdBean> methodList;
    private java.awt.CardLayout intvalLayout;
    private java.util.List<IMgtdBean> intvalList;
}
