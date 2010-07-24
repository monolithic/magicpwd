package test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.magicpwd._util.Jpng;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test
{

    private static Jpng png;
    static boolean run;

    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        JLabel l = new JLabel();
        f.getContentPane().add(l);
        JButton b = new JButton();
        b.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                if (run)
                {
                    png.suspend();
                }
                else
                {
                    png.continve();
                }
                run = !run;
            }
        });
        f.getContentPane().add(BorderLayout.SOUTH, b);
        f.setSize(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        try
        {
            png = new Jpng();
            png.setLabel(l);
            png.readIcons(Test.class.getResourceAsStream("/res/icon/wait.png"), 16, 16);
            png.start();
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
}
