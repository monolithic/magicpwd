/**
 * 
 */
package com.magicpwd._util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.magicpwd._cons.ConsEnv;

/**
 * @author Amon
 * 
 */
public class Jcsv
{

    private java.io.File fn;
    /**
     * 是否包含头部
     */
    private boolean hd = true;
    private String es = "\"";
    private String ee = "\"";
    private String sp = ",";
    private String sl = "\n";
    private String fe = ConsEnv.FILE_ENCODING;

    public Jcsv(java.io.File fileName)
    {
        this.fn = fileName;
    }

    public void setFilePath(java.io.File filePath)
    {
        this.fn = filePath;
    }

    public void setHd(boolean hd)
    {
        this.hd = hd;
    }

    public void setEs(String es)
    {
        this.es = es;
    }

    public void setEe(String ee)
    {
        this.ee = ee;
    }

    public void setFileEncoding(String encoding)
    {
        this.fe = encoding;
    }

    public ArrayList<ArrayList<String>> readFile() throws IOException
    {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fn), fe));

        String row = null;
        String tmp1;
        String tmp2;
        String line;
        if (hd)
        {
            line = br.readLine();
        }
        while (true)
        {
            line = br.readLine();
            // 最后一行存在错误的情况下，不做处理
            if (line == null)
            {
                break;
            }

            // 已有换行
            if (row != null)
            {
                // 中间换行
                row += sl + line;
            }
            else
            {
                row = line;
            }

            tmp1 = line.replaceAll("[" + es + "]{2}+", "").replaceAll("[" + ee + "]{2}+", "").replaceAll("(" + es + "\\s+" + sp + "\\s+" + ee + ")", es + sp + ee);
            tmp2 = tmp1.replaceAll(ee + "\\s*" + sp + "\\s*$", "");
            // 结束换行
            if (tmp1.equals(tmp2) && tmp2.lastIndexOf(ee) >= tmp2.lastIndexOf(es + sp + ee))
            {
                continue;
            }

            data.add(deCode(row));
            row = null;
        }
        br.close();

        return data;
    }

    public void saveFile(ArrayList<ArrayList<String>> data) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fn), fe));
        if (hd)
        {
            bw.newLine();
        }
        for (ArrayList<String> temp : data)
        {
            bw.write(enCode(temp));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private ArrayList<String> deCode(String text)
    {
        ArrayList<String> data = new ArrayList<String>();

        if (text == null)
        {
            return data;
        }

        text = text.trim();
        if (text.length() < 1)
        {
            return data;
        }

        if (!text.endsWith(sp))
        {
            text += sp;
        }

        boolean b = false;
        int s = 0;
        int e = text.indexOf(sp, s);
        String temp;
        StringBuilder sb = new StringBuilder();
        while (e >= s)
        {
            temp = text.substring(s, e);
            if (!b)
            {
                if (temp.startsWith(es))
                {
                    temp = temp.substring(es.length());

                    if (temp.endsWith(ee))
                    {
                        temp = temp.substring(0, temp.length() - ee.length());
                        temp = temp.replace(es + es, es);
                        if (!ee.equals(es))
                        {
                            temp = temp.replace(ee + ee, ee);
                        }
                        data.add(temp);
                    }
                    else
                    {
                        sb.append(temp).append(sp);
                        b = true;
                    }
                }
                else
                {
                    data.add(temp);
                }
            }
            else
            {
                if (temp.endsWith(ee))
                {
                    sb.append(temp.substring(0, temp.length() - ee.length()));
                    temp = sb.toString().replace(es + es, es);
                    if (!ee.equals(es))
                    {
                        temp = temp.replace(ee + ee, ee);
                    }
                    data.add(temp);
                    sb.delete(0, sb.length());
                    b = false;
                }
                else
                {
                    sb.append(temp).append(sp);
                }
            }
            s = e + 1;
            e = text.indexOf(sp, s);
        }

        return data;
    }

    private String enCode(ArrayList<String> data)
    {
        if (data == null)
        {
            return "";
        }

        if (data.size() < 1)
        {
            return sp;
        }

        StringBuilder sb = new StringBuilder();

        boolean b = false;
        for (String temp : data)
        {
            if (temp.indexOf(es) > -1)
            {
                temp = temp.replace(es, es + es);
                b = true;
            }

            if (!ee.equals(es))
            {
                if (temp.indexOf(ee) > -1)
                {
                    temp = temp.replace(ee, ee + ee);
                    b = true;
                }
            }

            if (temp.indexOf(sp) > -1 || temp.indexOf(sl) > -1)
            {
                b = true;
            }

            if (b)
            {
                temp = es + temp + ee;
                b = false;
            }
            sb.append(temp).append(sp);
        }
        return sb.toString();
    }
}
