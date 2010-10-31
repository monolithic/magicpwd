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
import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IEditItem;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public abstract class ADataBean extends AEditBean
{

    protected AFrame formPtn;
    protected IEditItem itemData;

    public ADataBean(AFrame formPtn)
    {
        this.formPtn = formPtn;
    }

    protected void initConfView()
    {
        pl_PropConf = new javax.swing.JPanel();
        pl_PropConf.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_DateConf = new BtnLabel();
        bt_DateConf.setIcon(formPtn.getUserMdl().readIcon(ConsEnv.FEEL_PATH + "options.png"));
        bt_DateConf.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DateConfActionPerformed(evt);
            }
        });
        pl_PropConf.add(bt_DateConf);

        AMpwdAction action = new AMpwdAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                mi_DataConfActionPerformed(e);
            }

            @Override
            public void doInit(Object object)
            {
            }

            @Override
            public void reInit(javax.swing.AbstractButton button)
            {
            }
        };

        pm_DataConf = new javax.swing.JPopupMenu();
        mi_DataDef = new javax.swing.JCheckBoxMenuItem();
        pm_DataConf.add(mi_DataDef);
        pm_DataConf.addSeparator();
        formPtn.getMenuPtn().getSubMenu("data-options", pm_DataConf, action);
    }

    protected void initConfLang()
    {
    }

    protected void initConfData()
    {
    }

    private void bt_DateConfActionPerformed(java.awt.event.ActionEvent evt)
    {
        pm_DataConf.show(bt_DateConf, 0, bt_DateConf.getSize().height);
    }

    private void mi_DataConfActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    protected boolean processData()
    {
        // 去除多余空格
        String data = tf_PropData.getText().trim();
        if (data.length() < 1)
        {
            System.out.println("请输入具体的数据！");
            return false;
        }

        // 数据格式合法化
        if (data.startsWith("."))
        {
            data = '0' + data;
        }
        if (data.endsWith("."))
        {
            data += '0';
        }

        // 起始限制
        StringBuilder regex = new StringBuilder("^");

        // 数据集合
        String ss = itemData.getSpec(IEditItem.SPEC_DATA_SET, "");
        if (Char.isValidate(ss))
        {
            // 非0
            if (ss.indexOf("0") < 0)
            {
                if (java.util.regex.Pattern.matches("^[+-]?0*(\\.0*)?$", data))
                {
                    System.out.println("请输入一个不为0的数值！");
                    return false;
                }
            }
            // 仅负数
            if ("-".equals(ss))
            {
                regex.append("-");
            }
            else
            {
                regex.append("[+-]?");
            }
        }

        // 整数部分
        regex.append("\\d+");

        // 小数部分
        String sd = itemData.getSpec(IEditItem.SPEC_DATA_DEC, "");
        int id = java.util.regex.Pattern.matches("^\\d+$", sd) ? Integer.parseInt(sd) : 0;
        if (id > 0)
        {
            regex.append("\\.").append("\\d{1,").append(id).append("}");
        }

        // 特殊符号
        String sc = itemData.getSpec(IEditItem.SPEC_DATA_CHAR, "");
        if (Char.isValidate(sc))
        {
            sc = '[' + sc + ']';

            // 是否可选
            String so = itemData.getSpec(IEditItem.SPEC_DATA_CHAR_OPT, "?");
            if ("?".equals(so))
            {
                sc += so;
            }
            // 符号位置
            String sp = itemData.getSpec(IEditItem.SPEC_DATA_CHAR_POS, "^");
            if ("$".equals(sp))
            {
                regex.append(sc);
            }
            else
            {
                regex.insert(1, sc);
            }
        }

        // 结束限制
        regex.append('$');

        if (!java.util.regex.Pattern.matches(regex.toString(), data))
        {
            System.out.println("您输入的数据有误！");
            return false;
        }

        itemData.setData(data);
        return true;
    }
    protected javax.swing.JTextField tf_PropData;
    // 配置信息
    protected javax.swing.JPanel pl_PropConf;
    private BtnLabel bt_DateConf;
    private javax.swing.JPopupMenu pm_DataConf;
    private javax.swing.JCheckBoxMenuItem mi_DataDef;
}
