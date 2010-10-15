/**
 * 
 */
package com.magicpwd.m;

import com.magicpwd._comn.item.EditItem;

import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd.$i.IEditItem;
import com.magicpwd.d.DBA3000;

/**
 * 记事便签数据模型
 * @author Amon
 */
public class NoteMdl extends GridMdl
{

    public NoteMdl(UserCfg userCfg, SafeMdl safeMdl)
    {
        super(userCfg, safeMdl);
    }

    public IEditItem initNote()
    {
        EditItem note = new EditItem(userCfg);
        note.setType(ConsDat.INDX_AREA);
        ls_ItemList.add(note);
        return note;
    }

    public void setNote(String name, String note)
    {
        ls_ItemList.get(ConsEnv.PWDS_HEAD_META).setName(name);
        ls_ItemList.get(ConsEnv.PWDS_HEAD_SIZE).setName(name);
        ls_ItemList.get(ConsEnv.PWDS_HEAD_SIZE).setData(note);
    }

    public IEditItem getNote()
    {
        return ls_ItemList.get(ConsEnv.PWDS_HEAD_SIZE);
    }

    public void saveData(boolean histBack) throws Exception
    {
        keys.setP30F0105(userCfg.getUserCode());
        keys.setHistBack(histBack);
        enCrypt(keys, ls_ItemList);
        DBA3000.savePwdsData(keys);
    }

    public int getSize()
    {
        return getRowCount();
    }
}
