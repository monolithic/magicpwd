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
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.prop.Kind;
import com.magicpwd.r.KindTN;
import com.magicpwd.v.mexp.KindDlg;

/**
 *
 * @author Amon
 */
public class UpdateAction extends AMexpAction implements IBackCall<Kind>
{

    public UpdateAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.tree.TreePath path = mpwdPtn.getSelectedKindValue();
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

        KindDlg kindDlg = new KindDlg(mpwdPtn, this);
        kindDlg.initView();
        kindDlg.initLang();
        kindDlg.initData((Kind) node.getUserObject());
        kindDlg.setVisible(true);
    }

    @Override
    public void doInit(String value)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button, String value)
    {
    }

    @Override
    public boolean callBack(String options, Kind object)
    {
        if (OPTIONS_APPLY.equalsIgnoreCase(options))
        {
            mpwdPtn.updateKindBySelected(object);
            return true;
        }
        return false;
    }
}
