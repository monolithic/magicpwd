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
import com.magicpwd._comn.item.EditItem;
import com.magicpwd._comn.prop.Char;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WButtonGroup;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import javax.swing.JOptionPane;

/**
 * Application: MagicPwd
 * Author     : Amon
 * Encoding   : UTF-8
 * Created    : 2010-10-27 20:24:00
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public abstract class APwdsBean extends AEditBean
{

    protected AFrame formPtn;
    protected IEditItem itemData;
    private boolean askOverWrite;

    public APwdsBean(AFrame formPtn)
    {
        this.formPtn = formPtn;
    }

    protected void initConfView()
    {
        pf_PropData = new javax.swing.JPasswordField();
        pf_PropData.setEchoChar(ConsEnv.PWDS_MASK);

        pl_PropConf = new javax.swing.JPanel();
        pl_PropConf.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_PwdsView = new BtnLabel();
        bt_PwdsView.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsViewActionPerformed(evt);
            }
        });
        pl_PropConf.add(bt_PwdsView);

        bt_PwdsGent = new BtnLabel();
        bt_PwdsGent.setIcon(formPtn.getUserMdl().readIcon(ConsEnv.FEEL_PATH + "pwds-generate.png"));
        bt_PwdsGent.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsGentActionPerformed(evt);
            }
        });
        pl_PropConf.add(bt_PwdsGent);

        bt_PwdsConf = new BtnLabel();
        bt_PwdsConf.setIcon(formPtn.getUserMdl().readIcon(ConsEnv.FEEL_PATH + "options.png"));
        bt_PwdsConf.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsConfActionPerformed(evt);
            }
        });
        pl_PropConf.add(bt_PwdsConf);

        pm_ConfMenu = new javax.swing.JPopupMenu();
        mu_SizeMenu = new javax.swing.JMenu();
        pm_ConfMenu.add(mu_SizeMenu);
        mu_CharMenu = new javax.swing.JMenu();
        pm_ConfMenu.add(mu_CharMenu);

        initSizeView();
        initCharView();
        initUrptView();
    }

    private void initSizeView()
    {
        bg_SizeGroup = new WButtonGroup();
        java.awt.event.ActionListener al = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_SizeMenuActionPerformed(evt);
            }
        };

        // 默认
        mi_SizeDef = new javax.swing.JCheckBoxMenuItem();
        mi_SizeDef.addActionListener(al);
        mu_SizeMenu.add(mi_SizeDef);
        bg_SizeGroup.add("0", mi_SizeDef);

        mu_SizeMenu.addSeparator();

        mi_SizeNum = new javax.swing.JCheckBoxMenuItem[6];
        javax.swing.JCheckBoxMenuItem menuItem;
        for (int i = 0, j = mi_SizeNum.length; i < j; i += 1)
        {
            menuItem = new javax.swing.JCheckBoxMenuItem();
            menuItem.addActionListener(al);
            mu_SizeMenu.add(menuItem);
            mi_SizeNum[i] = menuItem;
        }

        mu_SizeMenu.addSeparator();

        // 其它...
        mi_SizeMore = new javax.swing.JCheckBoxMenuItem();
        mi_SizeMore.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_SizeMoreActionPerformed(evt);
            }
        });
        mu_SizeMenu.add(mi_SizeMore);
    }

    private void initCharView()
    {
        bg_CharGroup = new WButtonGroup();
        java.awt.event.ActionListener al = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_CharMenuActionPerformed(evt);
            }
        };

        mi_CharDef = new javax.swing.JCheckBoxMenuItem();
        mi_CharDef.addActionListener(al);
        mu_CharMenu.add(mi_CharDef);
        bg_CharGroup.add("", mi_CharDef);

        mu_CharMenu.addSeparator();

        int sysSize = formPtn.getUserMdl().getCharMdl().getCharSys().size();
        int usrSize = formPtn.getUserMdl().getCharMdl().getCharUsr().size();
        mi_CharPre = new javax.swing.JCheckBoxMenuItem[sysSize + usrSize];

        javax.swing.JCheckBoxMenuItem menuItem;
        for (int i = 0; i < sysSize; i += 1)
        {
            menuItem = new javax.swing.JCheckBoxMenuItem();
            mu_CharMenu.add(menuItem);
            menuItem.addActionListener(al);
            mi_CharPre[i] = menuItem;
        }

        if (usrSize < 1)
        {
            return;
        }

        mu_CharMenu.addSeparator();

        for (int i = 0; i < usrSize; i += 1)
        {
            menuItem = new javax.swing.JCheckBoxMenuItem();
            mu_CharMenu.add(menuItem);
            menuItem.addActionListener(al);
            mi_CharPre[sysSize + i] = menuItem;
        }
    }

    private void initUrptView()
    {
        mi_LoopMenu = new javax.swing.JCheckBoxMenuItem();
        mi_LoopMenu.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_UrptMenuActionPerformed(evt);
            }
        });
        pm_ConfMenu.add(mi_LoopMenu);
        mi_LoopMenu.setSelected(formPtn.getUserMdl().isPwdsLoop());
    }

    protected void initConfLang()
    {
        Lang.setWText(bt_PwdsView, LangRes.P30F1507, "&M");
        Lang.setWTips(bt_PwdsView, LangRes.P30F1508, "点击显示口令(Alt + M)");

        Lang.setWText(bt_PwdsGent, LangRes.P30F150B, "&G");
        Lang.setWTips(bt_PwdsGent, LangRes.P30F150C, "生成口令(Alt + G)");

        Lang.setWText(bt_PwdsConf, LangRes.P30F150D, "&O");
        Lang.setWTips(bt_PwdsConf, LangRes.P30F150E, "口令设置(Alt + O)");

        initSizeLang();
        initCharLang();
        initNrptLang();
    }

    private void initSizeLang()
    {
        Lang.setWText(mu_SizeMenu, LangRes.P30F7C01, "口令长度");

        Lang.setWText(mi_SizeDef, LangRes.P30F7C02, "默认");
        mi_SizeDef.setActionCommand("0");

        String t = Lang.getLang(LangRes.P30F7C03, "位");
        String[] text =
        {
            "6", "8", "12", "16", "24", "32"
        };
        String[] keys =
        {
            "6", "8", "12", "16", "24", "32"
        };

        javax.swing.JCheckBoxMenuItem menuItem;
        for (int i = 0; i < 6; i += 1)
        {
            menuItem = mi_SizeNum[i];
            menuItem.setText(text[i] + t);
            menuItem.setToolTipText(text[i] + t);
            menuItem.setActionCommand(keys[i]);
            bg_SizeGroup.add(keys[i], menuItem);
        }

        Lang.setWText(mi_SizeMore, LangRes.P30F7C04, "其它...");
        bg_SizeGroup.add("-1", mi_SizeMore);
    }

    private void initCharLang()
    {
        Lang.setWText(mu_CharMenu, LangRes.P30F7C05, "字符空间");

        Lang.setWText(mi_CharDef, LangRes.P30F7C06, "默认");
        mi_CharDef.setActionCommand(ConsCfg.DEF_PWDS_HASH);

        javax.swing.JCheckBoxMenuItem menu;
        int i = 0;
        for (Char item : formPtn.getUserMdl().getCharMdl().getCharSys())
        {
            menu = mi_CharPre[i++];
            menu.setText(item.getP30F2104());
            menu.setToolTipText(item.getP30F2105());
            menu.setActionCommand(item.getP30F2103());
            bg_CharGroup.add(item.getP30F2103(), menu);
        }

        for (Char item : formPtn.getUserMdl().getCharMdl().getCharUsr())
        {
            menu = mi_CharPre[i++];
            menu.setText(item.getP30F2104());
            menu.setToolTipText(item.getP30F2105());
            menu.setActionCommand(item.getP30F2103());
            bg_CharGroup.add(item.getP30F2103(), menu);
        }
    }

    private void initNrptLang()
    {
        Lang.setWText(mi_LoopMenu, LangRes.P30F7C07, "允许重复");
    }

    protected void initConfData()
    {
        changeView(true);
    }

    private void bt_PwdsConfActionPerformed(java.awt.event.ActionEvent evt)
    {
        pm_ConfMenu.show(bt_PwdsConf, 0, bt_PwdsConf.getSize().height);
    }

    private void bt_PwdsGentActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (askOverWrite && pf_PropData.getPassword().length > 1)
        {
            if (Lang.showFirm(formPtn, "", "") != javax.swing.JOptionPane.YES_OPTION)
            {
                return;
            }
            askOverWrite = false;
        }

        try
        {
            String c = bg_CharGroup.getSelection().getActionCommand();
            c = getPwdsChar(c);
            String s = bg_SizeGroup.getSelection().getActionCommand();
            if (!com.magicpwd._util.Char.isValidatePositiveInteger(s))
            {
                s = ConsCfg.DEF_PWDS_SIZE;
            }
            pf_PropData.setText(new String(Util.nextRandomKey(c, Integer.parseInt(s), mi_LoopMenu.isSelected())));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(formPtn, null, exp.getLocalizedMessage());
        }
    }

    private void bt_PwdsViewActionPerformed(java.awt.event.ActionEvent evt)
    {
        changeView(pf_PropData.getEchoChar() == 0);
    }

    private void mi_SizeMenuActionPerformed(java.awt.event.ActionEvent evt)
    {
        String t = evt.getActionCommand();
        if (!com.magicpwd._util.Char.isValidatePositiveInteger(t))
        {
            t = "0";
            mi_SizeDef.setSelected(true);
        }

        itemData.setSpec(EditItem.SPEC_PWDS_SIZE, t);
    }

    private void mi_SizeMoreActionPerformed(java.awt.event.ActionEvent evt)
    {
        String s = JOptionPane.showInputDialog(formPtn, "", itemData.getSpec(EditItem.SPEC_PWDS_SIZE, ConsCfg.DEF_PWDS_SIZE));
        if (s == null)
        {
            return;
        }
        s = s.trim();
        if (!com.magicpwd._util.Char.isValidatePositiveInteger(s))
        {
            Lang.showMesg(formPtn, "", "请输入一个有效的自然数！");
            s = ConsCfg.DEF_PWDS_SIZE;
            mi_SizeDef.setSelected(true);
            return;
        }

        mi_SizeMore.setActionCommand(s);
        itemData.setSpec(EditItem.SPEC_PWDS_SIZE, s);
    }

    private void mi_CharMenuActionPerformed(java.awt.event.ActionEvent evt)
    {
        String t = evt.getActionCommand();
        if (!com.magicpwd._util.Char.isValidateHash(t))
        {
            return;
        }

        itemData.setSpec(EditItem.SPEC_PWDS_HASH, t);
    }

    private void mi_UrptMenuActionPerformed(java.awt.event.ActionEvent evt)
    {
        itemData.setSpec(EditItem.SPEC_PWDS_LOOP, mi_LoopMenu.isSelected() ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    private void changeView(boolean mask)
    {
        if (mask)
        {
            bt_PwdsView.setIcon(formPtn.readFavIcon(ConsEnv.FEEL_PATH + "pwds-mask.png", true));
            pf_PropData.setEchoChar(ConsEnv.PWDS_MASK);
            Lang.setWText(bt_PwdsView, LangRes.P30F1507, "&M");
            Lang.setWTips(bt_PwdsView, LangRes.P30F1508, "点击显示口令(Alt + M)");
        }
        else
        {
            bt_PwdsView.setIcon(formPtn.readFavIcon(ConsEnv.FEEL_PATH + "pwds-view.png", true));
            pf_PropData.setEchoChar('\0');
            Lang.setWText(bt_PwdsView, LangRes.P30F1509, "&M");
            Lang.setWTips(bt_PwdsView, LangRes.P30F150A, "点击隐藏口令(Alt + M)");
        }
    }

    private String getPwdsChar(String hash)
    {
        if (!com.magicpwd._util.Char.isValidateHash(hash))
        {
            return ConsCfg.DEF_PWDS_CHAR;
        }
        for (Char item : formPtn.getUserMdl().getCharMdl().getCharSys())
        {
            if (hash.equals(item.getP30F2103()))
            {
                return item.getP30F2106();
            }
        }
        for (Char item : formPtn.getUserMdl().getCharMdl().getCharUsr())
        {
            if (hash.equals(item.getP30F2103()))
            {
                return item.getP30F2106();
            }
        }
        return ConsCfg.DEF_PWDS_CHAR;
    }

    private void initMenuData()
    {
    }
    protected javax.swing.JPasswordField pf_PropData;
    // 配置信息
    protected javax.swing.JPanel pl_PropConf;
    private BtnLabel bt_PwdsConf;
    private BtnLabel bt_PwdsGent;
    private BtnLabel bt_PwdsView;
    private javax.swing.JPopupMenu pm_ConfMenu;
    private javax.swing.JMenu mu_SizeMenu;
    private javax.swing.JCheckBoxMenuItem mi_SizeDef;
    private javax.swing.JCheckBoxMenuItem[] mi_SizeNum;
    protected javax.swing.JCheckBoxMenuItem mi_SizeMore;
    protected WButtonGroup bg_SizeGroup;
    private javax.swing.JMenu mu_CharMenu;
    private javax.swing.JCheckBoxMenuItem mi_CharDef;
    private javax.swing.JCheckBoxMenuItem[] mi_CharPre;
    protected WButtonGroup bg_CharGroup;
    protected javax.swing.JCheckBoxMenuItem mi_LoopMenu;
}
