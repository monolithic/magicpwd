/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

/**
 *
 * @author Administrator
 */
public class EditVisableAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isEditViw();
        if (b)
        {
            showPropEdit(coreMdl.getUserCfg().isEditWnd());
        }
        else
        {
            pl_KeysEdit.setVisible(b);
            if (md_MpsDialog != null)
            {
                md_MpsDialog.setVisible(b);
            }
        }
        coreMdl.getUserCfg().setEditViw(b);
    }
}
