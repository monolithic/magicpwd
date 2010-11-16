/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.skin;

import com.magicpwd.__a.mpwd.AMpwdAction;

/**
 *
 * @author aven
 */
public class FeelAction extends AMpwdAction
{

    public FeelAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.getUserMdl().setFeel(e.getActionCommand());
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
