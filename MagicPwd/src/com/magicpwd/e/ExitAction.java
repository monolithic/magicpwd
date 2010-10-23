/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e;

import com.magicpwd.__a.tray.ATrayAction;
import com.magicpwd.v.TrayPtn;

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
        if (trayPtn.getCurrForm() != null)
        {
            trayPtn.getCurrForm().setVisible(false);
        }
        TrayPtn.endSave();
        System.exit(0);
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
