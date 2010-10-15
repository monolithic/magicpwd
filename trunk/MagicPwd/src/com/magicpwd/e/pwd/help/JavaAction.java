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
public class JavaAction extends APwdAction
{

    public JavaAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.showOptions(ConsEnv.PROP_JAVA);
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
