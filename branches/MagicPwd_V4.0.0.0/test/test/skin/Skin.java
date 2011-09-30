/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.skin;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.UIManager;

/**
 *
 * @author Administrator
 */
public class Skin extends JFrame
{

    public Skin()
    {
        JTree tree = new JTree();
        getContentPane().add(tree, BorderLayout.CENTER);

        JButton button = new JButton();
        button.setText("Test");
        getContentPane().add(button, BorderLayout.NORTH);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

        new Skin();
    }
}
