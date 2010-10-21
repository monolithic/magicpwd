/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpad;

import com.magicpwd.__a.mpad.AMpadAction;
import com.magicpwd.m.UserCfg;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class TopAction extends AMpadAction
{

    public TopAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserCfg cfg = coreMdl.getUserCfg();
        boolean b = !cfg.isTopMost();
        TrayPtn.getCurrForm().setAlwaysOnTop(b);
        cfg.setTopMost(b);
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
