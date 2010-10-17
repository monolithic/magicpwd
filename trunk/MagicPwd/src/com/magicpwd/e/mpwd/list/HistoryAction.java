/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.list;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._comn.Keys;

/**
 *
 * @author Amon
 */
public class HistoryAction extends AMpwdAction
{

    public HistoryAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        Object obj = mainPtn.getSelectedListValue();
        if (obj == null || !(obj instanceof Keys))
        {
            return;
        }
        mainPtn.showHistory();
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
