/*
 *
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
 * @author Amon
 */
public class Card
{

    public static java.io.File exportHtm(java.io.File src, java.io.File dst) throws Exception
    {
        Document doc = new SAXReader().read(src);

        Node node = doc.selectSingleNode("/magicpwd/card/template-res");
        String text;
        if (node != null)
        {
            text = node.getText();
            if (Util.isValidate(text))
            {
                File.copy(new java.io.File(text), dst, true);
            }
        }

        node = doc.selectSingleNode("/magicpwd/card/template-uri");
        if (node == null)
        {
            return null;
        }
        text = node.getText();
        if (!Util.isValidate(text))
        {
            return null;
        }

        return text(text, dst, ".htm");
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

        return text(text, dst, ".txt");
    }

    public static java.io.File exportPng(java.io.File src, java.io.File dst)
    {
        return null;
    }

    public static java.io.File exportSvg(java.io.File src, java.io.File dst) throws Exception
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

        return text(text, dst, ".svg");
    }

    public static java.io.File exportAll(java.io.File src, java.io.File dst)
    {
        return null;
    }

    private static java.io.File text(String src, java.io.File dst, String ext) throws Exception
    {
        java.io.InputStream stream = File.open4Read(src);
        if (stream == null)
        {
            return null;
        }

        java.io.InputStreamReader reader = new java.io.InputStreamReader(stream);

        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[1024];
        int len = reader.read(buf);
        while (len >= 0)
        {
            buffer.append(buf, 0, len);
            len = reader.read(buf);
        }

        reader.close();
        stream.close();

        GridMdl gm = UserMdl.getGridMdl();
        IEditItem item = gm.getItemAt(ConsEnv.PWDS_HEAD_META);
        dst = new java.io.File(dst, item.getName() + ext);
        if (!dst.exists())
        {
            dst.createNewFile();
        }

        for (int i = ConsEnv.PWDS_HEAD_SIZE, j = gm.getRowCount(); i < j; i += 1)
        {
            item = gm.getItemAt(i);
            replace(buffer, '#' + item.getName() + '#', item.getData());
        }

        java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(dst));
        writer.write(buffer.toString());
        writer.flush();
        writer.close();

        return dst;
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
