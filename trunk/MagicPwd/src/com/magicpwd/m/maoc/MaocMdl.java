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
package com.magicpwd.m.maoc;

import com.magicpwd.m.UserMdl;

/**
 * Application: MagicPwd
 * Author     : Amon
 * Encoding   : UTF-8
 * Created    : 2010-12-14 16:13:15
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class MaocMdl
{

    private UserMdl userMdl;
    private GridMdl gridMdl;
    private boolean distinct;

    public MaocMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void init()
    {
        gridMdl = new GridMdl(this);
    }

    /**
     * @return the gridMdl
     */
    public GridMdl getGridMdl()
    {
        return gridMdl;
    }

    /**
     * @return the distinct
     */
    public boolean isDistinct()
    {
        return distinct;
    }

    /**
     * @param distinct the distinct to set
     */
    public void setDistinct(boolean distinct)
    {
        this.distinct = distinct;
    }
}
