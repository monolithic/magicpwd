/*
 *  Copyright (C) 2011 Aven
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
package com.magicpwd.e.maoc.mnum;

import com.magicpwd.__a.maoc.AMaocAction;
import com.magicpwd._cons.lang.MaocRes;
import com.magicpwd._util.Lang;

/**
 *
 * @author Aven
 */
public class DeleteAction extends AMaocAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (Lang.showFirm(maocPtn, MaocRes.P30FBA0B, "确认要删除此常量吗？\n删除后其它依赖于此常量的常量或函数将无法参与运算！") == javax.swing.JOptionPane.YES_OPTION)
        {
            maocPtn.deleteNum();
        }
    }

    @Override
    public void doInit(String value)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button, String value)
    {
    }
}
