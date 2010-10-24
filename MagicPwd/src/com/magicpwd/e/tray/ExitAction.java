/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.tray;

import com.magicpwd.__a.tray.ATrayAction;

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
        trayPtn.endSave();
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
