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
public class Mark extends Item
{

    private int status = ConsDat.PWDS_STAT_0;

    public Mark()
    {
        super(ConsDat.INDX_MARK);
    }

    @Override
    public String getName()
    {
        return "";
    }

    @Override
    public void setName(String name)
    {
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
