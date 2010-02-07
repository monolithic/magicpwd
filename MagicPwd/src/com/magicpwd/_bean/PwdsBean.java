/**
 * 
 */
package com.magicpwd._bean;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comn.Item;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.EditBox;
import com.magicpwd.v.MenuPwd;

/**
 * 属性：口令
 * 键值：ConsEnv.INDX_PWDS
 * @author Amon
 */
public class PwdsBean extends javax.swing.JPanel implements IEditBean
{

    private boolean askOverRide;
    private IEditItem tpltData;
    private MenuPwd menuPwd;
    private IGridView gridView;
    private javax.swing.Icon viewPwds;
    private javax.swing.Icon hidePwds;
    private EditBox dataEdit;

    public PwdsBean(IGridView view)
    {
        gridView = view;
    }

    @Override
    public void initView()
    {
        dataEdit = new EditBox(this, false);
        dataEdit.initView();

        lb_PropName = new javax.swing.JLabel();

        tf_PropName = new javax.swing.JTextField(14);
        tf_PropName.addFocusListener(new java.awt.event.FocusAdapter()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                tf_PropName.selectAll();
            }
        });
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();

        pf_PropData = new javax.swing.JPasswordField();
        pf_PropData.setEchoChar('*');
        lb_PropData.setLabelFor(pf_PropData);

        lb_PropEdit = new javax.swing.JLabel();

        pl_PropEdit = new javax.swing.JPanel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropEdit);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg2.addComponent(pf_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE);
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
        vpg3.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg3);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        vpg4.addComponent(dataEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vpg4.addGroup(vsg);
        layout.setVerticalGroup(vpg4);

        bt_PwdsUcfg = new BtnLabel();
        bt_PwdsUcfg.setMnemonic('O');
        bt_PwdsUcfg.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_PWDS_UCFG)));
        bt_PwdsUcfg.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsUcfgActionPerformed(evt);
            }
        });

        bt_PwdsGent = new BtnLabel();
        bt_PwdsGent.setMnemonic('G');
        bt_PwdsGent.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_PWDS_GENT)));
        bt_PwdsGent.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsGentActionPerformed(evt);
            }
        });

        bt_PwdsView = new BtnLabel();
        bt_PwdsView.setMnemonic('M');
        hidePwds = new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_PWDS_HIDE));
        bt_PwdsView.setIcon(hidePwds);
        bt_PwdsView.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsViewActionPerformed(evt);
            }
        });

        menuPwd = new MenuPwd();
        menuPwd.initView();
    }

    @Override
    public void initLang()
    {
        dataEdit.initLang();

        viewPwds = new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_PWDS_VIEW));

        Lang.setWText(lb_PropName, LangRes.P30F1309, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F130A, "口令");
        Lang.setWTips(bt_PwdsView, LangRes.P30F1504, "显示口令");
        Lang.setWTips(bt_PwdsGent, LangRes.P30F1506, "生成口令");
        Lang.setWTips(bt_PwdsUcfg, LangRes.P30F1507, "口令设置");

        menuPwd.initLang();
    }

    @Override
    public void initData(Item tplt)
    {
        tpltData = tplt;
        String name = tpltData.getName();
        if (Util.isValidate(name) && name.startsWith(ConsDat.SP_TPL_LS) && name.endsWith(ConsDat.SP_TPL_RS))
        {
            name = name.substring(1, name.length() - 1);
        }
        tf_PropName.setText(name);
        pf_PropData.setText(tpltData.getData());
        menuPwd.setTplt(tplt);
    }

    @Override
    public void requestFocus()
    {
        if (!Util.isValidate(tf_PropName.getText()))
        {
            tf_PropName.requestFocus();
            return;
        }
        pf_PropData.requestFocus();
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (Lang.showFirm(this, LangRes.P30F1A01, "") == javax.swing.JOptionPane.YES_OPTION)
        {
            UserMdl.getGridMdl().wRemove(tpltData);
            gridView.selectNext(false);
        }
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_PropName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, "", "请输入口令名称！");
            tf_PropName.requestFocus();
            return;
        }

        tpltData.setName(name);
        tpltData.setData(new String(pf_PropData.getPassword()));
        UserMdl.getGridMdl().setModified(true);

        gridView.selectNext(!UserMdl.getGridMdl().isUpdate());
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        pf_PropData.selectAll();
        Util.setClipboardContents(new String(pf_PropData.getPassword()), UserMdl.getCfg().getClnClp());
    }

    private void bt_PwdsUcfgActionPerformed(java.awt.event.ActionEvent evt)
    {
        menuPwd.initData();
        menuPwd.show(bt_PwdsUcfg, 0, bt_PwdsUcfg.getSize().height);
    }

    private void bt_PwdsGentActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (askOverRide && pf_PropData.getPassword().length > 1)
        {
            if (Lang.showFirm(this, "", "") != javax.swing.JOptionPane.YES_OPTION)
            {
                return;
            }
            askOverRide = false;
        }

        try
        {
            char[] t = Util.nextRandomKey(menuPwd.getCharSets(), menuPwd.getCharSize(), menuPwd.isCharUrpt());
            pf_PropData.setText(new String(t));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, exp.getMessage(), "");
        }
    }

    private void bt_PwdsViewActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (pf_PropData.getEchoChar() == 0)
        {
            bt_PwdsView.setIcon(hidePwds);
            pf_PropData.setEchoChar('*');
            Lang.setWTips(bt_PwdsView, LangRes.P30F1504, "显示口令");
        }
        else
        {
            bt_PwdsView.setIcon(viewPwds);
            pf_PropData.setEchoChar('\0');
            Lang.setWTips(bt_PwdsView, LangRes.P30F1505, "隐藏口令");
        }
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropEdit;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JPanel pl_PropEdit;
    private javax.swing.JPasswordField pf_PropData;
    private javax.swing.JTextField tf_PropName;
    private BtnLabel bt_PwdsUcfg;
    private BtnLabel bt_PwdsGent;
    private BtnLabel bt_PwdsView;
}
