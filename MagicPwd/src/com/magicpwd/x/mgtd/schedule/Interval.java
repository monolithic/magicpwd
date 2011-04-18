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
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._comn.mpwd.Hint;
import com.magicpwd._comp.BtnLabel;
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
public class Interval extends javax.swing.JPanel implements IMgtdBean
{

    private MgtdDlg mgtdDlg;
    private javax.swing.SpinnerDateModel smFtime;
    private javax.swing.SpinnerDateModel smTtime;
    private javax.swing.SpinnerDateModel smStime;

    public Interval(MgtdDlg mgtdDlg)
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

        spIntval = new javax.swing.JSpinner();
        spIntval.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        cbIntval = new javax.swing.JComboBox();
        lbIntval = new javax.swing.JLabel();
        lbIntval.setLabelFor(cbIntval);

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
        javax.swing.GroupLayout.SequentialGroup hsg4 = layout.createSequentialGroup();
        hsg4.addComponent(spIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg4.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg4.addComponent(cbIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lbFtime, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbTtime, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbStime, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbIntval, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addGroup(hsg1);
        hpg2.addGroup(hsg2);
        hpg2.addGroup(hsg3);
        hpg2.addGroup(hsg4);
        javax.swing.GroupLayout.SequentialGroup hsg5 = layout.createSequentialGroup();
//        hsg5.addContainerGap();
        hsg5.addGroup(hpg1);
        hsg5.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg5.addGroup(hpg2);
//        hsg5.addContainerGap();
        layout.setHorizontalGroup(hsg5);

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
        vpg4.addComponent(spIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
//        vsg1.addContainerGap();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg3);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg4);
//        vsg1.addContainerGap();
        layout.setVerticalGroup(vsg1);
    }

    @Override
    public void initLang()
    {
        lbFtime.setText("起始时间(T)");
        lbTtime.setText("结束时间(S)");
        lbStime.setText("执行时间(S)");

        lbIntval.setText("间隔时间(I)");
        cbIntval.addItem("无");
        cbIntval.addItem("秒");
        cbIntval.addItem("分");
        cbIntval.addItem("时");
        cbIntval.addItem("周");
        cbIntval.addItem("日");
        cbIntval.addItem("月");
        cbIntval.addItem("年");
    }

    @Override
    public String getName()
    {
        return "interval";
    }

    @Override
    public String getTitle()
    {
        return "间隔提醒";
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

        Hint mtts = new Hint();
        mtts.setP30F0403(smStime.getDate().getTime());
        mtts.setP30F0404(0);
        mtts.setP30F0405("");
        return true;
    }
    private javax.swing.JLabel lbFtime;
    private javax.swing.JSpinner spFtime;
    private BtnLabel btFtime;
    private javax.swing.JLabel lbStime;
    private javax.swing.JSpinner spStime;
    private BtnLabel btStime;
    private javax.swing.JLabel lbTtime;
    private javax.swing.JSpinner spTtime;
    private BtnLabel btTtime;
    private javax.swing.JLabel lbIntval;
    private javax.swing.JSpinner spIntval;
    private javax.swing.JComboBox cbIntval;
}
