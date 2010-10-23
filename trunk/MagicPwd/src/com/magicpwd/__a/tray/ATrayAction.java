/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__a.tray;

import com.magicpwd.__a.AAction;
import com.magicpwd.__i.tray.ITrayAction;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public abstract class ATrayAction extends AAction implements ITrayAction
{

    protected TrayPtn trayPtn;

    @Override
    public void setTrayPtn(TrayPtn trayPtn)
    {
        this.trayPtn = trayPtn;
    }
}
