/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

/**
 *
 * @author Administrator
 */
public class FindVisableAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isFindViw();
        mainFind.setVisible(b);
        this.pack();

        mainMenu.setViewFindSelected(b);
        coreMdl.getUserCfg().setFindViw(b);
    }
}
