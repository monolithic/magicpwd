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
import com.magicpwd._cons.LangRes;
import com.magicpwd._enum.AuthLog;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;

/**
 *
 * @author Aven
 */
public class SignFp extends javax.swing.JPanel implements IUserView
{

    private UserPtn userPtn;
    /**
     * 登录错误次数
     */
    private int errCount;

    SignFp(UserPtn userPtn)
    {
        this.userPtn = userPtn;
    }

    @Override
    public void initView()
    {
        lbUserName = new javax.swing.JLabel();
        tfUserName = new javax.swing.JTextField();
        lbUserPwds = new javax.swing.JLabel();
        pfUserPwds = new javax.swing.JPasswordField();
        lbUserOpts = new javax.swing.JLabel();
        plUserOpts = new javax.swing.JPanel();

        lbUserOpts.setForeground(new java.awt.Color(0, 0, 255));
        lbUserOpts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbUserOpts.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e)
            {
                lbUserOptsMouseReleased(e);
            }
        });

        plUserOpts.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        plUserOpts.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lbUserName, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lbUserPwds, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tfUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
        hpg2.addComponent(pfUserPwds, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addGroup(hpg1);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addGroup(hpg2);
        javax.swing.GroupLayout.ParallelGroup hpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg3.addGroup(hsg1);
        hpg3.addComponent(lbUserOpts, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg3.addComponent(plUserOpts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addGroup(hpg3);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(hsg2);
        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lbUserName);
        vpg1.addComponent(tfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lbUserPwds);
        vpg2.addComponent(pfUserPwds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(lbUserOpts);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(plUserOpts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vsg1);
    }

    @Override
    public void initLang()
    {
        Lang.setWText(lbUserName, LangRes.P30FA30C, "登录用户(@U)");

        Lang.setWText(lbUserPwds, LangRes.P30FA30D, "安全口令(@P)");

        Lang.setWText(userPtn.getApplyButton(), LangRes.P30FA509, "找回(@F)");

        Lang.setWText(userPtn.getAbortButton(), LangRes.P30FA504, "取消(@C)");

        userPtn.setTitle(Lang.getLang(LangRes.P30FA204, "口令找回"));
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

    @Override
    public void btApplyActionPerformed(java.awt.event.ActionEvent e)
    {
        String name = tfUserName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tfUserName.requestFocus();
            return;
        }
        String pwds = new String(pfUserPwds.getPassword());
        if (!com.magicpwd._util.Char.isValidate(pwds))
        {
            Lang.showMesg(this, LangRes.P30FAA14, "请输入安全口令！");
            pfUserPwds.requestFocus();
            return;
        }

        StringBuffer sb = new StringBuffer(pwds);
        try
        {
            boolean b = userPtn.getUserMdl().signFp(name, sb);
            if (!b)
            {
                Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
                tfUserName.setText("");
                pfUserPwds.setText("");
                tfUserName.requestFocus();
                errCount += 1;
                if (errCount > 2)
                {
                    System.exit(0);
                }
                return;
            }
        }
        catch (java.security.NoSuchAlgorithmException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (javax.crypto.NoSuchPaddingException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
            tfUserName.setText("");
            pfUserPwds.setText("");
            errCount += 1;
            if (errCount > 2)
            {
                System.exit(0);
            }
            return;
        }

        if (userPtn.getBackCall() != null)
        {
            userPtn.getBackCall().callBack(AuthLog.signFp.name(), new UserDto(sb.toString()));
        }
        Lang.showMesg(null, LangRes.P30FAA18, "您的新口令是：{0}\n为了您的安全，请登录软件后尽快修改您的口令。", sb.toString());
    }

    @Override
    public void btAbortActionPerformed(java.awt.event.ActionEvent e)
    {
    }

    private void lbUserOptsMouseReleased(java.awt.event.MouseEvent evt)
    {
        plUserOpts.setVisible(!plUserOpts.isVisible());
        userPtn.pack();
    }
    private javax.swing.JLabel lbUserName;
    private javax.swing.JTextField tfUserName;
    private javax.swing.JLabel lbUserPwds;
    private javax.swing.JPasswordField pfUserPwds;
    private javax.swing.JLabel lbUserOpts;
    private javax.swing.JPanel plUserOpts;
}
