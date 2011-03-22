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
package com.magicpwd.e.mexp.kind;

import com.magicpwd.__a.mexp.AMexpAction;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.r.KindTN;

/**
 *
 * @author Amon
 */
public class DeleteAction extends AMexpAction
{

    public DeleteAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.tree.TreePath path = mexpPtn.getSelectedKindValue();
        if (path == null)
        {
            return;
        }

        Object obj = path.getLastPathComponent();
        if (obj == null || !(obj instanceof KindTN))
        {
            return;
        }

        KindTN node = (KindTN) obj;
        if (node.isRoot())
        {
            return;
        }

        if (Lang.showFirm(mexpPtn, LangRes.P30F7A1A, "执行此操作后，此类别下的其它类别将会移动到根类别下，\n确认要删除“{0}”类别么？", node.toString()) == javax.swing.JOptionPane.YES_OPTION)
        {
            mexpPtn.getTreeMdl().wRemove(path);
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
