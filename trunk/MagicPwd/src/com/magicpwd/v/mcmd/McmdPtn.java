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

import com.magicpwd.__a.AFrame;
import com.magicpwd.v.MenuPtn;

/**
 * 命令行模式
 * @author Aven
 */
public class McmdPtn extends AFrame
{

    public McmdPtn()
    {
        super(null, null);
    }

    public void initView()
    {
    }

    public void initLang()
    {
    }

    public void initData()
    {
    }

    public void showData()
    {
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
