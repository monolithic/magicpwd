/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mwiz;

import com.magicpwd.__a.mwiz.AMwizAction;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;

/**
 *
 * @author Amon
 */
public class DeleteAction extends AMwizAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
//        Object object = normPtn.getSelectedListValue();
//        if (object == null)
//        {
//            return;
//        }

        if (Lang.showFirm(normPtn, LangRes.P30F7A0A, "您正在进行的操作是删除记录数据及其所有历史信息，确认继续么？") != javax.swing.JOptionPane.YES_OPTION)
        {
            return;
        }
        if (Lang.showFirm(normPtn, LangRes.P30F7A0B, "确认一下您操作的正确性，要返回么？") != javax.swing.JOptionPane.NO_OPTION)
        {
            return;
        }
        normPtn.deleteKeys();
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }
}
