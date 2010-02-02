/**
 * 
 */
package com.magicpwd.m;

import com.magicpwd._comn.I1S2;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.table.DefaultTableModel;

import com.magicpwd._comn.Keys;
import com.magicpwd._comn.Pwds;
import com.magicpwd._comn.Item;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Hash;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;

/**
 * @author Amon
 * 
 */
public class GridMdl extends DefaultTableModel
{

    private boolean modified;
    private List<Item> ls_ItemList;
    private Keys keys;

    GridMdl()
    {
        ls_ItemList = new ArrayList<Item>();
        keys = new Keys();
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
     * @see javax.swing.table.DefaultTableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column)
    {
        return column == 1 ? Lang.getLang(LangRes.P30F7304, "属性(T)") : Lang.getLang(LangRes.P30F7303, "");
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
                case ConsDat.INDX_ICON - ConsDat.INDX_GUID:
                    return Lang.getLang(LangRes.P30F1112, "徽标");
                case ConsDat.INDX_TIME - ConsDat.INDX_GUID:
                    return Lang.getLang(LangRes.P30F110B, "提醒");
                default:
                    return row - 3;
            }
        }

        // 公共属性
        if (ls_ItemList == null)
        {
            return null;
        }
        return ls_ItemList.get(row);
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
        keys.setDefault();
        keys.setP30F0103(keysHash);
        keys.setP30F0104(UserMdl.getUserId());
        DBA3000.readPwdsData(keys);
        deCrypt(keys, ls_ItemList);
        fireTableDataChanged();
    }

    /**
     * 是否要更新原有数据
     * 
     * @param histBack
     * @throws Exception
     */
    public void saveData(boolean histBack, boolean repaint) throws Exception
    {
        if (keys.getP30F0104() == null)
        {
            keys.setP30F0104(UserMdl.getSec().getUsid());
        }
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
     * 
     */
    public void clear()
    {
        ls_ItemList.clear();
        keys.setDefault();
        modified = false;
    }

    public Item initGuid()
    {
        keys.setP30F0104(Hash.hash(false));
        java.sql.Timestamp stamp = new java.sql.Timestamp(System.currentTimeMillis());
        keys.setCurrTime(stamp);
        keys.setP30F010A(stamp);

        Item tplt = new Item(ConsDat.INDX_GUID);
        ls_ItemList.add(tplt);
        fireTableDataChanged();
        return tplt;
    }

    public void initMeta()
    {
        // 关键搜索
        Item tplt = new Item(ConsDat.INDX_META);
        ls_ItemList.add(tplt);

        // 徽标
        tplt = new Item(ConsDat.INDX_ICON);
        ls_ItemList.add(tplt);

        // 过时提醒
        tplt = new Item(ConsDat.INDX_TIME);
        ls_ItemList.add(tplt);

        keys.setP30F0102(ConsDat.PWDS_STAT_1);
        fireTableDataChanged();
    }

    /**
     * 添加指定类别的数据
     * 
     * @param type
     */
    public Item wAppend(int indx, int type)
    {
        return wAppend(indx, new Item(type));
    }

    /**
     * @param pi
     */
    public Item wAppend(int indx, Item tplt)
    {
        ls_ItemList.add(indx, tplt);
        fireTableDataChanged();
        return tplt;
    }

    /**
     * @param pi
     */
    public Item wAppend(Item tplt)
    {
        ls_ItemList.add(tplt);
        fireTableDataChanged();
        return tplt;
    }

    /**
     * 初始化指定类型的数据
     */
    public void wAppend(String typeHash)
    {
        DBA3000.selectTpltData(typeHash, ls_ItemList);
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
    public List<I1S2> wSelect(int type)
    {
        List<I1S2> list = new ArrayList<I1S2>();
        int i = 0;
        for (Item item : ls_ItemList)
        {
            if (item.getType() == type)
            {
                list.add(new I1S2(i, "", item.getName() + '（' + item.getData() + '）'));
            }
            i += 1;
        }
        return list;
    }

    public void wUpdate()
    {
        fireTableDataChanged();
    }

    public void wUpdate(int indx, Item tplt)
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

    public void wRemove(Item tplt)
    {
        ls_ItemList.remove(tplt);
        fireTableDataChanged();
        modified = true;
    }

    public int wImport(ArrayList<ArrayList<String>> data, String kindHash) throws Exception
    {
        int size = 0;
        int indx = 0;
        Item tplt;
        String text;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConsEnv.VIEW_DATE);
        for (ArrayList<String> temp : data)
        {
            switch ((temp.size() - 5) % 3)
            {
                case 2:
                    if (Util.isValidate(temp.get(temp.size() - 1)))
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

            // Guid
            tplt = new Item();
            tplt.setType(ConsDat.INDX_GUID);
            text = temp.get(indx++);
            tplt.setName(sdf.format(Util.stringToDate(text, '-', ':', ' ').getTime()));
            tplt.setData(ConsDat.HASH_TPLT);
            ls_ItemList.add(tplt);

            // Meta
            tplt = new Item();
            tplt.setType(ConsDat.INDX_META);
            text = temp.get(indx++);
            tplt.setName(text);
            keys.setP30F0107(text);
            text = temp.get(indx++);
            tplt.setData(text);
            keys.setP30F0108(text);
            ls_ItemList.add(tplt);

            // Past
            tplt = new Item();
            tplt.setType(ConsDat.INDX_TIME);
            text = temp.get(indx++);
            tplt.setData(text);
            keys.setP30F010A(new java.sql.Timestamp(Util.stringToDate(text, '-', ':', ' ').getTimeInMillis()));
            text = temp.get(indx++);
            tplt.setName(text);
            keys.setP30F010C(text);
            ls_ItemList.add(tplt);

            while (indx < temp.size())
            {
                tplt = new Item();
                tplt.setType(Integer.parseInt(temp.get(indx++)));
                tplt.setName(temp.get(indx++));
                tplt.setData(temp.get(indx++));
                ls_ItemList.add(tplt);
            }

            keys.setP30F0102(ConsDat.PWDS_STAT_1);
            keys.setP30F0104(Hash.hash(false));
            keys.setP30F0107(kindHash);
            keys.setP30F010A(new java.sql.Timestamp(System.currentTimeMillis()));
            enCrypt(keys, ls_ItemList);
            DBA3000.savePwdsData(keys);
            size += 1;
            Thread.sleep(200);
        }
        return size;
    }

    public int wExport(ArrayList<ArrayList<String>> data, String kindHash)
    {
        List<Keys> dataList = new ArrayList<Keys>();
        DBA3000.readKeysList(kindHash, dataList);
        if (dataList == null || dataList.size() < 1)
        {
            return 0;
        }

        int size = 0;
        ArrayList<String> temp;
        int indx;
        Item tplt;

        for (Keys item : dataList)
        {
            indx = 0;
            temp = new ArrayList<String>();
            try
            {
                clear();
                loadData(item.getP30F0103());
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
            ls_ItemList.add(tplt);

            // Past
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

        Item p = ls_ItemList.remove(row);
        ls_ItemList.add(to, p);
    }

    /**
     * 数据解密处理
     * 
     * @param dba
     */
    public final void deCrypt(Keys keys, List<Item> list) throws Exception
    {
        // 查询数据是否为空
        Pwds pwds = keys.getPassword();
        pwds.deCript(UserMdl.getDCipher(), UserMdl.getSec().getMask());
        StringBuffer text = pwds.getP30F0203();
        if (text.length() < 16)
        {
            return;
        }

        // 处理每一个数据
        StringTokenizer st = new StringTokenizer(text.toString(), ConsDat.SP_SQL_EE);
        int type;
        String name;
        String data;
        String spec;
        int dn;
        int dd;
        int ds;
        String t;
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
            Item tplt = new Item(type, name, data);
            if (spec.length() > 0)
            {
                tplt.deCodeSpec(spec, ConsDat.SP_SQL_KV);
            }
            list.add(tplt);
        }
    }

    /**
     * 数据加密处理
     * 
     * @param dba
     */
    public final void enCrypt(Keys keys, List<Item> list) throws Exception
    {
        Pwds pwds = keys.getPassword();

        // 字符串拼接
        StringBuffer text = pwds.getP30F0203();
        text.delete(0, text.length());
        for (Item item : list)
        {
            text.append(item.getType());
            text.append(ConsDat.SP_SQL_KV);
            text.append(item.getName());
            text.append(ConsDat.SP_SQL_KV);
            text.append(item.getData());
            text.append(item.enCodeSpec(ConsDat.SP_SQL_KV));
            text.append(ConsDat.SP_SQL_EE);
        }

        pwds.enCrypt(UserMdl.getECipher(), UserMdl.getSec().getMask());
    }

    /**
     * @return the kindHash
     */
    public String getKindHash()
    {
        return keys.getP30F0105();
    }

    /**
     * @param kindHash
     *            the typeHash to set
     */
    public void setKindHash(String kindHash)
    {
        keys.setP30F0105(kindHash);
    }

    /**
     * @return the keysHash
     */
    public String getKeysHash()
    {
        return keys.getP30F0104();
    }

    /**
     * @param keysHash
     *            the keysHash to set
     */
    public void setKeysHash(String keysHash)
    {
        keys.setP30F0104(keysHash);
    }

    /**
     * @return the keysName
     */
    public String getKeysName()
    {
        return keys.getP30F0107();
    }

    /**
     * @param keysName
     *            the keysName to set
     */
    public void setKeysName(String keysName)
    {
        keys.setP30F0107(keysName);
    }

    public void appendKeysNameTemp()
    {
        keys.setP30F0107(keys.getP30F0107() + '_' + keys.getCurrTime());
    }

    /**
     * @return the keysMeta
     */
    public String getKeysMeta()
    {
        return keys.getP30F0108();
    }

    /**
     * @param keysMeta
     *            the keysMeta to set
     */
    public void setKeysMeta(String keysMeta)
    {
        keys.setP30F0108(keysMeta);
    }

    public java.sql.Timestamp getCurrTime()
    {
        return keys.getCurrTime();
    }

    public void setCurrTime(java.sql.Timestamp keysDate)
    {
        keys.setCurrTime(keysDate);
    }

    public java.sql.Timestamp getPastDate()
    {
        return keys.getP30F010A();
    }

    public void setPastDate(java.sql.Timestamp stamp)
    {
        keys.setP30F010A(stamp);
    }

    public void setPastNote(String pastNote)
    {
        keys.setP30F010C(pastNote);
    }

    public boolean isUpdate()
    {
        return Util.isValidateHash(keys.getP30F0103());
    }

    public void setStatus(int status)
    {
        keys.setP30F0102(status);
    }

    /**
     * @param index
     * @return
     */
    public Item getTplt(int index)
    {
        return ls_ItemList.get(index);
    }
}
