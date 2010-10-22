/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.view;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public class EditVisibleAction extends AMpwdAction
{

    public EditVisibleAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.isEditVisible();
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
        setSelected(coreMdl.isEditVisible());
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
        button.setSelected(isSelected());
    }
}
