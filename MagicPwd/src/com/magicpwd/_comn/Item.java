/**
 * 
 */
package com.magicpwd._comn;

import com.magicpwd._cons.ConsDat;
import com.magicpwd._face.IEditItem;
import java.util.ArrayList;
import java.util.List;

import com.magicpwd.m.UserMdl;

/**
 * @author shangwen.yao
 * 
 */
public class Item
{
    /** 记录类别 */
    private int type;
    /** 记录名称 */
    private String name;
    /** 记录内容 */
    private String data;
    /** 专有内容 */
    private List<String> spec;

    /**
     * 
     */
    public Item()
    {
    }

    /**
     * @param type
     */
    public Item(int type)
    {
        this(type, "", "");
    }

    /**
     * @param type
     * @param name
     * @param data
     */
    public Item(int type, String name, String data)
    {
        this.type = type;
        this.name = name;
        this.data = data;
        setDefault();
    }

    /**
     * @return the type
     */
    public int getType()
    {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type)
    {
        this.type = type;
        setDefault();
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the data
     */
    public String getData()
    {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(String data)
    {
        this.data = data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return name;
    }

    /**
     * @param spec
     */
    public void addSpec(String spec)
    {
        this.spec.add(spec);
    }

    /**
     * @param index
     * @return
     */
    public String getSpec(int index)
    {
        return spec.get(index);
    }

    public String getSpec(int index, String defValue)
    {
        String temp = spec.get(index);
        return temp != null ? temp : defValue;
    }

    public void setSpec(int index, String spec)
    {
        this.spec.set(index, spec);
    }

    public String enCodeSpec(String c)
    {
        if (spec == null || spec.size() < 1)
        {
            return "";
        }

        StringBuffer text = new StringBuffer();
        for (String temp : spec)
        {
            text.append(c).append(temp);
        }
        return text.toString();
    }

    public void deCodeSpec(String text, String c)
    {
        if (text == null || text.length() < 1)
        {
            return;
        }

        spec.clear();

        int s = 0;
        int e = text.indexOf(c, s);
        while (e >= s)
        {
            spec.add(text.substring(s, e));
            s = e + 1;
            e = text.indexOf(c, s);
        }
    }

    public void setDefault()
    {
        if (type == ConsDat.INDX_PWDS)
        {
            spec = new ArrayList<String>(3);
            spec.add(UserMdl.getCfg().getPwdsSet());
            spec.add(UserMdl.getCfg().getPwdsLen());
            spec.add(UserMdl.getCfg().getPwdsUpt());
        }
        else if (type == ConsDat.INDX_FILE)
        {
            spec = new ArrayList<String>(1);
            spec.add("");
        }
        else
        {
            spec = null;
        }
    }
}
