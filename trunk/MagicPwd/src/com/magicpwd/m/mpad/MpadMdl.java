/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mpad;

import com.magicpwd.m.UserMdl;
import com.magicpwd.m.mwiz.KeysMdl;

/**
 *
 * @author Amon
 */
public final class MpadMdl
{

    private NoteMdl noteMdl;
    private KeysMdl keysMdl;

    public MpadMdl(UserMdl userMdl)
    {
        keysMdl = new KeysMdl(userMdl);
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

    /**
     * @return the keysMdl
     */
    public KeysMdl getKeysMdl()
    {
        return keysMdl;
    }

    /**
     * @param keysMdl the keysMdl to set
     */
    public void setKeysMdl(KeysMdl keysMdl)
    {
        this.keysMdl = keysMdl;
    }
}
