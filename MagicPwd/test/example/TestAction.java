/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import com.magicpwd.__i.mpwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.TrayPtn;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Administrator
 */
public class TestAction extends javax.swing.AbstractAction implements IPwdAction
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
    public void setMainPtn(MainPtn mainPtn)
    {
    }

    @Override
    public void setCoreMdl(CoreMdl coreMdl)
    {
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(Object object)
    {
    }

    @Override
    public boolean isVisible()
    {
        return true;
    }

    @Override
    public boolean isSelected()
    {
        return true;
    }
}
