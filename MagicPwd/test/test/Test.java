package test;

import java.util.Properties;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aven
 */
public class Test
{

    public static void main(String[] args)
    {
        Properties p = System.getProperties();
        for (String k : p.stringPropertyNames())
        {
            System.out.println(k + ":" + p.getProperty(k));
        }
    }
}
