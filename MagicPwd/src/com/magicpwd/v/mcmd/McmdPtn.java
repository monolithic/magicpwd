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
package com.magicpwd.v.mcmd;

import com.magicpwd.__a.AMpwdPtn;
import com.magicpwd.v.MenuPtn;

/**
 * 命令行模式
 * @author Aven
 */
public class McmdPtn extends AMpwdPtn
{

    public McmdPtn()
    {
        super(null, null);
    }

    @Override
    public boolean initView()
    {
        return true;
    }

    @Override
    public boolean initLang()
    {
        return true;
    }

    @Override
    public boolean initData()
    {
        return true;
    }

    @Override
    public boolean showData()
    {
        return true;
    }

    @Override
    public MenuPtn getMenuPtn()
    {
        return null;
    }

    @Override
    public boolean endSave()
    {
        return true;
    }

    @Override
    public void requestFocus()
    {
    }
}