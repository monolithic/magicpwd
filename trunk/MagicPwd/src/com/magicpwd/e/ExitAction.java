/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e;

import com.magicpwd.__a.AAction;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Administrator
 */
public class ExitAction extends AAction
{

    public ExitAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.JFrame frame = TrayPtn.getCurrForm();
        if (frame != null)
        {
            frame.setVisible(false);
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
