/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v.mwiz;

import com.magicpwd._util.Bean;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MenuPtn;

/**
 * 向导模式
 * @author Amon
 */
public class NormPtn extends javax.swing.JFrame
{

    private MenuPtn menuPtn;
    private CoreMdl coreMdl;

    public void initView()
    {
        menuPtn = new MenuPtn(coreMdl);
        try
        {
            menuPtn.loadData(new java.io.File(""));
            tbToolBar = menuPtn.getToolBar("mwiz");
        }
        catch (Exception e)
        {
            tbToolBar = new javax.swing.JToolBar();
        }

        tbFindBar = new javax.swing.JToolBar();
        tbToolBar.setFloatable(false);
        tbToolBar.setRollover(true);

        tbFindBar.setFloatable(false);
        tbFindBar.setRollover(false);

        tfKeysName = new javax.swing.JTextField();
        tbFindBar.add(tfKeysName);

        btKeysName = new javax.swing.JButton();
        tbFindBar.add(btKeysName);

        tbKeysList = new javax.swing.JTable();

        tbKeysList.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tbKeysListMouseClicked(evt);
            }
        });
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(tbKeysList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(tbToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE);
        hsg1.addComponent(tbFindBar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg1).addGroup(hsg2));

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addComponent(tbFindBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg.addComponent(tbToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addGroup(vpg);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE);
        vsg.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg));

        pack();
    }

    public void initLang()
    {
        Bean.setText(btKeysName, "搜索");
    }

    public void initData()
    {
    }

    private void tbKeysListMouseClicked(java.awt.event.MouseEvent e)
    {
    }
    private javax.swing.JButton btKeysName;
    private javax.swing.JToolBar tbFindBar;
    private javax.swing.JTable tbKeysList;
    private javax.swing.JToolBar tbToolBar;
    private javax.swing.JTextField tfKeysName;
}
