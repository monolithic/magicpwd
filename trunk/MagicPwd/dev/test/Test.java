package test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test
{

    public static void main(String[] args)
    {
        try
        {
            System.getProperties().storeToXML(new FileOutputStream("a.txt"), "");
        }
        catch (IOException ex)
        {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
