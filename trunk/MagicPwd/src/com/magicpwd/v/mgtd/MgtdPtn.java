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
import com.magicpwd._comp.date.WDateChooser;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.tray.TrayPtn;
import java.awt.event.ActionEvent;

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
        this.getContentPane().setLayout(new java.awt.FlowLayout());
        b = new javax.swing.JButton();
        b.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                test();
            }
        });
        this.getContentPane().add(b);

        this.setIconImage(Bean.getLogo(16));

        this.setSize(400, 300);
//        this.pack();
        Bean.centerForm(this, null);
        this.setVisible(true);
        return true;
    }

    @Override
    public boolean initLang()
    {
        setTitle(Lang.getLang(LangRes.P30F6201, "魔方密码"));
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
        WDateChooser dc = new WDateChooser();
        dc.show(b, 0, b.getHeight());
    }
    private javax.swing.JButton b;
}
