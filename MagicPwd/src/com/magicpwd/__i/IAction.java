/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__i;

import com.magicpwd.m.CoreMdl;

/**
 *
 * @author Amon
 */
public interface IAction extends javax.swing.Action
{

    void setCoreMdl(CoreMdl coreMdl);

    void doInit(Object object);

    void reInit(Object object);

    boolean isVisible();

    boolean isSelected();
}
