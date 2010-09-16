/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pad;

import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pad.MiniPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Administrator
 */
public class ExitAction extends javax.swing.AbstractAction implements IPadAction
{

    private MiniPtn miniPtn;
    private CoreMdl coreMdl;

    public ExitAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        TrayPtn.endSave();
    }

    @Override
    public void setMiniPtn(MiniPtn miniPtn)
    {
        this.miniPtn = miniPtn;
    }

    @Override
    public void setCoreMdl(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }
}
