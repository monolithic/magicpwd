package test;

import com.magicpwd._comp.ScreenKB;
import com.magicpwd._util.Logs;
import com.magicpwd.v.MenuPtn;
import javax.swing.JFrame;

public class Main
{

    public static void main(String args[])
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        MenuPtn ptn = new MenuPtn(null, null);
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
        ScreenKB skb = new ScreenKB();
        skb.initView();
        skb.initLang();
        frame.getContentPane().add(skb);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
