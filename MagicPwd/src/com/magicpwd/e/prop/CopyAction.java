/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.prop;

import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MainPtn;

/**
 *
 * @author Amon
 */
public class CopyAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private UserMdl coreMdl;

    public CopyAction(MainPtn mainPtn, UserMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
//        int row = tb_KeysView.getSelectedRow();
//        if (row < 0 || row > tb_KeysView.getRowCount() - 1)
//        {
//            return;
//        }
//        IEditItem tplt = coreMdl.getGridMdl().getItemAt(row);
//        Util.setClipboardContents(tplt.getData(), coreMdl.getUserCfg().getStayTime());
    }
}
