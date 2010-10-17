/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__a.mpad;

import com.magicpwd.__i.mpad.IPadAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.mpad.MiniPtn;

/**
 *
 * @author Amon
 */
public abstract class APadAction extends javax.swing.AbstractAction implements IPadAction
{

    protected MiniPtn miniPtn;
    protected CoreMdl coreMdl;

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
    public boolean isVisible()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSelected()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
