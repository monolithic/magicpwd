package example;

import com.magicpwd.__a.AAction;

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
        javax.swing.JOptionPane.showMessageDialog(trayPtn.getCurrPtn(), e.getActionCommand());
    }

    @Override
    public void doInit(String value)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button, String value)
    {
    }
}
