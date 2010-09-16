/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.File;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.e.pwd.skin.LookAction;
import com.magicpwd.m.UserCfg;
import com.magicpwd.r.AmonFF;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Amon
 */
public class MenuPtn
{

    private Document document;
    private UserCfg userCfg;
    private java.util.regex.Pattern pattern;

    public MenuPtn(UserCfg userCfg)
    {
        this.userCfg = userCfg;
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
        pattern = java.util.regex.Pattern.compile("^[$]P30F[A-F0-9]{4}$", java.util.regex.Pattern.CASE_INSENSITIVE);
        document = new SAXReader().read(stream);
        return document != null;
    }

    public javax.swing.JMenuBar getMenuBar(String menuId)
    {
        if (!Char.isValidate(menuId) || document == null)
        {
            return null;
        }
        Node node = document.getRootElement().selectSingleNode(Char.format("/magicpwd/menubar[@id='{0}']", menuId));
        if (node == null || !(node instanceof Element))
        {
            return null;
        }
        Element element = (Element) node;

        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
        menuBar.setName(menuId);

        List elementList = element.elements("menu");
        if (elementList != null)
        {
            java.util.HashMap<String, javax.swing.JMenu> menus = new java.util.HashMap<String, javax.swing.JMenu>();
            java.util.HashMap<String, javax.swing.Action> actions = new java.util.HashMap<String, javax.swing.Action>();
            java.util.HashMap<String, javax.swing.ButtonGroup> groups = new java.util.HashMap<String, javax.swing.ButtonGroup>();
            Element tmp;
            for (Object obj : elementList)
            {
                if (!(obj instanceof Element))
                {
                    continue;
                }
                tmp = (Element) obj;
                javax.swing.JMenu menu = createMenu(tmp, actions, groups);
                if (menu == null)
                {
                    continue;
                }
                menuBar.add(menu);
                if (Char.isValidate(tmp.attributeValue("id")))
                {
                    menus.put(tmp.attributeValue("id"), menu);
                }
            }

            final String KEY_SKIN = "skin";
            if (menus.containsKey(KEY_SKIN))
            {
                javax.swing.JMenu skin = menus.get(KEY_SKIN);
                if (skin == null)
                {
                    skin = new javax.swing.JMenu();
                    menuBar.add(skin);
                }
                loadSkin(skin);
            }
        }
        return menuBar;
    }

    public javax.swing.JPopupMenu getMenuPop(String menuId)
    {
        return null;
    }

    public javax.swing.JToolBar getToolBar(String toolId)
    {
        return null;
    }

    private javax.swing.JMenu createMenu(Element element, java.util.HashMap<String, javax.swing.Action> actions, java.util.HashMap<String, javax.swing.ButtonGroup> groups)
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
                    continue;
                }
                if ("seperator".equals(element.getName()))
                {
                    menu.addSeparator();
                    continue;
                }
            }
        }
        return menu;
    }

    private javax.swing.JMenuItem createItem(Element element, java.util.HashMap<String, javax.swing.Action> actions, java.util.HashMap<String, javax.swing.ButtonGroup> groups)
    {
        javax.swing.JMenuItem item = processType(element);
        processGroup(element, item, groups);
        processText(element, item);
        processTips(element, item);
        processIcon(element, item);
        processCommand(element, item);
        processStrokes(element, item);
        processAction(element, item);
        return item;
    }

    public void loadSkin(javax.swing.JMenu skinMenu)
    {
        java.io.File skinFile = new java.io.File(ConsEnv.DIR_SKIN);
        if (!skinFile.exists() || !skinFile.isDirectory() || !skinFile.canRead())
        {
            return;
        }

        loadLook(skinMenu);
        loadFeel(skinMenu);

        java.io.File[] files = skinFile.listFiles(new AmonFF("[^\\s]+[.ams]$", true));
        if (files == null || files.length < 1)
        {
            return;
        }

        // 动态扩展风格
        java.util.Properties prop = new java.util.Properties();
        for (java.io.File ams : files)
        {
            try
            {
                prop.load(new java.io.FileReader(ams));
                javax.swing.JMenuItem item = new javax.swing.JMenuItem();
                Bean.setText(item, getLang(prop, "text"));
                Bean.setTips(item, getLang(prop, "tips"));
                skinMenu.add(item);
                prop.clear();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
    }

    private void loadLook(javax.swing.JMenu skinMenu)
    {
        javax.swing.JMenu lookMenu = new javax.swing.JMenu();
        lookMenu.setText("Look");
        skinMenu.add(lookMenu);

        java.io.File lookFile = new java.io.File(ConsEnv.DIR_SKIN, ConsEnv.DIR_LOOK);
        if (!lookFile.exists() || !lookFile.isDirectory() || !lookFile.canRead())
        {
            return;
        }

        javax.swing.JCheckBoxMenuItem item;
        String skinName = userCfg.getCfg(ConsCfg.CFG_SKIN_NAME, ConsCfg.DEF_SKIN_SYS);
        LookAction action = new LookAction(userCfg);
        javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();

        // Java默认风格
        java.io.File defaultSkin = new java.io.File(lookFile, ConsEnv.SKIN_DEFAULT + '/' + ConsEnv.SKIN_LOOK_FILE);
        if (defaultSkin.exists() && defaultSkin.isFile() && defaultSkin.canRead())
        {
            item = new javax.swing.JCheckBoxMenuItem(action);
            Bean.setText(item, "default");
            Bean.setTips(item, "default");
            item.setActionCommand(ConsCfg.DEF_SKIN_DEF);
            item.setSelected(skinName.equals(ConsCfg.DEF_SKIN_DEF));
            lookMenu.add(item);
            group.add(item);
        }

        // 系统默认风格
        java.io.File sytemSkin = new java.io.File(lookFile, ConsEnv.SKIN_SYSTEM + '/' + ConsEnv.SKIN_LOOK_FILE);
        if (sytemSkin.exists() && sytemSkin.isFile() && sytemSkin.canRead())
        {
            item = new javax.swing.JCheckBoxMenuItem(action);
            Bean.setText(item, "system");
            Bean.setTips(item, "system");
            item.setActionCommand(ConsCfg.DEF_SKIN_SYS);
            item.setSelected(skinName.equals(ConsCfg.DEF_SKIN_SYS));
            lookMenu.add(item);
            group.add(item);
        }

        java.io.File dirs[] = lookFile.listFiles(new AmonFF(true, ConsEnv.SKIN_DEFAULT, ConsEnv.SKIN_SYSTEM));
        if (dirs == null || dirs.length < 1)
        {
            return;
        }

        lookMenu.addSeparator();

        for (java.io.File dir : dirs)
        {
            java.io.File aml = new java.io.File(dir, ConsEnv.SKIN_LOOK_FILE);
            if (!aml.exists() || !aml.isFile() || !aml.canRead())
            {
                continue;
            }
            try
            {
                Document doc = new SAXReader().read(new java.io.FileInputStream(aml));
                if (doc == null)
                {
                    continue;
                }
                Element root = doc.getRootElement();
                if (root == null)
                {
                    continue;
                }
                Element look = root.element("look");
                if (look == null)
                {
                    continue;
                }
                java.util.List<?> items = look.elements("item");
                if (items == null || items.size() < 1)
                {
                    continue;
                }
                String type = look.attributeValue("type");
                if (items.size() == 1)
                {
                    Element element = look.element("item");

                    item = new javax.swing.JCheckBoxMenuItem(action);
                    Bean.setText(item, getLang(element.attributeValue("text")));
                    Bean.setTips(item, getLang(element.attributeValue("tips")));
                    item.setSelected(skinName.equals(element.attributeValue("class")));
                    item.setActionCommand(type + ":" + dir.getName() + ',' + element.attributeValue("class"));
                    lookMenu.add(item);
                    group.add(item);
                }
                else
                {
                    String grpText = getLang(look.attributeValue("group"));
                    if (!com.magicpwd._util.Char.isValidate(grpText))
                    {
                        grpText = dir.getName();
                    }
                    javax.swing.JMenu subMenu = new javax.swing.JMenu();
                    Bean.setText(subMenu, grpText);
                    lookMenu.add(subMenu);

                    for (Object object : items)
                    {
                        if (!(object instanceof Element))
                        {
                            continue;
                        }
                        Element element = (Element) object;

                        item = new javax.swing.JCheckBoxMenuItem(action);
                        Bean.setText(item, getLang(element.attributeValue("text")));
                        Bean.setTips(item, getLang(element.attributeValue("tips")));
                        item.setSelected(skinName.equals(element.attributeValue("class")));
                        item.setActionCommand(type + ":" + dir.getName() + ',' + element.attributeValue("class"));
                        subMenu.add(item);
                        group.add(item);
                    }
                }
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
    }

    private static void loadFeel(javax.swing.JMenu skinMenu)
    {
        javax.swing.JMenu feelMenu = new javax.swing.JMenu();
        feelMenu.setText("Feel");
        skinMenu.add(feelMenu);
    }

    private javax.swing.JMenuItem processText(Element element, javax.swing.JMenuItem item)
    {
        String text = element.attributeValue("text");
        if (text != null && pattern.matcher(text).matches())
        {
            text = text.substring(1).toUpperCase();
            text = Lang.getLang(text, text);
        }
        Bean.setText(item, text != null ? text : "...");
        return item;
    }

    private javax.swing.JMenuItem processTips(Element element, javax.swing.JMenuItem item)
    {
        String tips = element.attributeValue("tips");
        if (tips != null && pattern.matcher(tips).matches())
        {
            tips = tips.substring(1).toUpperCase();
            tips = Lang.getLang(tips, tips);
        }
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
            javax.swing.JCheckBoxMenuItem item = new javax.swing.JCheckBoxMenuItem();
            item.setSelected(ConsCfg.DEF_TRUE.equalsIgnoreCase(element.attributeValue("checked")));
            return item;
        }
        else if ("radiobox".equals("type"))
        {
            javax.swing.JRadioButtonMenuItem item = new javax.swing.JRadioButtonMenuItem();
            item.setSelected(ConsCfg.DEF_TRUE.equalsIgnoreCase(element.attributeValue("checked")));
            return item;
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

    private static String getLang(java.util.Properties prop, String text)
    {
        if (text == null)
        {
            return text;
        }
        text = prop.getProperty(text);
        return java.util.regex.Pattern.matches("^[$]P30F[0123456789ABCDEF]{4}$", text) ? Lang.getLang(text, text) : text;
    }

    private static String getLang(String text)
    {
        return (text != null && java.util.regex.Pattern.matches("^[$]P30F[0123456789ABCDEF]{4}$", text)) ? Lang.getLang(text, text) : text;
    }
}
