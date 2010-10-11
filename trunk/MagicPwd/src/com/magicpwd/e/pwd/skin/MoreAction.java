/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.skin;

import com.magicpwd._util.Desk;
import java.awt.event.ActionEvent;

/**
 *
 * @author Administrator
 */
public class MoreAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Desk.browse(e.getActionCommand());
    }
}
