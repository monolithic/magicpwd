/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.$i;

import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author aven
 */
public interface IPwdAction extends java.awt.event.ActionListener
{

    void setMainPtn(MainPtn mainPtn);

    void setCoreMdl(CoreMdl coreMdl);

    void doInit(Object object);

    void reInit(Object object);

    boolean isEnabled();

    boolean isVisible();

    boolean isSelected();
}
