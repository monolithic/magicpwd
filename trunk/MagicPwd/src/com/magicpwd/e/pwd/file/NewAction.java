/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.file;

import com.magicpwd._util.Char;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

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
        if (!mainPtn.newKeys())
        {
            return;
        }

        if (coreMdl.getUserCfg().isEditVisible())
        {
            return;
        }

        String cmd = e.getActionCommand();
        if (Char.isValidate(cmd))
        {
            String[] arr = cmd.split(",");
            if (arr != null && arr.length == 2)
            {
                mainPtn.getMenuPtn().getButton(arr[0]).setSelected(true);
                mainPtn.getMenuPtn().getButton(arr[1]).setSelected(true);
            }
        }

        coreMdl.getUserCfg().setEditViw(true);
        coreMdl.getUserCfg().setEditWnd(true);

        mainPtn.setEditVisible(true);
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
