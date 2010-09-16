/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.prop;

import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public class CopyAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public CopyAction()
    {
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

    @Override
    public void doUpdate(Object object)
    {
    }
}
