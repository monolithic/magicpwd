/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mcmd;

import com.magicpwd.__i.IEditItem;
import com.magicpwd._comn.item.SignItem;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.LangRes;
import com.magicpwd._cons.mcmd.McmdEnv;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.m.SafeMdl;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Aven
 */
public class MattMdl extends SafeMdl
{
    private int curPage;
    private int maxPage;
    private boolean allPage;
    private java.util.Map<String, IEditItem> itemMaps;

    public MattMdl(UserMdl userMdl)
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

    @Override
    public void loadData(String mkeyHash) throws Exception
    {
        super.loadData(mkeyHash);
        maxPage = itemList.size() / McmdEnv.ATT_PAGE_SIZE;
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

    public String display()
    {
        StringBuilder buf = new StringBuilder();

        int tmp = itemList.size();
        int s = curPage * McmdEnv.ATT_PAGE_SIZE;
        int e = s + McmdEnv.ATT_PAGE_SIZE;
        if (e > tmp)
        {
            e = tmp;
        }

        char c = 'a';
        IEditItem item;

        if (curPage == 0)
        {
            item = itemList.get(s++);
            buf.append(Lang.getLang(LangRes.P30F1106, "日期 ")).append(item.toString()).append('\n');

            item = itemList.get(s++);
            buf.append(Lang.getLang(LangRes.P30F110A, "标题 ")).append(item.toString()).append('\n');

            item = itemList.get(s++);
            buf.append(Lang.getLang(LangRes.P30F1112, "徽标 ")).append(item.toString()).append('\n');

            item = itemList.get(s++);
            buf.append(Lang.getLang(LangRes.P30F110B, "提醒 ")).append(item.toString()).append('\n');
        }

        String t;
        while (s < e)
        {
            item = itemList.get(s++);
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

        if (maxPage > 0)
        {
            buf.append(Char.format("当前 {0}/{1}，输入<<、<、>或>>翻屏显示。\n", "" + (curPage + 1), "" + (maxPage + 1)));
        }
        return buf.toString();
    }

    public String firstPage()
    {
        curPage = 0;
        return "";
    }

    public String prevPage()
    {
        if (curPage <= 0)
        {
            return "";
        }

        curPage -= 1;
        return "";
    }

    public String nextPage()
    {
        if (curPage >= maxPage)
        {
            return "";
        }

        curPage += 1;
        return "";
    }

    public String lastPage()
    {
        curPage = maxPage;
        return "";
    }

    private String genKey(char c)
    {
        return (allPage ? "" + curPage : "") + c;
    }
}
