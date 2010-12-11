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
package com.magicpwd._bean;

import com.magicpwd.__a.AEditBean;
import com.magicpwd.__a.AFrame;
import com.magicpwd.__i.IEditItem;
import com.magicpwd.__i.mpwd.IMpwdBean;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WButtonGroup;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;

/**
 *
 * @author Amon
 */
public abstract class ASignBean extends AEditBean implements IMpwdBean
{

    private WTextBox dataBox;

    public ASignBean(AFrame formPtn)
    {
        this.formPtn = formPtn;
    }

    protected void initConfView()
    {
        tf_PropData = new javax.swing.JTextField();

        dataBox = new WTextBox(tf_PropData, true);
        dataBox.initView();

        pl_PropConf = new javax.swing.JPanel();
        pl_PropConf.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_SignConf = new BtnLabel();
        bt_SignConf.setIcon(formPtn.readFavIcon("pwds-options", false));
        pl_PropConf.add(bt_SignConf);

        pm_ConfMenu = new javax.swing.JPopupMenu();
        mi_ConfDef = new javax.swing.JCheckBoxMenuItem();
        pm_ConfMenu.add(mi_ConfDef);
        pm_ConfMenu.addSeparator();
    }

    protected void initConfLang()
    {
        Lang.setWText(bt_SignConf, LangRes.P30F1525, "@O");
        Lang.setWTips(bt_SignConf, LangRes.P30F1526, "标记属性设置(Alt + O)");

        Lang.setWText(mi_ConfDef, LangRes.P30F7E01, "默认标记(@D)");

        dataBox.initLang();
    }

    protected void initConfData()
    {
        bt_SignConf.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                bt_SignConfActionPerformed(e);
            }
        });

        java.awt.event.ActionListener action = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                mi_MenuItemActionPerformed(e);
            }
        };
        mi_ConfDef.addActionListener(action);
        formPtn.getMenuPtn().getSubMenu("line-options", pm_ConfMenu, action);
        WButtonGroup group = formPtn.getMenuPtn().getGroup("");
        if (group != null)
        {
            group.add(mi_ConfDef);
        }

        dataBox.initData();
    }

    protected void showConfData()
    {
        WButtonGroup group = formPtn.getMenuPtn().getGroup("");
        if (group != null)
        {
            if (!group.setSelected(itemData.getSpec(IEditItem.SPEC_LINE_TYPE, ""), true))
            {
                mi_ConfDef.setSelected(true);
            }
        }
    }

    private void bt_SignConfActionPerformed(java.awt.event.ActionEvent e)
    {
        pm_ConfMenu.show(bt_SignConf, 0, bt_SignConf.getSize().height);
    }

    private void mi_MenuItemActionPerformed(java.awt.event.ActionEvent e)
    {
    }
    protected javax.swing.JTextField tf_PropData;
    // 配置信息
    protected javax.swing.JPanel pl_PropConf;
    private BtnLabel bt_SignConf;
    protected javax.swing.JPopupMenu pm_ConfMenu;
    private javax.swing.JCheckBoxMenuItem mi_ConfDef;
}
