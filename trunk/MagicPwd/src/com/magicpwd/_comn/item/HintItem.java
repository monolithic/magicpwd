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
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import java.sql.Timestamp;

/**
 *
 * @author Amon
 */
public class HintItem extends EditItem
{

    private Timestamp time;

    public HintItem(UserMdl userMdl)
    {
        super(userMdl, ConsDat.INDX_HINT);
    }

    @Override
    public String getData()
    {
        if (time == null)
        {
            return "";
        }
        String name = time.toString();
        int dot = name.indexOf('.');
        return dot > 0 ? name.substring(0, dot) : name;
    }

    @Override
    public boolean setData(String data)
    {
        if (!com.magicpwd._util.Char.isValidateDateTime(data))
        {
            return false;
        }
        try
        {
            time = new java.sql.Timestamp(com.magicpwd._util.Date.toDate(data, '-', ':', ' ').getTimeInMillis());
            return true;
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            return false;
        }
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
