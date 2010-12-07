/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.user;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;

/**
 *
 * @author Amon
 */
public class CreateSkeyAction extends AMpwdAction
{

    public CreateSkeyAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (Char.isValidate(mainPtn.getUserMdl().getCfg(ConsCfg.CFG_USER_SKEY), 224))
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A28, "您已经设置过安全口令！");
            return;
        }

        trayPtn.getUserPtn(ConsEnv.INT_SIGN_SK, new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                if (params == null || params.length < 1)
                {
                    return false;
                }

                if (ConsEnv.STR_SIGN_SK.equals(params[0]))
                {
                    javax.swing.AbstractButton button = mainPtn.getMenuPtn().getButton("mpwd-skey");
                    if (button != null)
                    {
                        button.setEnabled(false);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void doInit(Object object)
    {
        setEnabled(!Char.isValidate(mainPtn.getUserMdl().getCfg(ConsCfg.CFG_USER_SKEY), 224));
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
        button.setEnabled(isEnabled());
    }
}
