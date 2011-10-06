/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mcmd;

import com.magicpwd._comn.mpwd.MpwdHeader;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Aven
 */
public class McatMdl
{
    private UserMdl userMdl;
    private java.util.List<MpwdHeader> mpwdList;
    private java.util.Map<String, MpwdHeader> mpwdMaps;
    private int curPage;
    private boolean allPage;

    public McatMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void init()
    {
        mpwdList = new java.util.ArrayList<MpwdHeader>();
        mpwdMaps = new java.util.HashMap<String, MpwdHeader>();
    }

    private void listKeys(String hash)
    {
        mpwdList.clear();
        DBA4000.listRecHeaderByCat(userMdl, hash, mpwdList);

        int cnt = mpwdList.size();
        if (cnt > 26)
        {
            cnt = 26;
        }

        char c = 'a';
        String t;
        MpwdHeader header;
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < cnt; i += 1)
        {
            header = mpwdList.get(i);
            t = genKey(c++);
            mpwdMaps.put(t, header);
            buf.append(t).append(' ').append(header.getP30F0109()).append('\n');
        }
        //Lang.showMesg(console, null, buf.toString());
    }

    public MpwdHeader viewKeys(String cmd)
    {
        cmd = cmd.trim().toLowerCase();
        if (!java.util.regex.Pattern.matches("^\\d*[a-z]$", cmd))
        {
            return null;
        }

        return mpwdMaps.get(cmd);
    }

    private String genKey(char c)
    {
        return (allPage ? "" + curPage : "") + c;
    }
}
