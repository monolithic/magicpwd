/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.edit;

/**
 *
 * @author Administrator
 */
public class AppendKeysAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (checkData())
        {
            tb_LastIndx = tb_KeysView.getSelectedRow();
            if (tb_LastIndx < ConsEnv.PWDS_HEAD_SIZE)
            {
                tb_LastIndx = tb_KeysView.getRowCount();
            }
            showPropEdit(coreMdl.getGridMdl().wAppend(tb_LastIndx, ConsDat.INDX_PWDS), true);
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        }
    }
}
