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
public class InfoVisibleAction extends AMpwdAction
{

    public InfoVisibleAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !mainPtn.getUserMdl().isInfoVisible();
        mainPtn.setInfoVisible(b);
        mainPtn.pack();
    }

    @Override
    public void doInit(Object object)
    {
        setSelected(mainPtn.getUserMdl().isInfoVisible());
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
        button.setSelected(isSelected());
    }
}