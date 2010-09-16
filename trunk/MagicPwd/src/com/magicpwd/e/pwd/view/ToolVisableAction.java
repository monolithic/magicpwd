/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.view;

import com.magicpwd.m.UserCfg;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public class ToolVisableAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public ToolVisableAction(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserCfg cfg = coreMdl.getUserCfg();
        boolean b = !cfg.isToolViw();
        mainPtn.setToolBeanVisible(b);
        mainPtn.pack();

//        mainMenu.setViewToolSelected(b);
        cfg.setToolViw(b);
    }
}
