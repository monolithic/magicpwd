/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpad;

import com.magicpwd.__a.mpad.AMpadAction;

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
        boolean b = !miniPtn.getUserMdl().isTopMost();
        miniPtn.setAlwaysOnTop(b);
        miniPtn.getUserMdl().setTopMost(b);
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
