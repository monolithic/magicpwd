/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Administrator
 */
public class TestAction extends javax.swing.AbstractAction
{

    public TestAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.JOptionPane.showMessageDialog(TrayPtn.getCurrForm(), e.getActionCommand());
    }
}
