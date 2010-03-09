/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m;

import com.magicpwd._comn.Char;
import com.magicpwd.d.DBA3000;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Amon
 */
public class CharMdl extends AbstractListModel
{

    private static boolean withSys;
    private List<Char> charSys;
    private List<Char> charUsr;

    public CharMdl()
    {
        charSys = new ArrayList<Char>(7);

        Char c = new Char();
        c.setP30F2103("10000001");
        c.setP30F2104("数字");
        c.setP30F2105("数字");
        c.setP30F2106("0123456789");
        charSys.add(c);

        c = new Char();
        c.setP30F2103("10000002");
        c.setP30F2104("小写字母");
        c.setP30F2105("小写字母");
        c.setP30F2106("abcdefghijklmnopqrstuvwxyz");
        charSys.add(c);

        c = new Char();
        c.setP30F2103("10000003");
        c.setP30F2104("大写字母");
        c.setP30F2105("大写字母");
        c.setP30F2106("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        charSys.add(c);

        c = new Char();
        c.setP30F2103("10000004");
        c.setP30F2104("特殊字符");
        c.setP30F2105("特殊字符");
        c.setP30F2106("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~");
        charSys.add(c);

        c = new Char();
        c.setP30F2103("10000005");
        c.setP30F2104("大小写字母");
        c.setP30F2105("大小写字母");
        c.setP30F2106("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        charSys.add(c);

        c = new Char();
        c.setP30F2103("10000006");
        c.setP30F2104("字母及数字");
        c.setP30F2105("字母及数字");
        c.setP30F2106("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        charSys.add(c);

        c = new Char();
        c.setP30F2103("10000007");
        c.setP30F2104("可输入字符");
        c.setP30F2105("可输入字符");
        c.setP30F2106("!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");
        charSys.add(c);
    }

    @Override
    public int getSize()
    {
        if (charUsr == null)
        {
            charUsr = DBA3000.selectCharData();
        }
        return (isWithSys() ? charSys.size() : 0) + charUsr.size();
    }

    @Override
    public Object getElementAt(int index)
    {
        if (isWithSys())
        {
            if (index < charSys.size())
            {
                return charSys.get(index);
            }
            index -= charSys.size();
            return charUsr.get(index);
        }

        return charUsr.get(index);
    }

    public void appendItem(Char data)
    {
        charUsr.add(data);
    }

    public Char removeItemAt(int index)
    {
        if (withSys)
        {
            index -= charSys.size();
        }
        if (index < 0 || index >= charUsr.size())
        {
            return null;
        }
        return charUsr.remove(index);
    }

    public List<Char> getCharSys()
    {
        return charSys;
    }

    public List<Char> getCharUsr()
    {
        return charUsr;
    }

    /**
     * @return the withSys
     */
    public static boolean isWithSys()
    {
        return withSys;
    }

    /**
     * @param aWithSys the withSys to set
     */
    public static void setWithSys(boolean aWithSys)
    {
        withSys = aWithSys;
    }
}
