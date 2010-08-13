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

    /**
     * 设置按钮的显示文本
     * @param c
     * @param wText
     * @param isHash
     */
    public static void setText(javax.swing.AbstractButton c, String t)
    {
        int i = -1;
        // 快捷字符替换
        if (t.length() > 0)
        {
            i = t.indexOf('@');
            if (i >= 0)
            {
                t = t.replace("@", "");
                if (t.length() > i)
                {
                    c.setMnemonic(t.charAt(i));
                }
            }
        }

        if (!(i == 0 && t.length() == 1))
        {
            c.setText(t);
            c.setDisplayedMnemonicIndex(i);
        }
    }

    public static void setTips(javax.swing.JComponent c, String t)
    {
        c.setToolTipText(t);
    }

    public static void registerKeyStrokeAction(javax.swing.JComponent component, javax.swing.KeyStroke stroke, javax.swing.Action action, String command, int condition)
    {
        command = (command != null) ? command : ((action != null) ? action.getValue(javax.swing.Action.NAME).toString() : "");
        javax.swing.InputMap inputMap = component.getInputMap(condition);
        if (inputMap != null)
        {
            inputMap.put(stroke, command);
            javax.swing.ActionMap actionMap = component.getActionMap();
            if (actionMap != null)
            {
                actionMap.put(command, action);
            }
        }
    }
}
