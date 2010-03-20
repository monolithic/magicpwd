/**
 * 
 */
package com.magicpwd._bean;

import com.magicpwd.MagicPwd;
import com.magicpwd._comn.GuidItem;
import com.magicpwd._comn.I1S2;
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
import com.magicpwd.v.MailPtn;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * 属性：向导
 * 键值：ConsEnv.INDX_GUID
 * @author Amon
 */
public class GuidBean extends javax.swing.JPanel implements IEditBean
{

    private GuidItem itemData;
    private IGridView gridView;
    private EditBean dataEdit;
    private BtnLabel bt_ReadMail;

    public GuidBean(IGridView view)
    {
        gridView = view;
    }

    @Override
    public void initView()
    {
        dataEdit = new EditBean(this, false);
        dataEdit.initView();
        dataEdit.setCopyButtonVisible(false);
        dataEdit.setDropButtonVisible(false);

        lb_PropName = new javax.swing.JLabel();
        tf_PropName = new javax.swing.JTextField(20);
        tf_PropName.setEditable(false);
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();
        cb_PropData = new javax.swing.JComboBox();
        cb_PropData.setModel(UserMdl.getCboxMdl());
        lb_PropData.setLabelFor(cb_PropData);

        lb_PropEdit = new javax.swing.JLabel();
        pl_PropEdit = new javax.swing.JPanel();
        pl_PropEdit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_ReadMail = new BtnLabel();
        bt_ReadMail.setIcon(Util.getIcon(ConsEnv.ICON_MAIL_OPEN));
        bt_ReadMail.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                readMailActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_ReadMail);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropEdit);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(cb_PropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
        vpg2.addComponent(cb_PropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
        Lang.setWText(lb_PropName, LangRes.P30F1301, "时间");
        Lang.setWText(lb_PropData, LangRes.P30F1302, "模板");

        Lang.setWText(bt_ReadMail, LangRes.P30F1519, "&M");
        Lang.setWTips(bt_ReadMail, LangRes.P30F151A, "检测邮件(Alt + M)");

        dataEdit.initLang();
    }

    @Override
    public void initData(IEditItem item)
    {
        itemData = (GuidItem) item;
        tf_PropName.setText(item.getName());

        String kind = itemData.getSpec(IEditItem.SPEC_GUID_TPLT);
        if (Util.isValidate(kind))
        {
            for (Tplt tplt : UserMdl.getCboxMdl().getAllItems())
            {
                if (kind.equals(tplt.getP30F1103()))
                {
                    cb_PropData.setSelectedItem(tplt);
                }
            }
        }
        bt_ReadMail.setVisible(Util.isValidate(kind));
        bt_ReadMail.setEnabled(ConsDat.HASH_MAIL.equals(kind));
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
        Tplt tplt = (Tplt) obj;
        itemData.setSpec(IEditItem.SPEC_GUID_TPLT, tplt.getP30F1103());
        gm.setModified(true);

        if (gm.getRowCount() < ConsEnv.PWDS_HEAD_SIZE)
        {
            gm.initMeta();
            gm.wAppend(tplt.getP30F1103());
        }

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
            Util.centerForm(mailDlg, MagicPwd.getCurrForm());
            MagicPwd.setMailDlg(mailDlg);
        }

        GridMdl gm = UserMdl.getGridMdl();

        MailPtn mailPtn = new MailPtn();
        mailPtn.initView();
        mailPtn.initLang();
        List<I1S2> mailList = gm.wSelect(ConsDat.INDX_MAIL);
        mailPtn.initMail(mailList);
        if (mailList.size() < 1)
        {
            Lang.showMesg(mailDlg, null, "没有可用的邮件类型数据！");
            return;
        }
        List<I1S2> userList = gm.wSelect(ConsDat.INDX_TEXT);
        mailPtn.initUser(userList);
        if (userList.size() < 1)
        {
            Lang.showMesg(mailDlg, null, "没有可用的文本类型数据！");
            return;
        }
        List<I1S2> pwdsList = gm.wSelect(ConsDat.INDX_PWDS);
        mailPtn.initPwds(pwdsList);
        if (pwdsList.size() < 1)
        {
            Lang.showMesg(mailDlg, null, "没有可用的口令类型数据！");
            return;
        }
        if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(MagicPwd.getCurrForm(), mailPtn, "登录确认", JOptionPane.OK_CANCEL_OPTION))
        {
            return;
        }

        String mail = mailList.get(mailPtn.getMail()).getK();
        String user = userList.get(mailPtn.getUser()).getK();
        String pwds = pwdsList.get(mailPtn.getPwds()).getK();

        String host = mail.substring(mail.indexOf('@') + 1);
        if (!Util.isValidate(host))
        {
            return;
        }
        String type = UserMdl.getMailCfg().getCfg(host + ".type");
        if (!Util.isValidate(type))
        {
            Lang.showMesg(mailDlg, null, "查找不到对应的服务信息，如有疑问请与作者联系！");
            return;
        }

        final Connect connect = new Connect(type, mail, pwds);
        connect.setUsername(user);

        // 读取服务器配置
        String cfg = UserMdl.getMailCfg().getCfg(type + '.' + host);
        if (!Util.isValidate(cfg))
        {
            return;
        }

        // 服务器地址
        String[] arr = (cfg + ":::false").split("[:]");
        connect.setHost(arr[0]);

        // 服务器端口
        cfg = arr[1].trim();
        if (Util.isValidateInteger(cfg))
        {
            connect.setPort(Integer.parseInt(cfg));
        }

        // 是否需要身份认证
        connect.setAuth("true".equalsIgnoreCase(arr[2].trim().toLowerCase()));
        // 是否需要安全认证
        connect.setJssl("true".equalsIgnoreCase(arr[3].trim().toLowerCase()));

        mailDlg.setVisible(true);
        new Thread()
        {

            @Override
            public void run()
            {
                MagicPwd.getMailDlg().append(connect, "");
            }
        }.start();
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropEdit;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JPanel pl_PropEdit;
    private javax.swing.JComboBox cb_PropData;
    private javax.swing.JTextField tf_PropName;
}
