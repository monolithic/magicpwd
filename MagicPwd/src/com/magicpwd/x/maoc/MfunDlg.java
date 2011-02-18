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
package com.magicpwd.x.maoc;

import com.magicpwd.__a.ADialog;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.S1S3;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.v.maoc.MaocPtn;

/**
 *
 * @author Amon
 */
public class MfunDlg extends ADialog
{

    private MaocPtn maocPtn;
    private IBackCall<S1S3> backCall;
    private S1S3 item;

    public MfunDlg(MaocPtn maocPtn, IBackCall<S1S3> backCall)
    {
        super(maocPtn, true);
        this.maocPtn = maocPtn;
        this.backCall = backCall;
    }

    public void initView()
    {
        lb_Name = new javax.swing.JLabel();
        tf_Name = new javax.swing.JTextField();
        lb_Name.setLabelFor(tf_Name);

        lb_Value = new javax.swing.JLabel();
        tf_Value = new javax.swing.JTextField();
        lb_Value.setLabelFor(tf_Value);

        ta_Remark = new javax.swing.JTextArea();
        lb_Remark = new javax.swing.JLabel();
        lb_Remark.setLabelFor(ta_Remark);

        javax.swing.JScrollPane sp_Remark = new javax.swing.JScrollPane(ta_Remark);

        bt_Abort = new javax.swing.JButton();
        bt_Apply = new javax.swing.JButton();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_Name, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lb_Value, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lb_Remark, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE);
        hpg2.addComponent(tf_Value, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE);
        hpg2.addComponent(sp_Remark, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addGroup(hpg1);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addGroup(hpg2);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(bt_Apply);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(bt_Abort);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addGroup(hsg1);
        hpg.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg2);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_Name);
        vpg1.addComponent(tf_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_Value);
        vpg2.addComponent(tf_Value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg3.addComponent(lb_Remark);
        vpg3.addComponent(sp_Remark, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg4.addComponent(bt_Apply);
        vpg4.addComponent(bt_Abort);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg3);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg4);
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);

        this.setResizable(false);
        this.setIconImage(Bean.getLogo(16));
    }

    public void initLang()
    {
        this.setTitle(Lang.getLang(LangRes.P30FB203, "函数管理"));

        Lang.setWText(lb_Name, LangRes.P30FB305, "函数名(@N)");
        Lang.setWText(lb_Value, LangRes.P30FB306, "函数值(@V)");
        Lang.setWText(lb_Remark, LangRes.P30FB307, "附注(@R)");

        Bean.setText(bt_Abort, "取消(@C)");
        Bean.setText(bt_Apply, "确认(@O)");
    }

    public void initData(S1S3 item)
    {
        bt_Abort.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                hideDialog();
            }
        });
        bt_Apply.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                bt_ApplyActionPerformed(e);
            }
        });

        if (item != null)
        {
            tf_Name.setText(item.getV());
            tf_Value.setText(item.getV2());
            ta_Remark.setText(item.getV3());
        }
        this.item = item;

        processEscape();

        this.pack();
        Bean.centerForm(this, maocPtn);
    }

    @Override
    protected boolean hideDialog()
    {
        setVisible(false);
        if (backCall != null)
        {
            backCall.callBack(IBackCall.OPTIONS_ABORT, null);
        }
        return true;
    }

    private void bt_ApplyActionPerformed(java.awt.event.ActionEvent e)
    {
        String name = tf_Name.getText().replaceAll("\\s+", "");
        if (!Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30FBA06, "函数名不能为空！");
            tf_Name.requestFocus();
            return;
        }
        if (!java.util.regex.Pattern.matches("^[A-Za-z][A-Za-z\\d]*(\\([A-Za-z][A-Za-z\\d]*(,[A-Za-z][A-Za-z\\d]*)*\\))?$", name))
        {
            Lang.showMesg(this, LangRes.P30FBA07, "函数名只能包含字母及数字，且以字母开始！");
            tf_Name.requestFocus();
            return;
        }
        String value = tf_Value.getText().replaceAll("\\s+", "");
        if (!Char.isValidate(value))
        {
            Lang.showMesg(this, LangRes.P30FBA08, "函数体不能为空！");
            tf_Value.requestFocus();
            return;
        }

        setVisible(false);

        if (backCall == null)
        {
            return;
        }

        if (item == null)
        {
            item = new S1S3();
        }

        item.setV(name);
        item.setV2(value);
        item.setV3(ta_Remark.getText());
        backCall.callBack(IBackCall.OPTIONS_APPLY, item);
    }
    private javax.swing.JTextField tf_Name;
    private javax.swing.JLabel lb_Name;
    private javax.swing.JTextField tf_Value;
    private javax.swing.JLabel lb_Value;
    private javax.swing.JTextArea ta_Remark;
    private javax.swing.JLabel lb_Remark;
    private javax.swing.JButton bt_Apply;
    private javax.swing.JButton bt_Abort;
}
