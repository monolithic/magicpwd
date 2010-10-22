/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.AmonFF;

/**
 *
 * @author Amon
 */
public class Bean
{

    private static javax.swing.ImageIcon bi_NoneIcon;
    private static java.util.Map<Integer, java.awt.image.BufferedImage> mp_LogoIcon;
    private static java.util.Map<String, javax.swing.Icon> mp_DataIcon;
    private static java.util.Map<String, javax.swing.Icon> mp_SkinIcon;

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

    public static void closeReader(java.io.Reader reader)
    {
        if (reader != null)
        {
            try
            {
                reader.close();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
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

    public static void closeStream(java.io.OutputStream stream)
    {
        if (stream != null)
        {
            try
            {
                stream.flush();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }

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

    public static javax.swing.Icon getDataIcon(String hash)
    {
        if (!Char.isValidateHash(hash))
        {
            return getNone();
        }
        if (mp_DataIcon == null)
        {
            mp_DataIcon = new java.util.HashMap<String, javax.swing.Icon>();
        }
        if (!mp_DataIcon.containsKey(hash))
        {
            mp_DataIcon.put(hash, new javax.swing.ImageIcon(Char.format("{0}/{1}/{2}.png", ConsEnv.DIR_DAT, ConsEnv.DIR_ICO, hash)));
        }
        return mp_DataIcon.get(hash);
    }

    public static void setDataIcon(String hash, javax.swing.Icon icon)
    {
        if (!Char.isValidateHash(hash))
        {
            return;
        }
        if (mp_DataIcon == null)
        {
            mp_DataIcon = new java.util.HashMap<String, javax.swing.Icon>();
        }
        mp_DataIcon.put(hash, icon);
    }

    public static synchronized javax.swing.ImageIcon getNone()
    {
        if (bi_NoneIcon == null)
        {
            bi_NoneIcon = new javax.swing.ImageIcon(createNone(16));
        }
        return bi_NoneIcon;
    }

    public static synchronized java.awt.image.BufferedImage getLogo(int size)
    {
        if (mp_LogoIcon == null)
        {
            mp_LogoIcon = new java.util.HashMap<Integer, java.awt.image.BufferedImage>();
        }

        java.awt.image.BufferedImage logo = mp_LogoIcon.get(size);
        if (logo == null)
        {
            try
            {
                java.io.InputStream stream = Util.class.getResourceAsStream(ConsEnv.ICON_PATH + Char.lPad(Integer.toHexString(size), 4, '0') + ".png");
                logo = javax.imageio.ImageIO.read(stream);
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

    public static javax.swing.Icon getIcon(String name)
    {
        return mp_SkinIcon != null ? mp_SkinIcon.get(name) : getNone();
    }

    public static boolean readIcon(java.io.InputStream stream, String prefix)
    {
        if (mp_SkinIcon == null)
        {
            mp_SkinIcon = new java.util.HashMap<String, javax.swing.Icon>();
        }

        try
        {
            java.awt.image.BufferedImage bufImg = javax.imageio.ImageIO.read(stream);

            int w = bufImg.getWidth();
            int h = bufImg.getHeight();
            for (int i = 0, j = 0; j < w; i += 1)
            {
                mp_SkinIcon.put(prefix + i, new javax.swing.ImageIcon(bufImg.getSubimage(j, 0, h, h)));
                j += h;
            }
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            closeStream(stream);
        }
    }

    public static javax.swing.ImageIcon readIcon(String path)
    {
        if (!Char.isValidate(path))
        {
            return null;
        }

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

    private static java.awt.image.BufferedImage createNone(int size)
    {
        java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        return bi;
    }

    private static java.awt.image.BufferedImage createLogo(int size)
    {
        java.awt.image.BufferedImage bi = createNone(size);
        return bi;
    }

    public static void loadLnF(UserMdl userMdl)
    {
        boolean deco = ConsCfg.DEF_TRUE.equalsIgnoreCase(userMdl.getCfg(ConsCfg.CFG_SKIN_DECO, ConsCfg.DEF_FALSE));
        javax.swing.JFrame.setDefaultLookAndFeelDecorated(deco);
        javax.swing.JDialog.setDefaultLookAndFeelDecorated(deco);

        String type = userMdl.getCfg(ConsCfg.CFG_SKIN_TYPE, "user");
        String name = userMdl.getCfg(ConsCfg.CFG_SKIN_NAME, "").trim();
        if (!Char.isValidate(name))
        {
            name = ConsCfg.DEF_SKIN_SYS;
            type = "java";
        }

        if ("java".equals(type))
        {
            // 系统默认界面
            if (ConsCfg.DEF_SKIN_DEF.equals(name))
            {
                return;
            }

            // 操作系统界面
            if (ConsCfg.DEF_SKIN_SYS.equalsIgnoreCase(name))
            {
                name = javax.swing.UIManager.getSystemLookAndFeelClassName();
            }

            // 使用界面
            try
            {
                javax.swing.UIManager.setLookAndFeel(name);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
            return;
        }

        String look = userMdl.getCfg(ConsCfg.CFG_SKIN_LOOK, "");
        if (!Char.isValidate(look))
        {
            return;
        }

        if ("synth".equals(type))
        {
            if (!Char.isValidate(look))
            {
                return;
            }

            // 判断目录是否存在
            java.io.File file = new java.io.File("skin/look/" + look, name);
            if (!file.exists() || !file.canRead() || !file.isDirectory())
            {
                return;
            }

            try
            {
                // 加载界面
                javax.swing.plaf.synth.SynthLookAndFeel synth = new javax.swing.plaf.synth.SynthLookAndFeel();
                synth.load(file.toURI().toURL());
                // 使用界面
                javax.swing.UIManager.setLookAndFeel(synth);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
            return;
        }

        if (!"user".equals(type))
        {
            return;
        }

        java.io.File file = new java.io.File("skin/look", look);
        if (!file.exists() || !file.canRead() || !file.isDirectory())
        {
            return;
        }

        java.io.File jars[] = file.listFiles(new AmonFF(".+\\.jar$", true));
        if (jars == null || jars.length < 1)
        {
            return;
        }

        try
        {
            // 加载扩展库
            loadJar(jars);

            // 使用界面
            javax.swing.UIManager.setLookAndFeel(name);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    public static void loadJar(java.io.File... files) throws Exception
    {
        if (files == null || files.length < 1)
        {
            return;
        }

        java.net.URLClassLoader loader = (java.net.URLClassLoader) ClassLoader.getSystemClassLoader();
        java.lang.reflect.Method method = java.net.URLClassLoader.class.getDeclaredMethod("addURL", java.net.URL.class);
        method.setAccessible(true);

        for (java.io.File file : files)
        {
            method.invoke(loader, file.toURI().toURL());
        }
    }
}
