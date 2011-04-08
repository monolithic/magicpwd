package test;

public class Form extends javax.swing.JFrame
{

    public Form()
    {
        javax.swing.JPanel p = new javax.swing.JPanel();

        this.getContentPane().add(p);
        this.pack();
        this.setTitle("Demo");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println(p.getLayout());
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
