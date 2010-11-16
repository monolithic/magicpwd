/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._bean.mpwd;

import com.magicpwd.__i.IEditItem;
import com.magicpwd.__i.mpwd.IMpwdBean;
import com.magicpwd._bean.ADataBean;
import com.magicpwd._comp.WEditBox;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._util.Bean;
import com.magicpwd.v.mpwd.MainPtn;

/**
 *
 * @author Amon
 */
public class DataBean extends ADataBean implements IMpwdBean
{

    private WEditBox dataEdit;
    private MainPtn mainPtn;
    private WTextBox nameBox;

    public DataBean(MainPtn mainPtn)
    {
        super(mainPtn);
        this.mainPtn = mainPtn;
    }

    @Override
    public void initView()
    {
        dataEdit = new WEditBox(mainPtn, this, false);
        dataEdit.initView();

        lb_PropConf = new javax.swing.JLabel();
        initConfView();

        lb_PropName = new javax.swing.JLabel();
        tf_PropName = new javax.swing.JTextField(14);
        lb_PropName.setLabelFor(tf_PropName);
        nameBox = new WTextBox(tf_PropName, true);
        nameBox.initView();

        lb_PropData = new javax.swing.JLabel();
        lb_PropData.setLabelFor(tf_PropData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropConf);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        hpg2.addComponent(pl_PropConf, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_PropName);
        vpg1.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_PropData);
        vpg2.addComponent(tf_PropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg3.addComponent(lb_PropConf);
        vpg3.addComponent(pl_PropConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg3);
        vsg1.addContainerGap(14, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        vpg.addGroup(vsg1);
        vpg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    @Override
    public void initLang()
    {
        Bean.setText(lb_PropName, "属性(@P)");
        Bean.setText(lb_PropData, "数值(@D)");

        initConfLang();
    }

    @Override
    public void initData()
    {
        initConfData();
    }

    @Override
    public void showData(IEditItem item)
    {
        itemData = item;

        tf_PropName.setText(getName());
        tf_PropData.setText(itemData.getData());

        showConfData();
    }

    @Override
    public void requestFocus()
    {
        if (!com.magicpwd._util.Char.isValidate(tf_PropName.getText()))
        {
            tf_PropName.requestFocus();
            return;
        }
        tf_PropData.requestFocus();
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!processData())
        {
            return;
        }

        mainPtn.updateSelected();
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JTextField tf_PropName;
    // 配置信息
    private javax.swing.JLabel lb_PropConf;
}
