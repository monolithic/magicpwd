/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.menu;

import com.magicpwd._enum.AppView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Skin;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MenuPtn;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

/**
 *
 * @author Administrator
 */
public class Menu extends javax.swing.AbstractAction
{

    private JFrame frame;
    private JMenuBar bar;
    private UserMdl userMdl;

    public Menu()
    {
    }

    public void init()
    {
        userMdl = new UserMdl(null);
        userMdl.loadCfg("");

        Skin.loadLook("");
        Lang.loadLang(userMdl.getLang());

        MenuPtn ptn = new MenuPtn(null, userMdl);
        try
        {
            ptn.loadData("dat/menu.xml");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

        frame = new JFrame();
        ptn.getMenuBar("mexp", null, frame.getRootPane());
        frame.setJMenuBar(bar);

        JToolBar toolBar = new JToolBar();
        ptn.getToolBar("mexp", toolBar, null, AppView.mexp);
        frame.getContentPane().add(toolBar, BorderLayout.NORTH);

        frame.setSize(new Dimension(400, 300));
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent e)
            {
                userMdl.saveCfg();
                System.exit(0);
            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void main(String args[])
    {
        new Menu().init();
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        bar.setVisible(!bar.isVisible());
    }
}
