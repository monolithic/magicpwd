/********************************************************************
 * 项目名称                ：rochoc<p>
 * 包名称                  ：com.rochoc.autoupdate<p>
 * 文件名称                ：Config.java<p>
 * 编写者                 ：kfzx-luoc<p>
 * 编写日期                ：2008-12-22<p>
 * 程序功能（类）描述    ：<p>
 * 用于更新的配置文件读取对像
 * 程序变更日期            ：
 * 变更作者                ：
 * 变更说明                ：
 ********************************************************************/
package com.magicpwd.v.masu;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Amon
 */
public class Config
{

    public static String cfgFile = ".\\config\\autoupdate.xml";
    private static Config config = null;
    /** xml的document*/
    private Document doc = null;

    public static Config getInstance()
    {
        if (config == null)
        {
            config = new Config();
        }
        return config;
    }

    private Config()
    {
        try
        {
            SAXReader reader = new SAXReader();
            doc = reader.read(cfgFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void refresh()
    {
        config = new Config();
    }

    public String getVerstion()
    {
        if (config == null)
        {
            return "";
        }
        List lst = doc.selectNodes("Info/Version");
        Element el = (Element) lst.get(0);
        return el.getText();
    }

    public String getServerIp()
    {
        if (config == null)
        {
            return "";
        }
        List lst = doc.selectNodes("Info/UpdateServer/Ip");
        Element el = (Element) lst.get(0);
        return el.getText();
    }

    public UpdFile[] getFiles()
    {
        if (config == null)
        {
            return null;
        }
        List file = doc.selectNodes("Info/Files");
        List lst = ((Element) file.get(0)).elements();
        if (lst.size() == 0)
        {
            return null;
        }
        UpdFile files[] = new UpdFile[lst.size()];
        for (int i = 0; i < lst.size(); i++)
        {
            Element el = (Element) lst.get(i);
            List childs = el.elements();
            Element name = (Element) childs.get(0);//Name
            Element path = (Element) childs.get(1);//Path
            Element ver = (Element) childs.get(2);//Version
            files[i] = new UpdFile(name.getText());
            if ("File".equals(el.getName()))
            {
                files[i].setType(0);//文件
            }
            else
            {
                files[i].setType(1);//目录
            }
            files[i].setPath(path.getText());
            files[i].setVersion(ver.getText());
        }
        return files;
    }

    public String getServerPort()
    {
        if (config == null)
        {
            return "";
        }
        List lst = doc.selectNodes("Info/UpdateServer/Port");
        Element el = (Element) lst.get(0);
        return el.getText();
    }

    public static void print(String msg)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss->>");
        String str = sdf.format(new Date());
        System.out.println(str + msg);
    }

    public static void main(String args[])
    {
        Config cfg = Config.getInstance();
        UpdFile files[] = cfg.getFiles();
        for (int i = 0; i < files.length; i++)
        {
            System.out.println(files[i]);
        }
        Config.print("test");
    }

    /**
     * 格式化路径，增加其尾部的目录分隔符
     *
     * @param p 要格式化的目录字符串
     */
    public static String formatPath(String p)
    {
        if (!p.endsWith(File.separator))
        {
            return (p + File.separator);
        }
        return p;
    }

    /**
     * 格式化路径，去除其尾部的目录分隔符
     *
     * @param p 要格式化的目录字符串
     */
    public static String unformatPath(String p)
    {
        if (p.endsWith(File.separator))
        {
            return (p.substring(0, p.length() - 1));
        }
        return p;
    }

    public static byte[] getCmd(String cmd)
    {
        //第一位用于标识是命令，后面8位为命令名
        byte cmdb[] = new byte[9];
        cmdb[0] = AUPD.CMD_DATA_SECT;
        byte[] tmp = cmd.getBytes();
        if (tmp.length != 8)
        {
            Config.print("命令有误:" + cmd + "<<");
            return null;
        }
        for (int i = 0; i < 8; i++)
        {
            cmdb[i + 1] = tmp[i];
        }
        return cmdb;
    }

    public static String parseCmd(byte cmd[])
    {
        return new String(cmd, 0, 8);
    }

    public static byte[] getLen(int len)
    {
        String slen = String.valueOf(len);
        while (slen.length() < 4)
        {
            slen = "0" + slen;
        }
        return slen.getBytes();
    }

    public static void copyArray(byte objary[], byte souary[], int o_begin, int s_begin, int len)
    {
        for (int i = 0; i < len; i++)
        {
            objary[o_begin + i] = souary[s_begin + i];
        }
    }
}
