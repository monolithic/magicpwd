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
public class FindVisibleAction extends APwdAction
{

    public FindVisibleAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isFindVisible();
        mainPtn.setFindVisible(b);
        mainPtn.pack();
    }

    @Override
    public void doInit(Object object)
    {
        setSelected(coreMdl.getUserCfg().isFindVisible());
    }

    @Override
    public void reInit(Object object)
    {
    }
}
