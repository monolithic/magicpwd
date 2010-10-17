/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.view;

import com.magicpwd.__a.APwdAction;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public class EditIsolateAction extends APwdAction
{

    public EditIsolateAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isEditIsolate();
        mainPtn.setEditIsolate(b);
        mainPtn.pack();

        String cmd = e.getActionCommand();
        if (Char.isValidate(cmd))
        {
            javax.swing.AbstractButton button = mainPtn.getMenuPtn().getButton(cmd);
            if (button != null)
            {
                button.setSelected(b);
            }
        }
    }

    @Override
    public void doInit(Object object)
    {
        setEnabled(coreMdl.getUserCfg().isEditVisible());
        setSelected(coreMdl.getUserCfg().isEditIsolate());
    }

    @Override
    public void reInit(Object object)
    {
    }
}
