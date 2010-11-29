package test;

import com.magicpwd._comp.WCubeBox;

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
        WCubeBox cb = new WCubeBox();
        cb.initData();
        this.getContentPane().add(cb);
        cb.start();

        this.setSize(400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Test();
    }
}
