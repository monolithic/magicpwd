/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpad;

import com.magicpwd.__a.mpad.APadAction;

/**
 *
 * @author Amon
 */
public class OpenAction extends APadAction
{

    public OpenAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        miniPtn.openNote();
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
