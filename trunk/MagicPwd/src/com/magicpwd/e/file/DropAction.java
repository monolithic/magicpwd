/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.file;

/**
 *
 * @author Administrator
 */
public class DropAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        int index = ls_GuidList.getSelectedIndex();
        if (index < 0 || index >= coreMdl.getListMdl().getSize())
        {
            return;
        }

        if (Lang.showFirm(this, LangRes.P30F7A0A, "您正在进行的操作是删除记录数据及其所有历史信息，确认继续么？") != javax.swing.JOptionPane.YES_OPTION)
        {
            return;
        }
        if (Lang.showFirm(this, LangRes.P30F7A0B, "确认一下您操作的正确性，要返回么？") != javax.swing.JOptionPane.NO_OPTION)
        {
            return;
        }
        coreMdl.getListMdl().wDelete(index);
        coreMdl.getGridMdl().clear();
        showPropEdit();
    }
}
