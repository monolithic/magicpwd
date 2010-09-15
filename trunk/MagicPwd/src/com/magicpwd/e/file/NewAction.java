/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.file;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class NewAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private UserMdl coreMdl;

    public NewAction(MainPtn mainPtn, UserMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        GridMdl gm = coreMdl.getGridMdl();
        if (gm.isModified())
        {
            if (Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30F7A09, "记录数据 {0} 已修改，要放弃修改吗？", gm.getItemAt(ConsEnv.PWDS_HEAD_META).getName()) != javax.swing.JOptionPane.YES_OPTION)
            {
                return;
            }
        }

        //tb_LastIndx = 0;
        gm.clear();
        if (!coreMdl.getUserCfg().isEditViw())
        {
//            mainMenu.setViewPropSelected(true);
//            mainMenu.setViewSideSelected(true);
            coreMdl.getUserCfg().setEditViw(true);
            coreMdl.getUserCfg().setEditWnd(true);
            mainPtn.showPropEdit(true);
        }
        mainPtn.showPropEdit(gm.initGuid(), true);
    }
}
