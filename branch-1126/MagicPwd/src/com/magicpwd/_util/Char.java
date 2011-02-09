/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

import com.magicpwd._cons.ConsEnv;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author Amon
 */
public class Char
{

    public static String format(String src, String... arg)
    {
        if (src != null)
        {
            for (int i = 0, j = arg.length; i < j; i += 1)
            {
                if (arg[i] != null)
                {
                    src = src.replace("{" + (i) + "}", arg[i]);
                }
            }
        }
        return src;
    }

    public static String decode(String txt, String sCode, String eCode)
    {
        if (txt != null)
        {
            try
            {
                return new String(txt.getBytes(sCode), eCode);
            }
            catch (Exception ex)
            {
                Logs.exception(ex);
            }
        }
        return txt;
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

    public static boolean isValidate(String t)
    {
        return t != null ? t.trim().length() > 0 : false;
    }

    public static boolean isValidate(String t, int size)
    {
        return t != null ? t.trim().length() == size : false;
    }

    public static boolean isValidate(String t, int min, int max)
    {
        if (t == null)
        {
            return false;
        }
        int l = t.trim().length();
        return (l >= min && l <= max);
    }

    public static boolean isValidateDate(String text, boolean formatOnly)
    {
        if (text == null)
        {
            return false;
        }
        if (formatOnly)
        {
            return Pattern.matches("(^[1-9][0-9]{0,3}[-\\/\\._](0[1-9]|1[012])[-\\/\\._](0[1-9]|[12][0-9]|3[01])$)", text);
        }
        return Pattern.matches("((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(10|12|0?[13578])([-\\/\\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(11|0?[469])([-\\/\\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(0?2)([-\\/\\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([3579][26]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$))", text);
    }

    public static boolean isValidateTime(String text)
    {
        if (text == null)
        {
            return false;
        }
        return Pattern.matches("(^([01][0-9]|2[0-3])[:\\.]([0-5][0-9])[:\\.]([0-5][0-9])$)", text);
    }

    public static boolean isValidateDateTime(String text)
    {
        if (text == null)
        {
            return false;
        }
        return Pattern.matches("^[1-9][0-9]{0,3}[-\\/\\._](0[1-9]|1[012])[-\\/\\._](0[1-9]|[12][0-9]|3[01]) ([01][0-9]|2[0-3])[:\\.]([0-5][0-9])[:\\.]([0-5][0-9])$", text);
    }

    public static boolean isValidateHash(String text)
    {
        if (text == null)
        {
            return false;
        }
        return Pattern.compile("^[\\w]{16}$", Pattern.CASE_INSENSITIVE).matcher(text).matches();
    }

    /**
     * 验证给定字符串是否为有效电子邮件
     * @param mail
     * @return
     */
    public static boolean isValidateMail(String mail)
    {
        if (mail == null)
        {
            return false;
        }
        return Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+", Pattern.CASE_INSENSITIVE).matcher(mail).matches();
    }

    public static boolean isValidateInteger(String t)
    {
        return t != null ? Pattern.compile("^[+-]?\\d+$", Pattern.CASE_INSENSITIVE).matcher(t).matches() : false;
    }

    public static boolean isValidateNegativeInteger(String t)
    {
        return t != null ? Pattern.compile("^[-][123456789]+\\d*$", Pattern.CASE_INSENSITIVE).matcher(t).matches() : false;
    }

    public static boolean isValidatePositiveInteger(String t)
    {
        return t != null ? Pattern.compile("^[+]?[123456789]+\\d*$", Pattern.CASE_INSENSITIVE).matcher(t).matches() : false;
    }

    public static String escape(String src)
    {
        StringBuilder buf = new StringBuilder(src.length() * 6);
        for (char c : src.toCharArray())
        {
            if (Character.isDigit(c) || Character.isLowerCase(c) || Character.isUpperCase(c))
            {
                buf.append(c);
                continue;
            }
            if (c < 256)
            {
                buf.append('%');
                if (c < 16)
                {
                    buf.append('0');
                }
                buf.append(Integer.toString(c, 16));
                continue;
            }

            buf.append("%u");
            buf.append(Integer.toString(c, 16));
        }
        return buf.toString();
    }

    public static String unescape(String src)
    {
        StringBuilder tmp = new StringBuilder(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length())
        {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos)
            {
                if (src.charAt(pos + 1) == 'u')
                {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                }
                else
                {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            }
            else
            {
                if (pos == -1)
                {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                }
                else
                {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static String lUpper(String text)
    {
        return "";
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
}