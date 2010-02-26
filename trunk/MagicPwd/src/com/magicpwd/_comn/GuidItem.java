/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comn;

import com.magicpwd._cons.ConsDat;
import java.sql.Timestamp;

/**
 * 使用说明：<br />
 * name:记录创建时间<br />
 * data:所属类别索引<br />
 * spec:[0]口令模板索引<br />
 *
 * @author Amon
 */
public class GuidItem extends EditItem
{

    private Timestamp time;

    public GuidItem()
    {
        super(ConsDat.INDX_GUID);
    }

    @Override
    public String getName()
    {
        String name = time.toString();
        int dot = name.indexOf('.');
        return (dot > 0) ? name = name.substring(0, dot) : name;
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
