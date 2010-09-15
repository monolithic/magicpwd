/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.help;

/**
 *
 * @author Administrator
 */
public class MlogAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(this, LangRes.P30F7A0F, "");
        }

        try
        {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(ConsEnv.MLOGSITE));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }
}
