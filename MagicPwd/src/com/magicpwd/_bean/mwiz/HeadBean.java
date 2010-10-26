/*
 *  Copyright (C) 2010 Administrator
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
package com.magicpwd._bean.mwiz;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;

/**
 * Application: MagicPwd
 * Author     : Administrator
 * Encoding   : UTF-8
 * Created    : 2010-10-24 23:52:16
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class HeadBean extends javax.swing.JPanel
{

    public void initView()
    {
        lb_MetaName = new javax.swing.JLabel();
        tf_MetaName = new javax.swing.JTextField(14);
        lb_MetaData = new javax.swing.JLabel();
        ta_MetaData = new javax.swing.JTextArea();
        lb_HintName = new javax.swing.JLabel();
        tf_HintName = new javax.swing.JTextField(14);
        lb_HintDate = new javax.swing.JLabel();
        tf_HintDate = new javax.swing.JTextField(14);
        ib_KeysIcon = new BtnLabel();
        ib_HintDate = new BtnLabel();

        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(ta_MetaData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(tf_MetaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(ib_KeysIcon);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(tf_HintDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(ib_HintDate);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_MetaName, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lb_MetaData, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lb_HintName, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lb_HintDate, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addGroup(hsg1);
        hpg2.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE);
        hpg2.addGroup(hsg2);
        hpg2.addComponent(tf_HintName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg3 = layout.createSequentialGroup();
        hsg3.addContainerGap();
        hsg3.addGroup(hpg1);
        hsg3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg3.addGroup(hpg2);
        hsg3.addContainerGap(14, Short.MAX_VALUE);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg3));

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_MetaName);
        vpg1.addComponent(tf_MetaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(ib_KeysIcon);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg2.addComponent(lb_MetaData);
        vpg2.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lb_HintName);
        vpg3.addComponent(tf_HintName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg4.addComponent(lb_HintDate);
        vpg4.addComponent(tf_HintDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg4.addComponent(ib_HintDate);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg3);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg4);
        vsg1.addContainerGap(14, Short.MAX_VALUE);
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg1));
    }

    public void initLang()
    {
        Bean.setText(lb_MetaName, Lang.getLang(LangRes.P30F1303, "标题"));

        Lang.setWText(ib_KeysIcon, LangRes.P30F151B, "&O");
        Lang.setWTips(ib_KeysIcon, LangRes.P30F151C, "提醒时间(Alt + O)");

        Bean.setText(lb_MetaName, Lang.getLang(LangRes.P30F1304, "搜索"));

        Lang.setWText(lb_HintName, LangRes.P30F1305, "提示");
        Lang.setWText(lb_HintDate, LangRes.P30F1306, "时间");

        Lang.setWText(ib_HintDate, LangRes.P30F151B, "&O");
        Lang.setWTips(ib_HintDate, LangRes.P30F151C, "提醒时间(Alt + O)");

//        javax.swing.JCheckBoxMenuItem item;
//        int k = 1;
//        for (int i = 1, j = mi_MenuItem.length; i < j; i += 1)
//        {
//            item = mi_MenuItem[i];
//            Lang.setWText(item, "P30FA60" + Integer.toHexString(k++).toUpperCase(), "");
//            Lang.setWTips(item, "P30FA60" + Integer.toHexString(k++).toUpperCase(), "");
//        }

        //nameBox.initLang();
    }

    public void initData()
    {
    }
    private BtnLabel ib_HintDate;
    private BtnLabel ib_KeysIcon;
    private javax.swing.JLabel lb_HintDate;
    private javax.swing.JLabel lb_HintName;
    private javax.swing.JLabel lb_MetaData;
    private javax.swing.JLabel lb_MetaName;
    private javax.swing.JTextArea ta_MetaData;
    private javax.swing.JTextField tf_HintDate;
    private javax.swing.JTextField tf_HintName;
    private javax.swing.JTextField tf_MetaName;
}
