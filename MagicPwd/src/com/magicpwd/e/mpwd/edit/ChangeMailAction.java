/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.edit;

import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd._cons.ConsDat;

/**
 *
 * @author Amon
 */
public class ChangeMailAction extends APwdAction
{

    public ChangeMailAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.changeBean(ConsDat.INDX_MAIL);
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