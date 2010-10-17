/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.edit;

import com.magicpwd.__a.mpwd.AMpwdAction;

/**
 *
 * @author aven
 */
public class FindAction extends AMpwdAction
{

    public FindAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.setFindVisible(true);
        mainPtn.pack();
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
