/**
 * 
 */
package com.magicpwd._prop;

import com.magicpwd._comn.Char;
import com.magicpwd._comn.S1S1;

import javax.swing.JPanel;

import com.magicpwd._comn.S1S3;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IPropBean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;

/**
 * @author Amon
 * 
 */
public class USetProp extends JPanel implements IPropBean
{

    private java.io.File backPath;

    public USetProp()
    {
    }

    @Override
    public void initView()
    {
        lb_UserLang = new javax.swing.JLabel();
        cb_UserLang = new javax.swing.JComboBox();
        lb_PwdsChar = new javax.swing.JLabel();
        cb_PwdsChar = new javax.swing.JComboBox();
        lb_PwdsSize = new javax.swing.JLabel();
        tf_PwdsSize = new javax.swing.JTextField(6);
        ck_PwdsUrpt = new javax.swing.JCheckBox();
        lb_BackCount = new javax.swing.JLabel();
        tf_BackCount = new javax.swing.JTextField(6);
        lb_BackPath = new javax.swing.JLabel();
        tf_BackPath = new javax.swing.JTextField(20);
        bt_BackPath = new javax.swing.JButton();
        lb_StayTime = new javax.swing.JLabel();
        tf_StayTime = new javax.swing.JTextField(6);

        bt_BackPath.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_BackPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_UserLang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_UserLang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_PwdsChar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_PwdsChar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_PwdsSize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_PwdsSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ck_PwdsUrpt))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_BackCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_BackCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_BackPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_BackPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_BackPath))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_StayTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_StayTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_UserLang)
                    .addComponent(cb_UserLang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_PwdsChar)
                    .addComponent(cb_PwdsChar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_PwdsSize)
                    .addComponent(tf_PwdsSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ck_PwdsUrpt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_BackCount)
                    .addComponent(tf_BackCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_BackPath)
                    .addComponent(tf_BackPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_BackPath))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_StayTime)
                    .addComponent(tf_StayTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }

    @Override
    public void initLang()
    {
        Lang.setWText(lb_PwdsChar, LangRes.P30F8301, "字符空间");
        Lang.setWText(lb_PwdsSize, LangRes.P30F8302, "口令长度");
        Lang.setWText(ck_PwdsUrpt, LangRes.P30F8303, "不可重复");

        Lang.setWText(lb_BackCount, LangRes.P30F8304, "备份数量");
        Lang.setWText(lb_BackPath, LangRes.P30F8305, "备份路径");
        Lang.setWText(bt_BackPath, LangRes.P30F8501, ">>");

        Lang.setWText(lb_StayTime, LangRes.P30F8306, "剪贴板口令驻留时间（秒）");

        Lang.setWText(lb_UserLang, LangRes.P30F8307, "语言区域");
//        Lang.setWText(bt_Update, LangRes.P30F8503, "保存");
//        Lang.setWText(bt_Default, LangRes.P30F8505, "默认");
    }

    @Override
    public void initData()
    {
        if (cb_UserLang.getItemCount() < 1)
        {
            java.util.Locale[] locales = java.util.Locale.getAvailableLocales();
            S1S1[] names = new S1S1[locales.length];
            java.util.Locale local;
            for (int i = 0; i < locales.length; i += 1)
            {
                local = locales[i];
                names[i] = new S1S1(local.getLanguage() + '_' + local.getCountry(), local.getDisplayName(local));
            }
            java.util.Arrays.sort(names);
            cb_UserLang.setModel(new javax.swing.DefaultComboBoxModel(names));
        }

        if (cb_PwdsChar.getItemCount() < 1)
        {
            for (Char item : UserMdl.getCharMdl().getCharSys())
            {
                cb_PwdsChar.addItem(item);
                if (item.getP30F2103().equals(UserMdl.getCfg().getPwdsSet()))
                {
                    cb_PwdsChar.setSelectedItem(item);
                }
            }
            for (Char item : UserMdl.getCharMdl().getCharUsr())
            {
                cb_PwdsChar.addItem(item);
                if (item.getP30F2103().equals(UserMdl.getCfg().getPwdsSet()))
                {
                    cb_PwdsChar.setSelectedItem(item);
                }
            }
        }

        tf_PwdsSize.setText(UserMdl.getCfg().getPwdsLen());
        ck_PwdsUrpt.setSelected(UserMdl.getCfg().isPwdsUpt());

        tf_BackCount.setText("" + UserMdl.getCfg().getBackNum());
        tf_BackPath.setText(UserMdl.getCfg().getBackDir());

        tf_StayTime.setText("" + UserMdl.getCfg().getClnClp());
    }

    @Override
    public JPanel getPanel()
    {
        return this;
    }

    private void bt_BackPathActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (backPath == null)
        {
            if (Util.isValidate(tf_BackPath.getText()))
            {
                backPath = new java.io.File(tf_BackPath.getText());
            }
            else
            {
                backPath = new java.io.File(ConsCfg.DEF_BACK_PATH);
            }
        }
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        jfc.setSelectedFile(backPath);
        int status = jfc.showOpenDialog(this);
        if (status != javax.swing.JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        backPath = jfc.getSelectedFile();
        if (!backPath.exists())
        {
            Lang.showMesg(this, LangRes.P30F7A1B, "您选择的目录不存在！");
            return;
        }
        if (!backPath.isDirectory())
        {
            Lang.showMesg(this, LangRes.P30F7A1C, "请选择一个合适的目录！");
            return;
        }
        if (!backPath.canWrite())
        {
            Lang.showMesg(this, LangRes.P30F7A1D, "无法保存数据到您选择的目录，请确认您是否有足够的权限！");
            return;
        }

        tf_BackPath.setText(backPath.getPath());
    }

    private void bt_UpdateActionPerformed(java.awt.event.ActionEvent evt)
    {
        // 口令空间
        S1S3 item = (S1S3) cb_PwdsChar.getSelectedItem();
        UserMdl.getCfg().setPwdsSet(item != null ? item.getK() : ConsCfg.DEF_PWDS_CHAR);

        // 口令长度
        String text = tf_PwdsSize.getText();
        int size;
        try
        {
            size = Integer.parseInt(text);
        }
        catch (NumberFormatException exp)
        {
            Logs.exception(exp);
            size = Integer.parseInt(ConsCfg.DEF_PWDS_SIZE);
        }
        if (size < 1 || size > 1024)
        {
            size = 8;
        }
        UserMdl.getCfg().setPwdsLen(size);

        // 可否重复
        UserMdl.getCfg().setPwdsUpt(ck_PwdsUrpt.isSelected());

        // 备份数量
        text = tf_BackCount.getText();
        try
        {
            size = Integer.parseInt(text);
        }
        catch (NumberFormatException exp)
        {
            Logs.exception(exp);
            size = Integer.parseInt(ConsCfg.DEF_BACK_SIZE);
        }
        if (size < 1 || size > 16)
        {
            size = 3;
        }
        UserMdl.getCfg().setBackNum(size);

        // 备份路径
        if (backPath == null)
        {
            if (Util.isValidate(tf_BackPath.getText()))
            {
                backPath = new java.io.File(tf_BackPath.getText());
            }
            else
            {
                backPath = new java.io.File(ConsCfg.DEF_BACK_PATH);
            }
        }
        if (!backPath.exists())
        {
            try
            {
                backPath.mkdirs();
            }
            catch (Exception exp)
            {
                Lang.showMesg(this, LangRes.P30F8A01, "创建数据备份目录失败！");
                return;
            }
        }
        if (!backPath.canWrite())
        {
            backPath = new java.io.File(ConsCfg.DEF_BACK_PATH);
        }
        UserMdl.getCfg().setBackDir(backPath.getPath());

        // 安全时间
        text = tf_StayTime.getText();
        try
        {
            size = Integer.parseInt(text);
        }
        catch (NumberFormatException exp)
        {
            Logs.exception(exp);
            size = Integer.parseInt(ConsCfg.DEF_SAFE_TIME);
        }
        if (size < 1 || size > 3600)
        {
            size = 60;
        }
        UserMdl.getCfg().setClnClp(size);
    }

    private void bt_DefaultActionPerformed(java.awt.event.ActionEvent evt)
    {
        cb_PwdsChar.setSelectedItem(new S1S3(ConsCfg.DEF_PWDS_HASH, "", "", ""));
        tf_PwdsSize.setText(ConsCfg.DEF_PWDS_SIZE);
        ck_PwdsUrpt.setSelected(false);

        tf_BackCount.setText(ConsCfg.DEF_BACK_SIZE);
        tf_BackPath.setText(ConsCfg.DEF_BACK_PATH);

        tf_StayTime.setText(ConsCfg.DEF_SAFE_TIME);
    }
    private javax.swing.JButton bt_BackPath;
    private javax.swing.JComboBox cb_PwdsChar;
    private javax.swing.JComboBox cb_UserLang;
    private javax.swing.JCheckBox ck_PwdsUrpt;
    private javax.swing.JLabel lb_BackCount;
    private javax.swing.JLabel lb_BackPath;
    private javax.swing.JLabel lb_PwdsChar;
    private javax.swing.JLabel lb_PwdsSize;
    private javax.swing.JLabel lb_StayTime;
    private javax.swing.JLabel lb_UserLang;
    private javax.swing.JTextField tf_BackCount;
    private javax.swing.JTextField tf_BackPath;
    private javax.swing.JTextField tf_PwdsSize;
    private javax.swing.JTextField tf_StayTime;
}
