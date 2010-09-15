/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.prop;

/**
 *
 * @author Amon
 */
public class CopyAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        int row = tb_KeysView.getSelectedRow();
        if (row < 0 || row > tb_KeysView.getRowCount() - 1)
        {
            return;
        }
        IEditItem tplt = coreMdl.getGridMdl().getItemAt(row);
        Util.setClipboardContents(tplt.getData(), coreMdl.getUserCfg().getStayTime());
    }
}
