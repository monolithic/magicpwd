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
package com.magicpwd.x.mgtd.method;

import com.magicpwd.__i.mgtd.IMgtdBean;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._util.Char;
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
public class Audio extends javax.swing.JPanel implements IMgtdBean
{

    private MgtdDlg mgtdDlg;

    public Audio(MgtdDlg mgtdDlg)
    {
        this.mgtdDlg = mgtdDlg;
    }

    @Override
    public void initView()
    {
        btPath = new BtnLabel();
        tfPath = new javax.swing.JTextField();
        lbPath = new javax.swing.JLabel();
        lbPath.setLabelFor(tfPath);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
//        hsg.addContainerGap();
        hsg.addComponent(lbPath);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(tfPath, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(btPath,21,21,21);
//        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(lbPath);
        vpg.addComponent(tfPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg.addComponent(btPath,21,21,21);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addContainerGap();
        vsg.addGroup(vpg);
//        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);
    }

    @Override
    public void initLang()
    {
        lbPath.setText("音频文件");
    }

    @Override
    public String getName()
    {
        return "audio";
    }

    @Override
    public String getTitle()
    {
        return "播放声音";
    }

    @Override
    public boolean showData(Mgtd mgtd)
    {
        tfPath.setText(mgtd.getP30F030F());
        return true;
    }

    @Override
    public boolean saveData(Mgtd mgtd)
    {
        String text = tfPath.getText();
        if (!Char.isValidate(text))
        {
            Lang.showMesg(mgtdDlg, null, "请输入或选择您要打开的文件！");
            tfPath.requestFocus();
            return false;
        }
        java.io.File file = new java.io.File(text);
        if (!file.exists())
        {
            Lang.showMesg(mgtdDlg, null, "您输入或选择的文件不存在！");
            tfPath.requestFocus();
            return false;
        }
        if (!file.canRead())
        {
            Lang.showMesg(mgtdDlg, null, "无法访问您输入或选择的文件！");
            tfPath.requestFocus();
            return false;
        }
        mgtd.setP30F030F(text);
        return true;
    }
    private BtnLabel btPath;
    private javax.swing.JTextField tfPath;
    private javax.swing.JLabel lbPath;
}
