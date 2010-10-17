/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.file;

import com.magicpwd.__a.APwdAction;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;

/**
 *
 * @author Amon
 */
public class DropAction extends APwdAction
{

    public DropAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        Object object = mainPtn.getSelectedListValue();
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
        coreMdl.getListMdl().wDelete(mainPtn.getSelectedListIndex());
        mainPtn.clearGrid();
        mainPtn.showPropInfo();
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(Object object)
    {
    }
}
