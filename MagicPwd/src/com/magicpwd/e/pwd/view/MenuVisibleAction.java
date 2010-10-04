/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.view;

import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.UserCfg;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public class MenuVisibleAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public MenuVisibleAction()
    {
        putValue(NAME, "com.magicpwd.e.pwd.view.MenuVisableAction");
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserCfg cfg = coreMdl.getUserCfg();

        boolean b = !cfg.isMenuViw();
        mainPtn.setMenuVisible(b);
        mainPtn.pack();

        cfg.setMenuViw(b);
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
        this.putValue("selected", coreMdl.getUserCfg().isMenuViw());
    }
}
