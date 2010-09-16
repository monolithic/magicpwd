/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.help;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public class MailAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public MailAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A11, "");
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
