package plan;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.magicpwd._util.Jpng;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Test
{

    private static Jpng png;
    static boolean run;

    public static void main(String[] args)
    {
        ActionListener lis = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("AAAAAAAA");
                System.out.println(e.getSource().toString());
            }
        };

        final JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("FILE");
        bar.add(file);
        JMenuItem item = new JMenuItem("Item");
        file.add(item);
        item.setMnemonic('I');
        item.setAccelerator(KeyStroke.getKeyStroke('A', Event.CTRL_MASK, false));
        item.addActionListener(lis);

        JFrame f = new JFrame();
        f.setJMenuBar(bar);
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

        javax.swing.Action action = new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                bar.setVisible(!bar.isVisible());
            }
        };

        KeyStroke ks = KeyStroke.getKeyStroke('A', Event.CTRL_MASK, false);
        //l.registerKeyboardAction(lis, "amon", ks, JComponent.WHEN_IN_FOCUSED_WINDOW);
        registerKeyStrokeAction(l, ks, action, "amon", JComponent.WHEN_IN_FOCUSED_WINDOW);
        registerKeyStrokeAction(l, KeyStroke.getKeyStroke('B', Event.CTRL_MASK, false), action, "amon", JComponent.WHEN_IN_FOCUSED_WINDOW);
        //((JPanel) f.getContentPane()).registerKeyboardAction(lis, "amon", ks, JComponent.WHEN_IN_FOCUSED_WINDOW);
        //f.getLayeredPane().registerKeyboardAction(lis, "amon", ks, JComponent.WHEN_IN_FOCUSED_WINDOW);
//        f.getRootPane().registerKeyboardAction(lis, "amon", ks, JComponent.WHEN_IN_FOCUSED_WINDOW);

        Container c = l.getParent();
        while (c != null)
        {
            if (c instanceof JComponent)
            {
                JComponent p = (JComponent) c;
                System.out.println(p);
                ActionMap map = p.getActionMap();
                if (map.keys() != null)
                {
                    for (Object obj : map.keys())
                    {
                        System.out.println(obj.toString());
                    }
                }
            }
            c = c.getParent();
        }
    }

    private static void registerKeyStrokeAction(JComponent c, KeyStroke key, Action act, String cmd, int cnd)
    {
        InputMap inputMap = c.getInputMap(cnd);
        if (inputMap != null)
        {
            ActionMap actionMap = c.getActionMap();
            inputMap.put(key, cmd);
            if (actionMap != null)
            {
                actionMap.put(cmd, act);
            }
        }
    }
}
