/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.File;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.e.pad.IPadAction;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.e.pwd.skin.LookAction;
import com.magicpwd.m.CoreMdl;
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
    private CoreMdl coreMdl;
    private java.util.regex.Pattern pattern;
    private java.util.HashMap<String, javax.swing.AbstractButton> buttons;
    private java.util.HashMap<String, javax.swing.AbstractAction> actions;

    public MenuPtn(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
        buttons = new java.util.HashMap<String, javax.swing.AbstractButton>();
        actions = new java.util.HashMap<String, javax.swing.AbstractAction>();
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

    public javax.swing.AbstractButton getButton(String id)
    {
        return buttons.get(id);
    }

    public javax.swing.AbstractAction getAction(String id)
    {
        return actions.get(id);
    }

    public javax.swing.JMenuBar getMenuBar(String menuId, javax.swing.JComponent component)
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
            java.util.HashMap<String, javax.swing.ButtonGroup> groups = new java.util.HashMap<String, javax.swing.ButtonGroup>();
            Element tmp;
            for (Object obj : elementList)
            {
                if (!(obj instanceof Element))
                {
                    continue;
                }
                tmp = (Element) obj;
                javax.swing.JMenu menu = createMenu(tmp, groups, component);
                if (menu == null)
                {
                    continue;
                }
                menuBar.add(menu);
                if (Char.isValidate(tmp.attributeValue("id")))
                {
                    buttons.put(tmp.attributeValue("id"), menu);
                }
            }

            final String KEY_SKIN = "skin";
            if (buttons.containsKey(KEY_SKIN))
            {
                javax.swing.JMenu skin = (javax.swing.JMenu) buttons.get(KEY_SKIN);
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
        if (!Char.isValidate(menuId) || document == null)
        {
            return null;
        }
        Node node = document.getRootElement().selectSingleNode(Char.format("/magicpwd/menupop[@id='{0}']", menuId));
        if (node == null || !(node instanceof Element))
        {
            return null;
        }
        Element element = (Element) node;

        javax.swing.JPopupMenu menuPop = new javax.swing.JPopupMenu();
        menuPop.setName(menuId);

        List elementList = element.elements();
        if (elementList != null)
        {
            java.util.HashMap<String, javax.swing.ButtonGroup> groups = new java.util.HashMap<String, javax.swing.ButtonGroup>();
            Element tmp;
            for (Object obj : elementList)
            {
                if (!(obj instanceof Element))
                {
                    continue;
                }
                tmp = (Element) obj;
                if ("menu".equals(tmp.getName()))
                {
                    menuPop.add(createMenu(tmp, groups, null));
                    continue;
                }
                if ("item".equals(tmp.getName()))
                {
                    menuPop.add(createItem(tmp, groups, null));
                    continue;
                }
                if ("seperator".equals(tmp.getName()))
                {
                    menuPop.addSeparator();
                    continue;
                }
            }
        }
        return menuPop;
    }

    public javax.swing.JToolBar getToolBar(String toolId)
    {
        if (!Char.isValidate(toolId) || document == null)
        {
            return null;
        }
        Node node = document.getRootElement().selectSingleNode(Char.format("/magicpwd/toolbar[@id='{0}']", toolId));
        if (node == null || !(node instanceof Element))
        {
            return null;
        }
        Element element = (Element) node;

        javax.swing.JToolBar toolBar = new javax.swing.JToolBar();
        toolBar.setName(toolId);

        List elementList = element.elements("item");
        if (elementList != null)
        {
            java.util.HashMap<String, javax.swing.ButtonGroup> groups = new java.util.HashMap<String, javax.swing.ButtonGroup>();
            Element tmp;
            for (Object obj : elementList)
            {
                if (!(obj instanceof Element))
                {
                    continue;
                }
                tmp = (Element) obj;
                javax.swing.AbstractButton button = createButton(tmp, groups, null);
                if (button == null)
                {
                    continue;
                }
                toolBar.add(button);
                if (Char.isValidate(tmp.attributeValue("id")))
                {
                    buttons.put(tmp.attributeValue("id"), button);
                }
            }
        }
        return toolBar;
    }

    private javax.swing.JMenu createMenu(Element element, java.util.HashMap<String, javax.swing.ButtonGroup> groups, javax.swing.JComponent component)
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
                    menu.add(createMenu(element, groups, component));
                    continue;
                }
                if ("item".equals(element.getName()))
                {
                    menu.add(createItem(element, groups, component));
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

    private javax.swing.JMenuItem createItem(Element element, java.util.HashMap<String, javax.swing.ButtonGroup> groups, javax.swing.JComponent component)
    {
        javax.swing.JMenuItem item = processType(element);
        processGroup(element, item, groups);
        processText(element, item);
        processTips(element, item);
        processIcon(element, item);
        processEnabled(element, item);
        processVisible(element, item);
        processAction(element, item, component);
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
        String skinName = coreMdl.getUserCfg().getCfg(ConsCfg.CFG_SKIN_NAME, ConsCfg.DEF_SKIN_SYS);
        LookAction action = new LookAction();
        action.setCoreMdl(coreMdl);
        javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();

        // Java默认风格
        java.io.File defaultSkin = new java.io.File(lookFile, ConsEnv.SKIN_DEFAULT + '/' + ConsEnv.SKIN_LOOK_FILE);
        if (defaultSkin.exists() && defaultSkin.isFile() && defaultSkin.canRead())
        {
            item = new javax.swing.JCheckBoxMenuItem(action);
            Bean.setText(item, Lang.getLang(LangRes.P30F7632, "默认界面"));
            Bean.setTips(item, "");
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
            Bean.setText(item, Lang.getLang(LangRes.P30F7633, "系统界面"));
            Bean.setTips(item, "");
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

    private javax.swing.AbstractButton processText(Element element, javax.swing.AbstractButton button)
    {
        String text = element.attributeValue("text");
        if (text != null && pattern.matcher(text).matches())
        {
            text = text.substring(1).toUpperCase();
            text = Lang.getLang(text, text);
        }
        Bean.setText(button, text != null ? text : "...");
        return button;
    }

    private javax.swing.AbstractButton processTips(Element element, javax.swing.AbstractButton button)
    {
        String tips = element.attributeValue("tips");
        if (tips != null && pattern.matcher(tips).matches())
        {
            tips = tips.substring(1).toUpperCase();
            tips = Lang.getLang(tips, tips);
        }
        Bean.setTips(button, tips);
        return button;
    }

    private javax.swing.AbstractButton processIcon(Element element, javax.swing.AbstractButton button)
    {
        String icon = element.attributeValue("icon");
        if (Char.isValidate(icon))
        {
            button.setIcon(createIcon(icon));
        }

        icon = element.attributeValue("icon-rollover");
        if (Char.isValidate(icon))
        {
            button.setRolloverIcon(createIcon(icon));
        }

        icon = element.attributeValue("icon-pressed");
        if (Char.isValidate(icon))
        {
            button.setPressedIcon(createIcon(icon));
        }

        icon = element.attributeValue("icon-disabled");
        if (Char.isValidate(icon))
        {
            button.setDisabledIcon(createIcon(icon));
        }
        return button;
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

    private static javax.swing.AbstractButton processGroup(Element element, javax.swing.AbstractButton button, java.util.HashMap<String, javax.swing.ButtonGroup> groups)
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
            bg.add(button);
        }
        return button;
    }

    private javax.swing.AbstractButton processEnabled(Element element, javax.swing.AbstractButton button)
    {
        String text = element.attributeValue("enabled");
        if (Char.isValidate(text))
        {
            button.setEnabled("true".equals(text));
        }
        return button;
    }

    private javax.swing.AbstractButton processVisible(Element element, javax.swing.AbstractButton button)
    {
        String text = element.attributeValue("visible");
        if (Char.isValidate(text))
        {
            button.setVisible("true".equals(text));
        }
        return button;
    }

    private static javax.swing.AbstractButton processCommand(Element element, javax.swing.AbstractButton button)
    {
        button.setActionCommand(element.attributeValue("command"));
        return button;
    }

    private static javax.swing.AbstractButton processStrokes(Element element, javax.swing.AbstractButton button, javax.swing.AbstractAction action, javax.swing.JComponent component)
    {
        java.util.List list = element.elements("stroke");
        if (list == null || list.size() < 1)
        {
            return button;
        }

        for (Object obj : list)
        {
            String temp = ((Element) obj).attributeValue("key");
            if (Char.isValidate(temp))
            {
                temp = temp.toUpperCase().replaceAll("~|SHIFT", "shift").replaceAll("\\^|CONTROL|CTRL", "control").replaceAll("#|ALT", "alt").replaceAll("!|META", "meta").replaceAll("[^-=`;',./\\[\\]a-zA-Z0-9]+", " ").trim();
                javax.swing.KeyStroke stroke = javax.swing.KeyStroke.getKeyStroke(temp);
                if (component != null)
                {
                    Bean.registerKeyStrokeAction(component, stroke, action, temp, javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
                }
                if (button instanceof javax.swing.JMenuItem)
                {
                    ((javax.swing.JMenuItem) button).setAccelerator(stroke);
                }
            }
        }
        return button;
    }

    private javax.swing.AbstractButton processAction(Element element, javax.swing.AbstractButton button, javax.swing.JComponent component)
    {
        java.util.List list = element.elements("action");
        if (list == null || list.size() < 1)
        {
            return button;
        }

        element = (Element) list.get(0);
        String name = element.attributeValue("id");
        boolean validate = Char.isValidate(name);
        javax.swing.AbstractAction action = validate ? actions.get(name) : null;
        if (action == null)
        {
            String type = element.attributeValue("class");
            if (Char.isValidate(type))
            {
                try
                {
                    Object obj = Class.forName(type).newInstance();
                    if (obj instanceof javax.swing.AbstractAction)
                    {
                        action = (javax.swing.AbstractAction) obj;
                        if (action instanceof IPwdAction)
                        {
                            IPwdAction pwdAction = (IPwdAction) action;
                            pwdAction.setMainPtn(TrayPtn.getMainPtn());
                            pwdAction.setCoreMdl(coreMdl);
                        }
                        else if (action instanceof IPadAction)
                        {
                            IPadAction padAction = (IPadAction) action;
                            padAction.setMiniPtn(TrayPtn.getMiniPtn());
                            padAction.setCoreMdl(coreMdl);
                        }
                        if (validate)
                        {
                            actions.put(name, action);
                        }
                    }
                }
                catch (Exception ex)
                {
                    Logs.exception(ex);
                    Lang.showMesg(null, null, ex.getLocalizedMessage());
                }
            }
        }
        button.addActionListener(action);
        processCommand(element, button);
        processStrokes(element, button, action, component);
        processReference(element, button, action);
        return button;
    }

    private javax.swing.AbstractButton processReference(Element element, javax.swing.AbstractButton button, javax.swing.AbstractAction action)
    {
        java.util.List list = element.elements("property");
        if (list == null || list.size() < 1)
        {
            return button;
        }

        String name;
        String refId;
        for (int i = 0, j = list.size(); i < j; i += 1)
        {
            element = (Element) list.get(i);

            // 处理属性
            name = element.attributeValue("name");
            if (!Char.isValidate(name))
            {
                continue;
            }

            // 处理引用
            refId = element.attributeValue("ref-id");
            if (!Char.isValidate(refId))
            {
                continue;
            }

            // 引用对象
            action = actions.get(refId);
            if (action == null)
            {
                continue;
            }

            try
            {
                java.lang.reflect.Method method = button.getAction().getClass().getDeclaredMethod("set" + Char.lUpper(name), java.net.URL.class);
                if (method != null)
                {
                    method.invoke(button.getAction(), action);
                }
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
        return button;
    }

    private javax.swing.Icon createIcon(String path)
    {
        java.io.InputStream stream = null;
        try
        {
            stream = File.open4Read(path);
            return new javax.swing.ImageIcon(javax.imageio.ImageIO.read(stream));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return null;
        }
        finally
        {
            if (stream != null)
            {
                try
                {
                    stream.close();
                }
                catch (Exception exp)
                {
                    Logs.exception(exp);
                }
            }
        }
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

    private javax.swing.AbstractButton createButton(Element element, java.util.HashMap<String, javax.swing.ButtonGroup> groups, javax.swing.JComponent component)
    {
        javax.swing.AbstractButton button = null;
        String type = element.attributeValue("type");
        if ("toggle".equals(type))
        {
            button = new javax.swing.JToggleButton();
        }
        else
        {
            button = new javax.swing.JButton();
        }
        processGroup(element, button, groups);
        processText(element, button);
        processTips(element, button);
        processIcon(element, button);
        processEnabled(element, button);
        processVisible(element, button);
        processAction(element, button, component);
        return button;
    }
}
