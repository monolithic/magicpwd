package test;

import com.magicpwd._util.Logs;
import com.magicpwd.v.MenuPtn;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Main
{

    public static void main(String args[])
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        MenuPtn ptn = new MenuPtn(null);
        try
        {
            ptn.loadData("dat/mpay.xml");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        JFrame frame = new JFrame();
        frame.setJMenuBar(ptn.getMenuBar("mpwd", frame.getRootPane()));
        frame.setSize(new Dimension(400, 300));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
