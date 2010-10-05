/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd.m.UserCfg;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Amon
 */
public class Bean
{

    private static ImageIcon bi_NoneIcon;
    private static Map<Integer, BufferedImage> mp_LogoIcon;
    private static Map<String, ImageIcon> mp_ImgList;

    public static void setText(BtnLabel c, String t)
    {
        int i = -1;
        // 快捷字符替换
        if (t.length() > 0)
        {
            i = t.indexOf(ConsEnv.CHAR_ALT_KEY);
            if (i >= 0)
            {
                t = t.replace(ConsEnv.CHAR_ALT_KEY, "");
                if (t.length() > i)
                {
                    c.setMnemonic(t.charAt(i));
                }
            }
        }

        if (!(i == 0 && t.length() == 1))
        {
            c.setText(t);
            if (i >= 0)
            {
                c.setText(t);
                c.setDisplayedMnemonicIndex(i);
            }
        }
    }

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
            i = t.indexOf(ConsEnv.CHAR_ALT_KEY);
            if (i >= 0)
            {
                t = t.replace(ConsEnv.CHAR_ALT_KEY, "");
                if (t.length() > i)
                {
                    c.setMnemonic(t.charAt(i));
                }
            }
        }

        if (!(i == 0 && t.length() == 1))
        {
            c.setText(t);
            if (i >= 0)
            {
                c.setDisplayedMnemonicIndex(i);
            }
        }
    }

    public static void setText(javax.swing.JLabel c, String t)
    {
        int i = -1;
        // 快捷字符替换
        if (t.length() > 0)
        {
            i = t.indexOf(ConsEnv.CHAR_ALT_KEY);
            if (i >= 0)
            {
                t = t.replace(ConsEnv.CHAR_ALT_KEY, "");
                if (t.length() > i)
                {
                    c.setDisplayedMnemonic(t.charAt(i));
                }
            }
        }

        if (!(i == 0 && t.length() == 1))
        {
            c.setText(t);
            if (i >= 0)
            {
                c.setDisplayedMnemonicIndex(i);
            }
        }
    }

    public static void setTips(javax.swing.JComponent c, String t)
    {
        if ("".equals(t))
        {
            t = null;
        }
        c.setToolTipText(t);
    }

    public static void registerKeyStrokeAction(javax.swing.JComponent component, javax.swing.KeyStroke stroke, javax.swing.Action action, String command, int condition)
    {
        command = (command != null) ? command : ((action != null) ? action.getValue(javax.swing.Action.NAME) + "" : "");
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

    public static void closeStream(java.io.InputStream stream)
    {
        if (stream != null)
        {
            try
            {
                stream.close();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
    }

    public static ImageIcon getIcon(String name)
    {
        if (!Char.isValidate(name))
        {
            return getNone();
        }
        if (mp_ImgList == null)
        {
            mp_ImgList = new HashMap<String, ImageIcon>();
        }
        return mp_ImgList.get(name);
    }

    public static void setIcon(String name, ImageIcon icon)
    {
        if (!Char.isValidate(name))
        {
            return;
        }
        if (mp_ImgList == null)
        {
            mp_ImgList = new HashMap<String, ImageIcon>();
        }
        mp_ImgList.put(name, icon);
    }

    public static synchronized ImageIcon getNone()
    {
        if (bi_NoneIcon == null)
        {
            bi_NoneIcon = new ImageIcon(createNone(16));
        }
        return bi_NoneIcon;
    }

    public static synchronized BufferedImage getLogo(int size)
    {
        if (mp_LogoIcon == null)
        {
            mp_LogoIcon = new HashMap<Integer, BufferedImage>();
        }

        BufferedImage logo = mp_LogoIcon.get(size);
        if (logo == null)
        {
            try
            {
                java.io.InputStream stream = Util.class.getResourceAsStream(ConsEnv.ICON_PATH + Char.lPad(Integer.toHexString(size), 4, '0') + ".png");
                logo = ImageIO.read(stream);
                stream.close();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                logo = createLogo(size);
            }
            mp_LogoIcon.put(size, logo);
        }
        return logo;
    }

    public static ImageIcon readIcon(UserCfg userCfg, String path)
    {
        if (!Char.isValidate(path))
        {
            return null;
        }

        path = path.replace("%feel%", userCfg.getCfg(ConsCfg.CFG_SKIN_FEEL, "default"));
        java.io.InputStream stream = null;
        try
        {
            stream = File.open4Read(path);
            return new javax.swing.ImageIcon(javax.imageio.ImageIO.read(stream));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return null;
        }
        finally
        {
            closeStream(stream);
        }
    }

    private static BufferedImage createNone(int size)
    {
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        return bi;
    }

    private static BufferedImage createLogo(int size)
    {
        BufferedImage bi = createNone(size);
        return bi;
    }
}
