/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.user;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._cons.ConsEnv;

/**
 *
 * @author aven
 */
public class PasswordAction extends AMpwdAction
{

    public PasswordAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.showOptions(ConsEnv.PROP_CHAR);
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
