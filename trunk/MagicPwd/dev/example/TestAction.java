/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import com.magicpwd.__a.AAction;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class TestAction extends AAction
{

    public TestAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.JOptionPane.showMessageDialog(TrayPtn.getCurrForm(), e.getActionCommand());
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }
}
