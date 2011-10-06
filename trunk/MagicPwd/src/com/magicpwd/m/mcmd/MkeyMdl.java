/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mcmd;

import com.magicpwd.__i.IEditItem;
import com.magicpwd._comn.item.SignItem;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.m.SafeMdl;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Aven
 */
public class MkeyMdl extends SafeMdl
{
    private int curPage;
    private boolean allPage;
    private java.util.Map<String, IEditItem> itemMaps;

    public MkeyMdl(UserMdl userMdl)
    {
        super(userMdl);
    }

    public void init()
    {
        itemMaps = new java.util.HashMap<String, IEditItem>();
    }

    @Override
    public void initHead()
    {
    }

    @Override
    public void initBody()
    {
    }

    @Override
    public void clear()
    {
        itemMaps.clear();
        itemList.clear();
        mkey.setDefault();
    }

    public boolean copyName(String key)
    {
        key = key.trim().toLowerCase();
        if (!java.util.regex.Pattern.matches("^\\d?[a-z]$", key))
        {
            return false;
        }

        if (!itemMaps.containsKey(key))
        {
            return false;
        }

        Util.copy2Clipboard(itemMaps.get(key).getName());
        return true;
    }

    public boolean copyData(String key)
    {
        key = key.trim().toLowerCase();
        if (!java.util.regex.Pattern.matches("^\\d?[a-z]$", key))
        {
            return false;
        }

        if (!itemMaps.containsKey(key))
        {
            return false;
        }

        Util.copy2Clipboard(itemMaps.get(key).getData());
        return true;
    }

    public void nextPage()
    {
    }

    public void prevPage()
    {
    }

    public String display()
    {
        StringBuilder buf = new StringBuilder();
        int cnt = itemList.size();
        int max = cnt > 26 ? 26 : cnt;

        char c = 'a';
        IEditItem item;

        int i = 0;
        item = itemList.get(i++);
        buf.append(Lang.getLang(LangRes.P30F1106, "日期 ")).append(item.toString()).append('\n');

        item = itemList.get(i++);
        buf.append(Lang.getLang(LangRes.P30F110A, "标题 ")).append(item.toString()).append('\n');

        item = itemList.get(i++);
        buf.append(Lang.getLang(LangRes.P30F1112, "徽标 ")).append(item.toString()).append('\n');

        item = itemList.get(i++);
        buf.append(Lang.getLang(LangRes.P30F110B, "提醒 ")).append(item.toString()).append('\n');

        String t;
        while (i < max)
        {
            item = itemList.get(i++);
            t = genKey(c++);
            itemMaps.put(t, item);
            buf.append(t).append(' ');
            if (item.getType() == ConsDat.INDX_SIGN)
            {
                buf.append(Char.format(Lang.getLang(item.getSpec(SignItem.SPEC_SIGN_TPLT), ""), item.getName()));
            }
            else
            {
                buf.append(item.toString());
            }
            buf.append('\n');
        }

        buf.append(Char.format("当前 {0}/{1}，输入<<、<、>或>>翻屏显示。\n", "" + curPage + 1, "" + cnt / 26 + 1));
        return buf.toString();
    }

    private String genKey(char c)
    {
        return (allPage ? "" + curPage : "") + c;
    }

    /**
     * @return the allPage
     */
    public boolean isAllPage()
    {
        return allPage;
    }

    /**
     * @param allPage the allPage to set
     */
    public void setAllPage(boolean allPage)
    {
        this.allPage = allPage;
    }
}
