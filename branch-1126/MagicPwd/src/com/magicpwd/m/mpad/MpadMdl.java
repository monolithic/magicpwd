/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mpad;

import com.magicpwd.m.UserMdl;

/**
 *
 * @author Amon
 */
public final class MpadMdl
{

    private UserMdl userMdl;
    private NoteMdl noteMdl;

    public MpadMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void init()
    {
        noteMdl = new NoteMdl(userMdl);
        noteMdl.init();
    }

    /**
     * @return the noteMdl
     */
    public NoteMdl getNoteMdl()
    {
        return noteMdl;
    }

    /**
     * @param noteMdl the noteMdl to set
     */
    public void setNoteMdl(NoteMdl noteMdl)
    {
        this.noteMdl = noteMdl;
    }
}
