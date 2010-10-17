/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e;

import com.magicpwd.__a.AAction;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author aven
 */
public class MpayAction extends AAction
{

    public MpayAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        TrayPtn.getInstance().showViewPtn(ConsEnv.VIEW_NORM);
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }
}
