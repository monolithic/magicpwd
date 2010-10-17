/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.file;

import com.magicpwd.__a.mpwd.APwdAction;

/**
 *
 * @author Amon
 */
public class SaveAction extends APwdAction
{

    public SaveAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.saveKeys();
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
