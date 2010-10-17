/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.view;

import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public class EditVisibleAction extends APwdAction
{

    public EditVisibleAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isEditVisible();
        mainPtn.setEditVisible(b);
        if (b)
        {
            mainPtn.showPropInfo();
        }
        mainPtn.pack();

        String cmd = e.getActionCommand();
        if (Char.isValidate(cmd))
        {
            javax.swing.AbstractButton button;
            for (String tmp : cmd.split(","))
            {
                button = mainPtn.getMenuPtn().getButton(tmp);
                if (button != null)
                {
                    button.setEnabled(b);
                }
            }
        }
    }

    @Override
    public void doInit(Object object)
    {
        setSelected(coreMdl.getUserCfg().isEditVisible());
    }

    @Override
    public void reInit(Object object)
    {
    }
}
