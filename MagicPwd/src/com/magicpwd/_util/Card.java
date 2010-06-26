/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._face.IEditItem;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.UserMdl;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 *
 * @author aven
 */
public class Card
{

    public static void exportHtm(java.io.File src, java.io.File dst)
    {
        try
        {
            Document doc = new SAXReader().read(src);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    public static java.io.File exportTxt(java.io.File src, java.io.File dst) throws Exception
    {
        Document doc = new SAXReader().read(src);
        Node node = doc.selectSingleNode("/magicpwd/card/template-uri");
        if (node == null)
        {
            return null;
        }
        String text = node.getText();
        if (!Util.isValidate(text))
        {
            return null;
        }

        java.io.InputStream stream = File.open4Read(text);
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(stream));
        StringBuffer buffer = new StringBuffer();
        String line = reader.readLine();
        while (line != null)
        {
            buffer.append(line);
            line = reader.readLine();
        }

        GridMdl gm = UserMdl.getGridMdl();
        IEditItem item;
        for (int i = ConsEnv.PWDS_HEAD_SIZE, j = gm.getRowCount(); i < j; i += 1)
        {
            item = gm.getItemAt(i);
            replace(buffer, '#' + item.getName() + '#', item.getData());
        }
        item = gm.getItemAt(ConsEnv.PWDS_HEAD_META);
        dst = new java.io.File(dst, item.getName() + ".txt");
        if (!dst.exists())
        {
            dst.createNewFile();
        }
        java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(dst));
        writer.write(buffer.toString());
        writer.flush();
        writer.close();

        return dst;
    }

    public static void exportPng(java.io.File src, java.io.File dst)
    {
    }

    public static void exportSvg(java.io.File src, java.io.File dst)
    {
    }

    public static void exportAll(java.io.File src, java.io.File dst)
    {
    }

    private static void replace(StringBuffer buf, String src, String dst)
    {
        int i = buf.indexOf(src);
        int k = src.length();
        while (i > -1)
        {
            buf.replace(i, i + k, dst);
            i = buf.indexOf(src, i + k);
        }
    }
}
