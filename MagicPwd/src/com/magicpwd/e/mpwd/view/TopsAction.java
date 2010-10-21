/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.view;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.m.UserCfg;

/**
 *
 * @author Amon
 */
public class TopsAction extends AMpwdAction
{

    public TopsAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserCfg cfg = coreMdl.getUserCfg();
        boolean b = !cfg.isTopMost();
        mainPtn.setAlwaysOnTop(b);
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
