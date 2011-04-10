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
package com.magicpwd.r;

import com.magicpwd._comn.prop.Kind;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.magicpwd.d.db.DBA3000;

/**
 * @author Amon
 * 
 */
public class KindTN extends DefaultMutableTreeNode
{
    /**
     * @param kvItem
     */
    public KindTN(Kind kvItem)
    {
        super(kvItem);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.tree.DefaultMutableTreeNode#isLeaf()
     */
    @Override
    public boolean isLeaf()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.tree.DefaultMutableTreeNode#getChildCount()
     */
    @Override
    public int getChildCount()
    {
        if (!hasLoaded)
        {
            loadChildren();
        }
        return super.getChildCount();
    }

    /**
     * 获取下级目录数据列表
     */
    private void loadChildren()
    {
        Kind kvItem = (Kind) getUserObject();
        if (kvItem != null)
        {
            List<Kind> list = DBA3000.selectKindData(kvItem.getC2010203());
            if (list != null)
            {
                for (int i = 0, j = list.size(); i < j; i += 1)
                {
                    insert(new KindTN(list.get(i)), i);
                }
            }
        }
        hasLoaded = true;
    }

    /**
     * @return
     */
    public boolean isLoaded()
    {
        return hasLoaded;
    }
    private boolean hasLoaded;
}
