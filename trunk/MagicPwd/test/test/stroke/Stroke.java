/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.stroke;

import com.magicpwd._util.Bean;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 *
 * @author aven
 */
public class Stroke
{

    private JFrame frame;
    private JMenuBar bar;

    public Stroke()
    {
    }

    public void init()
    {
        frame = new JFrame();
        JPanel c = (JPanel) frame.getContentPane();

        bar = new JMenuBar();
        JMenu file = new JMenu("File");
        bar.add(file);

        JMenuItem item = new JMenuItem("Test");
        javax.swing.AbstractAction action = new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                bar.setVisible(!bar.isVisible());
            }
        };
        item.addActionListener(action);
        file.add(item);
        KeyStroke stroke = KeyStroke.getKeyStroke("ctrl Q");
        item.setAccelerator(stroke);
        Bean.registerKeyStrokeAction(c, stroke, action, "q", JComponent.WHEN_IN_FOCUSED_WINDOW);

        item = new JMenuItem("Alert");
        action = new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                JOptionPane.showMessageDialog(frame, "OK");
            }
        };
        item.addActionListener(action);
        file.add(item);
        stroke = KeyStroke.getKeyStroke("ctrl M");
        item.setAccelerator(stroke);
        Bean.registerKeyStrokeAction(c, stroke, action, "m", JComponent.WHEN_IN_FOCUSED_WINDOW);

        bar.setVisible(false);
        frame.setJMenuBar(bar);
        frame.getContentPane().add(new JScrollPane(new JTextArea()));
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        new Stroke().init();
    }
}
