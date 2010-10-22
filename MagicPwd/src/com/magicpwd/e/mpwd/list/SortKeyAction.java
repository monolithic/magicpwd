/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.list;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._cons.ConsCfg;

/**
 *
 * @author Amon
 */
public class SortKeyAction extends AMpwdAction
{

    public SortKeyAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        coreMdl.setCfg(ConsCfg.CFG_VIEW_LIST_KEY, e.getActionCommand());
        mainPtn.showList();
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
