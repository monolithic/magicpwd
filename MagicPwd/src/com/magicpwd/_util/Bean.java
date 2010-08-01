/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

/**
 *
 * @author Amon
 */
public class Bean
{

    public static void registerKeyStrokeAction(javax.swing.JComponent component, javax.swing.KeyStroke stroke, javax.swing.Action action, String command, int condition)
    {
        command = (command != null) ? command : ((action != null) ? action.toString() : "");
        component.getInputMap(condition).put(stroke, command);
        component.getActionMap().put(command, action);
    }
}
