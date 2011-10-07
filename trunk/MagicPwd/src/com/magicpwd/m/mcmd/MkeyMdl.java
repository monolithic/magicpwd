/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mcmd;

import com.magicpwd._comn.mpwd.MpwdHeader;
import com.magicpwd._cons.mcmd.McmdEnv;
import com.magicpwd._util.Char;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Aven
 */
public class MkeyMdl
{
    private UserMdl userMdl;
    private java.util.List<MpwdHeader> mpwdList;
    private java.util.Map<String, MpwdHeader> mpwdMaps;
    private int curPage;
    private int maxPage;
    private boolean allPage;

    public MkeyMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void init()
    {
        mpwdList = new java.util.ArrayList<MpwdHeader>();
        mpwdMaps = new java.util.HashMap<String, MpwdHeader>();
    }

    public String listKey(String catHash)
    {
        mpwdList.clear();
        if (!Char.isValidate(catHash))
        {
            catHash = "0";
        }
        DBA4000.listKeyHeaderByCat(userMdl, catHash, mpwdList);

        int tmp = mpwdList.size();
        int s = curPage * McmdEnv.KEY_PAGE_SIZE;
        int e = s + McmdEnv.KEY_PAGE_SIZE;
        if (e > tmp)
        {
            e = tmp;
        }

        char c = 'a';
        String t;
        MpwdHeader header;
        StringBuilder buf = new StringBuilder();
        while (s < e)
        {
            header = mpwdList.get(s++);
            t = genKey(c++);
            mpwdMaps.put(t, header);
            buf.append(t).append(' ').append(header.getP30F0109()).append('\n');
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

    public MpwdHeader getKey(String cmd)
    {
        cmd = cmd.trim().toLowerCase();
        return java.util.regex.Pattern.matches("^\\d*[a-z]$", cmd) ? mpwdMaps.get(cmd) : null;
    }

    private String genKey(char c)
    {
        return (allPage ? "" + curPage : "") + c;
    }
}
