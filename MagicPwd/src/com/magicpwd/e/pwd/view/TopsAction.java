/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.view;

import com.magicpwd.__a.APwdAction;
import com.magicpwd.m.UserCfg;

/**
 *
 * @author Amon
 */
public class TopsAction extends APwdAction
{

    public TopsAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserCfg cfg = coreMdl.getUserCfg();
        boolean b = !cfg.isViewTop();
        mainPtn.setAlwaysOnTop(b);
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
