package test;

import com.magicpwd._user.SignUp;

public class Form extends javax.swing.JFrame
{

    public Form()
    {
        SignUp signUp = new SignUp(null);
        signUp.initView();
        signUp.initLang();
        signUp.initData();

        this.getContentPane().add(signUp);
        this.pack();
        this.setTitle("Demo");
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
        new Form().setVisible(true);
    }
}
