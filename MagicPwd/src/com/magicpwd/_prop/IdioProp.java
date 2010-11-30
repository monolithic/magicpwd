/*
 *  Copyright (C) 2010 Amon
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
package com.magicpwd._prop;

import com.magicpwd.__i.IPropBean;
import com.magicpwd._comp.LnkLabel;
import com.magicpwd._comp.WCubeBox;

/**
 *
 * @author Amon
 */
public class IdioProp extends javax.swing.JPanel implements IPropBean
{

    private WCubeBox cb_CubeBox;
    private final int USER_CNT = 4;

    public IdioProp()
    {
    }

    @Override
    public void initView()
    {
        pl_CubePanel = new javax.swing.JPanel();
        cb_CubeBox = new WCubeBox();
        pl_CubePanel.add(cb_CubeBox);

        pl_IdioPanel = new javax.swing.JPanel();
        pl_IdioPanel.setLayout(new java.awt.GridBagLayout());

        lb_UserName = new javax.swing.JLabel[USER_CNT];
        ll_UserMail = new LnkLabel[USER_CNT];
        javax.swing.JLabel lbl;
        LnkLabel lnk;
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        for (int i = 0; i < USER_CNT; i += 1)
        {
            lbl = new javax.swing.JLabel();
            gbc.gridx = 0;
            gbc.gridy = i;
            pl_IdioPanel.add(lbl, gbc);
            lb_UserName[i] = lbl;

            lnk = new LnkLabel();
            gbc.gridx = 1;
            gbc.gridy = i;
            pl_IdioPanel.add(lnk, gbc);
            ll_UserMail[i] = lnk;
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg.addComponent(pl_IdioPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE);
        hpg.addComponent(pl_CubePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(pl_CubePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(pl_IdioPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE);
        vsg.addContainerGap();
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(vsg);
        layout.setVerticalGroup(vpg);
    }

    @Override
    public void initLang()
    {
        int i = 0;
        lb_UserName[i].setText("Amon");
        ll_UserMail[i].setText("a@b.c");
        ll_UserMail[i].setLinkUrl("a@b.c");

        i += 1;
        lb_UserName[i].setText("Aven");
        ll_UserMail[i].setText("a@b.c");

        i += 1;
        lb_UserName[i].setText("Sand");
        ll_UserMail[i].setText("a@b.c");

        i += 1;
        lb_UserName[i].setText("sanjer");
        ll_UserMail[i].setText("a@b.c");
    }

    @Override
    public void initData()
    {
        cb_CubeBox.setPreferredSize(new java.awt.Dimension(96, 96));
        cb_CubeBox.initData();
    }

    @Override
    public void showData()
    {
        cb_CubeBox.start();
    }

    @Override
    public void saveData()
    {
        cb_CubeBox.stop();
    }

    @Override
    public javax.swing.JPanel getPanel()
    {
        return this;
    }
    private javax.swing.JLabel[] lb_UserName;
    private LnkLabel[] ll_UserMail;
    private javax.swing.JPanel pl_CubePanel;
    private javax.swing.JPanel pl_IdioPanel;
}
