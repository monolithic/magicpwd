/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pad;

import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MiniPtn;

/**
 *
 * @author Administrator
 */
public class HideAction extends javax.swing.AbstractAction
{

    private MiniPtn miniPtn;
    private CoreMdl coreMdl;

    public HideAction(MiniPtn miniPtn, CoreMdl coreMdl)
    {
        this.miniPtn = miniPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        miniPtn.hideWindow();
    }
}
