/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

/**
 *
 * @author Administrator
 */
public class MenuVisableAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isMenuViw();
        mainMenu.setVisible(b);
        this.pack();

        mainMenu.setViewMenuSelected(b);
        coreMdl.getUserCfg().setMenuViw(b);
    }
}
