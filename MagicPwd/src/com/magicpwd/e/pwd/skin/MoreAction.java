/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.skin;

import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd._util.Desk;

/**
 *
 * @author Administrator
 */
public class MoreAction extends APwdAction
{

    public MoreAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        Desk.browse(e.getActionCommand());
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
