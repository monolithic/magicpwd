/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.tray;

import com.magicpwd.__a.tray.ATrayAction;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;

/**
 *
 * @author Amon
 */
public class ExitAction extends ATrayAction
{

    public ExitAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.JFrame frame = trayPtn.getCurrForm();
        if (frame == null || frame.getState() == javax.swing.JFrame.ICONIFIED)
        {
            frame = null;
        }
        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(frame, LangRes.P30F1A04, "确认要退出吗？"))
        {
            return;
        }

        if (trayPtn.getCurrForm() != null)
        {
            trayPtn.getCurrForm().setVisible(false);
        }
        trayPtn.endExit(0);
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
