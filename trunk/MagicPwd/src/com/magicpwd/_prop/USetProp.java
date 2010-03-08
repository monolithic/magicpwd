/**
 * 
 */
package com.magicpwd._prop;

import com.magicpwd._comn.Char;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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
        initPwdsView();
        initBackView();
        initSafeView();
        initBaseView();
    }

    @Override
    public void initLang()
    {
        initPwdsLang();
        initBackLang();
        initSafeLang();
        initBaseLang();
    }

    @Override
    public void initData()
    {
        DefaultComboBoxModel cm = new DefaultComboBoxModel();
        int indx = 0;
        List<Char> list = UserMdl.getCharDef();
        Char item;
        for (int i = 0, j = list.size(); i < j; i += 1)
        {
            item = list.get(i);
            cm.addElement(item);
            if (item.equals(UserMdl.getCfg().getPwdsSet()))
            {
                indx = i;
            }
        }
        cb_PwdsChar.setModel(cm);
        cb_PwdsChar.setSelectedIndex(indx);

        tf_PwdsSize.setText(UserMdl.getCfg().getPwdsLen());
        ck_PwdsUrpt.setSelected(UserMdl.getCfg().isPwdsUpt());

        tf_BackSize.setText("" + UserMdl.getCfg().getBackNum());
        tf_BackPath.setText(UserMdl.getCfg().getBackDir());

        tf_SafeTime.setText("" + UserMdl.getCfg().getClnClp());
    }

    @Override
    public JPanel getPanel()
    {
        return this;
    }

    private void initPwdsView()
    {
        pl_PwdsProp = new javax.swing.JPanel();

        lb_PwdsChar = new javax.swing.JLabel();
        cb_PwdsChar = new javax.swing.JComboBox();
        lb_PwdsSize = new javax.swing.JLabel();
        tf_PwdsSize = new javax.swing.JTextField();
        ck_PwdsUrpt = new javax.swing.JCheckBox();

        lb_PwdsChar.setLabelFor(cb_PwdsChar);
        lb_PwdsSize.setLabelFor(tf_PwdsSize);
        tf_PwdsSize.setColumns(6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_PwdsProp);
        pl_PwdsProp.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addComponent(lb_PwdsChar).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cb_PwdsChar,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(
                layout.createSequentialGroup().addComponent(lb_PwdsSize).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
                tf_PwdsSize, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(
                ck_PwdsUrpt))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_PwdsChar).addComponent(cb_PwdsChar,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_PwdsSize).addComponent(tf_PwdsSize,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(ck_PwdsUrpt))));
    }

    private void initBackView()
    {
        pl_BackProp = new javax.swing.JPanel();

        lb_BackSize = new javax.swing.JLabel();
        tf_BackSize = new javax.swing.JTextField();
        lb_BackPath = new javax.swing.JLabel();
        tf_BackPath = new javax.swing.JTextField();
        bt_BackPath = new javax.swing.JButton();

        lb_BackSize.setLabelFor(tf_BackSize);
        lb_BackPath.setLabelFor(tf_BackPath);
        tf_BackSize.setColumns(6);

        bt_BackPath.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_BackPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_BackProp);
        pl_BackProp.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addComponent(lb_BackSize).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(tf_BackSize,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(
                layout.createSequentialGroup().addComponent(lb_BackPath).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(tf_BackPath,
                javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_BackPath))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_BackSize).addComponent(tf_BackSize,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_BackPath).addComponent(bt_BackPath).addComponent(tf_BackPath,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE))));
    }

    private void initSafeView()
    {
        pl_SafeProp = new javax.swing.JPanel();

        lb_ClnClp = new javax.swing.JLabel();
        tf_SafeTime = new javax.swing.JTextField();

        tf_SafeTime.setColumns(8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_SafeProp);
        pl_SafeProp.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(lb_ClnClp).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(tf_SafeTime,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(92, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                lb_ClnClp).addComponent(tf_SafeTime, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))));
    }

    private void initBaseView()
    {
        bt_Update = new javax.swing.JButton();
        bt_Default = new javax.swing.JButton();

        bt_Update.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_UpdateActionPerformed(evt);
            }
        });
        bt_Default.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DefaultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap(132, Short.MAX_VALUE).addComponent(bt_Default).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_Update)).addComponent(pl_PwdsProp, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE).addComponent(
                pl_BackProp, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE).addComponent(
                pl_SafeProp, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addComponent(pl_PwdsProp, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pl_BackProp,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pl_SafeProp,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_Update).addComponent(bt_Default))));
    }

    private void initPwdsLang()
    {
        Lang.setWText(lb_PwdsChar, LangRes.P30F8301, "字符空间");
        Lang.setWText(lb_PwdsSize, LangRes.P30F8302, "口令长度");
        Lang.setWText(ck_PwdsUrpt, LangRes.P30F8303, "不可重复");
    }

    private void initBackLang()
    {
        Lang.setWText(lb_BackSize, LangRes.P30F8304, "备份数量");
        Lang.setWText(lb_BackPath, LangRes.P30F8305, "备份路径");
        Lang.setWText(bt_BackPath, LangRes.P30F8501, ">>");
    }

    private void initSafeLang()
    {
        Lang.setWText(lb_ClnClp, LangRes.P30F8306, "剪贴板口令驻留时间（秒）");
    }

    private void initBaseLang()
    {
        pl_PwdsProp.setBorder(javax.swing.BorderFactory.createTitledBorder(Lang.getLang(LangRes.P30F8901, "口令")));

        pl_BackProp.setBorder(javax.swing.BorderFactory.createTitledBorder(Lang.getLang(LangRes.P30F8902, "备份")));

        pl_SafeProp.setBorder(javax.swing.BorderFactory.createTitledBorder(Lang.getLang(LangRes.P30F8903, "安全")));

        Lang.setWText(bt_Update, LangRes.P30F8503, "保存");
        Lang.setWText(bt_Default, LangRes.P30F8505, "默认");
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
        text = tf_BackSize.getText();
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
        text = tf_SafeTime.getText();
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

        tf_BackSize.setText(ConsCfg.DEF_BACK_SIZE);
        tf_BackPath.setText(ConsCfg.DEF_BACK_PATH);

        tf_SafeTime.setText(ConsCfg.DEF_SAFE_TIME);
    }
    private javax.swing.JComboBox cb_PwdsChar;
    private javax.swing.JLabel lb_PwdsChar;
    private javax.swing.JLabel lb_PwdsSize;
    private javax.swing.JCheckBox ck_PwdsUrpt;
    private javax.swing.JTextField tf_PwdsSize;
    private javax.swing.JButton bt_BackPath;
    private javax.swing.JLabel lb_BackPath;
    private javax.swing.JLabel lb_BackSize;
    private javax.swing.JTextField tf_BackPath;
    private javax.swing.JTextField tf_BackSize;
    private javax.swing.JLabel lb_ClnClp;
    private javax.swing.JTextField tf_SafeTime;
    private javax.swing.JPanel pl_PwdsProp;
    private javax.swing.JPanel pl_BackProp;
    private javax.swing.JPanel pl_SafeProp;
    private javax.swing.JButton bt_Update;
    private javax.swing.JButton bt_Default;
}
