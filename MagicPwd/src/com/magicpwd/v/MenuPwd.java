/**
 * 
 */
package com.magicpwd.v;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.magicpwd._comn.Char;
import com.magicpwd._comn.EditItem;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditItem;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;

/**
 * @author Amon
 * 
 */
public class MenuPwd extends JPopupMenu
{

    private IEditItem tpltData;
    private int charSize;
    private String charSets;

    public MenuPwd()
    {
    }

    public void initView()
    {
        mu_SizeMenu = new javax.swing.JMenu();
        add(mu_SizeMenu);
        mu_CharMenu = new javax.swing.JMenu();
        add(mu_CharMenu);

        initSizeView();
        initCharView();
        initUrptView();
    }

    public void initLang()
    {
        initSizeLang();
        initCharLang();
        initNrptLang();
    }

    public void initData()
    {
        if (UserMdl.isCharUpd())
        {
            mu_CharMenu.removeAll();
            initCharView();
            initCharLang();
            setTplt(tpltData);
            UserMdl.setCharUpd(false);
        }
    }

    public void setTplt(IEditItem tplt)
    {
        this.tpltData = tplt;

        String size = tplt.getSpec(EditItem.SPEC_PWDS_SIZE);
        boolean checked = true;
        if (com.magicpwd._util.Char.isValidatePositiveInteger(size))
        {
            charSize = Integer.parseInt(size);
            for (javax.swing.JCheckBoxMenuItem item : mi_SizeNum)
            {
                if (size.equals(item.getActionCommand()))
                {
                    item.setSelected(true);
                    checked = false;
                    break;
                }
            }
        }
        else
        {
            // 取用户默认配置数据
            size = UserMdl.getUserCfg().getPwdsLen();
            if (!com.magicpwd._util.Char.isValidate(size))
            {
                size = ConsCfg.DEF_PWDS_SIZE;
            }
            charSize = Integer.parseInt(size);
        }
        mi_SizeDef.setSelected(checked);

        String hash = tplt.getSpec(EditItem.SPEC_PWDS_HASH);
        checked = true;
        if (!com.magicpwd._util.Char.isValidate(hash))
        {
            hash = UserMdl.getUserCfg().getPwdsKey();
        }
        if (com.magicpwd._util.Char.isValidate(hash))
        {
            for (javax.swing.JCheckBoxMenuItem item : mi_CharPre)
            {
                if (hash.equals(item.getActionCommand()))
                {
                    item.setSelected(true);
                    charSets = (String) item.getClientProperty("prop_char");
                    checked = false;
                    break;
                }
            }
        }
        else
        {
            hash = ConsCfg.DEF_PWDS_HASH;
            charSets = ConsCfg.DEF_PWDS_CHAR;
        }
        mi_CharDef.setSelected(true);

        String loop = tpltData.getSpec(EditItem.SPEC_PWDS_LOOP);
        if (!com.magicpwd._util.Char.isValidate(loop))
        {
            loop = UserMdl.getUserCfg().getPwdsLoop();
        }
        mi_LoopMenu.setSelected(ConsCfg.DEF_TRUE.equalsIgnoreCase(loop));
    }

    public String getCharSets()
    {
        return charSets;
    }

    public int getCharSize()
    {
        return charSize;
    }

    public boolean isCharLoop()
    {
        return mi_LoopMenu.isSelected();
    }

    private void initSizeView()
    {
        javax.swing.ButtonGroup bg = new javax.swing.ButtonGroup();
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
        bg.add(mi_SizeDef);

        mu_SizeMenu.addSeparator();

        mi_SizeNum = new javax.swing.JCheckBoxMenuItem[6];
        javax.swing.JCheckBoxMenuItem menuItem;
        for (int i = 0; i < 6; i += 1)
        {
            menuItem = new javax.swing.JCheckBoxMenuItem();
            menuItem.addActionListener(al);
            mu_SizeMenu.add(menuItem);
            bg.add(menuItem);
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
        bg.add(mi_SizeMore);
        mu_SizeMenu.add(mi_SizeMore);
    }

    private void initCharView()
    {
        javax.swing.ButtonGroup bg = new javax.swing.ButtonGroup();
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
        bg.add(mi_CharDef);

        mu_CharMenu.addSeparator();

        int sysSize = UserMdl.getCharMdl().getCharSys().size();
        int usrSize = UserMdl.getCharMdl().getCharUsr().size();
        mi_CharPre = new javax.swing.JCheckBoxMenuItem[sysSize + usrSize];

        javax.swing.JCheckBoxMenuItem menuItem;
        for (int i = 0; i < sysSize; i += 1)
        {
            menuItem = new javax.swing.JCheckBoxMenuItem();
            bg.add(menuItem);
            mu_CharMenu.add(menuItem);
            menuItem.addActionListener(al);
            bg.add(menuItem);
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
            bg.add(menuItem);
            mu_CharMenu.add(menuItem);
            menuItem.addActionListener(al);
            bg.add(menuItem);
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
        add(mi_LoopMenu);
        mi_LoopMenu.setSelected(UserMdl.getUserCfg().isPwdsLoop());
    }

    private void initSizeLang()
    {
        Lang.setWText(mu_SizeMenu, LangRes.P30F7C01, "口令长度");

        Lang.setWText(mi_SizeDef, LangRes.P30F7C02, "默认");
        mi_SizeDef.setActionCommand(ConsCfg.DEF_PWDS_SIZE);

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
        }

        Lang.setWText(mi_SizeMore, LangRes.P30F7C04, "其它...");
    }

    private void initCharLang()
    {
        Lang.setWText(mu_CharMenu, LangRes.P30F7C05, "字符空间");

        Lang.setWText(mi_CharDef, LangRes.P30F7C06, "默认");
        mi_CharDef.setActionCommand(ConsCfg.DEF_PWDS_HASH);
        mi_CharDef.putClientProperty("prop_char", ConsCfg.DEF_PWDS_CHAR);

        javax.swing.JCheckBoxMenuItem menu;
        int i = 0;
        for (Char item : UserMdl.getCharMdl().getCharSys())
        {
            menu = mi_CharPre[i++];
            menu.setText(item.getP30F2104());
            menu.setToolTipText(item.getP30F2105());
            menu.setActionCommand(item.getP30F2103());
            menu.putClientProperty("prop_char", item.getP30F2106());
        }

        for (Char item : UserMdl.getCharMdl().getCharUsr())
        {
            menu = mi_CharPre[i++];
            menu.setText(item.getP30F2104());
            menu.setToolTipText(item.getP30F2105());
            menu.setActionCommand(item.getP30F2103());
            menu.putClientProperty("prop_char", item.getP30F2106());
        }
    }

    private void initNrptLang()
    {
        Lang.setWText(mi_LoopMenu, LangRes.P30F7C07, "允许重复");
    }

    /**
     * @param evt
     */
    private void mi_SizeMenuActionPerformed(java.awt.event.ActionEvent evt)
    {
        String t = evt.getActionCommand();
        if (!com.magicpwd._util.Char.isValidate(t))
        {
            return;
        }

        try
        {
            charSize = Integer.parseInt(t);
        }
        catch (NumberFormatException exp)
        {
            Logs.exception(exp);
            t = ConsCfg.DEF_PWDS_SIZE;
            charSize = Integer.parseInt(t);
            mi_SizeDef.setSelected(true);
        }

        tpltData.setSpec(EditItem.SPEC_PWDS_SIZE, t);
    }

    private void mi_SizeMoreActionPerformed(java.awt.event.ActionEvent evt)
    {
        String s = JOptionPane.showInputDialog(TrayPtn.getCurrForm(), "", tpltData.getSpec(EditItem.SPEC_PWDS_SIZE));
        if (s == null)
        {
            return;
        }
        s = s.trim();
        if (s.length() < 1)
        {
            Lang.showMesg(TrayPtn.getCurrForm(), "", "");
            return;
        }

        try
        {
            charSize = Integer.parseInt(s);
        }
        catch (NumberFormatException exp)
        {
            Logs.exception(exp);
            s = ConsCfg.DEF_PWDS_SIZE;
            charSize = Integer.parseInt(s);
            mi_SizeDef.setSelected(true);
        }

        tpltData.setSpec(EditItem.SPEC_PWDS_SIZE, s);
    }

    /**
     * @param evt
     */
    private void mi_CharMenuActionPerformed(java.awt.event.ActionEvent evt)
    {
        String t = evt.getActionCommand();
        if (!com.magicpwd._util.Char.isValidate(t))
        {
            return;
        }

        tpltData.setSpec(EditItem.SPEC_PWDS_HASH, t.substring(0, 8));
        javax.swing.JCheckBoxMenuItem item = (javax.swing.JCheckBoxMenuItem) evt.getSource();
        charSets = (String) item.getClientProperty("prop_char");
    }

    /**
     * @param evt
     */
    private void mi_UrptMenuActionPerformed(java.awt.event.ActionEvent evt)
    {
        tpltData.setSpec(EditItem.SPEC_PWDS_LOOP, mi_LoopMenu.isSelected() ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FAIL);
    }
    private javax.swing.JMenu mu_SizeMenu;
    private javax.swing.JCheckBoxMenuItem mi_SizeDef;
    private javax.swing.JCheckBoxMenuItem[] mi_SizeNum;
    private javax.swing.JCheckBoxMenuItem mi_SizeMore;
    private javax.swing.JMenu mu_CharMenu;
    private javax.swing.JCheckBoxMenuItem mi_CharDef;
    private javax.swing.JCheckBoxMenuItem[] mi_CharPre;
    private javax.swing.JCheckBoxMenuItem mi_LoopMenu;
}
