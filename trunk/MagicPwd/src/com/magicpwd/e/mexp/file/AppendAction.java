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
package com.magicpwd.e.mexp.file;

import com.magicpwd.__a.mexp.AMexpAction;
import com.magicpwd._enum.AppView;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public class AppendAction extends AMexpAction
{

    public AppendAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (!mexpPtn.newKeys())
        {
            return;
        }

        if (mexpPtn.getUserMdl().isEditVisible(AppView.mexp))
        {
            return;
        }

        String cmd = e.getActionCommand();
        if (Char.isValidate(cmd))
        {
            String[] arr = cmd.split(",");
            if (arr != null && arr.length == 2)
            {
                mexpPtn.getMenuPtn().getButton(arr[0]).setSelected(true);
                mexpPtn.getMenuPtn().getButton(arr[1]).setSelected(true);
            }
        }

        mexpPtn.getUserMdl().setEditVisible(AppView.mexp, true);
        mexpPtn.getUserMdl().setEditIsolate(AppView.mexp, true);

        mexpPtn.setEditVisible(true);
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
