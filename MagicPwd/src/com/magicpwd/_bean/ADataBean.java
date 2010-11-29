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
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WButtonGroup;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.v.MenuPtn;

/**
 *
 * @author Amon
 */
public abstract class ADataBean extends AEditBean
{

    public ADataBean(AFrame formPtn)
    {
        this.formPtn = formPtn;
    }

    protected void initConfView()
    {
        tf_PropData = new javax.swing.JTextField();

        pl_PropConf = new javax.swing.JPanel();
        pl_PropConf.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_DateConf = new BtnLabel();
        bt_DateConf.setIcon(formPtn.readFavIcon("data-options", false));
        bt_DateConf.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DateConfActionPerformed(evt);
            }
        });
        pl_PropConf.add(bt_DateConf);

        pm_DataConf = new javax.swing.JPopupMenu();
        mi_DataDef = new javax.swing.JCheckBoxMenuItem();
        pm_DataConf.add(mi_DataDef);
        pm_DataConf.addSeparator();
    }

    protected void initConfLang()
    {
        Bean.setText(mi_DataDef, Lang.getLang(LangRes.P30F7C08, "允许为空"));
    }

    protected void initConfData()
    {
        mi_DataDef.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                mi_DataDefActionPerformed(e);
            }
        });

        formPtn.getMenuPtn().getSubMenu("data-options", pm_DataConf, new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                mi_DataConfActionPerformed(e);
            }
        });
    }

    protected void showConfData()
    {
        MenuPtn menuPtn = formPtn.getMenuPtn();
        javax.swing.AbstractButton button;

        // 可否为空
        mi_DataDef.setSelected(IEditItem.SPEC_VALUE_TRUE.equals(itemData.getSpec(IEditItem.SPEC_DATA_OPT)));
        // 数据类型
        WButtonGroup group = menuPtn.getGroup("data-template");
        group.setSelected("dec:" + itemData.getSpec(IEditItem.SPEC_DATA_DEC, "0").replace("f", ""), true, "dec:-1");

        // 固定长度
        button = menuPtn.getButton("data-fixed-int");
        if (button != null)
        {
            button.setSelected(itemData.getSpec(IEditItem.SPEC_DATA_INT, "").endsWith("f"));
        }
        button = menuPtn.getButton("data-fixed-dec");
        if (button != null)
        {
            button.setSelected(itemData.getSpec(IEditItem.SPEC_DATA_DEC, "").endsWith("f"));
        }

        // 数据集合
        String ss = itemData.getSpec(IEditItem.SPEC_DATA_SET, "+0-");
        if (!Char.isValidate(ss))
        {
            ss = "+0-";
        }
        button = menuPtn.getButton("data-pos");
        if (button != null)
        {
            button.setSelected(ss.indexOf('+') >= 0);
        }
        button = menuPtn.getButton("data-0");
        if (button != null)
        {
            button.setSelected(ss.indexOf('0') >= 0);
        }
        button = menuPtn.getButton("data-nav");
        if (button != null)
        {
            button.setSelected(ss.indexOf('-') >= 0);
        }

        // 是否使用特殊符号
        String sc = itemData.getSpec(IEditItem.SPEC_DATA_CHAR);
        boolean specChar = Char.isValidate(sc);
        button = menuPtn.getButton("data-notation");
        if (button != null)
        {
            button.setSelected(specChar);
        }

        changeCharacter(sc);

        // 符号位置
        group = menuPtn.getGroup("data-position");
        if (group != null)
        {
            group.setSelected("pos:" + itemData.getSpec(IEditItem.SPEC_DATA_CHAR_POS, "^"), true);
        }

        button = menuPtn.getButton("data-optional");
        if (button != null)
        {
            button.setSelected("?".equals(itemData.getSpec(IEditItem.SPEC_DATA_CHAR_OPT)));
        }

        changeNotation(specChar);
    }

    private void requestCharacter(String cmd)
    {
        do
        {
            cmd = javax.swing.JOptionPane.showInputDialog(formPtn, Lang.getLang(LangRes.P30F7A50, "请输入特殊符号：\n提示：除加减号（“+”、“-”）及数值以外的字符！"), itemData.getSpec(IEditItem.SPEC_DATA_CHAR, ""));
            if (cmd == null)
            {
                return;
            }
        }
        while (!java.util.regex.Pattern.matches("[^-+\\d]", cmd.trim()));
        changeCharacter(cmd);
        itemData.setSpec(IEditItem.SPEC_DATA_CHAR, cmd);
    }

    private void changeCharacter(String chr)
    {
        javax.swing.AbstractButton button = formPtn.getMenuPtn().getButton("data-character");
        if (button != null)
        {
            button.setText(Char.format(Lang.getLang(LangRes.P30F7C12, "特殊符号：{0}"), Char.isValidate(chr) ? chr : "N/A"));
        }
    }

    private void changeNotation(boolean enabled)
    {
        MenuPtn menuPtn = formPtn.getMenuPtn();
        javax.swing.AbstractButton button = menuPtn.getButton("data-character");
        if (button != null)
        {
            button.setEnabled(enabled);
        }

        button = menuPtn.getButton("data-thehead");
        if (button != null)
        {
            button.setEnabled(enabled);
        }

        button = menuPtn.getButton("data-thefoot");
        if (button != null)
        {
            button.setEnabled(enabled);
        }

        button = menuPtn.getButton("data-optional");
        if (button != null)
        {
            button.setEnabled(enabled);
        }
    }

    private void bt_DateConfActionPerformed(java.awt.event.ActionEvent evt)
    {
        pm_DataConf.show(bt_DateConf, 0, bt_DateConf.getSize().height);
    }

    private void mi_DataDefActionPerformed(java.awt.event.ActionEvent evt)
    {
        itemData.setSpec(IEditItem.SPEC_DATA_OPT, mi_DataDef.isSelected() ? IEditItem.SPEC_VALUE_TRUE : IEditItem.SPEC_VALUE_FAIL);
    }

    private void mi_DataConfActionPerformed(java.awt.event.ActionEvent evt)
    {
        String cmd = evt.getActionCommand();
        if (!Char.isValidate(cmd))
        {
            return;
        }

        // 小数精度
        if (cmd.startsWith("dec:"))
        {
            readPrecision(IEditItem.SPEC_DATA_DEC, evt);
            return;
        }

        // 整数精度
        if (cmd.startsWith("int:"))
        {
            readPrecision(IEditItem.SPEC_DATA_INT, evt);
            return;
        }

        // 数据集合
        if (cmd.startsWith("set:"))
        {
            cmd = cmd.substring(4);
            if (!java.util.regex.Pattern.matches("^[-+0]$", cmd))
            {
                return;
            }

            javax.swing.AbstractButton button = (javax.swing.AbstractButton) evt.getSource();
            if (button != null)
            {
                String set = itemData.getSpec(IEditItem.SPEC_DATA_SET, "+0-");
                if (button.isSelected())
                {
                    set += cmd;
                }
                else
                {
                    set = set.replace(cmd, "");
                }
                if (!Char.isValidate(set))
                {
                    set = "+0-";
                }
                itemData.setSpec(IEditItem.SPEC_DATA_SET, set);
            }
            return;
        }

        // 是否使用特殊符号
        if ("notation".equals(cmd))
        {
            javax.swing.AbstractButton button = (javax.swing.AbstractButton) evt.getSource();
            if (button != null)
            {
                boolean b = button.isSelected();
                changeNotation(b);
                if (b)
                {
                    requestCharacter("");
                }
                else
                {
                    itemData.setSpec(IEditItem.SPEC_DATA_CHAR, "");
                    changeCharacter("");
                }
            }
            return;
        }

        // 符号变更
        if ("character".equals(cmd))
        {
            requestCharacter("");
            return;
        }

        // 符号位置
        if (cmd.startsWith("pos:"))
        {
            cmd = cmd.substring(4);
            if ("^".equals(cmd) || "$".equals(cmd))
            {
                itemData.setSpec(IEditItem.SPEC_DATA_CHAR_POS, cmd);
            }
            return;
        }

        // 可选输入
        if ("optional".contains(cmd))
        {
            javax.swing.AbstractButton button = (javax.swing.AbstractButton) evt.getSource();
            if (button != null)
            {
                itemData.setSpec(IEditItem.SPEC_DATA_CHAR_OPT, button.isSelected() ? "?" : "");
            }
            return;
        }
    }

    private void readPrecision(int idx, java.awt.event.ActionEvent evt)
    {
        String spc = itemData.getSpec(idx, "");
        String cmd = evt.getActionCommand().substring(4);

        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("^[+-]?\\d+$").matcher(cmd);
        if (matcher.find())
        {
            cmd = matcher.group();
            if (cmd.charAt(0) == '-')
            {
                do
                {
                    cmd = javax.swing.JOptionPane.showInputDialog(formPtn, Lang.getLang(LangRes.P30F7A4F, "请输入一个非负数值！"), spc.replace("f", ""));
                    if (cmd == null)
                    {
                        return;
                    }
                    cmd = cmd.trim();
                }
                while (!java.util.regex.Pattern.matches("^\\d+$", cmd));
            }
            itemData.setSpec(idx, spc.replaceAll("\\d+", cmd));
            return;
        }
        if ("f".equals(cmd))
        {
            javax.swing.AbstractButton button = (javax.swing.AbstractButton) evt.getSource();
            if (button != null)
            {
                if (button.isSelected())
                {
                    spc += cmd;
                }
                else
                {
                    spc = spc.replace(cmd, "");
                }
                itemData.setSpec(idx, spc);
            }
            return;
        }
    }

    protected boolean processData()
    {
        // 去除多余空格
        String data = tf_PropData.getText().replaceAll("[,\\s]+", "");
        tf_PropData.setText(data);

        if (data.length() < 1)
        {
            if (IEditItem.SPEC_VALUE_TRUE.equals(itemData.getSpec(IEditItem.SPEC_DATA_OPT)))
            {
                return true;
            }
            Lang.showMesg(formPtn, LangRes.P30F7A4B, "数值不能为空！");
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
        String sc = itemData.getSpec(IEditItem.SPEC_DATA_CHAR, "");
        boolean bc = Char.isValidate(sc);
        String ss = itemData.getSpec(IEditItem.SPEC_DATA_SET, "+0-");

        if (Char.isValidate(ss))
        {
            // 非0
            if (ss.indexOf("0") < 0)
            {
                if (java.util.regex.Pattern.matches("^[+-]?0*(\\.0*)?$", bc ? data.replace(sc, "") : data))
                {
                    Lang.showMesg(formPtn, LangRes.P30F7A4C, "请输入一个不为0的数值！");
                    return false;
                }
            }
            // 仅0
            else if ("0".equals(ss))
            {
                if (!java.util.regex.Pattern.matches("^[+-]?0*(\\.0*)?$", bc ? data.replace(sc, "") : data))
                {
                    Lang.showMesg(formPtn, LangRes.P30F7A4D, "您输入的数值有误！");
                    return false;
                }
            }
            else
            {
                ss = ss.replace("0", "");

                // 仅负数
                if ("-".equals(ss))
                {
                    regex.append("[-]");
                }
                else
                {
                    regex.append("[+-]?");
                }
            }
        }

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\d+");

        // 整数部分
        String si = itemData.getSpec(IEditItem.SPEC_DATA_INT, "");
        java.util.regex.Matcher mi = pattern.matcher(si);
        int ii = mi.find() ? Integer.parseInt(mi.group()) : 0;
        regex.append("\\d").append(ii > 0 ? "{1," + ii + "}" : "+");

        // 小数部分
        String sd = itemData.getSpec(IEditItem.SPEC_DATA_DEC, "");
        java.util.regex.Matcher md = pattern.matcher(sd);
        int id = md.find() ? Integer.parseInt(md.group()) : 0;
        if (id > 0)
        {
            regex.append("(\\.").append("\\d{1,").append(id).append("})?");
        }

        // 特殊符号
        if (bc)
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
            Lang.showMesg(formPtn, LangRes.P30F7A4D, "您输入的数值有误！");
            return false;
        }

        // 固定长度
        if (sd.endsWith("f"))
        {
            data = fix(data, true, id);
        }
        if (si.endsWith("f"))
        {
            data = fix(data, false, ii);
        }

        itemData.setData(data);
        return true;
    }

    private static String fix(String txt, boolean dec, int cnt)
    {
        java.util.regex.Matcher matcher;
        // 小数定长
        if (dec)
        {
            if (cnt < 1)
            {
                return txt;
            }

            matcher = java.util.regex.Pattern.compile("[.]\\d+").matcher(txt);
            if (!matcher.find())
            {
                return txt;
            }

            String src = matcher.group();
            int i = src.length() - 1;
            if (i >= cnt)
            {
                return txt;
            }

            StringBuilder dst = new StringBuilder(src);
            while (i++ < cnt)
            {
                dst.append('0');
            }
            return txt.replace(src, dst.toString());
        }

        // 整数定长
        matcher = java.util.regex.Pattern.compile("\\d+[.]").matcher(txt);
        if (!matcher.find())
        {
            return txt;
        }

        String src = matcher.group();
        int i = src.length() - 1;
        if (i >= cnt)
        {
            return txt;
        }

        StringBuilder dst = new StringBuilder(src);
        while (i++ < cnt)
        {
            dst.insert(0, '0');
        }
        return txt.replace(src, dst.toString());
    }
    protected javax.swing.JTextField tf_PropData;
    // 配置信息
    protected javax.swing.JPanel pl_PropConf;
    private BtnLabel bt_DateConf;
    private javax.swing.JPopupMenu pm_DataConf;
    private javax.swing.JCheckBoxMenuItem mi_DataDef;
}