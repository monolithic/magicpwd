/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e;

import com.magicpwd.v.TrayPtn;

/**
 *
 * @author aven
 */
public class ViewAction extends javax.swing.AbstractAction
{

    private TrayPtn trayPtn;

    public ViewAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        trayPtn.changeView();
    }
}
