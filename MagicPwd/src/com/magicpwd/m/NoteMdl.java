/**
 * 
 */
package com.magicpwd.m;

import com.magicpwd._comn.Guid;
import java.util.ArrayList;
import java.util.List;

import com.magicpwd._comn.Keys;
import com.magicpwd._comn.Meta;
import com.magicpwd._comn.Hint;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._face.IEditItem;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;

/**
 * @author Amonsoft
 */
public class NoteMdl
{

    private boolean modified;
    private List<IEditItem> ls_ItemList;
    private Keys keys;

    NoteMdl()
    {
        ls_ItemList = new ArrayList<IEditItem>();
        keys = new Keys();
    }

    public IEditItem initGuid()
    {
        Guid guid = new Guid();
        guid.setData(ConsDat.HASH_NOTE);
        ls_ItemList.add(guid);
        return guid;
    }

    public IEditItem initMeta()
    {
        Meta meta = new Meta();
        ls_ItemList.add(meta);
        return meta;
    }

    public IEditItem initLogo()
    {
        Hint note = new Hint();
        ls_ItemList.add(note);
        return note;
    }

    public IEditItem initHint()
    {
        Hint note = new Hint();
        ls_ItemList.add(note);
        return note;
    }

    public void setNote(String name, String note)
    {
        ls_ItemList.get(ConsEnv.PWDS_HEAD_META).setName(name);
        ls_ItemList.get(ConsEnv.PWDS_HEAD_SIZE).setName(name);
        ls_ItemList.get(ConsEnv.PWDS_HEAD_SIZE).setData(note);
    }

    public void loadData(String keysHash) throws Exception
    {
        clear();
        keys.setP30F0103(keysHash);
        DBA3000.readPwdsData(keys);
        UserMdl.getGridMdl().deCrypt(keys, ls_ItemList);
    }

    public void saveData(boolean histBack) throws Exception
    {
        keys.setP30F0102(ConsDat.PWDS_STAT_1);
        keys.setP30F0104(UserMdl.getUserId());
        keys.setP30F0105(ConsDat.HASH_NOTE);
        keys.setHistBack(histBack);
        UserMdl.getGridMdl().enCrypt(keys, ls_ItemList);
        DBA3000.savePwdsData(keys);
    }

    public void clear()
    {
        ls_ItemList.clear();
        keys.setDefault();
        modified = false;
    }

    public int getSize()
    {
        return ls_ItemList.size();
    }

    public IEditItem getItemAt(int index)
    {
        return ls_ItemList.get(index);
    }

    public void setStatus(int status)
    {
        keys.setP30F0102(status);
        modified = true;
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

    /**
     * 
     * @return
     */
    public boolean isUpdate()
    {
        return Util.isValidateHash(keys.getP30F0103());
    }
}
