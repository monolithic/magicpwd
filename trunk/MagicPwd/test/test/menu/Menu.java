/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.menu;

import com.magicpwd.MagicPwd;
import com.magicpwd._util.Lang;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MenuPtn;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

/**
 *
 * @author Administrator
 */
public class Menu
{

    public Menu()
    {
        final CoreMdl coreMdl = new CoreMdl();
        coreMdl.preLoad();
        coreMdl.loadCfg();

        MagicPwd.loadLnF(coreMdl.getUserCfg());
        Lang.loadLang(coreMdl.getUserCfg());

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
        frame.setJMenuBar(ptn.getMenuBar("magicpwd"));

        JToolBar bar = new JToolBar();
        bar.add(new JMenuItem("ABC"));
        frame.getContentPane().add(bar, BorderLayout.NORTH);

        frame.setSize(new Dimension(400, 300));
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent e)
            {
                coreMdl.getUserCfg().saveCfg();
                System.exit(0);
            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void main(String args[])
    {
    }
}
