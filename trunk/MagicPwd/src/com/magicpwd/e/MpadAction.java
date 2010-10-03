/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author aven
 */
public class MpadAction extends javax.swing.AbstractAction
{

    public MpadAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        TrayPtn.getInstance().showViewPtn(ConsEnv.VIEW_MINI);
    }
}
