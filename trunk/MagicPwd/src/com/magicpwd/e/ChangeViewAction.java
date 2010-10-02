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
public class ChangeViewAction extends javax.swing.AbstractAction
{

    private TrayPtn trayPtn;

    public ChangeViewAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        trayPtn.changeView(true ? "guid" : "icon");
    }
}
