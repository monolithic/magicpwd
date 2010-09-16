/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.menu;

import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MenuPtn;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Administrator
 */
public class Menu
{

    public static void main(String args[])
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        CoreMdl coreMdl = new CoreMdl();
        coreMdl.preLoad();
        coreMdl.loadUserCfg();
        MenuPtn ptn = new MenuPtn(coreMdl);
        try
        {
            ptn.loadData("dat/menu.xml");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        JFrame frame = new JFrame();
        frame.setJMenuBar(ptn.getMenuBar("template"));
        frame.setSize(new Dimension(400, 300));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
