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
public class PrevAction extends AMpwdAction
{

    public PrevAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.movePrev();
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
