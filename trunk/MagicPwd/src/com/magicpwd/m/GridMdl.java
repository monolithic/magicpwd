/**
 * 
 */
package com.magicpwd.m;

import com.magicpwd._comn.item.GuidItem;
import com.magicpwd._comn.I1S2;
import com.magicpwd._comn.Keys;
import com.magicpwd._comn.item.PwdsItem;
import com.magicpwd._comn.item.EditItem;
import com.magicpwd._comn.item.LogoItem;
import com.magicpwd._comn.item.MetaItem;
import com.magicpwd._comn.item.HintItem;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditItem;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.d.DBA3000;

/**
 * @author Amon
 * 
 */
public class GridMdl extends javax.swing.table.DefaultTableModel
{

    /**
     * 临时数据保存
     */
    private boolean interim;
    private boolean modified;
    protected java.util.ArrayList<IEditItem> ls_ItemList;
    protected Keys keys;
    private SafeMdl safeMdl;
    private UserCfg userCfg;

    GridMdl(UserCfg userCfg, SafeMdl safeDml)
    {
        ls_ItemList = new java.util.ArrayList<IEditItem>();
        keys = new Keys();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.table.DefaultTableModel#getColumnCount()
     */
    @Override
    public int getColumnCount()
    {
        return 2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnIndex == 0 ? Integer.class : String.class;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.table.DefaultTableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int columnIndex)
    {
        return columnIndex == 1 ? Lang.getLang(LangRes.P30F7304, "属性(T)") : Lang.getLang(LangRes.P30F7303, "");
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.DefaultTableModel#getRowCount()
     */
    @Override
    public int getRowCount()
    {
        return ls_ItemList != null ? ls_ItemList.size() : 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column)
    {
        // 索引列
        if (column == 0)
        {
            switch (row)
            {
                case ConsDat.INDX_GUID - ConsDat.INDX_GUID:
                    return Lang.getLang(LangRes.P30F1106, "日期");
                case ConsDat.INDX_META - ConsDat.INDX_GUID:
                    return Lang.getLang(LangRes.P30F110A, "标题");
                case ConsDat.INDX_LOGO - ConsDat.INDX_GUID:
                    return Lang.getLang(LangRes.P30F1112, "徽标");
                case ConsDat.INDX_HINT - ConsDat.INDX_GUID:
                    return Lang.getLang(LangRes.P30F110B, "提醒");
                default:
                    return row + 1 - ConsEnv.PWDS_HEAD_SIZE;
            }
        }

        // 公共属性
        if (ls_ItemList == null)
        {
            return null;
        }
        return ls_ItemList.get(row);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
     */
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    /**
     * 是否使用临时口令名称
     *
     * @return
     */
    public boolean isInterim()
    {
        return interim;
    }

    /**
     * @param interim
     */
    public void setInterim(boolean interim)
    {
        this.interim = interim;
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
     * @param modified
     */
    public void setModified(boolean modified)
    {
        this.modified = modified;
    }

    /**
     * 读取指定索引的密码数据
     * 
     * @param keysHash
     */
    public void loadData(String keysHash) throws Exception
    {
        clear();
        keys.setP30F0104(keysHash);
        keys.setP30F0105(userCfg.getUserCode());
        if (DBA3000.readPwdsData(keys))
        {
            deCrypt(keys, ls_ItemList);
            fireTableDataChanged();
        }
    }

    /**
     * 是否要更新原有数据
     * 
     * @param histBack
     * @throws Exception
     */
    public void saveData(boolean histBack, boolean repaint) throws Exception
    {
        keys.setP30F0105(userCfg.getUserCode());
        keys.setHistBack(histBack);
        enCrypt(keys, ls_ItemList);
        DBA3000.savePwdsData(keys);
        clear();
        if (repaint)
        {
            fireTableDataChanged();
        }
    }

    /**
     * 向导初始化
     * @return
     */
    public IEditItem initGuid()
    {
        GuidItem guid = new GuidItem(userCfg);
        guid.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
        ls_ItemList.add(guid);
        fireTableDataChanged();
        return guid;
    }

    /**
     * 关键搜索
     * @return
     */
    public IEditItem initMeta()
    {
        MetaItem meta = new MetaItem(userCfg);
        ls_ItemList.add(meta);
        return meta;
    }

    /**
     * 徽标
     * @return
     */
    public IEditItem initLogo()
    {
        LogoItem logo = new LogoItem(userCfg);
        ls_ItemList.add(logo);
        return logo;
    }

    /**
     * 过时提醒
     * @return
     */
    public IEditItem initHint()
    {
        HintItem hint = new HintItem(userCfg);
        ls_ItemList.add(hint);
        return hint;
    }

    /**
     * @param index
     * @return
     */
    public IEditItem getItemAt(int index)
    {
        return ls_ItemList.get(index);
    }

    /**
     *
     */
    public void clear()
    {
        ls_ItemList.clear();
        keys.setDefault();
        modified = false;
    }

    /**
     * 添加指定类别的数据
     * 
     * @param type
     */
    public EditItem wAppend(int indx, int type)
    {
        return wAppend(indx, new EditItem(userCfg, type));
    }

    /**
     * @param pi
     */
    public EditItem wAppend(int indx, EditItem item)
    {
        ls_ItemList.add(indx, item);
        fireTableDataChanged();
        return item;
    }

    /**
     * @param pi
     */
    public EditItem wAppend(EditItem item)
    {
        ls_ItemList.add(item);
        fireTableDataChanged();
        return item;
    }

    /**
     * 初始化指定类型的数据
     */
    public void wAppend(String typeHash)
    {
        DBA3000.selectTpltData(userCfg, typeHash, ls_ItemList);
        fireTableDataChanged();
    }

    /**
     * 查找特定类别且具有特定附加属性的字段
     * @param type
     * @param args
     */
    public int wSelect(int type, int args)
    {
        int t = (type | args);
        int n = 0;
        int l = 0;
        for (int i = 0, j = ls_ItemList.size(); i < j; i += 1)
        {
            if (ls_ItemList.get(i).getType() == t)
            {
                l = i;
                n += 1;
            }
        }
        return n == 1 ? l : -1;
    }

    /**
     * 
     * @param type
     * @return
     */
    public java.util.List<I1S2> wSelect(int type)
    {
        java.util.ArrayList<I1S2> list = new java.util.ArrayList<I1S2>();
        int i = 0;
        for (IEditItem item : ls_ItemList)
        {
            if (item.getType() == type)
            {
                list.add(new I1S2(i, item.getData(), item.getName()));
            }
            i += 1;
        }
        return list;
    }

    public void wUpdate()
    {
        fireTableDataChanged();
    }

    public void wUpdate(int indx, EditItem tplt)
    {
        ls_ItemList.set(indx, tplt);
        fireTableDataChanged();
    }

    public void wRemove(int indx)
    {
        ls_ItemList.remove(indx);
        fireTableDataChanged();
        modified = true;
    }

    public void wRemove(IEditItem item)
    {
        ls_ItemList.remove(item);
        fireTableDataChanged();
        modified = true;
    }

    public int wImport(java.util.ArrayList<java.util.ArrayList<String>> data, String kindHash) throws Exception
    {
        int size = 0;
        int indx = 0;
        EditItem tplt;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConsEnv.VIEW_DATE);
        for (java.util.ArrayList<String> temp : data)
        {
            switch ((temp.size() - 5) % 3)
            {
                case 2:
                    if (com.magicpwd._util.Char.isValidate(temp.get(temp.size() - 1)))
                    {
                        temp.add("");
                        break;
                    }
                    temp.remove(temp.size() - 1);
                case 1:
                    temp.remove(temp.size() - 1);
                    break;
            }

            indx = 0;
            keys.setDefault();
            ls_ItemList.clear();
            keys.setP30F0105(userCfg.getUserCode());

            // Guid
            GuidItem guid = new GuidItem(userCfg);
            guid.setTime(new java.sql.Timestamp(com.magicpwd._util.Date.stringToDate(temp.get(indx++), '-', ':', ' ').getTimeInMillis()));
            guid.setData(kindHash);
            ls_ItemList.add(guid);

            // Meta
            MetaItem meta = new MetaItem(userCfg);
            meta.setName(temp.get(indx++));
            meta.setData(temp.get(indx++));
            ls_ItemList.add(meta);

            // Logo
            ls_ItemList.add(new LogoItem(userCfg));

            // Hint
            HintItem hint = new HintItem(userCfg);
            String text = temp.get(indx++);
            if (com.magicpwd._util.Char.isValidate(text))
            {
                hint.setTime(new java.sql.Timestamp(com.magicpwd._util.Date.stringToDate(text, '-', ':', ' ').getTimeInMillis()));
            }
            hint.setName(temp.get(indx++));
            ls_ItemList.add(hint);

            while (indx < temp.size())
            {
                tplt = new EditItem(userCfg);
                tplt.setType(Integer.parseInt(temp.get(indx++)));
                tplt.setName(temp.get(indx++));
                tplt.setData(temp.get(indx++));
                ls_ItemList.add(tplt);
            }

            enCrypt(keys, ls_ItemList);
            DBA3000.savePwdsData(keys);
            size += 1;
            Thread.sleep(200);
        }
        return size;
    }

    public int wExport(java.util.ArrayList<java.util.ArrayList<String>> data, String kindHash)
    {
        java.util.ArrayList<Keys> dataList = new java.util.ArrayList<Keys>();
        DBA3000.readKeysList(userCfg, kindHash, dataList);
        if (dataList == null || dataList.size() < 1)
        {
            return 0;
        }

        int size = 0;
        java.util.ArrayList<String> temp;
        int indx;
        IEditItem tplt;

        for (Keys item : dataList)
        {
            indx = 0;
            temp = new java.util.ArrayList<String>();
            try
            {
                clear();
                loadData(item.getP30F0104());
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                continue;
            }

            // Guid
            tplt = ls_ItemList.get(indx++);
            temp.add(tplt.getName());

            // Meta
            tplt = ls_ItemList.get(indx++);
            temp.add(tplt.getName());
            temp.add(tplt.getData());

            // Logo
            tplt = ls_ItemList.get(indx++);

            // Hint
            tplt = ls_ItemList.get(indx++);
            temp.add(tplt.getData());
            temp.add(tplt.getName());

            while (indx < ls_ItemList.size())
            {
                tplt = ls_ItemList.get(indx++);
                temp.add("" + tplt.getType());
                temp.add(tplt.getName());
                temp.add(tplt.getData());
            }

            size += 1;
            data.add(temp);
        }
        return size;
    }

    /**
     * @param row
     * @param to
     */
    public void wMoveto(int row, int to)
    {
        if (row == to)
        {
            return;
        }

        IEditItem p = ls_ItemList.remove(row);
        ls_ItemList.add(to, p);
        modified = true;
    }

    public final StringBuffer deCrypt(PwdsItem pwds) throws Exception
    {
//        pwds.deCript(coreMdl.getDCipher(), coreMdl.getSec().getMask());
//        return pwds.getP30F0203();
        StringBuffer buf = pwds.getP30F0203();
        String tmp = buf.toString();
        return buf.delete(0, buf.length()).append(safeMdl.deCript(tmp));
    }

    /**
     * 数据解密处理
     * 
     * @param dba
     */
    public final void deCrypt(Keys keys, java.util.List<IEditItem> list) throws Exception
    {
        // 查询数据是否为空
        StringBuffer text = deCrypt(keys.getPassword());
        if (text.length() < 1)
        {
            return;
        }

        // Guid
        GuidItem guid = new GuidItem(userCfg);
        guid.setData(keys.getP30F0106());
        guid.setTime(keys.getP30F0107());
        guid.setSpec(IEditItem.SPEC_GUID_TPLT, keys.getP30F0108());
        list.add(guid);

        // MetaItem
        MetaItem meta = new MetaItem(userCfg);
        meta.setName(keys.getP30F0109());
        meta.setData(keys.getP30F010A());
        list.add(meta);

        // LogoItem
        LogoItem logo = new LogoItem(userCfg);
        logo.setName(keys.getP30F010B());
        logo.setData(keys.getP30F010C());
        list.add(logo);

        // HintItem
        HintItem hint = new HintItem(userCfg);
        hint.setTime(keys.getP30F010D());
        hint.setName(keys.getP30F010E());
        list.add(hint);

        // 处理每一个数据
        java.util.StringTokenizer st = new java.util.StringTokenizer(text.toString(), ConsDat.SP_SQL_EE);
        String name;
        String data;
        String spec;
        int dn;
        int dd;
        int ds;
        int type;
        String t;
        EditItem item;
        while (st.hasMoreTokens())
        {
            t = st.nextToken() + ConsDat.SP_SQL_KV;
            dn = t.indexOf(ConsDat.SP_SQL_KV);
            dd = t.indexOf(ConsDat.SP_SQL_KV, dn + 1);
            ds = t.indexOf(ConsDat.SP_SQL_KV, dd + 1);

            type = Integer.parseInt(t.substring(0, dn));
            name = t.substring(dn + 1, dd);
            data = t.substring(dd + 1, ds);
            spec = t.substring(ds + 1, t.length());
            item = new EditItem(userCfg, type, name, data);
            if (spec.length() > 0)
            {
                item.deCodeSpec(spec, ConsDat.SP_SQL_KV);
            }
            list.add(item);
        }
    }

    public final StringBuffer enCrypt(PwdsItem pwds) throws Exception
    {
//        pwds.enCrypt(coreMdl.getECipher(), coreMdl.getSec().getMask());
//        return pwds.getP30F0203();
        StringBuffer buf = pwds.getP30F0203();
        String tmp = buf.toString();
        return buf.delete(0, buf.length()).append(safeMdl.enCrypt(tmp));
    }

    /**
     * 数据加密处理
     * 
     * @param dba
     */
    public final void enCrypt(Keys keys, java.util.List<IEditItem> list) throws Exception
    {
        PwdsItem pwds = keys.getPassword();
        StringBuffer text = pwds.getP30F0203();
        text.delete(0, text.length());

        // Guid
        GuidItem guid = (GuidItem) list.get(ConsEnv.PWDS_HEAD_GUID);
        keys.setP30F0106(guid.getData());
        keys.setP30F0107(guid.getTime());
        keys.setP30F0108(guid.getSpec(IEditItem.SPEC_GUID_TPLT));

        // MetaItem
        MetaItem meta = (MetaItem) list.get(ConsEnv.PWDS_HEAD_META);
        keys.setP30F0109(interim ? '<' + meta.getName() + '_' + keys.getP30F0107() + '>' : meta.getName());
        keys.setP30F010A(meta.getData());
        interim = false;

        // LogoItem
        LogoItem logo = (LogoItem) list.get(ConsEnv.PWDS_HEAD_LOGO);
        keys.setP30F010B(logo.getName());
        keys.setP30F010C(logo.getData());

        // HintItem
        HintItem note = (HintItem) list.get(ConsEnv.PWDS_HEAD_HINT);
        keys.setP30F010D(note.getTime());
        keys.setP30F010E(note.getName());

        // 字符串拼接
        IEditItem item;
        for (int i = ConsEnv.PWDS_HEAD_SIZE, j = list.size(); i < j; i += 1)
        {
            item = list.get(i);
            text.append(item.getType());
            text.append(ConsDat.SP_SQL_KV);
            text.append(item.getName());
            text.append(ConsDat.SP_SQL_KV);
            text.append(item.getData());
            text.append(item.enCodeSpec(ConsDat.SP_SQL_KV));
            text.append(ConsDat.SP_SQL_EE);
        }

        enCrypt(pwds);
    }

    public boolean isUpdate()
    {
        return com.magicpwd._util.Char.isValidateHash(keys.getP30F0104());
    }

    public void setKeysMode(int mode)
    {
        keys.setP30F0102(mode);
        DBA3000.saveKeysData(keys);
    }

    public void setKeysNote(int note)
    {
        keys.setP30F0103(note);
        DBA3000.saveKeysData(keys);
    }

    public int getSequence()
    {
        return keys.getP30F0101();
    }

    public String getKeysHash()
    {
        return keys.getP30F0104();
    }
}
