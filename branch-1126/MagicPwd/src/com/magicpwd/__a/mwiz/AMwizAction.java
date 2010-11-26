/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__a.mwiz;

import com.magicpwd.__a.AAction;
import com.magicpwd.__i.mwiz.IMwizAction;
import com.magicpwd.v.mwiz.NormPtn;

/**
 *
 * @author Amon
 */
public abstract class AMwizAction extends AAction implements IMwizAction
{

    protected NormPtn normPtn;

    @Override
    public void setNormPtn(NormPtn normPtn)
    {
        this.normPtn = normPtn;
    }
}
