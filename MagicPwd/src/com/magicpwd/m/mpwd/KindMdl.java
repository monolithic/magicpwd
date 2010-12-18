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
package com.magicpwd.m.mpwd;

import com.magicpwd._comn.prop.Kind;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.magicpwd.r.KindTN;
import com.magicpwd._cons.ConsDat;
import com.magicpwd.d.db.DBA3000;

/**
 * @author Amon
 * 
 */
public class KindMdl extends DefaultTreeModel
{

    KindMdl(KindTN root)
    {
        super(root);
    }

    void init()
    {
    }

    public void wAppend(TreePath path, Kind kind)
    {
        KindTN r = (KindTN) path.getLastPathComponent();
        if (r == null)
        {
            return;
        }
        r.add(new KindTN(kind));
        nodeStructureChanged(r);

        Kind k = (Kind) r.getUserObject();
        kind.setC2010101(r.getChildCount());
        kind.setC2010104(k.getC2010103());
        DBA3000.updateKindData(kind);
    }

    public void wUpdate(TreePath path, Kind kind)
    {
        KindTN c = (KindTN) path.getLastPathComponent();
        if (c == null)
        {
            return;
        }
        c.setUserObject(kind);
        nodeChanged(c);
        DBA3000.updateKindData(kind);
    }

    public void wRemove(TreePath path)
    {
        KindTN c = (KindTN) path.getLastPathComponent();
        if (c == null)
        {
            return;
        }
        int size = c.getChildCount();
        KindTN r = (KindTN) c.getRoot();
        removeNodeFromParent(c);
        for (int i = 0; i < size; i += 1)
        {
            r.add((KindTN) c.getChildAt(0));
        }
        Kind item = (Kind) c.getUserObject();
        DBA3000.deleteKindData(ConsDat.HASH_ROOT, item, r.getChildCount());
        nodeStructureChanged(r);
    }
}
