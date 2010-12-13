/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__i.tray;

import com.magicpwd.__i.IAction;
import com.magicpwd.v.tray.TrayPtn;

/**
 *
 * @author Amon
 */
public interface ITrayAction extends IAction
{

    void setTrayPtn(TrayPtn trayPtn);
}
