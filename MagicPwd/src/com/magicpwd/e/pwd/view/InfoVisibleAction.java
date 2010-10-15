/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.view;

import com.magicpwd.$a.APwdAction;

/**
 *
 * @author Amon
 */
public class InfoVisibleAction extends APwdAction
{

    public InfoVisibleAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isInfoVisible();
        mainPtn.setInfoVisible(b);
        mainPtn.pack();
    }

    @Override
    public void doInit(Object object)
    {
        setSelected(coreMdl.getUserCfg().isInfoVisible());
    }

    @Override
    public void reInit(Object object)
    {
    }
}
