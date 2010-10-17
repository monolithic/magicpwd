/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.user;

import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd._cons.ConsEnv;

/**
 *
 * @author aven
 */
public class TemplateAction extends APwdAction
{

    public TemplateAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.showOptions(ConsEnv.PROP_TPLT);
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
