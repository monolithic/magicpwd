/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.safe;

import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class CloudConfigAction extends APwdAction
{

    public CloudConfigAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        TrayPtn.getUserPtn(ConsEnv.INT_SIGN_CS, new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                if (params != null && params.length > 0)
                {
                    if (ConsEnv.STR_SIGN_CS.equals(params[0]))
                    {
                        return configDocs(params);
                    }
                }
                return false;
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

    private boolean configDocs(String... params)
    {
        if (params == null || params.length < 3)
        {
            return false;
        }

        try
        {
            if (!Char.isValidateEmail(params[1]))
            {
                params[1] = params[2] + '@' + params[1];
            }
            mainPtn.saveCfg("pop_mail", params[1] + '\n' + params[2] + '\n' + params[3]);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            Lang.showMesg(mainPtn, null, ex.getLocalizedMessage());
        }
        return true;
    }
}
