/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.edit;

import com.magicpwd.__a.mpwd.AMpwdAction;

/**
 *
 * @author Amon
 */
public class MovetoPrevAction extends AMpwdAction
{

    public MovetoPrevAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.movetoPrev();
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
