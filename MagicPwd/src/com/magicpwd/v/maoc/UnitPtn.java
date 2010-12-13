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
package com.magicpwd.v.maoc;

import com.magicpwd._comn.S1S2;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import com.magicpwd.m.maoc.KeysMdl;
import com.magicpwd.m.maoc.UnitMdl;

/**
 *
 * @author Amon
 */
public class UnitPtn extends javax.swing.JPanel
{

    private MaocPtn maocPtn;
    private UserMdl userMdl;
    private UnitMdl unitMdl;
    private KeysMdl keysMdl;
    private S1S2 lastItem;
    private java.util.ArrayList<UnitBar> bodyList;

    public UnitPtn(MaocPtn maocPtn, UserMdl userMdl)
    {
        this.maocPtn = maocPtn;
        this.userMdl = userMdl;
    }

    public void initView()
    {
        pl_Panel = new javax.swing.JPanel();
        cb_Combo = new javax.swing.JComboBox();
        lb_Label = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addContainerGap();
        hsg1.addComponent(pl_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap(1, Short.MAX_VALUE);
        hsg2.addComponent(lb_Label);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(cb_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg1).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg2));

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(cb_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg.addComponent(lb_Label);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(pl_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg);
        vsg.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg));
    }

    public void initLang()
    {
        Bean.setText(lb_Label, Lang.getLang(LangRes.P30FB301, "单位类型(@T)"));
    }

    public void initData()
    {
        unitMdl = new UnitMdl(userMdl);
        unitMdl.init();

        keysMdl = new KeysMdl(userMdl);
        keysMdl.init();
        cb_Combo.setModel(keysMdl);
        cb_Combo.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                cb_ComboActionPerformed(e);
            }
        });

        bodyList = new java.util.ArrayList<UnitBar>();
    }

    public void compute(String input)
    {
        for (UnitBar ptn : bodyList)
        {
            ptn.showData(input);
        }
    }

    private void cb_ComboActionPerformed(java.awt.event.ActionEvent evt)
    {
        Object object = cb_Combo.getSelectedItem();
        if (object == null || object.equals(lastItem) || !(object instanceof S1S2))
        {
            return;
        }
        lastItem = (S1S2) object;

        try
        {
            unitMdl.loadData(lastItem.getK());
            bodyList.clear();

            int step = ConsEnv.PWDS_HEAD_SIZE;
            UnitBar bodyPtn;
            int size = unitMdl.getItemSize();
            while (step < size)
            {
                bodyPtn = new UnitBar(this, unitMdl);
                step = bodyPtn.initView(step);
                bodyPtn.initLang();
                bodyPtn.initData();
                bodyList.add(bodyPtn);
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return;
        }

        pl_Panel.removeAll();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_Panel);
        pl_Panel.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        int j = bodyList.size() - 1;
        for (int i = 0; i < j; i += 1)
        {
            hsg.addComponent(bodyList.get(i), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
            hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
        }
        hsg.addComponent(bodyList.get(j), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg));

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false);
        while (j >= 0)
        {
            vpg.addComponent(bodyList.get(j--), javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        }
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addGroup(vpg);
        vsg.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg));
    }
    private javax.swing.JComboBox cb_Combo;
    private javax.swing.JLabel lb_Label;
    private javax.swing.JPanel pl_Panel;
}
