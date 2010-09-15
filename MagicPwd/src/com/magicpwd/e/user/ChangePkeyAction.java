/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.user;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._user.UserSign;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class ChangePkeyAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserSign us = new UserSign(TrayPtn.getCurrForm());
        us.initView(ConsEnv.INT_SIGN_PK);
        us.initLang();
        us.initData();
    }
}
