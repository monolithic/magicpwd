/**
 * 
 */
package com.magicpwd.m;

import java.util.ArrayList;
import java.util.List;

import com.magicpwd._comn.Keys;
import com.magicpwd._comn.Item;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._util.Hash;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;

/**
 * @author Amonsoft
 * 
 */
public class NoteMdl
{

    private boolean modified;
    private List<Item> ls_ItemList;
    private Keys pwds;

    NoteMdl()
    {
        ls_ItemList = new ArrayList<Item>();
        pwds = new Keys();
    }

    public Item initGuid()
    {
        pwds.setP30F0103(Hash.hash(false));
        java.sql.Timestamp stamp = new java.sql.Timestamp(System.currentTimeMillis());
        pwds.setP30F0106(stamp);
        pwds.setP30F010A(stamp);

        String name = stamp.toString();
        int i = name.lastIndexOf('.');
        if (i > 0)
        {
            name = name.substring(0, i);
        }

        Item tplt = new Item(ConsDat.INDX_GUID);
        tplt.setName(name);
        tplt.setData(ConsDat.HASH_NOTE);
        pwds.setP30F0105(ConsDat.HASH_NOTE);
        ls_ItemList.add(tplt);
        return tplt;
    }

    public Item initMeta()
    {
        Item tplt = new Item(ConsDat.INDX_META);
        ls_ItemList.add(tplt);
        pwds.setP30F0102(ConsDat.PWDS_STAT_1);
        return tplt;
    }

    public Item initPast()
    {
        Item tplt = new Item(ConsDat.INDX_NOTE);
        ls_ItemList.add(tplt);
        return tplt;
    }

    public Item initNote()
    {
        Item tplt = new Item(ConsDat.INDX_AREA);
        ls_ItemList.add(tplt);
        return tplt;
    }

    public void setNote(String name, String note)
    {
        ls_ItemList.get(1).setName(name);
        pwds.setP30F0107(name);

        ls_ItemList.get(3).setName(name);
        ls_ItemList.get(3).setData(note);
    }

    public void loadData(String keysHash) throws Exception
    {
        clear();
        pwds.setP30F0103(keysHash);
        DBA3000.readPwdsData(pwds);
        UserMdl.getGridMdl().deCrypt(pwds, ls_ItemList);
    }

    public void saveData(boolean histBack) throws Exception
    {
        if (pwds.getP30F0103() == null)
        {
            pwds.setP30F0103(Hash.hash(false));
        }
        if (pwds.getP30F0105() == null)
        {
            pwds.setP30F0105(UserMdl.getUserId());
        }
        pwds.setHistBack(histBack);
        UserMdl.getGridMdl().enCrypt(pwds, ls_ItemList);
        DBA3000.savePwdsData(pwds);
        //pwds.setUpdate(true);
    }

    public void clear()
    {
        ls_ItemList.clear();
        pwds.setDefault();
        modified = false;
    }

    public int getSize()
    {
        return ls_ItemList.size();
    }

    public Item getTplt(int index)
    {
        return ls_ItemList.get(index);
    }

    /**
     * 数据是否被修改过
     * 
     * @return
     */
    public boolean isModified()
    {
        return modified;
    }

    public boolean isUpdate()
    {
        return Util.isValidateHash(pwds.getP30F0103());
    }
}
