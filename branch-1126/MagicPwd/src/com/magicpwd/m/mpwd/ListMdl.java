/**
 * 
 */
package com.magicpwd.m.mpwd;

import com.magicpwd._comn.Keys;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;
import java.sql.Timestamp;
import java.util.Calendar;

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

    public void listTask(java.util.Date s, java.util.Date t)
    {
        int c = keysList.size();
        keysList.clear();
        fireIntervalRemoved(this, 0, c);
        DBA3000.readTaskList(userMdl, new java.sql.Timestamp(s.getTime()), new java.sql.Timestamp(t.getTime()), keysList);
        c = keysList.size();
        fireIntervalAdded(this, 0, c);
    }

    public boolean listKeysByHash(String typeHash)
    {
        int s = keysList.size();
        keysList.clear();
        fireIntervalRemoved(this, 0, s);
        boolean b = DBA3000.readKeysList(userMdl, typeHash, keysList);
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
        boolean b = DBA3000.findUserData(userMdl, keysMeta, keysList);
        s = keysList.size();
        b &= s > 0;
        if (b)
        {
            fireIntervalAdded(this, 0, s);
        }
        return b;
    }

    public void listPast()
    {
        int n = keysList.size();
        keysList.clear();
        fireIntervalRemoved(this, 0, n);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Timestamp s = new Timestamp(c.getTimeInMillis());
        Timestamp e = new Timestamp(c.getTimeInMillis());
        DBA3000.findHintList(userMdl, s, e, keysList);
        n = keysList.size();
        fireIntervalAdded(this, 0, n);
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
        DBA3000.deletePwdsData(keysList.get(index).getP30F0104());
        keysList.remove(index);
        fireIntervalRemoved(this, 0, index);
    }

    public Keys getElement(int index)
    {
        return keysList.get(index);
    }
}
