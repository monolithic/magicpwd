/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mpwd;

import com.magicpwd._comn.prop.Kind;
import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.KindTN;

/**
 *
 * @author Amon
 */
public final class MpwdMdl
{

    private TreeMdl treeMdl;
    private ListMdl listMdl;
    private GridMdl gridMdl;
    private UserMdl userMdl;

    public MpwdMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void init()
    {
        listMdl = new ListMdl(userMdl);
        listMdl.init();

        Kind kind = new Kind();
        kind.setC2010103(ConsDat.HASH_ROOT);
        kind.setC2010105("魔方密码");
        kind.setC2010106("魔方密码");
        treeMdl = new TreeMdl(new KindTN(kind));
        treeMdl.init();

        gridMdl = new GridMdl(userMdl);
        gridMdl.init();
    }

    /**
     * @return the listMdl
     */
    public ListMdl getListMdl()
    {
        return listMdl;
    }

    /**
     * @return the treeMdl
     */
    public TreeMdl getTreeMdl()
    {
        return treeMdl;
    }

    /**
     * @return the gridMdl
     */
    public GridMdl getGridMdl()
    {
        return gridMdl;
    }
}
