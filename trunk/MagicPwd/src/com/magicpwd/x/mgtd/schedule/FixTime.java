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
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._comn.mpwd.Hint;
import com.magicpwd._comp.BtnLabel;
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
public class FixTime extends javax.swing.JPanel implements IMgtdBean
{

    private MgtdDlg mgtdDlg;
    private javax.swing.SpinnerDateModel smTime;

    public FixTime(MgtdDlg mgtdDlg)
    {
        this.mgtdDlg = mgtdDlg;
    }

    @Override
    public void initView()
    {
        btTime = new BtnLabel();
        spTime = new javax.swing.JSpinner();
        smTime = new javax.swing.SpinnerDateModel();
        spTime.setModel(smTime);
        lbTime = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
//        hsg.addContainerGap();
        hsg.addComponent(lbTime);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(spTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(btTime, 21, 21, 21);
//        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(lbTime);
        vpg.addComponent(spTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg.addComponent(btTime, 21, 21, 21);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addContainerGap();
        vsg.addGroup(vpg);
//        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);
    }

    @Override
    public void initLang()
    {
        lbTime.setText("提醒时间");
    }

    @Override
    public String getName()
    {
        return "fixTime";
    }

    @Override
    public String getTitle()
    {
        return "定时提醒";
    }

    @Override
    public boolean showData(Mgtd mgtd)
    {
        Hint mtts = mgtd.getMtts(0);
        if (mtts != null)
        {
            spTime.setValue(new java.util.Date(mtts.getP30F0403()));
            return true;
        }
        return false;
    }

    @Override
    public boolean saveData(Mgtd mgtd)
    {
        Hint mtts = new Hint();
        mtts.setP30F0402(mgtd.getP30F0308());
        mtts.setP30F0403(smTime.getDate().getTime());
        mtts.setP30F0404(0);
        mtts.setP30F0405("");
        mgtd.addMtts(mtts);
        return true;
    }
    private BtnLabel btTime;
    private javax.swing.JLabel lbTime;
    private javax.swing.JSpinner spTime;
}
