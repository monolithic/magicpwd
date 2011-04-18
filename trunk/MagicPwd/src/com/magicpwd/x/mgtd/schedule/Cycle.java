/*
 *  Copyright (C) 2011 Aven
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
package com.magicpwd.x.mgtd.schedule;

import com.magicpwd.__i.mgtd.IMgtdBean;
import com.magicpwd._comn.I1S2;
import com.magicpwd._comn.mpwd.Hint;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._enum.GtdUnit;
import com.magicpwd._util.Lang;
import com.magicpwd.x.mgtd.MgtdDlg;

/**
 * Application: MagicPwd
 * Author     : Aven
 * Encoding   : UTF-8
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class Cycle extends javax.swing.JPanel implements IMgtdBean
{

    private MgtdDlg mgtdDlg;
    private javax.swing.DefaultListModel lmEnum;
    private javax.swing.SpinnerDateModel smFtime;
    private javax.swing.SpinnerDateModel smTtime;
    private javax.swing.SpinnerDateModel smStime;

    public Cycle(MgtdDlg mgtdDlg)
    {
        this.mgtdDlg = mgtdDlg;
    }

    @Override
    public void initView()
    {
        btFtime = new BtnLabel();
        spFtime = new javax.swing.JSpinner();
        smFtime = new javax.swing.SpinnerDateModel();
        spFtime.setModel(smFtime);
        lbFtime = new javax.swing.JLabel();
        lbFtime.setLabelFor(spFtime);

        btTtime = new BtnLabel();
        spTtime = new javax.swing.JSpinner();
        smTtime = new javax.swing.SpinnerDateModel();
        spTtime.setModel(smTtime);
        lbTtime = new javax.swing.JLabel();
        lbTtime.setLabelFor(spTtime);

        btStime = new BtnLabel();
        spStime = new javax.swing.JSpinner();
        smStime = new javax.swing.SpinnerDateModel();
        spStime.setModel(smStime);
        lbStime = new javax.swing.JLabel();
        lbStime.setLabelFor(spStime);

        cbIntval = new javax.swing.JComboBox();
        cbIntval.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                cbIntvalActionPerformed(e);
            }
        });
        lbIntval = new javax.swing.JLabel();
        lbIntval.setLabelFor(cbIntval);

        lsEnum = new javax.swing.JList();
        lmEnum = new javax.swing.DefaultListModel();
        lsEnum.setModel(lmEnum);
        lsEnum.setVisibleRowCount(5);
        javax.swing.JScrollPane spEnum = new javax.swing.JScrollPane(lsEnum);
        lbEnum = new javax.swing.JLabel();
        lbEnum.setLabelFor(lsEnum);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(spFtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(btFtime, 21, 21, 21);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(spTtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(btTtime, 21, 21, 21);
        javax.swing.GroupLayout.SequentialGroup hsg3 = layout.createSequentialGroup();
        hsg3.addComponent(spStime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg3.addComponent(btStime, 21, 21, 21);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbEnum);
        hpg1.addComponent(lbFtime);
        hpg1.addComponent(lbTtime);
        hpg1.addComponent(lbStime);
        hpg1.addComponent(lbIntval);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addGroup(hsg1);
        hpg2.addGroup(hsg2);
        hpg2.addGroup(hsg3);
        hpg2.addComponent(cbIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(spEnum, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg4 = layout.createSequentialGroup();
//        hsg4.addContainerGap();
        hsg4.addGroup(hpg1);
        hsg4.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg4.addGroup(hpg2);
//        hsg4.addContainerGap();
        layout.setHorizontalGroup(hsg4);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lbFtime);
        vpg1.addComponent(spFtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(btFtime, 21, 21, 21);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lbTtime);
        vpg2.addComponent(spTtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg2.addComponent(btTtime, 21, 21, 21);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lbStime);
        vpg3.addComponent(spStime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg3.addComponent(btStime, 21, 21, 21);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg4.addComponent(lbIntval);
        vpg4.addComponent(cbIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg5 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg5.addComponent(lbEnum);
        vpg5.addComponent(spEnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg3);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg4);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg5);
//        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);
    }

    @Override
    public void initLang()
    {
        lbFtime.setText("起始时间");
        lbTtime.setText("结束时间");
        lbStime.setText("执行时间");
        lbIntval.setText("间隔周期");
        cbIntval.addItem("秒");
        cbIntval.addItem("分");
        cbIntval.addItem("时");
        cbIntval.addItem("日");
        cbIntval.addItem("周");
        cbIntval.addItem("月");
        cbIntval.addItem("年");
    }

    @Override
    public String getName()
    {
        return "cycle";
    }

    @Override
    public String getTitle()
    {
        return "周期提醒";
    }

    @Override
    public boolean showData(Mgtd mgtd)
    {
        return true;
    }

    @Override
    public boolean saveData(Mgtd mgtd)
    {
        mgtd.setP30F030C(smFtime.getDate().getTime());
        mgtd.setP30F030D(smTtime.getDate().getTime());

        Object[] objs = lsEnum.getSelectedValues();
        if (objs == null || objs.length < 1)
        {
            Lang.showMesg(mgtdDlg, null, "请选择提醒周期信息！");
            lsEnum.requestFocus();
            return false;
        }

        Hint hint;
        for (Object obj : objs)
        {
            if (!(obj instanceof I1S2))
            {
                continue;
            }
            I1S2 item = (I1S2) obj;
            hint = new Hint();
            hint.setP30F0403(smStime.getDate().getTime());
            hint.setP30F0404(item.getI());
            hint.setP30F0405("");
            mgtd.addHint(hint);
        }
        return true;
    }

    private void cbIntvalActionPerformed(java.awt.event.ActionEvent e)
    {
        int idx = cbIntval.getSelectedIndex() + 1;
        if (idx == GtdUnit.second.ordinal())
        {
            lmEnum.clear();
            for (int i = 0; i <= 59; i += 1)
            {
                lmEnum.addElement(i);
            }
            return;
        }
        if (idx == GtdUnit.minute.ordinal())
        {
            lmEnum.clear();
            for (int i = 0; i <= 59; i += 1)
            {
                lmEnum.addElement(i);
            }
            return;
        }
        if (idx == GtdUnit.hour.ordinal())
        {
            lmEnum.clear();
            for (int i = 0; i <= 23; i += 1)
            {
                lmEnum.addElement(i);
            }
            return;
        }
        if (idx == GtdUnit.day.ordinal())
        {
            lmEnum.clear();
            for (int i = 1; i <= 31; i += 1)
            {
                lmEnum.addElement(i);
            }
            return;
        }
        if (idx == GtdUnit.week.ordinal())
        {
            lmEnum.clear();
            for (int i = 0; i <= 6; i += 1)
            {
                lmEnum.addElement(i);
            }
            return;
        }
        if (idx == GtdUnit.month.ordinal())
        {
            lmEnum.clear();
            for (int i = 1; i <= 12; i += 1)
            {
                lmEnum.addElement(i);
            }
            return;
        }
        if (idx == GtdUnit.year.ordinal())
        {
            lmEnum.clear();
            for (int i = 0; i <= 20; i += 1)
            {
                lmEnum.addElement(2010 + i);
            }
            return;
        }
    }
    private BtnLabel btFtime;
    private BtnLabel btStime;
    private BtnLabel btTtime;
    private javax.swing.JComboBox cbIntval;
    private javax.swing.JLabel lbEnum;
    private javax.swing.JLabel lbFtime;
    private javax.swing.JLabel lbIntval;
    private javax.swing.JLabel lbStime;
    private javax.swing.JLabel lbTtime;
    private javax.swing.JList lsEnum;
    private javax.swing.JSpinner spFtime;
    private javax.swing.JSpinner spStime;
    private javax.swing.JSpinner spTtime;
}
