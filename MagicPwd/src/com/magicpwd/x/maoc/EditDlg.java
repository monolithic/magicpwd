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
package com.magicpwd.x.maoc;

import com.magicpwd._comn.S1S3;

/**
 *
 * @author Aven
 */
public class EditDlg extends javax.swing.JPanel
{

    public void initView()
    {
        lb_Name = new javax.swing.JLabel();
        tf_Name = new javax.swing.JTextField();
        lb_Value = new javax.swing.JLabel();
        tf_Value = new javax.swing.JTextField();
        lb_Remark = new javax.swing.JLabel();
        ta_Remark = new javax.swing.JTextArea();

        lb_Name.setLabelFor(tf_Name);

        lb_Value.setLabelFor(tf_Value);

        lb_Remark.setLabelFor(ta_Remark);
        javax.swing.JScrollPane sp_Remark = new javax.swing.JScrollPane(ta_Remark);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_Remark);
        hpg1.addComponent(lb_Value);
        hpg1.addComponent(lb_Name);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE);
        hpg2.addComponent(tf_Value, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addGroup(hpg1);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addGroup(hpg2);
        javax.swing.GroupLayout.ParallelGroup hpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg3.addGroup(hsg1);
        hpg3.addComponent(sp_Remark, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addGroup(hpg3);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg2));

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_Name);
        vpg1.addComponent(tf_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_Value);
        vpg2.addComponent(tf_Value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(lb_Remark);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(sp_Remark, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE);
        vsg1.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg1));
    }

    public void initLang()
    {
        lb_Name.setText("常量名(N)");
        lb_Value.setText("常量值(V)");
        lb_Remark.setText("备注(R)");
    }

    public void initData()
    {
    }

    public S1S3 getData()
    {
        return new S1S3(null, tf_Name.getText(), tf_Value.getText(), ta_Remark.getText());
    }

    public String getInputName()
    {
        return tf_Name.getText();
    }

    public String getInputValue()
    {
        return tf_Value.getText();
    }

    public String getInputRemark()
    {
        return ta_Remark.getText();
    }
    private javax.swing.JLabel lb_Name;
    private javax.swing.JLabel lb_Remark;
    private javax.swing.JLabel lb_Value;
    private javax.swing.JTextArea ta_Remark;
    private javax.swing.JTextField tf_Name;
    private javax.swing.JTextField tf_Value;
}
