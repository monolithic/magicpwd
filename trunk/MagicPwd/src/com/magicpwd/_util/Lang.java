/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.IcoLabel;
import com.magicpwd._cons.LangRes;
import java.awt.Component;
import java.util.ResourceBundle;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Amon
 */
public class Lang
{

    private static ResourceBundle lang;
    private static String tips;

    static
    {
        try
        {
            lang = ResourceBundle.getBundle("lang.lang");
            tips = lang.getString(LangRes.P30FA208);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        if (tips == null)
        {
            tips = "友情提示";
        }
    }

    private Lang()
    {
    }

    public static String getLang(String key, String def)
    {
        key = key != null ? lang.getString(key) : def;
        return key != null ? key : def;
    }

    /**
     * 设置按钮的显示文本
     * @param c
     * @param wText
     * @param isHash
     */
    public static void setWText(AbstractButton c, String sid, String def)
    {
        Bean.setText(c, getLang(sid, def));
    }

    /**
     * 设置标签的显示文本
     * @param c
     * @param wText
     * @param isHash
     */
    public static void setWText(BtnLabel c, String sid, String def)
    {
        String txt = getLang(sid, def);
        Bean.setText(c, txt);
    }

    public static void setWText(IcoLabel c, String sid, String def)
    {
        String txt = getLang(sid, def);
        int idx = -1;
        // 快捷字符替换
        if (txt.length() > 0)
        {
            idx = txt.indexOf('&');
            if (idx >= 0)
            {
                txt = txt.replace("&", "");
                if (txt.length() > idx)
                {
                    c.setMnemonic(txt.charAt(idx));
                }
            }
        }

        if (!(idx == 0 && txt.length() == 1))
        {
            c.setText(txt);
            c.setDisplayedMnemonicIndex(idx);
        }
    }

    /**
     * 设置文本区域的默认显示信息
     * @param c
     * @param wText
     * @param isHash
     */
    public static void setWText(JTextComponent c, String key, String def)
    {
        c.setText(getLang(key, def));
    }

    /**
     * 设置标签的显示文本
     * @param c
     * @param wText
     * @param isHash
     */
    public static void setWText(JLabel c, String sid, String def)
    {
        String txt = getLang(sid, def);
        Bean.setText(c, txt);
    }

    /**
     * @param c
     * @param wTips
     * @param isHash
     */
    public static void setWTips(JComponent c, String sid, String tip)
    {
        c.setToolTipText(getLang(sid, tip));
    }

    public static void showMesg(Component c, String t, String d, String... z)
    {
        t = getLang(t, d);
        if (z != null)
        {
            t = Char.format(t, z);
        }
        JOptionPane.showMessageDialog(c, t, tips, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int showFirm(Component c, String t, String d, String... z)
    {
        t = getLang(t, d);
        if (z != null)
        {
            t = Char.format(t, z);
        }
        return JOptionPane.showConfirmDialog(c, t, tips, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}
