/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

/**
 *
 * @author Administrator
 */
public class InfoVisableAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isInfoViw();
        mainInfo.setVisible(b);
        this.pack();

        mainMenu.setViewInfoSelected(b);
        coreMdl.getUserCfg().setInfoViw(b);
    }
}
