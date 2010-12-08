/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.safe;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;

/**
 *
 * @author Amon
 */
public class RemoteConfigAction extends AMpwdAction implements IBackCall
{

    public RemoteConfigAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        trayPtn.getUserPtn(ConsEnv.INT_SIGN_CS, this);
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }

    @Override
    public boolean callBack(Object sender, java.util.EventListener event, String... params)
    {
        if (params == null || params.length < 4 || !ConsEnv.STR_SIGN_CS.equals(params[0]))
        {
            return false;
        }

        try
        {
            if (!Char.isValidateMail(params[1]))
            {
                params[1] = params[2] + '@' + params[1];
            }
            mainPtn.setCfgText("pop_mail", params[1] + '\n' + params[2] + '\n' + params[3]);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            Lang.showMesg(mainPtn, null, ex.getLocalizedMessage());
        }
        return true;
    }
}
