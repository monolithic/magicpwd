/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.File;
import com.magicpwd._util.Logs;
import com.magicpwd.e.skin.LookAction;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Amon
 */
public class MenuPtn
{

    private Document document;

    public MenuPtn()
    {
    }

    public boolean loadData(String uri) throws Exception
    {
        return loadData(File.open4Read(uri));
    }

    public boolean loadData(java.io.File file) throws Exception
    {
        return loadData(new java.io.FileInputStream(file));
    }

    public boolean loadData(java.io.InputStream stream) throws Exception
    {
        document = new SAXReader().read(stream);
        return document != null;
    }

    public javax.swing.JMenuBar getMenuBar(String menuId)
    {
        if (!Char.isValidate(menuId) || document == null)
        {
            return null;
        }
        Element element = document.getRootElement();
        if (element == null || !"magicpwd".equals(element.getName()))
        {
            return null;
        }
        for (Object ele : element.elements())
        {
            Element obj = (Element) ele;
            System.out.println("====");
            System.out.println(obj.getNamespaceURI());
            System.out.println(obj.getQualifiedName());
            System.out.println(obj.getUniquePath());
            System.out.println(obj.getQName());
            System.out.println(obj);
        }
        element = element.element(Char.format("menubar[@id={0}]", menuId));
        if (element == null)
        {
            return null;
        }
        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
        menuBar.setName(menuId);
        List elementList = element.elements("menu");
        if (elementList != null)
        {
            java.util.HashMap<String, javax.swing.Action> actions = new java.util.HashMap<String, javax.swing.Action>();
            java.util.HashMap<String, javax.swing.ButtonGroup> groups = new java.util.HashMap<String, javax.swing.ButtonGroup>();
            for (Object obj : elementList)
            {
                if (!(obj instanceof Element))
                {
                    continue;
                }
                javax.swing.JMenu menu = createMenu((Element) obj, actions, groups);
                if (menu != null)
                {
                    menuBar.add(menu);
                }
            }
        }
        return null;
    }

    public javax.swing.JPopupMenu getMenuPop(String menuId)
    {
        return null;
    }

    private static javax.swing.JMenu createMenu(Element element, java.util.HashMap<String, javax.swing.Action> actions, java.util.HashMap<String, javax.swing.ButtonGroup> groups)
    {
        javax.swing.JMenu menu = new javax.swing.JMenu();

        processText(element, menu);
        processTips(element, menu);
        processIcon(element, menu);

        List list = element.elements();
        if (list != null)
        {
            for (Object obj : list)
            {
                if (!(obj instanceof Element))
                {
                    continue;
                }
                element = (Element) obj;
                if ("menu".equals(element.getName()))
                {
                    menu.add(createMenu(element, actions, groups));
                    continue;
                }
                if ("item".equals(element.getName()))
                {
                    menu.add(createItem(element, actions, groups));
                }
                if ("seperator".equals(element.getName()))
                {
                    menu.addSeparator();
                }
            }
        }
        return null;
    }

    private static javax.swing.JMenuItem createItem(Element element, java.util.HashMap<String, javax.swing.Action> actions, java.util.HashMap<String, javax.swing.ButtonGroup> groups)
    {
        javax.swing.JMenuItem item = processType(element);
        processGroup(element, item, groups);
        processText(element, item);
        processTips(element, item);
        processIcon(element, item);
        processCommand(element, item);
        processStrokes(element, item);
        processAction(element, item);
        return null;
    }

    private static void loadSkin()
    {
    }

    private static void loadLook()
    {
        java.io.File look = new java.io.File("skin", "look");
        if (!look.exists() || !look.canRead() || !look.isDirectory())
        {
            return;
        }
        java.io.File dirs[] = look.listFiles();
        if (dirs == null || dirs.length < 1)
        {
            return;
        }

        LookAction action = new LookAction();
        for (java.io.File dir : dirs)
        {
            java.io.File aml = new java.io.File(dir, "look.aml");
            if (!aml.exists() || !aml.canRead() || !aml.isFile())
            {
                continue;
            }
            try
            {
                java.util.Properties prop = new java.util.Properties();
                prop.load(new java.io.FileInputStream(aml));
                javax.swing.JCheckBoxMenuItem item = new javax.swing.JCheckBoxMenuItem();
                Bean.setText(item, prop.getProperty("text"));
                Bean.setTips(item, prop.getProperty("tips"));
                item.setAction(action);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
    }

    private static void loadFeel()
    {
    }

    private static javax.swing.JMenuItem processText(Element element, javax.swing.JMenuItem item)
    {
        String text = element.attributeValue("text");
        Bean.setText(item, text != null ? text : "...");
        return item;
    }

    private static javax.swing.JMenuItem processTips(Element element, javax.swing.JMenuItem item)
    {
        String tips = element.attributeValue("tips");
        Bean.setTips(item, tips != null ? tips : "...");
        return item;
    }

    private static javax.swing.JMenuItem processIcon(Element element, javax.swing.JMenuItem item)
    {
        item.setIcon(null);
        item.setRolloverIcon(null);
        item.setDisabledIcon(null);
        item.setPressedIcon(null);
        return item;
    }

    private static javax.swing.JMenuItem processType(Element element)
    {
        String type = element.attributeValue("type");
        if ("checkbox".equals(type))
        {
            return new javax.swing.JCheckBoxMenuItem();
        }
        else if ("radiobox".equals("type"))
        {
            return new javax.swing.JRadioButtonMenuItem();
        }

        return new javax.swing.JMenuItem();
    }

    private static javax.swing.JMenuItem processGroup(Element element, javax.swing.JMenuItem item, java.util.HashMap<String, javax.swing.ButtonGroup> groups)
    {
        String group = element.attributeValue("group");
        if (Char.isValidate(group))
        {
            javax.swing.ButtonGroup bg = groups.get(group);
            if (bg == null)
            {
                bg = new javax.swing.ButtonGroup();
                groups.put(group, bg);
            }
            bg.add(item);
        }
        return item;
    }

    private static javax.swing.JMenuItem processCommand(Element element, javax.swing.JMenuItem item)
    {
        return item;
    }

    private static javax.swing.JMenuItem processStrokes(Element element, javax.swing.JMenuItem item)
    {
        return item;
    }

    private static javax.swing.JMenuItem processAction(Element element, javax.swing.JMenuItem item)
    {
        return item;
    }

    private javax.swing.Icon createIcon(String path)
    {
        return null;
    }
}
