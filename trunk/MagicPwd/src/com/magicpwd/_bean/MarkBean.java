/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._bean;

import com.magicpwd._comn.I1S2;
import com.magicpwd._comn.Item;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.EditBox;

/**
 *
 * @author Administrator
 */
public class MarkBean extends javax.swing.JPanel implements IEditBean
{

    private IEditItem tpltData;
    private IGridView gridView;
    private EditBox dataEdit;

    public MarkBean(IGridView view)
    {
        gridView = view;
    }

    @Override
    public void initView()
    {
        dataEdit = new EditBox(this, false);
        dataEdit.initView();
        dataEdit.setDropButtonVisible(false);

        lb_PropName = new javax.swing.JLabel();

        cb_PropName = new javax.swing.JComboBox();
        cb_PropName.addItem(new I1S2(ConsDat.PWDS_STAT_0, "默认", "默认"));
        cb_PropName.addItem(new I1S2(ConsDat.PWDS_STAT_1, "使用中", "使用中"));
        cb_PropName.addItem(new I1S2(ConsDat.PWDS_STAT_2, "待注册", "待注册"));
        cb_PropName.addItem(new I1S2(ConsDat.PWDS_STAT_3, "已过期", "已过期"));
        cb_PropName.addItem(new I1S2(ConsDat.PWDS_STAT_4, "已丢失", "已丢失"));
        cb_PropName.addItem(new I1S2(ConsDat.PWDS_STAT_5, "已禁用", "已禁用"));
        cb_PropName.addItem(new I1S2(ConsDat.PWDS_STAT_6, "已删除", "已删除"));
        lb_PropName.setLabelFor(cb_PropName);

        lb_PropData = new javax.swing.JLabel();

        ta_PropData = new javax.swing.JTextArea();
        lb_PropData.setLabelFor(ta_PropData);
        ta_PropData.setLineWrap(true);
        ta_PropData.setRows(3);

        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        sp1.setViewportView(ta_PropData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropName);
        hpg1.addComponent(lb_PropData);

        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(cb_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
        vpg1.addComponent(cb_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);

        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg2.addComponent(lb_PropData);
        vpg2.addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

        javax.swing.GroupLayout.SequentialGroup vpg3 = layout.createSequentialGroup();
        vpg3.addGroup(vpg1);
        vpg3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vpg3.addGroup(vpg2);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(vpg3);
        vpg.addComponent(dataEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    @Override
    public void initLang()
    {
        Lang.setWText(lb_PropName, LangRes.P30F1311, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F1312, "附注");

        dataEdit.initLang();
    }

    @Override
    public void initData(Item tplt)
    {
        tpltData = tplt;
        String name = tpltData.getName();
        if (Util.isValidate(name) && name.startsWith(ConsDat.SP_TPL_LS) && name.endsWith(ConsDat.SP_TPL_RS))
        {
            name = name.substring(1, name.length() - 1);
        }
        cb_PropName.setSelectedItem(null);
        ta_PropData.setText(tpltData.getData());
    }

    @Override
    public void requestFocus()
    {
        ta_PropData.requestFocus();
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        ta_PropData.selectAll();
        Util.setClipboardContents(ta_PropData.getText());
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        Object obj = cb_PropName.getSelectedItem();
        if (obj == null)
        {
            Lang.showMesg(this, LangRes.P30F7A2A, "请选择记录状态！");
            cb_PropName.requestFocus();
            return;
        }
        String name = obj.toString();

        tpltData.setName(name);
        tpltData.setData(ta_PropData.getText());
        UserMdl.getGridMdl().setModified(true);

        gridView.selectNext(!UserMdl.getGridMdl().isUpdate());
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (Lang.showFirm(this, LangRes.P30F1A01, "确认要删除此数据么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            UserMdl.getGridMdl().wRemove(tpltData);
            gridView.selectNext(false);
        }
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JTextArea ta_PropData;
    private javax.swing.JComboBox cb_PropName;
}
