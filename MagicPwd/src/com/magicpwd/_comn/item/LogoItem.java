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

import com.magicpwd.__a.AEditItem;
import com.magicpwd._cons.ConsDat;
import com.magicpwd.m.UserMdl;

/**
 * name:徽标索引
 * data:徽标说明
 * @author Amon
 */
public class LogoItem extends AEditItem
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

    @Override
    public String exportAsTxt()
    {
        return "";
    }

    @Override
    public String exportAsXml()
    {
        return "";
    }

    @Override
    public boolean importByTxt(String txt)
    {
        return true;
    }

    @Override
    public boolean importByXml(String xml)
    {
        return true;
    }
}
