/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

import com.magicpwd.m.UserCfg;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MainPtn;

/**
 *
 * @author Amon
 */
public class EditVisableAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public EditVisableAction(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserCfg cfg = coreMdl.getUserCfg();
        boolean b = !cfg.isEditViw();
        if (b)
        {
            mainPtn.showPropEdit(cfg.isEditWnd());
        }
        else
        {
            mainPtn.setEditBeanVisible(b);
        }
        cfg.setEditViw(b);
    }
}
