/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.user;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._user.UserPtn;
import com.magicpwd._util.Lang;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class CreateSkeyAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public CreateSkeyAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        String skey = coreMdl.getUserCfg().getCfg(ConsCfg.CFG_USER_SKEY);
        if (skey != null && skey.length() == 224)
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A28, "您已经设置过安全口令！");
            return;
        }

        UserPtn us = new UserPtn(TrayPtn.getCurrForm());
        us.setBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                //mainMenu.setUserSecretEnabled();
                return true;
            }
        });
        us.initView(ConsEnv.INT_SIGN_SK);
        us.initLang();
        us.initData();
    }

    @Override
    public void setMainPtn(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    @Override
    public void setCoreMdl(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    @Override
    public void doUpdate(Object object)
    {
    }
}