/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comn;

import com.magicpwd._cons.ConsDat;
import java.sql.Timestamp;

/**
 *
 * @author Administrator
 */
public class Guid extends Item
{

    private Timestamp time;

    public Guid()
    {
        super(ConsDat.INDX_GUID);
    }

    @Override
    public String getName()
    {
        return time.toString();
    }

    @Override
    public void setName(String name)
    {
    }

    public Timestamp getTime()
    {
        return time;
    }

    public void setTime(Timestamp time)
    {
        this.time = time;
    }
}
