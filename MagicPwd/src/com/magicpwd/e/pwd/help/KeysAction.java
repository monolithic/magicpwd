/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.help;

import com.magicpwd.$a.APwdAction;
import com.magicpwd._cons.ConsEnv;

/**
 *
 * @author Amon
 */
public class KeysAction extends APwdAction
{

    public KeysAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.showOptions(ConsEnv.PROP_SKEY);
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
