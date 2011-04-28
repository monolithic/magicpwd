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
package com.magicpwd.__a;

import com.magicpwd.__i.IEditItem;
import com.magicpwd._comn.item.GuidItem;
import com.magicpwd._comn.item.LogoItem;
import com.magicpwd._comn.item.MetaItem;
import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserMdl;

/**
 * 口令属性
 * @author Amon
 */
public abstract class AEditItem implements IEditItem
{

    /** 记录类别 */
    private int type;
    /** 记录名称 */
    private String name;
    /** 记录内容 */
    private String data;
    /** 专有内容 */
    protected java.util.List<String> spec;
    protected UserMdl userCfg;

    /**
     * 
     */
    protected AEditItem(UserMdl userCfg)
    {
        this(userCfg, 0);
    }

    /**
     * @param type
     */
    protected AEditItem(UserMdl userCfg, int type)
    {
        this(userCfg, type, "", "");
    }

    /**
     * @param type
     * @param name
     * @param data
     */
    protected AEditItem(UserMdl userCfg, int type, String name, String data)
    {
        this.userCfg = userCfg;
        this.type = type;
        this.name = name;
        this.data = data;
        this.spec = new java.util.ArrayList<String>(5);
        setDefault();
    }

    public static IEditItem getInstance(UserMdl userMdl, int type)
    {
        switch (type)
        {
            case ConsDat.INDX_TEXT:
                return new LogoItem(userMdl);
            case ConsDat.INDX_PWDS:
                return new LogoItem(userMdl);
            case ConsDat.INDX_LINK:
                return new LogoItem(userMdl);
            case ConsDat.INDX_MAIL:
                return new LogoItem(userMdl);
            case ConsDat.INDX_DATE:
                return new LogoItem(userMdl);
            case ConsDat.INDX_AREA:
                return new LogoItem(userMdl);
            case ConsDat.INDX_FILE:
                return new LogoItem(userMdl);
            case ConsDat.INDX_DATA:
                return new LogoItem(userMdl);
            case ConsDat.INDX_LIST:
                return new LogoItem(userMdl);
            case ConsDat.INDX_SIGN:
                return new LogoItem(userMdl);
            case ConsDat.INDX_GUID:
                return new GuidItem(userMdl);
            case ConsDat.INDX_META:
                return new MetaItem(userMdl);
            case ConsDat.INDX_LOGO:
                return new LogoItem(userMdl);
            case ConsDat.INDX_HINT:
                return new LogoItem(userMdl);
            default:
                return null;
        }
    }

    public static IEditItem getInstance(UserMdl userMdl, int type, String name, String data)
    {
        IEditItem item = getInstance(userMdl, type);
        if (item != null)
        {
            item.setName(name);
            item.setData(data);
        }
        return item;
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
        return (index > -1 && index < spec.size()) ? spec.get(index) : null;
    }

    @Override
    public String getSpec(int index, String defValue)
    {
        if (index > -1 && index < spec.size())
        {
            String temp = spec.get(index);
            return temp != null ? temp : defValue;
        }
        return defValue;
    }

    @Override
    public void setSpec(int index, String spec)
    {
        if (index == this.spec.size())
        {
            this.spec.add(spec);
        }
        else
        {
            this.spec.set(index, spec);
        }
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
//
//    @Override
//    public final void setDefault()
//    {
//        switch (type)
//        {
//            case ConsDat.INDX_GUID:
//                spec = new ArrayList<String>(2);
//                spec.add(SPEC_VALUE_NONE);
//                spec.add(SPEC_VALUE_NONE);
//                break;
//            case ConsDat.INDX_PWDS:
//                spec = new ArrayList<String>(3);
//                spec.add(userCfg.getPwdsKey());
//                spec.add(userCfg.getPwdsLen());
//                spec.add(userCfg.getPwdsLoop());
//                break;
//            case ConsDat.INDX_DATE:
//                spec = new ArrayList<String>(1);
//                spec.add(SPEC_VALUE_NONE);
//                break;
//            case ConsDat.INDX_FILE:
//                spec = new ArrayList<String>(1);
//                spec.add(SPEC_VALUE_NONE);
//                break;
//            case ConsDat.INDX_DATA:
//                spec = new ArrayList<String>(7);
//                spec.add(SPEC_VALUE_TRUE);
//                spec.add("+0-");
//                spec.add("0");
//                spec.add("8");
//                spec.add(SPEC_VALUE_NONE);
//                spec.add(SPEC_VALUE_TRUE);
//                spec.add("^");
//                spec.add(SPEC_VALUE_FAIL);
//                break;
//            case ConsDat.INDX_SIGN:
//                spec = new ArrayList<String>(2);
//                spec.add("def");
//                spec.add("P30F7E02");
//                break;
//            default:
//                spec = null;
//                break;
//        }
//    }

    public static String doEscape(String txt)
    {
        return txt != null ? txt.replace("\\", "\\\\").replace(",", "\\,").replace(";", "\\;").replaceAll("[\r\n]+", "\\n") : txt;
    }

    public static String unEscape(String txt)
    {
        return txt != null ? txt.replace("\\n", "\n").replace("\\;", ";").replace("\\,", ",").replace("\\\\", "\\") : txt;
    }
    /**
     * 附加属性常量
     */
    public static final String SPEC_VALUE_TRUE = "1";
    public static final String SPEC_VALUE_FAIL = "0";
    public static final String SPEC_VALUE_NONE = "";
}
