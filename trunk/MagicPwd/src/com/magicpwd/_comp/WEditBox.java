/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comp;

import com.magicpwd.__i.IEditBean;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Amon
 */
public class WEditBox extends javax.swing.JPanel
{

    private IEditBean editBean;
    private UserMdl userCfg;
    private boolean metaData;

    public WEditBox(UserMdl umdl, IEditBean bean, boolean meta)
    {
        userCfg = umdl;
        editBean = bean;
        metaData = meta;
    }

    public void initView()
    {
        bt_DropData = new BtnLabel();
        bt_DropData.setIcon(userCfg.readIcon(ConsEnv.FEEL_PATH + "prop-drop.png"));
        bt_DropData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editBean.dropDataActionPerformed(evt);
            }
        });

        bt_SaveData = new BtnLabel();
        bt_SaveData.setIcon(userCfg.readIcon(ConsEnv.FEEL_PATH + "prop-save.png"));
        bt_SaveData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editBean.saveDataActionPerformed(evt);
            }
        });

        bt_CopyData = new BtnLabel();
        bt_CopyData.setIcon(userCfg.readIcon(ConsEnv.FEEL_PATH + "prop-copy.png"));
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
            Lang.setWText(bt_CopyData, LangRes.P30F1501, "&C");
            Lang.setWTips(bt_CopyData, LangRes.P30F1502, "复制属性数据(Alt + C)");
        }
        Lang.setWText(bt_SaveData, LangRes.P30F1503, "&U");
        Lang.setWTips(bt_SaveData, LangRes.P30F1504, "应用属性变更(Alt + U)");
        if (!metaData)
        {
            Lang.setWText(bt_DropData, LangRes.P30F1505, "&D");
            Lang.setWTips(bt_DropData, LangRes.P30F1506, "删除属性数据(Alt + D)");
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
