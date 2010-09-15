/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

/**
 *
 * @author Administrator
 */
public class FindAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (!coreMdl.getUserCfg().isFindViw())
        {
            mainFind.setVisible(true);
            mainMenu.setViewFindSelected(true);
            coreMdl.getUserCfg().setFindViw(true);
        }
        mainFind.requestFocus();
    }
}
