/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mcmd;

import com.magicpwd._comn.mpwd.Mcat;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.mcmd.McmdEnv;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Aven
 */
public class McatMdl
{
    private UserMdl userMdl;
    private java.util.List<Mcat> mcatList;
    private int curIndex;
    private java.util.Map<String, Mcat> mcatMaps;
    private int curPage;
    private boolean allPage;

    public McatMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void init()
    {
        mcatList = new java.util.ArrayList<Mcat>(5);
        mcatMaps = new java.util.HashMap<String, Mcat>();

        Mcat mcat = new Mcat();
        mcat.setC2010203(ConsDat.HASH_ROOT);
        mcat.setC2010106("魔方密码");
        mcat.setC2010207("魔方密码");
        mcatList.add(mcat);
    }

    public String listCat()
    {
        java.util.List<Mcat> list = DBA4000.listCatByHash(userMdl, mcatList.get(curIndex).getC2010203());

        int tmp = list.size();
        int s = curPage * McmdEnv.CAT_PAGE_SIZE;
        int e = s + McmdEnv.CAT_PAGE_SIZE;
        if (e > tmp)
        {
            e = tmp;
        }

        StringBuilder buf = new StringBuilder();
        int i = 0;
        Mcat mcat;
        String t;
        while (s < e)
        {
            mcat = list.get(s++);
            t = genKey(i++);
            buf.append(t).append(' ').append(mcat.getC2010206()).append('\n');
            mcatMaps.put(t, mcat);
        }
        return buf.toString();
    }

    public String firstPage()
    {
        return "";
    }

    public String prevPage()
    {
        return "";
    }

    public String nextPage()
    {
        return "";
    }

    public String lastPage()
    {
        return "";
    }

    public Mcat getCat()
    {
        return mcatList.get(curIndex);
    }

    private String genKey(int i)
    {
        return (allPage ? "" + curPage : "") + i;
    }
}
