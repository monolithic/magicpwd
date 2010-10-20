/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._bean.mwiz;

/**
 *
 * @author Amon
 */
public class AreaBean extends javax.swing.JPanel
{

    public void initView()
    {
        ta_PropData = new javax.swing.JTextArea();

        ta_PropData.setColumns(20);
        ta_PropData.setRows(4);
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(ta_PropData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
    }

    public void initLang()
    {
    }

    public void initData()
    {
    }

    public void showData()
    {
    }
    private javax.swing.JTextArea ta_PropData;
}
