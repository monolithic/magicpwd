/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.user;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._user.UserSign;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class ChangePkeyAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public ChangePkeyAction(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserSign us = new UserSign(TrayPtn.getCurrForm());
        us.initView(ConsEnv.INT_SIGN_PK);
        us.initLang();
        us.initData();
    }
}
