/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.view;

import com.magicpwd.__a.mpwd.AMpwdAction;

/**
 *
 * @author Amon
 */
public class ToolVisibleAction extends AMpwdAction
{

    public ToolVisibleAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !mainPtn.getUserMdl().isToolVisible();
        mainPtn.setToolVisible(b);
        mainPtn.pack();
    }

    @Override
    public void doInit(Object object)
    {
        setSelected(mainPtn.getUserMdl().isToolVisible());
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
        button.setSelected(isSelected());
    }
}
