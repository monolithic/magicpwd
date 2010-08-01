/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

import java.util.regex.Pattern;

/**
 *
 * @author Amon
 */
public class Char
{

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
    public static boolean isValidateEmail(String mail)
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
}
