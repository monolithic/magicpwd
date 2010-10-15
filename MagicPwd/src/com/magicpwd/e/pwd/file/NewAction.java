/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.file;

import com.magicpwd.$a.APwdAction;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public class NewAction extends APwdAction
{

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

        coreMdl.getUserCfg().setEditVisible(true);
        coreMdl.getUserCfg().setEditIsolate(true);

        mainPtn.setEditVisible(true);
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
