/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.data;

import com.magicpwd._comn.PwdsItem;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._face.IBackCall;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Administrator
 */
public class ConfigAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserSign us = new UserSign(TrayPtn.getCurrForm());
        us.setBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return configDocs(params);
            }
        });
        us.initView(ConsEnv.INT_SIGN_CS);
        us.initLang();
        us.initData();
    }

    private boolean configDocs(String... params)
    {
        if (params == null || params.length < 3)
        {
            return false;
        }

        try
        {
            PwdsItem pwds = new PwdsItem();
            pwds.getP30F0203().append(params[1]).append('\n').append(params[2]);
            UserMdl.getGridMdl().enCrypt(pwds);
            DBA3000.saveConfig("pop_mail", pwds.getP30F0203().toString());
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            Lang.showMesg(TrayPtn.getCurrForm(), null, ex.getLocalizedMessage());
        }
        return true;
    }
}
