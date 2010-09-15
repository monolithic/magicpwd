/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.edit;

/**
 *
 * @author Administrator
 */
public class ChangeAreaAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (checkData())
        {
            int idx = tb_KeysView.getSelectedRow();
            if (idx >= ConsEnv.PWDS_HEAD_SIZE && idx < tb_KeysView.getRowCount())
            {
                IEditItem tplt = coreMdl.getGridMdl().getItemAt(idx);
                if (tplt.getType() != ConsDat.INDX_AREA)
                {
                    tplt.setType(ConsDat.INDX_AREA);
                    showPropEdit(tplt, true);
                    coreMdl.getGridMdl().setModified(true);
                }
            }
        }
    }
}
