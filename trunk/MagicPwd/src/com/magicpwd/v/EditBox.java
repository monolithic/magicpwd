/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;

/**
 *
 * @author Amon
 */
public class EditBox extends javax.swing.JPanel
{

    private IEditBean editBean;
    private boolean metaData;

    public EditBox(IEditBean bean, boolean meta)
    {
        editBean = bean;
        metaData = meta;
    }

    public void initView()
    {
        bt_DropData = new BtnLabel();
        bt_DropData.setMnemonic('D');
        bt_DropData.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_PROP_DELT)));
        bt_DropData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editBean.dropDataActionPerformed(evt);
            }
        });

        bt_SaveData = new BtnLabel();
        bt_SaveData.setMnemonic('U');
        bt_SaveData.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_PROP_UPDT)));
        bt_SaveData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editBean.saveDataActionPerformed(evt);
            }
        });

        bt_CopyData = new BtnLabel();
        bt_CopyData.setMnemonic('C');
        bt_CopyData.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_PROP_COPY)));
        bt_CopyData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editBean.copyDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hgp = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hgp.addGap(0, 0, Short.MAX_VALUE);
        hgp.addComponent(bt_DropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hgp.addComponent(bt_SaveData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hgp.addComponent(bt_CopyData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setHorizontalGroup(hgp);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap(1, Short.MAX_VALUE);
        vsg.addComponent(bt_CopyData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(bt_SaveData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(bt_DropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vsg);
        layout.setVerticalGroup(vpg);
    }

    public void initLang()
    {
        if (!metaData)
        {
            Lang.setWTips(bt_CopyData, LangRes.P30F1501, "复制");
        }
        Lang.setWTips(bt_SaveData, LangRes.P30F1502, "保存");
        if (!metaData)
        {
            Lang.setWTips(bt_DropData, LangRes.P30F1503, "删除");
        }
    }

    public void setCopyButtonEnabled(boolean enabled)
    {
        bt_CopyData.setEnabled(enabled);
    }

    public void setCopyButtonVisible(boolean visible)
    {
        bt_CopyData.setVisible(visible);
    }

    public void setSaveButtonEnabled(boolean enabled)
    {
        bt_SaveData.setEnabled(enabled);
    }

    public void setSaveButtonVisible(boolean visible)
    {
        bt_SaveData.setVisible(visible);
    }

    public void setDropButtonEnabled(boolean enabled)
    {
        bt_DropData.setEnabled(enabled);
    }

    public void setDropButtonVisible(boolean visible)
    {
        bt_DropData.setVisible(visible);
    }
    private BtnLabel bt_CopyData;
    private BtnLabel bt_SaveData;
    private BtnLabel bt_DropData;
}
