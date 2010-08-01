/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

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

    public boolean loadData(String filePath)
    {
        return true;
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
        element = element.element(Char.format("menubar[id='{0}']", menuId));
        if (element == null)
        {
            return null;
        }
        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
        menuBar.setName(menuId);
        List elementList = element.elements("menu");
        if (elementList != null)
        {
            for (Object obj : elementList)
            {
                if (!(obj instanceof Element))
                {
                    continue;
                }
                javax.swing.JMenu menu = createMenu((Element) obj);
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

    private static javax.swing.JMenu createMenu(Element element)
    {
        javax.swing.JMenu menu = new javax.swing.JMenu();

        String text = element.attributeValue("text");
        Bean.setText(menu, text != null ? text : "...");

        String tips = element.attributeValue("tips");
        Bean.setText(menu, tips != null ? tips : "...");

        menu.setIcon(null);
        menu.setRolloverIcon(null);
        menu.setDisabledIcon(null);
        menu.setPressedIcon(null);

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
                    menu.add(createMenu(element));
                    continue;
                }
                if ("item".equals(element.getName()))
                {
                    menu.add(createItem(element));
                }
            }
        }
        return null;
    }

    private static javax.swing.JMenuItem createItem(Node node)
    {
        return null;
    }

    private javax.swing.Icon createIcon(String path)
    {
        return null;
    }
}
