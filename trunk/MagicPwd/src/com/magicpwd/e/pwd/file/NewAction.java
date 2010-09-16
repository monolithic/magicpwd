/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.file;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class NewAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public NewAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        GridMdl gm = getCoreMdl().getGridMdl();
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
            getCoreMdl().getUserCfg().setEditViw(true);
            getCoreMdl().getUserCfg().setEditWnd(true);
            getMainPtn().showPropEdit(true);
        }
        getMainPtn().showPropEdit(gm.initGuid(), true);
    }

    /**
     * @return the mainPtn
     */
    public MainPtn getMainPtn()
    {
        return mainPtn;
    }

    /**
     * @param mainPtn the mainPtn to set
     */
    public void setMainPtn(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    /**
     * @return the coreMdl
     */
    public CoreMdl getCoreMdl()
    {
        return coreMdl;
    }

    /**
     * @param coreMdl the coreMdl to set
     */
    public void setCoreMdl(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }
}
