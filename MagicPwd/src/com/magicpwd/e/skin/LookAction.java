/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.skin;

import com.magicpwd._util.Char;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author aven
 */
public class LookAction extends AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if (!Char.isValidate(command))
        {
            return;
        }
        String type;
        String name;

        if ("default".equals(command))
        {
            type = "default";
            name = javax.swing.UIManager.getCrossPlatformLookAndFeelClassName();
        }
        else if ("system".equals(command))
        {
            type = "system";
            name = javax.swing.UIManager.getSystemLookAndFeelClassName();
        }
        else
        {
            String[] arr = command.split(",");
            if (arr == null || arr.length != 2)
            {
                return;
            }
            type = arr[0];
            name = arr[1];
        }
        JOptionPane.showMessageDialog(null, command);
    }
}
