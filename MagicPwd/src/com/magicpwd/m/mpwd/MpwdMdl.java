/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mpwd;

import com.magicpwd._comn.prop.Kind;
import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.r.KindTN;

/**
 *
 * @author Amon
 */
public class MpwdMdl extends CoreMdl
{

    private ListMdl listMdl;
    private TreeMdl treeMdl;

    public MpwdMdl()
    {
    }

    public void loadPre()
    {
        listMdl = new ListMdl(this);
        Kind kind = new Kind();
        kind.setC2010103(ConsDat.HASH_ROOT);
        kind.setC2010105("魔方密码");
        kind.setC2010106("魔方密码");
        treeMdl = new TreeMdl(new KindTN(kind));
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
}
