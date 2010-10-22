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
public class EditIsolateAction extends AMpwdAction
{

    public EditIsolateAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !mainPtn.getUserMdl().isEditIsolate();
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
        setEnabled(mainPtn.getUserMdl().isEditVisible());
        setSelected(mainPtn.getUserMdl().isEditIsolate());
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
        button.setEnabled(isEnabled());
        button.setSelected(isSelected());
    }
}
