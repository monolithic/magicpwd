/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.user;

import com.magicpwd.$a.APwdAction;
import com.magicpwd.$i.IBackCall;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class CreateSkeyAction extends APwdAction
{

    public CreateSkeyAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (Char.isValidate(coreMdl.getUserCfg().getCfg(ConsCfg.CFG_USER_SKEY), 224))
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A28, "您已经设置过安全口令！");
            return;
        }

        TrayPtn.getUserPtn(ConsEnv.INT_SIGN_SK, new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                if (params != null && params.length > 0)
                {
                    if (ConsEnv.STR_SIGN_SK.equals(params[0]))
                    {
                        setEnabled(false);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void doInit(Object object)
    {
        setEnabled(!Char.isValidate(coreMdl.getUserCfg().getCfg(ConsCfg.CFG_USER_SKEY), 224));
    }

    @Override
    public void reInit(Object object)
    {
    }
}
