/*
 *  Copyright (C) 2011 Amon
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.v.mgtd;

import com.magicpwd.__a.AMpwdPtn;
import com.magicpwd._util.Bean;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.tray.TrayPtn;
import com.magicpwd.x.mgtd.MgtdDlg;

/**
 *
 * @author Amon
 */
public class MgtdPtn extends AMpwdPtn
{

    public MgtdPtn(TrayPtn trayPtn, UserMdl userMdl)
    {
        super(trayPtn, userMdl);
    }

    @Override
    public boolean initView()
    {
        tbTaskTool = new javax.swing.JToolBar();
        tbTaskTool.setRollover(true);
        this.getContentPane().add(tbTaskTool, java.awt.BorderLayout.NORTH);

        jButton1 = new javax.swing.JButton();
        jButton1.setText("Demo");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                test();
            }
        });
        tbTaskTool.add(jButton1);

        plTaskList = new javax.swing.JPanel();
        this.getContentPane().add(plTaskList, java.awt.BorderLayout.CENTER);

        tbTaskList = new javax.swing.JTable();
        javax.swing.JScrollPane spTaskList = new javax.swing.JScrollPane(tbTaskList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plTaskList);
        plTaskList.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addComponent(spTaskList, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE);
        hsg.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg));

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(spTaskList, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE);
        vsg.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg));

        this.setIconImage(Bean.getLogo(16));

        this.pack();
        Bean.centerForm(this, null);
        this.setVisible(true);
        return true;
    }

    @Override
    public boolean initLang()
    {
        return true;
    }

    @Override
    public boolean initData()
    {
        return true;
    }

    @Override
    public boolean showData()
    {
        return true;
    }

    @Override
    public MenuPtn getMenuPtn()
    {
        return null;
    }

    @Override
    public boolean endSave()
    {
        return true;
    }

    @Override
    public void requestFocus()
    {
    }

    private void test()
    {
//        WDateChooser dc = new WDateChooser();
//        dc.show(b, 0, b.getHeight());
        MgtdDlg dlg = new MgtdDlg(this, true);
        dlg.initView();
        dlg.initLang();
        dlg.initData();
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JTable tbTaskList;
    private javax.swing.JToolBar tbTaskTool;
    private javax.swing.JPanel plTaskList;
}
