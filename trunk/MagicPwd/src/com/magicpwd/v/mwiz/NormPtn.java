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
    private java.util.HashMap<String, javax.swing.Icon> iconMap;

    public NormPtn()
    {
    }

    public void initView()
    {
        iconMap = new java.util.HashMap<String, javax.swing.Icon>();
//        Bean.readIcon(NormPtn.class.getResourceAsStream(ConsEnv.ICON_PATH + "mwiz16.png"), iconMap);

        menuPtn = new MenuPtn(coreMdl);
        try
        {
            menuPtn.loadData(new java.io.File("dat/mwiz.xml"));
            tbToolBar = menuPtn.getToolBar("mwiz");
        }
        catch (Exception e)
        {
            tbToolBar = new javax.swing.JToolBar();
        }
        tbToolBar.add(new javax.swing.JButton(Bean.getIcon("mwiz0")));
        tbToolBar.add(new javax.swing.JButton(Bean.getIcon("mwiz1")));
        tbToolBar.add(new javax.swing.JButton(Bean.getIcon("mwiz2")));
        tbToolBar.add(new javax.swing.JButton(Bean.getIcon("mwiz3")));

        tbToolBar.setFloatable(false);
        tbToolBar.setRollover(true);

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
        hsg1.addContainerGap();
        hsg1.addComponent(tbToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE);
        hsg1.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg1).addGroup(hsg2));

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(tbToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE);
        vsg.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg));

        pack();
    }

    public void initLang()
    {
    }

    public void initData()
    {
    }

    public javax.swing.Icon getIcon(String hash)
    {
        javax.swing.Icon icon = iconMap.get(hash);
        if (icon == null)
        {
            icon = Bean.getIcon(hash);
        }
        return icon;
    }

    private void tbKeysListMouseClicked(java.awt.event.MouseEvent e)
    {
    }
    private javax.swing.JTable tbKeysList;
    private javax.swing.JToolBar tbToolBar;
}
