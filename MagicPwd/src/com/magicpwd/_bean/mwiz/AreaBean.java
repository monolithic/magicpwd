/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._bean.mwiz;

import com.magicpwd.__i.mwiz.IMwizBean;
import com.magicpwd.m.mwiz.KeysMdl;

/**
 *
 * @author Amon
 */
public class AreaBean extends javax.swing.JPanel implements IMwizBean
{

    @Override
    public void initView()
    {
        ta_PropData = new javax.swing.JTextArea();

        ta_PropData.setColumns(20);
        ta_PropData.setRows(4);
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(ta_PropData);

        initConf();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
    }

    @Override
    public void initLang()
    {
    }

    @Override
    public void initData()
    {
    }

    @Override
    public void showData(KeysMdl keysMdl)
    {
    }

    @Override
    public void setLabelFor(javax.swing.JLabel label)
    {
    }

    private void initConf()
    {
    }
    private javax.swing.JTextArea ta_PropData;
}
