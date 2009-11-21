/**
 * 
 */
package com.magicpwd._bean;

import com.magicpwd._comn.Item;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.EditBox;

/**
 * 属性：关键搜索
 * 键值：ConsEnv.INDX_META
 * @author amon
 * 
 */
public class MetaBean extends javax.swing.JPanel implements IEditBean {

    private Item tpltData;
    private IGridView gridView;
    private EditBox dataEdit;

    public MetaBean(IGridView view) {
        gridView = view;
    }

    public void initView() {
        dataEdit = new EditBox(this, true);
        dataEdit.initView();

        lb_PropName = new javax.swing.JLabel();

        tf_PropName = new javax.swing.JTextField();
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();

        ta_PropData = new javax.swing.JTextArea();
        ta_PropData.setLineWrap(true);
        ta_PropData.setRows(3);
        lb_PropData.setLabelFor(ta_PropData);

        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        sp1.setViewportView(ta_PropData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropName);
        hpg1.addComponent(lb_PropData);

        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

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

        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg2.addComponent(lb_PropData);
        vpg2.addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);

        javax.swing.GroupLayout.SequentialGroup vpg3 = layout.createSequentialGroup();
        vpg3.addGroup(vpg1);
        vpg3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vpg3.addGroup(vpg2);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(vpg3);
        vpg.addComponent(dataEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    public void initLang() {
        dataEdit.initLang();

        Lang.setWText(lb_PropName, LangRes.P30F1303, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F1304, "搜索");
    }

    public void initData(Item tplt) {
        tpltData = tplt;
        tf_PropName.setText(tpltData.getName());
        ta_PropData.setText(tpltData.getData());
    }

    @Override
    public void requestFocus() {
        tf_PropName.requestFocus();
    }

    public void saveDataActionPerformed(java.awt.event.ActionEvent evt) {
        String name = tf_PropName.getText();
        if (!Util.isValidate(name)) {
            Lang.showMesg(this, "", "记录标题不能为空!");
            tf_PropName.requestFocus();
            return;
        }

        UserMdl.getGridMdl().setKeysName(name);
        tpltData.setName(name);

        String meta = ta_PropData.getText();
        UserMdl.getGridMdl().setKeysMeta(meta);
        tpltData.setData(meta);
        UserMdl.getGridMdl().setModified(true);

        gridView.selectNext(!UserMdl.getGridMdl().isUpdate());
    }

    public void copyDataActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public void dropDataActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JTextArea ta_PropData;
    private javax.swing.JTextField tf_PropName;
}
