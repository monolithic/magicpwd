package test;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Form extends javax.swing.JFrame
{

    public Form()
    {
        javax.swing.JPanel p = new javax.swing.JPanel();

        this.getContentPane().add(p);
        this.pack();
        this.setTitle("Demo");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void test()
    {
        this.getContentPane().setLayout(new FlowLayout());

        // input and output file names
        String strInFile = "tmp/c.ico";
        String strOutFile = "output.ico";
        try
        {
            java.io.File inFile = new java.io.File(strInFile);

            java.util.List<java.awt.image.BufferedImage> images = net.sf.image4j.codec.ico.ICODecoder.read(inFile);

            for (int i = 0; i < images.size(); i++)
            {
                java.awt.image.BufferedImage img = images.get(i);
                int bpp = img.getColorModel().getPixelSize();
                int width = img.getWidth();
                int height = img.getHeight();
                System.out.println("    # " + i + ": size=" + width + "x" + height + "; colour depth=" + bpp + " bpp");
                JLabel b = new JLabel();
                b.setIcon(new ImageIcon(img));
                this.getContentPane().add(b);
            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
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
        new Form().test();
    }
}
