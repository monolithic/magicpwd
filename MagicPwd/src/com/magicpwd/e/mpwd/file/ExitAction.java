/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.file;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class ExitAction extends AMpwdAction
{

    public ExitAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (mainPtn.gridModified() && javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(mainPtn, LangRes.P30F7A42, "您的数据尚未保存，确认要退出吗？"))
        {
            return;
        }

        mainPtn.setVisible(false);
        TrayPtn.endSave();
        System.exit(0);
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
