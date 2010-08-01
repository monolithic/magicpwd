/**
 * 
 */
package com.magicpwd.m;

import com.magicpwd._comn.Keys;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import com.magicpwd.d.DBA3000;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Amon
 * 
 */
public class ListMdl extends DefaultListModel
{

    private List<Keys> dataList;

    ListMdl()
    {
        dataList = new ArrayList<Keys>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public Object getElementAt(int index)
    {
        return dataList.get(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize()
    {
        return dataList != null ? dataList.size() : 0;
    }

    public void listName(String typeHash)
    {
        int s = dataList.size();
        dataList.clear();
        fireIntervalRemoved(this, 0, s);
        DBA3000.readKeysList(typeHash, dataList);
        s = dataList.size();
        fireIntervalAdded(this, 0, s);
    }

    public void listPast()
    {
        int n = dataList.size();
        dataList.clear();
        fireIntervalRemoved(this, 0, n);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Timestamp s = new Timestamp(c.getTimeInMillis());
        Timestamp e = new Timestamp(c.getTimeInMillis());
        DBA3000.findHintList(s, e, dataList);
        n = dataList.size();
        fireIntervalAdded(this, 0, n);
    }

    public boolean findName(String keysName)
    {
        int s = dataList.size();
        dataList.clear();
        fireIntervalRemoved(this, 0, s);
        boolean b = DBA3000.findUserData(keysName, dataList);
        s = dataList.size();
        b &= s > 0;
        if (b)
        {
            fireIntervalAdded(this, 0, s);
        }
        return b;
    }

    public boolean updtName(int indx, String name)
    {
        Keys item = dataList.get(indx);
        item.setP30F0109(name);
        //item.setV2(name);
        fireContentsChanged(this, indx, indx);
        return true;
    }

    public void wAppend()
    {
    }

    public void wUpdate()
    {
        fireIntervalRemoved(this, 0, dataList.size());
    }

    public void wRemove(int index)
    {
        dataList.remove(index);
        fireIntervalRemoved(this, 0, index);
    }

    public void wDelete(int index)
    {
        DBA3000.deletePwdsData(dataList.get(index).getP30F0104());
        dataList.remove(index);
        fireIntervalRemoved(this, 0, index);
    }

    public Keys getElement(int index)
    {
        return dataList.get(index);
    }
}
