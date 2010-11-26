/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__i;

import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public interface IAction extends javax.swing.Action
{

    void setTrayPtn(TrayPtn trayPtn);

    void doInit(Object object);

    void reInit(javax.swing.AbstractButton button);

    boolean isVisible();

    boolean isSelected();
}
