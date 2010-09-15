/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

import com.magicpwd.m.UserCfg;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MainPtn;

/**
 *
 * @author Amon
 */
public class EditRelatedAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private UserMdl coreMdl;

    public EditRelatedAction(MainPtn mainPtn, UserMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
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
}