package test;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
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
            ButtonGroup bg = new ButtonGroup();
            bg.setSelected(new JCheckBox().getModel(), true);
            System.out.println(action.getValue(action.ACCELERATOR_KEY));
            System.out.println(action.getValue(action.NAME));
        }
    }
}
