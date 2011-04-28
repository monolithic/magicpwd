/*
 *  Copyright (C) 2011 yaoshangwen
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
 *
 * @author yaoshangwen
 */
public class DataItem extends AEditItem
{

    public static final int SPEC_DATA_OPT = 0;//可选输入
    public static final int SPEC_DATA_SET = 1;//数据集
    public static final int SPEC_DATA_INT = 2;//整数位
    public static final int SPEC_DATA_DEC = 3;//小数位
    public static final int SPEC_DATA_CHAR = 4;//特殊符号
    public static final int SPEC_DATA_CHAR_OPT = 5;//是否可选
    public static final int SPEC_DATA_CHAR_POS = 6;//符号位置
    public static final int SPEC_DATA_EXP = 7;//表达式

    public DataItem(UserMdl userMdl)
    {
        super(userMdl, ConsDat.INDX_DATA);
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
