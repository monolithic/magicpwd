/**
 * 系统共用工具类
 * CopyRight: MagicPwd.com
 * Homepage:http://magicpwd.com/
 * Project:http://magicpwd.dev.java.net/
 * Email:Amon@amonsoft.cn
 */
package com.magicpwd._util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import com.magicpwd.r.AmonFF;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd.c.MenuEvt;
import com.magicpwd.m.UserMdl;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @author Amon
 * 
 */
public final class Util
{

    public static final File icoPath = new File(ConsEnv.DIR_DAT, ConsEnv.DIR_ICO);
    private static ImageIcon bi_NoneIcon;
    private static Map<Integer, BufferedImage> mp_LogoIcon;
    private static Map<Integer, ImageIcon> mp_IcoList;
    private static Map<String, ImageIcon> mp_ImgList;

    /**
     * 长整形数据加密
     * @param l
     * @param bigCase
     * @return
     */
    public static String encodeLong(long l, boolean bigCase)
    {
        // 不同进制使用的数值表示字符
        char[] digits = bigCase ? ConsEnv.UPPER_DIGEST : ConsEnv.LOWER_DIGEST;

        // 缓冲字符数组
        char[] buf = new char[16];
        int charPos = 16;
        do
        {
            buf[--charPos] = digits[(int) (l & 0xF)];
            l >>>= 4;
        }
        while (charPos > 0);

        // 返回符合用户要求格式的数组字符串
        return new String(buf);
    }

    public static final boolean isValidate(String t)
    {
        return t != null ? t.trim().length() > 0 : false;
    }

    public static final boolean isValidate(String t, int size)
    {
        return t != null ? t.trim().length() == size : false;
    }

    public static final boolean isValidate(String t, int min, int max)
    {
        if (t == null)
        {
            return false;
        }
        int l = t.trim().length();
        return (l >= min && l <= max);
    }

    public static final boolean isValidateHash(String text)
    {
        if (text == null)
        {
            return false;
        }
        return Pattern.compile("^[\\w]{16}$", Pattern.CASE_INSENSITIVE).matcher(text).matches();
    }

    public static final boolean isValidateInteger(String t)
    {
        return t != null ? Pattern.compile("^[+-]?\\d+$", Pattern.CASE_INSENSITIVE).matcher(t).matches() : false;
    }

    /**
     * 验证给定字符串是否为有效电子邮件
     * @param mail
     * @return
     */
    public static final boolean isValidateEmail(String mail)
    {
        if (mail == null)
        {
            return false;
        }
        return Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+", Pattern.CASE_INSENSITIVE).matcher(mail).matches();
    }

    public static ImageIcon getIcon(int name)
    {
        if (mp_IcoList == null)
        {
            mp_IcoList = new HashMap<Integer, ImageIcon>();
            synchronized (icoPath)
            {
                try
                {
                    java.io.InputStream stream = Util.class.getResourceAsStream(ConsEnv.ICON_PATH + "icon.png");
                    BufferedImage bufImg = ImageIO.read(stream);
                    stream.close();

                    for (int i = 0, j = 0; i < ConsEnv.ICON_SIZE; i += 1)
                    {
                        mp_IcoList.put(i, new ImageIcon(bufImg.getSubimage(j, 0, 16, 16)));
                        j += 16;
                    }
                }
                catch (Exception exp)
                {
                    Logs.exception(exp);
                }
            }
        }
        return mp_IcoList.get(name);
    }

    public static ImageIcon getIcon(String name)
    {
        if (!isValidateHash(name))
        {
            return getNone();
        }
        if (mp_ImgList == null)
        {
            mp_ImgList = new HashMap<String, ImageIcon>();
        }
        if (!mp_ImgList.containsKey(name))
        {
            synchronized (icoPath)
            {
                BufferedImage image = getImage(new File(icoPath, name + ".png"));
                mp_ImgList.put(name, image != null ? new ImageIcon(image) : bi_NoneIcon);
            }
        }
        return mp_ImgList.get(name);
    }

    public static void setIcon(String name, ImageIcon icon)
    {
        if (!isValidateHash(name))
        {
            return;
        }
        if (mp_ImgList == null)
        {
            mp_ImgList = new HashMap<String, ImageIcon>();
        }
        mp_ImgList.put(name, icon);
    }

    public static ImageIcon getIcon(File file)
    {
        if (mp_ImgList == null)
        {
            mp_ImgList = new HashMap<String, ImageIcon>();
        }
        String name = file.getName().split("\\.")[0];
        if (!mp_ImgList.containsKey(name))
        {
            synchronized (icoPath)
            {
                BufferedImage image = getImage(file);
                mp_ImgList.put(name, image != null ? new ImageIcon(image) : bi_NoneIcon);
            }
        }
        return mp_ImgList.get(name);
    }

    public static BufferedImage getImage(String name)
    {
        return getImage(new File(name));
    }

    public static BufferedImage getImage(File file)
    {
        if (file == null || !file.exists() || !file.isFile() || !file.canRead())
        {
            return null;
        }

        try
        {
            java.io.InputStream stream = new java.io.FileInputStream(file);
            BufferedImage img = ImageIO.read(stream);
            stream.close();
            return img;
        }
        catch (Exception exp)
        {
            Logs.log(file.getName());
            Logs.exception(exp);
            return null;
        }
    }

    public static ImageIcon getNone()
    {
        if (bi_NoneIcon == null)
        {
            bi_NoneIcon = new ImageIcon(createNone(16));
        }
        return bi_NoneIcon;
    }

    public static BufferedImage getLogo(int size)
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
                java.io.InputStream stream = Util.class.getResourceAsStream(ConsEnv.ICON_PATH + Util.lPad(Integer.toHexString(size), 4, '0') + ".png");
                logo = ImageIO.read(stream);
                stream.close();
            }
            catch (Exception exp)
            {
                logo = createLogo(size);
            }
            mp_LogoIcon.put(size, logo);
        }
        return logo;
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

    public static File nextBackupFile(int size) throws Exception
    {
        String dir = UserMdl.getUserCfg().getBackDir();
        File bak = new File(dir);
        if (!bak.isDirectory())
        {
            bak.mkdir();
        }

        File[] list = bak.listFiles(new AmonFF("^amon_[^.]+\\.backup$", false));

        File backup;
        if (list != null)
        {
            int len = list.length;
            if (len == size)
            {
                backup = list[0];
                for (int i = 1; i < len; i += 1)
                {
                    if (backup.lastModified() > list[i].lastModified())
                    {
                        backup = list[i];
                    }
                }
                backup.delete();
            }
            else if (len > size)
            {
                sortFileByLastModified(list);
                len -= size;
                while (len > -1)
                {
                    list[len--].delete();
                }
            }
        }

        backup = new File(dir, format(ConsEnv.FILE_BACK, currTime()));
        backup.createNewFile();
        return backup;
    }

    private static String currTime()
    {
        return new SimpleDateFormat(ConsEnv.FILE_DATE).format(new Date());
    }

    private static void sortFileByLastModified(File[] list)
    {
        File temp;
        for (int i = 0, j = list.length; i < j; i += 1)
        {
            temp = list[i];
            for (int m = i + 1, n = j; m < n; m += 1)
            {
                if (temp.lastModified() > list[m].lastModified())
                {
                    list[i] = list[m];
                    list[m] = temp;
                    temp = list[i];
                }
            }
        }
    }

    public static String format(String src, String... arg)
    {
        if (src != null)
        {
            int i = 0;
            for (String tmp : arg)
            {
                src = src.replace("{" + (i++) + "}", tmp);
            }
        }
        return src;
    }

    public static String lPad(String s, int length, char c)
    {
        if (length <= s.length())
        {
            return s;
        }

        char[] pad = new char[length - s.length()];
        Arrays.fill(pad, c);
        return new String(pad) + s;
    }

    /**
     * 去除左空白
     * 
     * @param s
     * @return
     */
    public static String lTrim(String s)
    {
        return lTrim(s, " ");
    }

    /**
     * @param s
     * @param c
     * @return
     */
    public static String lTrim(String s, String c)
    {
        return (s != null) ? s.replaceAll("^[\\s" + c + "]+", c) : s;
    }

    /**
     * 右填充指定字符，使原字符串达到指定的长度
     * 
     * @param s
     * @param length
     * @param c
     * @return
     */
    public static String rPad(String s, int length, char c)
    {
        if (length <= s.length())
        {
            return s;
        }

        char[] pad = new char[length - s.length()];
        Arrays.fill(pad, c);
        return s + new String(pad);
    }

    /**
     * 去除右空白
     * 
     * @param s
     * @return
     */
    public static String rTrim(String s)
    {
        return (s != null) ? s.replaceAll("[\\s　]+$", "") : s;
    }

    /**
     * 按正常十六进制将字节数组转换为可以显示的字符串数据
     * 
     * @param v
     *            等进行转换的字节数组
     * @param bigCase
     *            返回结果字符串是否使用大写字符，true大写，false小写
     * @return
     */
    public static String bytesToString(byte[] v, boolean bigCase)
    {
        return bytesToString(v, bigCase ? ConsEnv.UPPER_NUMBER : ConsEnv.LOWER_NUMBER);
    }

    /**
     * 指定转换参考码内的可显示字符串数据
     * 
     * @param c
     *            字节转换参考码，其长度不能小于16
     * @param v
     *            待转换的字节数组
     * @return
     */
    public static String bytesToString(byte[] v, char[] c)
    {
        // 转换参考码及字节数组合法性判断
        if (c == null || c.length < 16 || v == null || v.length < 1)
        {
            return "";
        }

        // 缓冲字符串大小判断及创建
        int len = v.length;
        StringBuffer strBuf = new StringBuffer(len << 1);

        // 字节数据转换为可显示字符串数据
        byte tmp;
        for (int i = 0; i < len; ++i)
        {
            tmp = v[i];
            strBuf.append(c[(tmp >>> 4) & 0xF]);
            strBuf.append(c[tmp & 0xF]);
        }

        return strBuf.toString();
    }

    /**
     * 按常规规则进行字符串到字节数组的变换
     * 
     * @param s
     * @return
     */
    public static byte[] stringToBytes(String s, boolean bigCase)
    {
        return stringToBytes(s, bigCase ? ConsEnv.UPPER_NUMBER : ConsEnv.LOWER_NUMBER);
    }

    /**
     * 按指定变换规则进行字符串到字节数组的变换
     * 
     * @param s
     * @param c
     * @return
     */
    public static byte[] stringToBytes(String s, char[] c)
    {
        char[] t = s.toCharArray();
        int i = 0, j = 0, k = t.length;
        byte[] b = new byte[k >>> 1];
        byte p;
        while (i < k)
        {
            p = 0;
            p |= charIndex(t[i++], c) << 4;
            p |= charIndex(t[i++], c);
            b[j++] = p;
        }
        return b;
    }

    private static int charIndex(char a, char[] c)
    {
        int i = 0;
        for (char t : c)
        {
            if (a == t)
            {
                break;
            }
            i += 1;
        }
        return i;
    }

    public static void addHideAction(ActionMap actionMap, InputMap inputMap, final MenuEvt menuEvt)
    {
        actionMap.put(ConsEnv.EVENT_FILE_HIDE, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.fileHideActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK), ConsEnv.EVENT_FILE_HIDE);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK), ConsEnv.EVENT_FILE_HIDE);
    }

    public static void addSortAction(ActionMap actionMap, InputMap inputMap, final MenuEvt menuEvt)
    {
        actionMap.put(ConsEnv.EVENT_EDIT_PREV, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.editPrevActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_PREV);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_PREV);

        actionMap.put(ConsEnv.EVENT_EDIT_NEXT, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.editNextActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_NEXT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_NEXT);

        actionMap.put(ConsEnv.EVENT_EDIT_TEXT, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.editTextActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_TEXT);

        actionMap.put(ConsEnv.EVENT_EDIT_PWDS, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.editPwdsActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_PWDS);

        actionMap.put(ConsEnv.EVENT_EDIT_LINK, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.editLinkActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_LINK);

        actionMap.put(ConsEnv.EVENT_EDIT_MAIL, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.editMailActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_MAIL);

        actionMap.put(ConsEnv.EVENT_EDIT_DATE, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.editDateActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_DATE);

        actionMap.put(ConsEnv.EVENT_EDIT_AREA, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.editAreaActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_AREA);

        actionMap.put(ConsEnv.EVENT_EDIT_FILE, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.editFileActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, InputEvent.CTRL_MASK), ConsEnv.EVENT_EDIT_FILE);
    }

    public static void addFileAction(ActionMap actionMap, InputMap inputMap, final MenuEvt menuEvt)
    {
        actionMap.put(ConsEnv.EVENT_FILE_APND, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.fileApndActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK), ConsEnv.EVENT_FILE_APND);

        actionMap.put(ConsEnv.EVENT_FILE_SAVE, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.fileSaveActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK), ConsEnv.EVENT_FILE_SAVE);

        actionMap.put(ConsEnv.EVENT_FILE_OPEN, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.fileOpenActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK), ConsEnv.EVENT_FILE_OPEN);
    }

    public static void addEditAction(ActionMap actionMap, InputMap inputMap, final MenuEvt menuEvt)
    {
        actionMap.put(ConsEnv.EVENT_VIEW_TOP1, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.viewTop1ActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), ConsEnv.EVENT_VIEW_TOP1);
    }

    public static void addViewAction(ActionMap actionMap, InputMap inputMap, final MenuEvt menuEvt)
    {
        actionMap.put(ConsEnv.EVENT_VIEW_EDIT, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.viewEditActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, InputEvent.ALT_MASK), ConsEnv.EVENT_VIEW_EDIT);

        actionMap.put(ConsEnv.EVENT_VIEW_SIDE, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.viewSideActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, InputEvent.ALT_MASK), ConsEnv.EVENT_VIEW_SIDE);

        actionMap.put(ConsEnv.EVENT_VIEW_FIND, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.viewFindActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, InputEvent.ALT_MASK), ConsEnv.EVENT_VIEW_FIND);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK), ConsEnv.EVENT_VIEW_FIND);

        actionMap.put(ConsEnv.EVENT_VIEW_MENU, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.viewMenuActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.ALT_MASK), ConsEnv.EVENT_VIEW_MENU);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK), ConsEnv.EVENT_VIEW_MENU);

        actionMap.put(ConsEnv.EVENT_VIEW_TOOL, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.viewToolActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, InputEvent.ALT_MASK), ConsEnv.EVENT_VIEW_TOOL);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK), ConsEnv.EVENT_VIEW_TOOL);

        actionMap.put(ConsEnv.EVENT_VIEW_INFO, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.viewInfoActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, InputEvent.ALT_MASK), ConsEnv.EVENT_VIEW_INFO);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK), ConsEnv.EVENT_VIEW_INFO);
    }

    public static void addDataAction(ActionMap actionMap, InputMap inputMap, final MenuEvt menuEvt)
    {
        actionMap.put(ConsEnv.EVENT_DATA_SKEY, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuEvt.helpSKeyActionPerformed(evt);
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), ConsEnv.EVENT_DATA_SKEY);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK), ConsEnv.EVENT_DATA_SKEY);
    }

    public static void removeAction(InputMap inputMap)
    {
        inputMap.remove(KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.ALT_MASK));
    }

    public static char[] nextRandomKey(char[] sets, int size, boolean unRepeat) throws Exception
    {
        if (sets == null)
        {
            throw new Exception("随机口令生成异常：口令字符空间不能为空！");
        }
        if (unRepeat && sets.length < size)
        {
            throw new Exception("随机口令生成异常：口令长度 " + size + " 大于口令字符空间长度 " + sets.length + " ，无法正确生成不可重复口令！");
        }

        char[] r = new char[size];
        Random random = new Random();
        for (int i = 0, l = sets.length, t; i < size; i++)
        {
            t = random.nextInt(l);
            r[i] = sets[t];
            if (unRepeat)
            {
                l -= 1;
                sets[t] = sets[l];
            }
        }

        return r;
    }

    /**
     * 向系统剪贴板添加数据
     * 
     * @param text
     */
    public static void setClipboardContents(String text)
    {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    private static Timer timer;
    private static int curStep;

    public static void setClipboardContents(String text, final int maxDelay)
    {
        setClipboardContents(text);

        if (timer == null)
        {
            timer = new Timer(100, new AbstractAction()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    lookSystemClipboard(maxDelay);
                }
            });
        }
        curStep = 0;
        if (!timer.isRunning())
        {
            timer.start();
        }
    }

    private static void lookSystemClipboard(int maxDelay)
    {
        if (curStep < maxDelay)
        {
            curStep += 1;
        }
        else
        {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(null), null);
            timer.stop();
        }
    }

    public static Calendar stringToDate(String datetime, char datesp, char timesp, char dtsp) throws Exception
    {
        // 若指定日期时间字符串为空，则直接返回当前时间
        if (!Util.isValidate(datetime))
        {
            return Calendar.getInstance();
        }

        // 日期时间字符串分隔
        int dtspIndx = datetime.indexOf(dtsp);
        String date = datetime;
        String time = "";
        if (dtspIndx >= 0 && dtspIndx < datetime.length())
        {
            date = datetime.substring(0, dtspIndx);
            time = datetime.substring(dtspIndx + 1);
        }

        // 日期对象
        Calendar cal = Calendar.getInstance();

        // 日期信息解析
        if (Util.isValidate(date))
        {
            // 读取日期分隔符在日期字符串中位置信息
            int f = date.indexOf(datesp);
            int e = date.lastIndexOf(datesp);

            String y = null;// 年份
            String m = null;// 月份
            String d = null;// 日期
            // 没有日期分隔符号的情况下，日期字符串均按年份处理
            if (f < 0)
            {
                y = date;
            }
            // 存在日期分隔符号的情况下的处理
            else if (f >= 0)
            {
                // 只存在一个日期分隔符号的情况下，日期字符串按年月格式处理
                if (f == e)
                {
                    y = date.substring(0, f);
                    m = date.substring(e + 1);
                }
                // 存在两个日期分隔符号的情况下，日期字符串按年月日格式处理
                else
                {
                    y = date.substring(0, f);
                    m = date.substring(f + 1, e);
                    d = date.substring(e + 1);
                }
            }

            // 年份信息读取
            if (Util.isValidate(y))
            {
                cal.set(Calendar.YEAR, Integer.parseInt(y));
            }

            // 月份信息读取
            if (Util.isValidate(m))
            {
                cal.set(Calendar.MONTH, Integer.parseInt(m) - 1);
            }

            // 日期信息读取
            if (Util.isValidate(d))
            {
                cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d));
            }
        }

        // 时间信息解析
        if (Util.isValidate(time))
        {
            // 读取日期分隔符在日期字符串中位置信息
            int f = time.indexOf(timesp);
            int e = time.lastIndexOf(timesp);

            String h = null;// 小时
            String m = null;// 分钟
            String s = null;// 秒钟
            // 没有时间分隔符号的情况下，时间字符串均按小时处理
            if (f < 0)
            {
                h = time;
            }
            // 存在日期分隔符号的情况下的处理
            else if (f >= 0)
            {
                // 只存在一个日期分隔符号的情况下，日期字符串按年月格式处理
                if (f == e)
                {
                    h = time.substring(0, f);
                    m = time.substring(e + 1);
                }
                // 存在两个日期分隔符号的情况下，日期字符串按年月日格式处理
                else
                {
                    h = time.substring(0, f);
                    m = time.substring(f + 1, e);
                    s = time.substring(e + 1);
                }
            }

            // 年份信息读取
            if (Util.isValidate(h))
            {
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h));
            }

            // 月份信息读取
            if (Util.isValidate(m))
            {
                cal.set(Calendar.MINUTE, Integer.parseInt(m));
            }

            // 日期信息读取
            if (Util.isValidate(s))
            {
                cal.set(Calendar.SECOND, Integer.parseInt(s));
            }
        }

        return cal;
    }

    public static void centerForm(java.awt.Window form, java.awt.Window root)
    {
        Dimension d = (root != null ? root.getSize() : java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        Dimension s = form.getSize();
        form.setLocation((d.width - s.width) >> 1, (d.height - s.height) >> 1);
        form.setLocationRelativeTo(root);
    }

    public static void scrollToVisible(JTable table, int rowIndex, int vColIndex, boolean center)
    {
        if (!(table.getParent() instanceof JViewport))
        {
            return;
        }

        JViewport viewport = (JViewport) table.getParent();
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
        Rectangle viewRect = viewport.getViewRect();
        rect.setLocation(rect.x - viewRect.x, rect.y - viewRect.y);

        if (center)
        {
            int centerX = (viewRect.width - rect.width) >> 1;
            int centerY = (viewRect.height - rect.height) >> 1;

            if (rect.x < centerX)
            {
                centerX = -centerX;
            }
            if (rect.y < centerY)
            {
                centerY = -centerY;
            }
            rect.translate(centerX, centerY);
        }

        viewport.scrollRectToVisible(rect);
    }

    public static char[] nextRandomKey(String sets, int size, boolean unRepeat) throws Exception
    {
        if (sets == null)
        {
            throw new Exception("随机口令生成异常：口令字符空间不能为空！");
        }
        return nextRandomKey(sets.toCharArray(), size, unRepeat);
    }

    public static boolean checkUpdate(String sid, String ver) throws Exception
    {
        if (sid == null)
        {
            throw new IOException("未知软件标记信息！");
        }
        if (ver == null)
        {
            throw new IOException("未知软件版本信息！");
        }

        ver = ver.toUpperCase();
        if (ver.charAt(0) != 'V')
        {
            ver = 'V' + ver;
        }

        // 属性读取
        Document document = new SAXReader().read(new URL(ConsEnv.HOMEPAGE + "soft/soft0001.ashx?sid=" + sid));
        Node node = document.selectSingleNode("/amonsoft/version");
        if (node == null)
        {
            throw new Exception("读取软件版本信息出错！");
        }

        return ver.compareToIgnoreCase(node.getText()) < 0;
    }

    /**
     * 用户输入文本转换为数据库可接受文本
     * @param text
     * @return
     */
    public static String text2DB(String text)
    {
        return text != null ? text.replace("\\", "\\\\").replace("'", "''") : "";
    }

    public static String db2Text(String text)
    {
        return "";
    }
}
