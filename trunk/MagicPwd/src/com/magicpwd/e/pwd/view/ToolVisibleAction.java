/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.view;

import com.magicpwd.__a.mpwd.APwdAction;

/**
 *
 * @author Amon
 */
public class ToolVisibleAction extends APwdAction
{

    public ToolVisibleAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isToolVisible();
        mainPtn.setToolVisible(b);
        mainPtn.pack();
    }

    @Override
    public void doInit(Object object)
    {
        setSelected(coreMdl.getUserCfg().isToolVisible());
    }

    @Override
    public void reInit(Object object)
    {
    }
}
