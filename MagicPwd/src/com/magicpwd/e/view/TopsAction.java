/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

/**
 *
 * @author Administrator
 */
public class TopsAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isViewTop();
        TrayPtn.getCurrForm().setAlwaysOnTop(b);

        coreMdl.getUserCfg().setViewTop(b);
    }
}
