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
package com.magicpwd.m.mexp;

import com.magicpwd._comn.mpwd.Keys;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import com.magicpwd.d.db.DBA4000;
import com.magicpwd.m.UserMdl;

/**
 * @author Amon
 * 
 */
public class ListMdl extends DefaultListModel
{

    private java.util.List<Keys> keysList;
    private UserMdl userMdl;

    ListMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    void init()
    {
        keysList = new ArrayList<Keys>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public Object getElementAt(int index)
    {
        return keysList.get(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize()
    {
        return keysList != null ? keysList.size() : 0;
    }

    public void listHint(java.util.Date s, java.util.Date t)
    {
        int c = keysList.size();
        keysList.clear();
        fireIntervalRemoved(this, 0, c);
        DBA4000.findHintList(userMdl, new java.sql.Timestamp(s.getTime()), new java.sql.Timestamp(t.getTime()), keysList);
        c = keysList.size();
        fireIntervalAdded(this, 0, c);
    }

    public boolean listKeysByKind(String typeHash)
    {
        int s = keysList.size();
        keysList.clear();
        fireIntervalRemoved(this, 0, s);
        boolean b = DBA4000.readKeysList(userMdl, typeHash, keysList);
        s = keysList.size();
        b &= s > 0;
        fireIntervalAdded(this, 0, s);
        return b;
    }

    public boolean listKeysByMeta(String keysMeta)
    {
        int s = keysList.size();
        keysList.clear();
        fireIntervalRemoved(this, 0, s);
        boolean b = DBA4000.findUserData(userMdl, keysMeta, keysList);
        s = keysList.size();
        b &= s > 0;
        if (b)
        {
            fireIntervalAdded(this, 0, s);
        }
        return b;
    }

    public boolean updtName(String hash, String name, String icon)
    {
        Keys keys;
        for (int i = 0, j = keysList.size(); i < j; i += 1)
        {
            keys = keysList.get(i);
            if (keys.getP30F0104().equals(hash))
            {
                keys.setP30F0109(name);
                keys.setP30F010B(icon);
                fireContentsChanged(this, i, i);
                return true;
            }
        }
        return false;
    }

    public void wAppend(Keys keys)
    {
        keysList.add(keys);
        this.fireIntervalAdded(this, 0, keysList.size());
    }

    public void wUpdate()
    {
        fireIntervalRemoved(this, 0, keysList.size());
    }

    public void wRemove(int index)
    {
        keysList.remove(index);
        fireIntervalRemoved(this, 0, index);
    }

    public void wRemove(Keys keys)
    {
        keysList.remove(keys);
        fireIntervalRemoved(this, 0, keysList.size());
    }

    public void wDelete(int index)
    {
        if (index > -1 && index < keysList.size())
        {
            DBA4000.deletePwdsData(keysList.get(index).getP30F0104());
            keysList.remove(index);
            fireIntervalRemoved(this, 0, index);
        }
    }

    public Keys getElement(int index)
    {
        return keysList.get(index);
    }
}
