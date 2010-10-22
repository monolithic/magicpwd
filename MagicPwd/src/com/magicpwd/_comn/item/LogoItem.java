/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comn.item;

import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Amon
 */
public class LogoItem extends EditItem
{

    public LogoItem(UserMdl userMdl)
    {
        super(userMdl, ConsDat.INDX_LOGO);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return getData();
    }
}
