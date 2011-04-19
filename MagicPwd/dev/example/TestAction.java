package example;

import com.magicpwd.__a.AAction;
import com.magicpwd.x.mgtd.MgtdDlg;

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
        MgtdDlg dlg = new MgtdDlg(null, false);
        dlg.initView();
        dlg.initLang();
        dlg.initData(null);
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
