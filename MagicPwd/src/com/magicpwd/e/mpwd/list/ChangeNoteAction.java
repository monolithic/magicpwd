/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.list;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._comn.Keys;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public class ChangeNoteAction extends AMpwdAction
{

    public ChangeNoteAction()
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
            ((Keys) obj).setP30F0103(val);
        }
        mainPtn.changeNote(val);
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
