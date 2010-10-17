/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.user;

import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd.v.TrayPtn;
import java.util.EventListener;

/**
 *
 * @author Amon
 */
public class ChangePkeyAction extends APwdAction
{

    public ChangePkeyAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        TrayPtn.getUserPtn(ConsEnv.INT_SIGN_PK, new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, EventListener event, String... params)
            {
                return true;
            }
        });
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(Object object)
    {
    }
}
