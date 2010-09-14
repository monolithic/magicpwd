/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.file;

import com.magicpwd.MagicPwd;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class ExitAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private UserMdl coreMdl;

    public ExitAction(MainPtn mainPtn, UserMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (coreMdl.getGridMdl().isModified() && javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30F7A42, "您的数据尚未保存，确认要退出吗？"))
        {
            return;
        }

        TrayPtn.getCurrForm().setVisible(false);
        MagicPwd.endSave();
        System.exit(0);
    }
}
