/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test
{

    public static void main(String[] args)
    {
        FileOutputStream fos = null;
        try
        {
            Properties p = new Properties();
            p.setProperty("k1", "v1");
            p.setProperty("k2", "v2");
            fos = new FileOutputStream("D:\\123.xml");
            p.storeToXML(fos, "");
        } catch (IOException ex)
        {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                fos.close();
            } catch (IOException ex)
            {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
