/*
 *  Copyright (C) 2010 Amon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd._comn.item;

import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserMdl;
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

    public GuidItem(UserMdl userMdl)
    {
        super(userMdl, ConsDat.INDX_GUID);
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
