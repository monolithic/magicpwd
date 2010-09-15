/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.edit;

import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MainPtn;

/**
 *
 * @author Amon
 */
public class AppendKeysAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private UserMdl coreMdl;

    public AppendKeysAction(MainPtn mainPtn, UserMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        mainPtn.appendBean(ConsDat.INDX_PWDS);
    }
}
