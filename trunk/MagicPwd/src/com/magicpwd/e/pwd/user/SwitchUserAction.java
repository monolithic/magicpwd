/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.user;

import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public class SwitchUserAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public SwitchUserAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
    }

    @Override
    public void setMainPtn(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    @Override
    public void setCoreMdl(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    @Override
    public void doUpdate(Object object)
    {
    }
}
