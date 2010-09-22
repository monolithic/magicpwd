/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.file;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class NewAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public NewAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (mainPtn.gridModified())
        {
            if (Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30F7A09, "记录数据 {0} 已修改，要放弃修改吗？", mainPtn.getMeta().getName()) != javax.swing.JOptionPane.YES_OPTION)
            {
                return;
            }
        }

        //tb_LastIndx = 0;
        mainPtn.clearGrid();
        if (!coreMdl.getUserCfg().isEditVisible())
        {
            mainPtn.getMenuPtn().getButton("").setSelected(true);
//            mainMenu.setViewPropSelected(true);
//            mainMenu.setViewSideSelected(true);
            coreMdl.getUserCfg().setEditViw(true);
            coreMdl.getUserCfg().setEditWnd(true);
            mainPtn.showPropEdit(true);
        }
//        mainPtn.showPropEdit(gm.initGuid(), true);
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
