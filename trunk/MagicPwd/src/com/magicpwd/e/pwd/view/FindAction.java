/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.view;

import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public class FindAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public FindAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
//        if (!coreMdl.getUserCfg().isFindViw())
//        {
//            mainFind.setVisible(true);
//            mainMenu.setViewFindSelected(true);
//            coreMdl.getUserCfg().setFindViw(true);
//        }
//        mainFind.requestFocus();
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
