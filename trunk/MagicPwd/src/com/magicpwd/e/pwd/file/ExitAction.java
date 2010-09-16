/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.file;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class ExitAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public ExitAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (getCoreMdl().getGridMdl().isModified() && javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30F7A42, "您的数据尚未保存，确认要退出吗？"))
        {
            return;
        }

        TrayPtn.getCurrForm().setVisible(false);
        TrayPtn.endSave();
        System.exit(0);
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
