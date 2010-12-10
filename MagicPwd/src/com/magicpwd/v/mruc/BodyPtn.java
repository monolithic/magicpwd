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
package com.magicpwd.v.mruc;

import com.magicpwd.__i.IEditItem;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Bean;
import com.magicpwd.m.mwiz.KeysMdl;

/**
 *
 * @author Amon
 */
public class BodyPtn extends javax.swing.JPanel
{

    private KeysMdl keysMdl;
    private int currStep;

    public BodyPtn(KeysMdl keysMdl)
    {
        this.keysMdl = keysMdl;
    }

    public void initView(Integer step)
    {
        IEditItem editItem = keysMdl.getItemAt(step);
        if (editItem.getType() != ConsDat.INDX_LINE)
        {
            return;
        }

        currStep = step;
        step += 1;

        ls_NameList = new java.util.ArrayList<javax.swing.JLabel>();
        ls_DataList = new java.util.ArrayList<javax.swing.JTextField>();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        javax.swing.JLabel label;
        javax.swing.JTextField field;

        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        while (true)
        {
            editItem = keysMdl.getItemAt(step);
            if (editItem.getType() == ConsDat.INDX_LINE)
            {
                break;
            }
            step += 1;

            label = new javax.swing.JLabel();
            ls_NameList.add(label);
            hpg1.addComponent(label, javax.swing.GroupLayout.Alignment.TRAILING);
            field = new javax.swing.JTextField(16);
            ls_DataList.add(field);
            hpg2.addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        }
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        hsg.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        int j = step - 1;
        javax.swing.GroupLayout.ParallelGroup vpg;
        for (int i = currStep + 1; i < j; i += 1)
        {
            vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
            vpg.addComponent(ls_NameList.get(i));
            vpg.addComponent(ls_DataList.get(i), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
            vsg.addGroup(vpg);
            vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        }
        vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(ls_NameList.get(j));
        vpg.addComponent(ls_DataList.get(j), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addGroup(vpg);
        vsg.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vsg);
    }

    public void initLang()
    {
        if (ls_NameList.size() < 1 || currStep < ConsEnv.PWDS_HEAD_SIZE)
        {
            return;
        }

        int step = currStep;
        IEditItem editItem = keysMdl.getItemAt(step++);
        setBorder(javax.swing.BorderFactory.createTitledBorder(editItem.getName()));

        for (int i = 0, j = ls_NameList.size(); i < j; i += 1)
        {
            editItem = keysMdl.getItemAt(step + i);
            Bean.setText(ls_NameList.get(i), editItem.getName());
        }
    }

    public void initData()
    {
        if (ls_DataList.size() < 1 || currStep < ConsEnv.PWDS_HEAD_SIZE)
        {
            return;
        }

        IEditItem editItem = keysMdl.getItemAt(currStep);
        setBorder(javax.swing.BorderFactory.createTitledBorder(editItem.getName()));

        for (int i = 1, j = ls_DataList.size(); i < j; i += 1)
        {
            ls_DataList.get(i).addActionListener(new java.awt.event.ActionListener()
            {

                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
//                tf_UserNameActionPerformed(evt);
                }
            });
        }
    }
    private java.util.ArrayList<javax.swing.JLabel> ls_NameList;
    private java.util.ArrayList<javax.swing.JTextField> ls_DataList;
}
