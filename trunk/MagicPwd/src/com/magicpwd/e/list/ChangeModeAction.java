/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.list;

/**
 *
 * @author Administrator
 */
public class ChangeModeAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        String command = evt.getActionCommand();
        int val = java.util.regex.Pattern.matches("^[+-]?\\d+$", command) ? Integer.parseInt(command) : 0;

        Object obj = ls_GuidList.getSelectedValue();
        if (obj instanceof Keys)
        {
            ((Keys) obj).setP30F0102(val);
        }
        coreMdl.getGridMdl().setKeysMode(val);
    }
}
