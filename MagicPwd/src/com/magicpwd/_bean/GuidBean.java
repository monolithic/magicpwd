/**
 * 
 */
package com.magicpwd._bean;

import com.magicpwd.MagicPwd;
import com.magicpwd._comn.Guid;
import com.magicpwd._comn.Item;
import com.magicpwd._comn.Tplt;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IGridView;
import com.magicpwd._mail.Connect;
import com.magicpwd._mail.MailDlg;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.EditBox;

/**
 * 属性：向导
 * 键值：ConsEnv.INDX_GUID
 * @author Amon
 */
public class GuidBean extends javax.swing.JPanel implements IEditBean
{

    private Guid dataItem;
    private IGridView gridView;
    private EditBox dataEdit;
    private BtnLabel bl_ReadMail;

    public GuidBean(IGridView view)
    {
        gridView = view;
    }

    @Override
    public void initView()
    {
        dataEdit = new EditBox(this, false);
        dataEdit.initView();
        dataEdit.setCopyButtonVisible(false);
        dataEdit.setDropButtonVisible(false);

        lb_PropName = new javax.swing.JLabel();

        tf_PropName = new javax.swing.JTextField(20);
        tf_PropName.setEditable(false);
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();

        cb_PropData = new javax.swing.JComboBox();
        lb_PropData.setLabelFor(cb_PropData);

        lb_PropEdit = new javax.swing.JLabel();

        pl_PropEdit = new javax.swing.JPanel();
        pl_PropEdit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bl_ReadMail = new BtnLabel();
        bl_ReadMail.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_PROP_UPDT)));
        bl_ReadMail.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                readMailActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bl_ReadMail);

        ck_CheckAll = new javax.swing.JCheckBox();
        pl_PropEdit.add(ck_CheckAll);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropEdit);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg2.addComponent(cb_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
        vpg2.addComponent(cb_PropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
    }

    @Override
    public void initLang()
    {
        Lang.setWText(lb_PropName, LangRes.P30F1301, "时间");
        Lang.setWText(lb_PropData, LangRes.P30F1302, "模板");
        Lang.setWText(ck_CheckAll, LangRes.P30F1319, "总是提示(&R)");
        Lang.setWTips(bl_ReadMail, LangRes.P30F150D, "检测邮件(Alt + M)");

        dataEdit.initLang();
    }

    @Override
    public void initData(Item item)
    {
        dataItem = (Guid) item;
        tf_PropName.setText(item.getName());
        cb_PropData.setModel(UserMdl.getCboxMdl());
        pl_PropEdit.setVisible(Util.isValidate(dataItem.getSpec(IEditItem.SPEC_GUID_TPLT)));
        ck_CheckAll.setSelected(ConsDat.SPEC_VALUE_TRUE.equals(dataItem.getSpec(IEditItem.SPEC_GUID_CHCK)));
    }

    @Override
    public void requestFocus()
    {
        cb_PropData.requestFocus();
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        Object obj = cb_PropData.getSelectedItem();
        if (obj == null)
        {
            Lang.showMesg(this, LangRes.P30F7A29, "请选择口令模板!");
            cb_PropData.requestFocus();
            return;
        }

        GridMdl gm = UserMdl.getGridMdl();
        if (gm.getRowCount() < ConsEnv.PWDS_HEAD_SIZE)
        {
            gm.initMeta();
        }
        Tplt tplt = (Tplt) obj;
        dataItem.addSpec(tplt.getP30F1103());
        gm.wAppend(tplt.getP30F1103());

        gridView.selectNext(!gm.isUpdate());
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    public void readMailActionPerformed(java.awt.event.ActionEvent evt)
    {
        MailDlg mailDlg = MagicPwd.getMailDlg();
        if (mailDlg == null)
        {
            mailDlg = new MailDlg();
            mailDlg.initView();
            mailDlg.initLang();
            mailDlg.initData();
            MagicPwd.setMailDlg(mailDlg);
        }
        mailDlg.setVisible(true);

        String mail = "";
        String user = "";
        String pwds = "";
        GridMdl gm = UserMdl.getGridMdl();
        if (ck_CheckAll.isSelected())
        {
            // 邮件账户
            int tmp = gm.wSelect(ConsDat.INDX_MAIL, ConsDat.TYPE_MAIL_MAIL);
            if (tmp >= ConsEnv.PWDS_HEAD_SIZE)
            {
                mail = gm.getItemAt(tmp).getData();
            }

            // 登录用户
            tmp = gm.wSelect(ConsDat.INDX_TEXT, ConsDat.TYPE_MAIL_USER);
            if (tmp >= ConsEnv.PWDS_HEAD_SIZE)
            {
                user = gm.getItemAt(tmp).getData();
            }

            // 认证口令
            tmp = gm.wSelect(ConsDat.INDX_PWDS, ConsDat.TYPE_MAIL_PWDS);
            if (tmp >= ConsEnv.PWDS_HEAD_SIZE)
            {
                pwds = gm.getItemAt(tmp).getData();
            }
        }

        if (!Util.isValidateEmail(mail))
        {
        }
        Connect connect = new Connect("pop3", "Amon.CT@live.com", "c~9xJpa&");
        connect.setUsername("Amon.CT");
        connect.setHost("pop.live.com");
        connect.setPort(995);
        connect.setAuth(true);
        mailDlg.append(connect, "");
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropEdit;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JPanel pl_PropEdit;
    private javax.swing.JCheckBox ck_CheckAll;
    private javax.swing.JComboBox cb_PropData;
    private javax.swing.JTextField tf_PropName;
}
