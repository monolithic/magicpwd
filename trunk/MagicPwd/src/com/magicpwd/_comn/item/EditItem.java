/**
 * 
 */
package com.magicpwd._comn.item;

import com.magicpwd.__i.IEditItem;
import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserCfg;
import java.util.ArrayList;
import java.util.List;

/**
 * 口令属性
 * @author Amon
 */
public class EditItem implements IEditItem
{

    /** 记录类别 */
    private int type;
    /** 记录名称 */
    private String name;
    /** 记录内容 */
    private String data;
    /** 专有内容 */
    private List<String> spec;
    private UserCfg userCfg;

    /**
     * 
     */
    public EditItem(UserCfg userCfg)
    {
        this(userCfg, 0);
    }

    /**
     * @param type
     */
    public EditItem(UserCfg userCfg, int type)
    {
        this(userCfg, type, "", "");
    }

    /**
     * @param type
     * @param name
     * @param data
     */
    public EditItem(UserCfg userCfg, int type, String name, String data)
    {
        this.userCfg = userCfg;
        this.type = type;
        this.name = name;
        this.data = data;
        setDefault();
    }

    /**
     * @return the type
     */
    @Override
    public int getType()
    {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    @Override
    public void setType(int type)
    {
        this.type = type;
        setDefault();
    }

    /**
     * @return the name
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the data
     */
    @Override
    public String getData()
    {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    @Override
    public boolean setData(String data)
    {
        this.data = data;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return getName();
    }

    /**
     * @param spec
     */
    @Override
    public void addSpec(String spec)
    {
        this.spec.add(spec);
    }

    /**
     * @param index
     * @return
     */
    @Override
    public String getSpec(int index)
    {
        return spec.get(index);
    }

    @Override
    public String getSpec(int index, String defValue)
    {
        String temp = spec.get(index);
        return temp != null ? temp : defValue;
    }

    @Override
    public void setSpec(int index, String spec)
    {
        this.spec.set(index, spec);
    }

    @Override
    public String enCodeSpec(String c)
    {
        if (spec == null || spec.size() < 1)
        {
            return "";
        }

        StringBuilder text = new StringBuilder();
        for (int i = 0, j = spec.size(); i < j; i += 1)
        {
            text.append(c).append(spec.get(i));
        }
        return text.toString();
    }

    @Override
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

    @Override
    public void setDefault()
    {
        switch (type)
        {
            case ConsDat.INDX_GUID:
                spec = new ArrayList<String>(2);
                spec.add("");
                spec.add("");
                break;
            case ConsDat.INDX_PWDS:
                spec = new ArrayList<String>(3);
                spec.add(userCfg.getPwdsKey());
                spec.add(userCfg.getPwdsLen());
                spec.add(userCfg.getPwdsLoop());
                break;
            case ConsDat.INDX_DATE:
                spec = new ArrayList<String>(1);
                spec.add("");
                break;
            case ConsDat.INDX_FILE:
                spec = new ArrayList<String>(1);
                spec.add("");
                break;
            case ConsDat.INDX_DATA:
                spec = new ArrayList<String>(5);
                spec.add("");
                spec.add("");
                spec.add("");
                spec.add("");
                spec.add("");
                break;
            default:
                spec = null;
                break;
        }
    }
}
