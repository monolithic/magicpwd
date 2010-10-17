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
public class MenuVisibleAction extends AMpwdAction
{

    public MenuVisibleAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isMenuVisible();
        mainPtn.setMenuVisible(b);
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
                    button.setSelected(b);
                }
            }
        }
    }

    @Override
    public void doInit(Object object)
    {
        setSelected(coreMdl.getUserCfg().isMenuVisible());
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
        button.setSelected(isSelected());
    }
}
