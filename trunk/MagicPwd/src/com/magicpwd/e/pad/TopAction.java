/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pad;

import com.magicpwd.m.UserCfg;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pad.MiniPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Administrator
 */
public class TopAction extends javax.swing.AbstractAction implements IPadAction
{

    private MiniPtn miniPtn;
    private CoreMdl coreMdl;

    public TopAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        UserCfg cfg = coreMdl.getUserCfg();
        boolean b = !cfg.isViewTop();
        TrayPtn.getCurrForm().setAlwaysOnTop(b);
        cfg.setViewTop(b);
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

    @Override
    public void doUpdate(Object object)
    {
    }
}
