/**
 * 
 */
package com.magicpwd._bean;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.v.MenuPwd;
import com.magicpwd.v.TrayPtn;

/**
 * 属性：口令
 * 键值：ConsEnv.INDX_PWDS
 * @author Amon
 */
public class PwdsBean extends javax.swing.JPanel implements IEditBean
{

    private boolean askOverRide;
    private IEditItem itemData;
    private MenuPwd menuPwd;
    private IGridView gridView;
    private EditBean dataEdit;

    public PwdsBean(IGridView view)
    {
        gridView = view;
    }

    @Override
    public void initView()
    {
        dataEdit = new EditBean(this, false);
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
        pf_PropData.setEchoChar(ConsEnv.PWDS_MASK);
        lb_PropData.setLabelFor(pf_PropData);

        lb_PropEdit = new javax.swing.JLabel();
        pl_PropEdit = new javax.swing.JPanel();
        pl_PropEdit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_PwdsView = new BtnLabel();
        bt_PwdsView.setIcon(Util.getIcon(ConsEnv.ICON_PWDS_HIDE));
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
        bt_PwdsGent.setIcon(Util.getIcon(ConsEnv.ICON_PWDS_GENT));
        bt_PwdsGent.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsGentActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_PwdsGent);

        bt_PwdsUcfg = new BtnLabel();
        bt_PwdsUcfg.setIcon(Util.getIcon(ConsEnv.ICON_PWDS_UCFG));
        bt_PwdsUcfg.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PwdsUcfgActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_PwdsUcfg);

        menuPwd = new MenuPwd();
//        menuPwd.initView();

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
        dataEdit.initLang();

        Lang.setWText(lb_PropName, LangRes.P30F1309, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F130A, "口令");

        Lang.setWText(bt_PwdsView, LangRes.P30F1507, "&M");
        Lang.setWTips(bt_PwdsView, LangRes.P30F1508, "点击显示口令(Alt + M)");

        Lang.setWText(bt_PwdsGent, LangRes.P30F150B, "&G");
        Lang.setWTips(bt_PwdsGent, LangRes.P30F150C, "生成口令(Alt + G)");

        Lang.setWText(bt_PwdsUcfg, LangRes.P30F150D, "&O");
        Lang.setWTips(bt_PwdsUcfg, LangRes.P30F150E, "口令设置(Alt + O)");

//        menuPwd.initLang();
    }

    @Override
    public void initData(IEditItem tplt)
    {
        itemData = tplt;
        String name = itemData.getName();
        if (com.magicpwd._util.Char.isValidate(name) && name.startsWith(ConsDat.SP_TPL_LS) && name.endsWith(ConsDat.SP_TPL_RS))
        {
            name = name.substring(1, name.length() - 1);
        }
        tf_PropName.setText(name);
        pf_PropData.setText(itemData.getData());
//        menuPwd.setTplt(tplt);
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
        if (Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30F1A01, "确认要删除此属性数据么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            gridView.getCoreMdl().getGridMdl().wRemove(itemData);
            gridView.selectNext(0, true);
        }
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_PropName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30F7A35, "请输入口令名称！");
            tf_PropName.requestFocus();
            return;
        }

        itemData.setName(name);
        itemData.setData(new String(pf_PropData.getPassword()));
        gridView.getCoreMdl().getGridMdl().setModified(true);

        gridView.selectNext(gridView.getCoreMdl().getGridMdl().isUpdate() ? 0 : 1, true);
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        pf_PropData.selectAll();
        Util.setClipboardContents(new String(pf_PropData.getPassword()), gridView.getCoreMdl().getUserCfg().getStayTime());
    }

    private void bt_PwdsUcfgActionPerformed(java.awt.event.ActionEvent evt)
    {
//        menuPwd.initData();
//        menuPwd.show(bt_PwdsUcfg, 0, bt_PwdsUcfg.getSize().height);
    }

    private void bt_PwdsGentActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (askOverRide && pf_PropData.getPassword().length > 1)
        {
            if (Lang.showFirm(TrayPtn.getCurrForm(), "", "") != javax.swing.JOptionPane.YES_OPTION)
            {
                return;
            }
            askOverRide = false;
        }

        try
        {
//            char[] t = Util.nextRandomKey(menuPwd.getCharSets(), menuPwd.getCharSize(), menuPwd.isCharLoop());
//            pf_PropData.setText(new String(t));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, null, exp.getLocalizedMessage());
        }
    }

    private void bt_PwdsViewActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (pf_PropData.getEchoChar() == 0)
        {
            bt_PwdsView.setIcon(Util.getIcon(ConsEnv.ICON_PWDS_HIDE));
            pf_PropData.setEchoChar(ConsEnv.PWDS_MASK);
            Lang.setWText(bt_PwdsView, LangRes.P30F1507, "&M");
            Lang.setWTips(bt_PwdsView, LangRes.P30F1508, "点击显示口令(Alt + M)");
        }
        else
        {
            bt_PwdsView.setIcon(Util.getIcon(ConsEnv.ICON_PWDS_VIEW));
            pf_PropData.setEchoChar('\0');
            Lang.setWText(bt_PwdsView, LangRes.P30F1509, "&M");
            Lang.setWTips(bt_PwdsView, LangRes.P30F150A, "点击隐藏口令(Alt + M)");
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
