/********************************************************************
  * 项目名称                ：rochoc<p>
  * 包名称                  ：com.rochoc.autoupdate<p>
  * 文件名称                ：ClientVerParser.java<p>
  * 编写者                 ：kfzx-luoc<p>
  * 编写日期                ：2008-12-23<p>
  * 程序功能（类）描述    ：<p>
  * 客户端版本号解释器
  * 程序变更日期            ：
 * 变更作者                ：
 * 变更说明                ：
********************************************************************/
package com.magicpwd.v.masu;

import java.io.Reader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Amon
 */
public class ClientVerParser
{
    private Reader xml = null;
    /** xml的document*/
    private Document doc = null;
    
    public ClientVerParser(Reader reader)
    {
        xml = reader;
        parse();
    }
    
    private void parse()
    {
        try
        {
           SAXReader reader = new SAXReader();
           doc = reader.read(xml);
        }catch(Exception e)
       {
            e.printStackTrace();
        }
    }
    public String getVerstion()
    {
        List lst = doc.selectNodes("Info/Version");
        Element el = (Element)lst.get(0);
        return el.getText();
   }
    public UpdFile [] getFiles()
    {
        List file = doc.selectNodes("Info/Files");
        List lst = ((Element)file.get(0)).elements();
        if(lst.size()==0)
        {
            return null;
        }
       UpdFile files[] = new UpdFile[lst.size()];
        for(int i=0;i<lst.size();i++)
        {
            Element el = (Element)lst.get(i);
            List childs = el.elements();
            Element name = (Element)childs.get(0);//Name
            Element path = (Element)childs.get(1);//Path
            Element ver = (Element)childs.get(2);//Version
            files[i] = new UpdFile(name.getText());
           if("File".equals(el.getName()))
            {
               files[i].setType(0);//文件
           }else
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
       List lst = doc.selectNodes("Info/UpdateServer/Port");
        Element el = (Element)lst.get(0);
        return el.getText();
    }
}