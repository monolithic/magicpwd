package com.magicpwd._bean;

import com.magicpwd.MagicPwd;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;

/**
 * 属性：附注
 * 键值：ConsEnv.INDX_AREA
 * @author Amon
 */
public class AreaBean extends javax.swing.JPanel implements IEditBean
{

    private IEditItem itemData;
    private IGridView gridView;
    private EditBean dataEdit;

    public AreaBean(IGridView view)
    {
        gridView = view;
    }

    @Override
    public void initView()
    {
        dataEdit = new EditBean(this, false);
        dataEdit.initView();

        lb_PropName = new javax.swing.JLabel();
        tf_PropName = new javax.swing.JTextField(14);
        tf_PropName.addFocusListener(new java.awt.event.FocusAdapter()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                tf_PropName.selectAll();
            }
        });
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();
        ta_PropData = new javax.swing.JTextArea();
        lb_PropData.setLabelFor(ta_PropData);
        ta_PropData.setLineWrap(true);
        ta_PropData.setRows(3);
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
        Lang.setWText(lb_PropName, LangRes.P30F1311, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F1312, "附注");

        dataEdit.initLang();
    }

    @Override
    public void initData(IEditItem tplt)
    {
        itemData = tplt;
        String name = itemData.getName();
        if (Util.isValidate(name) && name.startsWith(ConsDat.SP_TPL_LS) && name.endsWith(ConsDat.SP_TPL_RS))
        {
            name = name.substring(1, name.length() - 1);
        }
        tf_PropName.setText(name);
        ta_PropData.setText(itemData.getData());
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
        String name = tf_PropName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30F7A38, "请输入附注名称！");
            tf_PropName.requestFocus();
            return;
        }

        itemData.setName(name);
        itemData.setData(ta_PropData.getText());
        UserMdl.getGridMdl().setModified(true);

        gridView.selectNext(!UserMdl.getGridMdl().isUpdate());
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (Lang.showFirm(MagicPwd.getCurrForm(), LangRes.P30F1A01, "确认要删除此属性数据么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            UserMdl.getGridMdl().wRemove(itemData);
            gridView.selectNext(false);
        }
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JTextArea ta_PropData;
    private javax.swing.JTextField tf_PropName;
}
