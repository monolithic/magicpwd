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
public class EditRelatedAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public EditRelatedAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserCfg cfg = coreMdl.getUserCfg();

        boolean b = !cfg.isEditWnd();

        // if (!cfg.isEditViw())
        // {
        // mainMenu.setViewSideSelected(false);
        // mainTool.setPropSideSelected(false);
        // return;
        // }

        if (cfg.isEditViw())
        {
            mainPtn.showPropEdit(b);
        }
        mainPtn.pack();

//        mainMenu.setViewSideSelected(b);
//        mainTool.setPropSideSelected(b);

        cfg.setEditWnd(b);
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
}
