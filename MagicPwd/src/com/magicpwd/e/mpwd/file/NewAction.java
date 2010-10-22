/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.file;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public class NewAction extends AMpwdAction
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

        if (mainPtn.getUserMdl().isEditVisible())
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

        mainPtn.getUserMdl().setEditVisible(true);
        mainPtn.getUserMdl().setEditIsolate(true);

        mainPtn.setEditVisible(true);
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
