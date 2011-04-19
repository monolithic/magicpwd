/*
 *  Copyright (C) 2011 Aven
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
package com.magicpwd.x.mgtd.schedule;

import com.magicpwd.__i.mgtd.IMgtdBean;
import com.magicpwd._comn.mpwd.Hint;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._util.Lang;
import com.magicpwd.x.mgtd.MgtdDlg;

/**
 * Application: MagicPwd
 * Author     : Aven
 * Encoding   : UTF-8
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class Special extends javax.swing.JPanel implements IMgtdBean
{

    private MgtdDlg mgtdDlg;

    public Special(MgtdDlg mgtdDlg)
    {
        this.mgtdDlg = mgtdDlg;
    }

    @Override
    public void initView()
    {
        lbSpecial = new javax.swing.JLabel();
        cbStartup = new javax.swing.JCheckBox();
        cbBeforeExit = new javax.swing.JCheckBox();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(cbBeforeExit);
        hpg1.addComponent(cbStartup);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
//        hsg1.addContainerGap();
        hsg1.addComponent(lbSpecial);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addGroup(hpg1);
//        hsg1.addContainerGap();
        layout.setHorizontalGroup(hsg1);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lbSpecial);
        vpg1.addComponent(cbStartup);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
//        vsg1.addContainerGap();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(cbBeforeExit);
//        vsg1.addContainerGap();
        layout.setVerticalGroup(vsg1);
    }

    @Override
    public void initLang()
    {
        lbSpecial.setText("特殊功能");
        cbStartup.setText("程序启动时");
        cbBeforeExit.setText("程序退出时");
    }

    @Override
    public String getName()
    {
        return "special";
    }

    @Override
    public String getTitle()
    {
        return "特殊提醒";
    }

    @Override
    public boolean showData(Mgtd mgtd)
    {
        for (Hint hint : mgtd.getHintList())
        {
            if (-2L == hint.getP30F0403())
            {
                cbStartup.setSelected(true);
                continue;
            }
            if (-3L == hint.getP30F0403())
            {
                cbBeforeExit.setSelected(true);
                continue;
            }
        }
        return true;
    }

    @Override
    public boolean saveData(Mgtd mgtd)
    {
        java.util.List<Hint> list = new java.util.ArrayList<Hint>();

        if (cbStartup.isSelected())
        {
            Hint hint = new Hint();
            hint.setP30F0403(-2L);
            hint.setP30F0404(0);
            hint.setP30F0405("");
            list.add(hint);
        }
        if (cbBeforeExit.isSelected())
        {
            Hint hint = new Hint();
            hint.setP30F0403(-3L);
            hint.setP30F0404(0);
            hint.setP30F0405("");
            list.add(hint);
        }
        if (list.size() < 1)
        {
            Lang.showMesg(mgtdDlg, null, "请选择特殊提醒时间！");
            return false;
        }

        mgtd.setHintList(list);
        return true;
    }
    private javax.swing.JCheckBox cbStartup;
    private javax.swing.JCheckBox cbBeforeExit;
    private javax.swing.JLabel lbSpecial;
}
