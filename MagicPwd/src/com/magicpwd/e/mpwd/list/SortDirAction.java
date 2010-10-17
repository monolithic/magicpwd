/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.list;

import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd._cons.ConsCfg;

/**
 *
 * @author Amon
 */
public class SortDirAction extends APwdAction
{

    public SortDirAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        coreMdl.getUserCfg().setCfg(ConsCfg.CFG_VIEW_LIST_ASC, e.getActionCommand());
        mainPtn.showList();
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
