/**
 * 
 */
package com.magicpwd._bean;

import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.EditBox;

/**
 * 属性：关键搜索
 * 键值：ConsEnv.INDX_META
 * @author Amon
 * 
 */
public class MetaBean extends javax.swing.JPanel implements IEditBean
{

    private IEditItem tpltData;
    private IGridView gridView;
    private EditBox dataEdit;

    public MetaBean(IGridView view)
    {
        gridView = view;
    }

    @Override
    public void initView()
    {
        dataEdit = new EditBox(this, true);
        dataEdit.initView();
        dataEdit.setDropButtonVisible(false);

        lb_PropName = new javax.swing.JLabel();
        tf_PropName = new javax.swing.JTextField(14);
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();
        ta_PropData = new javax.swing.JTextArea();
        ta_PropData.setLineWrap(true);
        ta_PropData.setRows(3);
        lb_PropData.setLabelFor(ta_PropData);
        javax.swing.JScrollPane sp_PropData = new javax.swing.JScrollPane(ta_PropData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropName);
        hpg1.addComponent(lb_PropData);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(sp_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
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
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addComponent(lb_PropData);
        vsg1.addContainerGap(49, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg2.addGroup(vsg1);
        vpg2.addComponent(sp_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup vsg2 = layout.createSequentialGroup();
        vsg2.addGroup(vpg1);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addGroup(vpg2);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(vsg2);
        vpg.addComponent(dataEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    @Override
    public void initLang()
    {
        dataEdit.initLang();

        Lang.setWText(lb_PropName, LangRes.P30F1303, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F1304, "搜索");
    }

    @Override
    public void initData(IEditItem tplt)
    {
        tpltData = tplt;
        tf_PropName.setText(tpltData.getName());
        ta_PropData.setText(tpltData.getData());
    }

    @Override
    public void requestFocus()
    {
        tf_PropName.requestFocus();
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_PropName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30FAA1A, "记录标题不能为空!");
            tf_PropName.requestFocus();
            return;
        }

        GridMdl gm = UserMdl.getGridMdl();
        tpltData.setName(name);
        tpltData.setData(ta_PropData.getText());
        gm.setModified(true);

        gridView.selectNext(!gm.isUpdate());
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JTextArea ta_PropData;
    private javax.swing.JTextField tf_PropName;
}
