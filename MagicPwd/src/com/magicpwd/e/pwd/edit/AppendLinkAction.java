/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.edit;

import com.magicpwd.$a.APwdAction;
import com.magicpwd._cons.ConsDat;

/**
 *
 * @author Amon
 */
public class AppendLinkAction extends APwdAction
{

    public AppendLinkAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.appendBean(ConsDat.INDX_LINK);
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
