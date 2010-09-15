/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pad;

import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MiniPtn;

/**
 *
 * @author Administrator
 */
public class DropAction extends javax.swing.AbstractAction
{

    private MiniPtn miniPtn;
    private UserMdl coreMdl;

    public DropAction(MiniPtn miniPtn, UserMdl coreMdl)
    {
        this.miniPtn = miniPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
    }
}
