/*
 *  Copyright (C) 2010 Amon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd._comn.item;

import com.magicpwd.__i.IEditItem;
import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserMdl;
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
    private UserMdl userCfg;

    /**
     * 
     */
    public EditItem(UserMdl userCfg)
    {
        this(userCfg, 0);
    }

    /**
     * @param type
     */
    public EditItem(UserMdl userCfg, int type)
    {
        this(userCfg, type, "", "");
    }

    /**
     * @param type
     * @param name
     * @param data
     */
    public EditItem(UserMdl userCfg, int type, String name, String data)
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
    public final void setDefault()
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
                spec = new ArrayList<String>(7);
                spec.add(SPEC_VALUE_TRUE);
                spec.add("+0-");
                spec.add("0");
                spec.add("6");
                spec.add("");
                spec.add(SPEC_VALUE_TRUE);
                spec.add("^");
                break;
            case ConsDat.INDX_SIGN:
                spec = new ArrayList<String>(2);
                spec.add("def");
                spec.add("P30F7E02");
                break;
            default:
                spec = null;
                break;
        }
    }
}
