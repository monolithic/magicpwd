/**
 * 
 */
package com.magicpwd._bean.pwd;

import com.magicpwd._comn.prop.Char;
import com.magicpwd._comn.item.EditItem;
import com.magicpwd._comp.WEditBox;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WButtonGroup;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.v.TrayPtn;
import com.magicpwd.v.pwd.MainPtn;
import javax.swing.JOptionPane;

/**
 * 属性：口令
 * 键值：ConsEnv.INDX_PWDS
 * @author Amon
 */
public class PwdsBean extends javax.swing.JPanel implements IEditBean
{

    private IEditItem itemData;
    private MainPtn mainPtn;
    private WEditBox dataEdit;
    private WTextBox nameBox;
    private javax.swing.ImageIcon icoMask;
    private javax.swing.ImageIcon icoView;
    private boolean askOverWrite;
//    private WTextBox dataBox;

    public PwdsBean(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    @Override
    public void initView()
    {
        initMenuView();

        dataEdit = new WEditBox(mainPtn.getCoreMdl().getUserCfg(), this, false);
        dataEdit.initView();

        lb_PropName = new javax.swing.JLabel();
        tf_PropName = new javax.swing.JTextField(14);
        nameBox = new WTextBox(tf_PropName, true);
        nameBox.initView();
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();
        pf_PropData = new javax.swing.JPasswordField();
        pf_PropData.setEchoChar(ConsEnv.PWDS_MASK);
        lb_PropData.setLabelFor(pf_PropData);

        lb_PropEdit = new javax.swing.JLabel();
        pl_PropEdit = new javax.swing.JPanel();
        pl_PropEdit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_PwdsView = new BtnLabel();
        bt_PwdsView.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsViewActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_PwdsView);

        bt_PwdsGent = new BtnLabel();
        bt_PwdsGent.setIcon(Bean.readIcon(ConsEnv.FEEL_PATH + "pwds-generate.png", mainPtn.getCoreMdl().getUserCfg()));
        bt_PwdsGent.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsGentActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_PwdsGent);

        bt_PwdsConf = new BtnLabel();
        bt_PwdsConf.setIcon(Bean.readIcon(ConsEnv.FEEL_PATH + "pwds-options.png", mainPtn.getCoreMdl().getUserCfg()));
        bt_PwdsConf.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsConfActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_PwdsConf);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropEdit);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(pf_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        hpg2.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_PropName);
        vpg1.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_PropData);
        vpg2.addComponent(pf_PropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg3.addComponent(lb_PropEdit);
        vpg3.addComponent(pl_PropEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg3);
        vsg1.addContainerGap(14, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        vpg.addGroup(vsg1);
        vpg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    @Override
    public void initLang()
    {
        Lang.setWText(lb_PropName, LangRes.P30F1309, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F130A, "口令");

        Lang.setWText(bt_PwdsView, LangRes.P30F1507, "&M");
        Lang.setWTips(bt_PwdsView, LangRes.P30F1508, "点击显示口令(Alt + M)");

        Lang.setWText(bt_PwdsGent, LangRes.P30F150B, "&G");
        Lang.setWTips(bt_PwdsGent, LangRes.P30F150C, "生成口令(Alt + G)");

        Lang.setWText(bt_PwdsConf, LangRes.P30F150D, "&O");
        Lang.setWTips(bt_PwdsConf, LangRes.P30F150E, "口令设置(Alt + O)");

        initMenuLang();

        nameBox.initLang();
        dataEdit.initLang();
    }

    @Override
    public void initData()
    {
        nameBox.initData();

        icoMask = Bean.readIcon(ConsEnv.FEEL_PATH + "pwds-mask.png", mainPtn.getCoreMdl().getUserCfg());
        icoView = Bean.readIcon(ConsEnv.FEEL_PATH + "pwds-view.png", mainPtn.getCoreMdl().getUserCfg());

        initMenuData();

        changeView(true);
    }

    @Override
    public void showData(IEditItem item)
    {
        itemData = item;
        String name = itemData.getName();
        if (com.magicpwd._util.Char.isValidate(name) && name.startsWith(ConsDat.SP_TPL_LS) && name.endsWith(ConsDat.SP_TPL_RS))
        {
            name = name.substring(1, name.length() - 1);
        }
        tf_PropName.setText(name);
        pf_PropData.setText(itemData.getData());

        String size = itemData.getSpec(IEditItem.SPEC_PWDS_SIZE, "0");
        if (!com.magicpwd._util.Char.isValidatePositiveInteger(size))
        {
            size = "0";
        }
        if (!bg_SizeGroup.setSelected(size, true))
        {
            mi_SizeMore.setSelected(true);
            mi_SizeMore.setActionCommand(size);
        }
        bg_CharGroup.setSelected(itemData.getSpec(IEditItem.SPEC_PWDS_HASH), true);
        mi_LoopMenu.setSelected(ConsCfg.DEF_TRUE.equals(itemData.getSpec(IEditItem.SPEC_PWDS_LOOP)));
    }

    @Override
    public void requestFocus()
    {
        if (!com.magicpwd._util.Char.isValidate(tf_PropName.getText()))
        {
            tf_PropName.requestFocus();
            return;
        }
        pf_PropData.requestFocus();
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (Lang.showFirm(mainPtn, LangRes.P30F1A01, "确认要删除此属性数据么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            mainPtn.removeSelected();
        }
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_PropName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A35, "请输入口令名称！");
            tf_PropName.requestFocus();
            return;
        }

        itemData.setName(name);
        itemData.setData(new String(pf_PropData.getPassword()));

        mainPtn.updateSelected();
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        pf_PropData.selectAll();
        Util.setClipboardContents(new String(pf_PropData.getPassword()), mainPtn.getCoreMdl().getUserCfg().getStayTime());
    }

    private void initMenuView()
    {
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

        int sysSize = mainPtn.getCoreMdl().getCharMdl().getCharSys().size();
        int usrSize = mainPtn.getCoreMdl().getCharMdl().getCharUsr().size();
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
        mi_LoopMenu.setSelected(mainPtn.getCoreMdl().getUserCfg().isPwdsLoop());
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

    private void initMenuLang()
    {
        initSizeLang();
        initCharLang();
        initNrptLang();
    }

    private void initCharLang()
    {
        Lang.setWText(mu_CharMenu, LangRes.P30F7C05, "字符空间");

        Lang.setWText(mi_CharDef, LangRes.P30F7C06, "默认");
        mi_CharDef.setActionCommand(ConsCfg.DEF_PWDS_HASH);

        javax.swing.JCheckBoxMenuItem menu;
        int i = 0;
        for (Char item : mainPtn.getCoreMdl().getCharMdl().getCharSys())
        {
            menu = mi_CharPre[i++];
            menu.setText(item.getP30F2104());
            menu.setToolTipText(item.getP30F2105());
            menu.setActionCommand(item.getP30F2103());
            bg_CharGroup.add(item.getP30F2103(), menu);
        }

        for (Char item : mainPtn.getCoreMdl().getCharMdl().getCharUsr())
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

    private void initMenuData()
    {
    }

    private void bt_PwdsConfActionPerformed(java.awt.event.ActionEvent evt)
    {
        pm_ConfMenu.show(bt_PwdsConf, 0, bt_PwdsConf.getSize().height);
    }

    private void bt_PwdsGentActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (askOverWrite && pf_PropData.getPassword().length > 1)
        {
            if (Lang.showFirm(mainPtn, "", "") != javax.swing.JOptionPane.YES_OPTION)
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
            Lang.showMesg(mainPtn, null, exp.getLocalizedMessage());
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
        String s = JOptionPane.showInputDialog(TrayPtn.getCurrForm(), "", itemData.getSpec(EditItem.SPEC_PWDS_SIZE, ConsCfg.DEF_PWDS_SIZE));
        if (s == null)
        {
            return;
        }
        s = s.trim();
        if (!com.magicpwd._util.Char.isValidatePositiveInteger(s))
        {
            Lang.showMesg(mainPtn, "", "请输入一个有效的自然数！");
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
            bt_PwdsView.setIcon(icoMask);
            pf_PropData.setEchoChar(ConsEnv.PWDS_MASK);
            Lang.setWText(bt_PwdsView, LangRes.P30F1507, "&M");
            Lang.setWTips(bt_PwdsView, LangRes.P30F1508, "点击显示口令(Alt + M)");
        }
        else
        {
            bt_PwdsView.setIcon(icoView);
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
        for (Char item : mainPtn.getCoreMdl().getCharMdl().getCharSys())
        {
            if (hash.equals(item.getP30F2103()))
            {
                return item.getP30F2106();
            }
        }
        for (Char item : mainPtn.getCoreMdl().getCharMdl().getCharUsr())
        {
            if (hash.equals(item.getP30F2103()))
            {
                return item.getP30F2106();
            }
        }
        return ConsCfg.DEF_PWDS_CHAR;
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropEdit;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JPanel pl_PropEdit;
    private javax.swing.JPasswordField pf_PropData;
    private javax.swing.JTextField tf_PropName;
    private BtnLabel bt_PwdsConf;
    private BtnLabel bt_PwdsGent;
    private BtnLabel bt_PwdsView;
    private javax.swing.JPopupMenu pm_ConfMenu;
    private javax.swing.JMenu mu_SizeMenu;
    private javax.swing.JCheckBoxMenuItem mi_SizeDef;
    private javax.swing.JCheckBoxMenuItem[] mi_SizeNum;
    private javax.swing.JCheckBoxMenuItem mi_SizeMore;
    private WButtonGroup bg_SizeGroup;
    private javax.swing.JMenu mu_CharMenu;
    private javax.swing.JCheckBoxMenuItem mi_CharDef;
    private javax.swing.JCheckBoxMenuItem[] mi_CharPre;
    private WButtonGroup bg_CharGroup;
    private javax.swing.JCheckBoxMenuItem mi_LoopMenu;
}
