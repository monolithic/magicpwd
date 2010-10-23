/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e;

import com.magicpwd.__a.tray.ATrayAction;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;

/**
 *
 * @author Amon
 */
public class MailAction extends ATrayAction
{

    public MailAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(trayPtn.getCurrForm(), LangRes.P30F7A11, "");
        }

        try
        {
            java.awt.Desktop.getDesktop().mail(new java.net.URI("mailto:" + ConsEnv.SOFTMAIL));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }
}
