/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.user;

import com.magicpwd.__a.APwdAction;
import com.magicpwd._cons.ConsEnv;

/**
 *
 * @author aven
 */
public class OptionsAction extends APwdAction
{

    public OptionsAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.showOptions(ConsEnv.PROP_USET);
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
