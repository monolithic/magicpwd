/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__a.mpwd;

import com.magicpwd.__a.AAction;
import com.magicpwd.__i.mpwd.IMpwdAction;
import com.magicpwd.v.mpwd.MainPtn;

/**
 *
 * @author Amon
 */
public abstract class AMpwdAction extends AAction implements IMpwdAction
{

    protected MainPtn mainPtn;

    @Override
    public void setMainPtn(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }
}
