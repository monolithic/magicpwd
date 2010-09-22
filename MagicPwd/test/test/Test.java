package test;

import javax.swing.Action;
import javax.swing.JTextArea;

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
        for (Action action : new JTextArea().getActions())
        {
            System.out.println(action.getValue(action.ACCELERATOR_KEY));
            System.out.println(action.getValue(action.NAME));
        }
    }
}
