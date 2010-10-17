/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pad;

import com.magicpwd.__a.mpad.APadAction;
import com.magicpwd.m.UserCfg;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class TopAction extends APadAction
{

    public TopAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserCfg cfg = coreMdl.getUserCfg();
        boolean b = !cfg.isViewTop();
        TrayPtn.getCurrForm().setAlwaysOnTop(b);
        cfg.setViewTop(b);
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(Object object)
    {
    }
}
