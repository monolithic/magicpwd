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
public class DropAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public DropAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
//        int row = tb_KeysView.getSelectedRow();
//        if (row < ConsEnv.PWDS_HEAD_SIZE || row > tb_KeysView.getRowCount() - 1)
//        {
//            return;
//        }
//        if (Lang.showFirm(this, LangRes.P30F1A01, "确认要删除此属性数据么？") == javax.swing.JOptionPane.YES_OPTION)
//        {
//            coreMdl.getGridMdl().wRemove(row);
//            selectNext(0, true);
//        }
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
