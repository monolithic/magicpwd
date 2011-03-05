package test;

import org.javia.arity.Symbols;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aven
 */
public class Test extends javax.swing.JFrame
{

    public Test()
    {
        this.setSize(400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        Symbols symbols = new Symbols();
        try
        {
            symbols.define(symbols.compileWithName("f (x , y)=1"));
            System.out.println(symbols.eval("1+1"));
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
}
