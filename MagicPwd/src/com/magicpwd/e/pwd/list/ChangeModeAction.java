/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.list;

import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd._comn.Keys;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public class ChangeModeAction extends APwdAction
{

    public ChangeModeAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        String command = e.getActionCommand();
        int val = Char.isValidateInteger(command) ? Integer.parseInt(command) : 0;

        Object obj = mainPtn.getSelectedListValue();
        if (obj instanceof Keys)
        {
            ((Keys) obj).setP30F0102(val);
        }
        mainPtn.changeMode(val);
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
