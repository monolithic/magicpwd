/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e;

/**
 *
 * @author Administrator
 */
public class ExitAction extends javax.swing.AbstractAction
{

    public ExitAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.JOptionPane.showMessageDialog(null, e.getActionCommand());
    }
}
