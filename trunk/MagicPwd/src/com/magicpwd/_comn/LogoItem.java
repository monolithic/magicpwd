/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comn;

import com.magicpwd._cons.ConsDat;

/**
 *
 * @author Administrator
 */
public class LogoItem extends EditItem
{

    public LogoItem()
    {
        super(ConsDat.INDX_LOGO);
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
