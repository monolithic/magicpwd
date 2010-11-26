/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__a.mpad;

import com.magicpwd.__a.AAction;
import com.magicpwd.__i.mpad.IMpadAction;
import com.magicpwd.v.mpad.MiniPtn;

/**
 *
 * @author Amon
 */
public abstract class AMpadAction extends AAction implements IMpadAction
{

    protected MiniPtn miniPtn;

    @Override
    public void setMiniPtn(MiniPtn miniPtn)
    {
        this.miniPtn = miniPtn;
    }
}
