/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.help;

/**
 *
 * @author Administrator
 */
public class MailAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(this, LangRes.P30F7A11, "");
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
}
