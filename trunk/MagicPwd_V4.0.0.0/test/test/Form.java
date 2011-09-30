package test;

import com.magicpwd._comp.ScreenKB;

public class Form extends javax.swing.JFrame
{

    public Form()
    {
        ScreenKB p = new ScreenKB();
        p.initView();
        p.initLang();

        this.getContentPane().add(p);
        this.pack();
        this.setTitle("Demo");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String args[])
    {
        try
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception exp)
        {
        }
        new Form();
    }
}
