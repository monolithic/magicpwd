/*
 *  Copyright (C) 2011 Aven
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
package com.magicpwd._user;

import com.magicpwd.__i.IUserView;

/**
 *
 * @author Aven
 */
public class SignUp extends javax.swing.JPanel implements IUserView
{

    private UserPtn userPtn;

    public SignUp(UserPtn userPtn)
    {
        this.userPtn = userPtn;
    }

    @Override
    public void initView()
    {
        lbUserName = new javax.swing.JLabel();
        tfUserName = new javax.swing.JTextField();
        lbUserPwd1 = new javax.swing.JLabel();
        pfUserPwd1 = new javax.swing.JPasswordField();
        lbUserPwd2 = new javax.swing.JLabel();
        pfUserPwd2 = new javax.swing.JPasswordField();
        lbUserOpts = new javax.swing.JLabel();
        plUserOpts = new javax.swing.JPanel();
        btAbort = new javax.swing.JButton();
        btApply = new javax.swing.JButton();
        lbUpgrade = new javax.swing.JLabel();

        lbUserOpts.setForeground(new java.awt.Color(0, 0, 255));
        lbUserOpts.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbUserOpts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbUserOpts.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                lbUserOptsMouseReleased(evt);
            }
        });

        plUserOpts.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout plUserOptsLayout = new javax.swing.GroupLayout(plUserOpts);
        plUserOpts.setLayout(plUserOptsLayout);
        plUserOptsLayout.setHorizontalGroup(
                plUserOptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 226, Short.MAX_VALUE));
        plUserOptsLayout.setVerticalGroup(
                plUserOptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));

        btAbort.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btAbortActionPerformed(evt);
            }
        });

        btApply.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btApplyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lbUserName, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbUserPwd1, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbUserPwd2, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tfUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
        hpg2.addComponent(pfUserPwd1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
        hpg2.addComponent(pfUserPwd2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addGroup(hpg1);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addGroup(hpg2);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(lbUpgrade);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE);
        hsg2.addComponent(btApply);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(btAbort);
        javax.swing.GroupLayout.ParallelGroup hpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg3.addGroup(hsg1);
        hpg3.addComponent(lbUserOpts, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg3.addComponent(plUserOpts);
        hpg3.addGroup(hsg2);
        layout.setHorizontalGroup(hpg3);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lbUserName);
        vpg1.addComponent(tfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lbUserPwd1);
        vpg2.addComponent(pfUserPwd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lbUserPwd2);
        vpg3.addComponent(pfUserPwd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg4.addComponent(btAbort);
        vpg4.addComponent(btApply);
        vpg4.addComponent(lbUpgrade);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
//        vsg1.addContainerGap();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg3);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(lbUserOpts);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(plUserOpts);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vsg1.addGroup(vpg4);
//        vsg1.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg1));
    }

    @Override
    public void initLang()
    {
        lbUserName.setText("登录用户(U)");
        lbUserPwd1.setText("用户口令(P)");
        lbUserPwd2.setText("口令确认(V)");
        lbUserOpts.setText("更新选项");
        lbUpgrade.setText("升级");
        btApply.setText("注册(O)");
        btAbort.setText("取消(C)");
    }

    @Override
    public void initData()
    {
    }

    @Override
    public javax.swing.JPanel getPanel()
    {
        return this;
    }

    private void lbUserOptsMouseReleased(java.awt.event.MouseEvent evt)
    {
        plUserOpts.setVisible(!plUserOpts.isVisible());
        userPtn.pack();
    }

    private void btApplyActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void btAbortActionPerformed(java.awt.event.ActionEvent evt)
    {
    }
//    private void signUp()
//    {
//        // 登录名称检测
//        String un = tf_UserName.getText();
//        if (!com.magicpwd._util.Char.isValidate(un))
//        {
//            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
//            tf_UserName.requestFocus();
//            return;
//        }
//
//        // 口令为空检测
//        String p1 = new String(pf_UserKey0.getPassword());
//        if (!com.magicpwd._util.Char.isValidate(p1))
//        {
//            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
//            pf_UserKey0.requestFocus();
//            return;
//        }
//
//        // 口令不一致检测
//        String p2 = new String(pf_UserKey1.getPassword());
//        if (!p1.equals(p2))
//        {
//            Lang.showMesg(this, LangRes.P30FAA05, "您输入的口令不一致，请重新输入！");
//            pf_UserKey0.setText("");
//            pf_UserKey1.setText("");
//            pf_UserKey0.requestFocus();
//            return;
//        }
//        tf_UserName.setText("");
//        pf_UserKey0.setText("");
//        pf_UserKey1.setText("");
//
//        try
//        {
//            boolean b = userMdl.signUp(un, p1);
//            if (b)
//            {
//                userMdl.setCfg(ConsCfg.CFG_USER_LAST, un);
//                DBA4000.initDataBase();
//            }
//            else
//            {
//                Lang.showMesg(this, LangRes.P30FAA06, "注册用户失败，请更换用户名及口令后重试！");
//                tf_UserName.requestFocus();
//                return;
//            }
//        }
//        catch (Exception exp)
//        {
//            Logs.exception(exp);
//            Lang.showMesg(this, LangRes.P30FAA07, "创建用户失败，请重新启动本程序尝试！");
//            System.exit(0);
//            return;
//        }
//
//        this.setVisible(false);
//        if (backCall != null)
//        {
//            if (!backCall.callBack(AuthLog.signUp.name(), null))
//            {
//                System.exit(0);
//                return;
//            }
//        }
//        dispoze();
//    }
    private javax.swing.JButton btAbort;
    private javax.swing.JButton btApply;
    private javax.swing.JLabel lbUserOpts;
    private javax.swing.JLabel lbUpgrade;
    private javax.swing.JLabel lbUserName;
    private javax.swing.JLabel lbUserPwd1;
    private javax.swing.JLabel lbUserPwd2;
    private javax.swing.JPasswordField pfUserPwd1;
    private javax.swing.JPasswordField pfUserPwd2;
    private javax.swing.JPanel plUserOpts;
    private javax.swing.JTextField tfUserName;
}
