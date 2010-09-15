/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

/**
 *
 * @author Administrator
 */
public class EditRelatedAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isEditWnd();

        // if (!UserMdl.getUserCfg().isEditViw())
        // {
        // mainMenu.setViewSideSelected(false);
        // mainTool.setPropSideSelected(false);
        // return;
        // }

        if (coreMdl.getUserCfg().isEditViw())
        {
            showPropEdit(b);
        }
        this.pack();

        mainMenu.setViewSideSelected(b);
        mainTool.setPropSideSelected(b);

        coreMdl.getUserCfg().setEditWnd(b);
    }
}
