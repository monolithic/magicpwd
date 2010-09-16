/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.file;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MainPtn;

/**
 *
 * @author Amon
 */
public class DropAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public DropAction(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        Object object = mainPtn.getSelectedItem();
        if (object == null)
        {
            return;
        }

        if (Lang.showFirm(mainPtn, LangRes.P30F7A0A, "您正在进行的操作是删除记录数据及其所有历史信息，确认继续么？") != javax.swing.JOptionPane.YES_OPTION)
        {
            return;
        }
        if (Lang.showFirm(mainPtn, LangRes.P30F7A0B, "确认一下您操作的正确性，要返回么？") != javax.swing.JOptionPane.NO_OPTION)
        {
            return;
        }
//        coreMdl.getListMdl().wDelete(index);
        coreMdl.getGridMdl().clear();
        mainPtn.showPropEdit();
    }
}
