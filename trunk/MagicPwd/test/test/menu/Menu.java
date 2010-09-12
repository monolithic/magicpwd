/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.menu;

import com.magicpwd.v.MenuPtn;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class Menu
{

    public static void main(String args[])
    {
        MenuPtn ptn = new MenuPtn();
        try
        {
            ptn.loadData("dat/menu.xml");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        JFrame frame = new JFrame();
        frame.setJMenuBar(ptn.getMenuBar("magicpwd"));
        frame.setSize(new Dimension(400, 300));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
