/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e;

import com.magicpwd.__a.tray.ATrayAction;
import com.magicpwd._cons.ConsEnv;

/**
 *
 * @author aven
 */
public class MpadAction extends ATrayAction
{

    public MpadAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        trayPtn.showViewPtn(ConsEnv.APP_MODE_MPAD);
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
