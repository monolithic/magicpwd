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
package com.magicpwd.m.mpro;

import com.magicpwd._comn.mpwd.Hint;
import com.magicpwd._comn.mpwd.Mkey;
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

    private java.util.List<Mkey> mkeyList;
    private UserMdl userMdl;

    ListMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    void init()
    {
        mkeyList = new ArrayList<Mkey>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public Object getElementAt(int index)
    {
        return mkeyList.get(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize()
    {
        return mkeyList != null ? mkeyList.size() : 0;
    }

    public void reLayout()
    {
        this.fireContentsChanged(this, 0, mkeyList.size());
    }

    public void listHint(java.util.List<Hint> hintList)
    {
        int c = mkeyList.size();
        mkeyList.clear();
//        fireIntervalRemoved(this, 0, c);
        DBA4000.findHintList(userMdl, hintList, mkeyList);
        c = mkeyList.size();
        fireIntervalAdded(this, 0, c);
    }

    public void listHint(java.util.Date s, java.util.Date t)
    {
        int c = mkeyList.size();
        mkeyList.clear();
        fireIntervalRemoved(this, 0, c);
//        DBA4000.findHintList(userMdl, new java.sql.Timestamp(s.getTime()), new java.sql.Timestamp(t.getTime()), keysList);
        c = mkeyList.size();
        fireIntervalAdded(this, 0, c);
    }

    public boolean listKeysByKind(String typeHash)
    {
        userMdl.clearDataIcon();

        int s = mkeyList.size();
        mkeyList.clear();
        fireIntervalRemoved(this, 0, s);
        boolean b = DBA4000.readKeysList(userMdl, typeHash, mkeyList);
        s = mkeyList.size();
        b &= s > 0;
        fireIntervalAdded(this, 0, s);
        return b;
    }

    public boolean listKeysByMeta(String keysMeta)
    {
        userMdl.clearDataIcon();

        int s = mkeyList.size();
        mkeyList.clear();
        fireIntervalRemoved(this, 0, s);
        boolean b = DBA4000.findUserData(userMdl, keysMeta, mkeyList);
        s = mkeyList.size();
        b &= s > 0;
        if (b)
        {
            fireIntervalAdded(this, 0, s);
        }
        return b;
    }

    public boolean updtName(String mkeyHash, String mkeyName, String iconPath, String iconHash)
    {
        Mkey keys;
        for (int i = 0, j = mkeyList.size(); i < j; i += 1)
        {
            keys = mkeyList.get(i);
            if (keys.getP30F0104().equals(mkeyHash))
            {
                keys.setP30F0109(mkeyName);
                keys.setP30F010B(iconHash);
                keys.setP30F010C(iconPath);
                fireContentsChanged(this, i, i);
                return true;
            }
        }
        return false;
    }

    public void wAppend(Mkey keys)
    {
        mkeyList.add(keys);
        this.fireIntervalAdded(this, 0, mkeyList.size());
    }

    public void wUpdate()
    {
        fireIntervalRemoved(this, 0, mkeyList.size());
    }

    public void wRemove(int index)
    {
        mkeyList.remove(index);
        fireIntervalRemoved(this, 0, index);
    }

    public void wRemove(Mkey keys)
    {
        mkeyList.remove(keys);
        fireIntervalRemoved(this, 0, mkeyList.size());
    }

    public void wDelete(int index)
    {
        if (index > -1 && index < mkeyList.size())
        {
            DBA4000.deletePwdsData(userMdl, mkeyList.get(index).getP30F0104());
            mkeyList.remove(index);
            fireIntervalRemoved(this, 0, index);
        }
    }

    public Mkey getElement(int index)
    {
        return mkeyList.get(index);
    }
}
