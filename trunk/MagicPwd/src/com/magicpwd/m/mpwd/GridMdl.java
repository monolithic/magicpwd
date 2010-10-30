/**
 * 
 */
package com.magicpwd.m.mpwd;

import com.magicpwd.__i.IEditItem;
import com.magicpwd._comn.I1S2;
import com.magicpwd._comn.Keys;
import com.magicpwd._comn.item.GuidItem;
import com.magicpwd._comn.item.EditItem;
import com.magicpwd._comn.item.LogoItem;
import com.magicpwd._comn.item.MetaItem;
import com.magicpwd._comn.item.HintItem;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.SafeMdl;
import com.magicpwd.m.UserMdl;

/**
 * @author Amon
 */
public class GridMdl extends SafeMdl implements javax.swing.table.TableModel, java.io.Serializable
{

    private javax.swing.event.EventListenerList listenerList;

    public GridMdl(UserMdl userMdl)
    {
        super(userMdl);
        listenerList = new javax.swing.event.EventListenerList();
    }

    @Override
    public void initHead()
    {
        initGuid();
        fireTableDataChanged();
    }

    @Override
    public void initBody()
    {
        initMeta();
        initLogo();
        initHint();

        DBA3000.selectTpltData(userMdl, ls_ItemList.get(ConsEnv.PWDS_HEAD_GUID).getSpec(IEditItem.SPEC_GUID_TPLT), ls_ItemList);
        fireTableDataChanged();
    }

    /**
     *
     */
    @Override
    public void clear()
    {
        ls_ItemList.clear();
        keys.setDefault();
        setModified(false);
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
    }

    @Override
    public void addTableModelListener(javax.swing.event.TableModelListener l)
    {
        listenerList.add(javax.swing.event.TableModelListener.class, l);
    }

    @Override
    public void removeTableModelListener(javax.swing.event.TableModelListener l)
    {
        listenerList.remove(javax.swing.event.TableModelListener.class, l);
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
     * @param index
     * @return
     */
    @Override
    public IEditItem getItemAt(int index)
    {
        return ls_ItemList.get(index);
    }

    /**
     * 添加指定类别的数据
     * 
     * @param type
     */
    public EditItem wAppend(int indx, int type)
    {
        return wAppend(indx, new EditItem(userMdl, type));
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
        setModified(true);
    }

    public void wRemove(IEditItem item)
    {
        ls_ItemList.remove(item);
        fireTableDataChanged();
        setModified(true);
    }

    public int wImport(java.util.ArrayList<java.util.ArrayList<String>> data, String kindHash) throws Exception
    {
        int size = 0;
        int indx = 0;
        EditItem tplt;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
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
            keys.setP30F0105(userMdl.getCode());

            // Guid
            GuidItem guid = new GuidItem(userMdl);
            guid.setTime(new java.sql.Timestamp(com.magicpwd._util.Date.stringToDate(temp.get(indx++), '-', ':', ' ').getTimeInMillis()));
            guid.setData(kindHash);
            ls_ItemList.add(guid);

            // Meta
            MetaItem meta = new MetaItem(userMdl);
            meta.setName(temp.get(indx++));
            meta.setData(temp.get(indx++));
            ls_ItemList.add(meta);

            // Logo
            ls_ItemList.add(new LogoItem(userMdl));

            // Hint
            HintItem hint = new HintItem(userMdl);
            String text = temp.get(indx++);
            if (com.magicpwd._util.Char.isValidate(text))
            {
                hint.setTime(new java.sql.Timestamp(com.magicpwd._util.Date.stringToDate(text, '-', ':', ' ').getTimeInMillis()));
            }
            hint.setName(temp.get(indx++));
            ls_ItemList.add(hint);

            while (indx < temp.size())
            {
                tplt = new EditItem(userMdl);
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
        DBA3000.readKeysList(userMdl, kindHash, dataList);
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
        setModified(true);
    }

    public boolean setKeysKind(String hash)
    {
        boolean b = keys.getP30F0106().equals(hash);
        keys.setP30F0106(hash);
        DBA3000.saveKeysData(keys);
        return !b;
    }

    public void saveData(boolean histBack, boolean repaint) throws Exception
    {
        saveData(histBack);
        if (repaint)
        {
            fireTableDataChanged();
        }
    }

    public int getSequence()
    {
        return keys.getP30F0101();
    }

    public void fireTableChanged(javax.swing.event.TableModelEvent e)
    {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2)
        {
            if (listeners[i] == javax.swing.event.TableModelListener.class)
            {
                ((javax.swing.event.TableModelListener) listeners[i + 1]).tableChanged(e);
            }
        }
    }

    public void fireTableCellUpdated(int row, int column)
    {
        fireTableChanged(new javax.swing.event.TableModelEvent(this, row, row, column));
    }

    public void fireTableRowsDeleted(int firstRow, int lastRow)
    {
        fireTableChanged(new javax.swing.event.TableModelEvent(this, firstRow, lastRow, javax.swing.event.TableModelEvent.ALL_COLUMNS, javax.swing.event.TableModelEvent.DELETE));
    }

    public void fireTableRowsUpdated(int firstRow, int lastRow)
    {
        fireTableChanged(new javax.swing.event.TableModelEvent(this, firstRow, lastRow, javax.swing.event.TableModelEvent.ALL_COLUMNS, javax.swing.event.TableModelEvent.UPDATE));
    }

    public void fireTableRowsInserted(int firstRow, int lastRow)
    {
        fireTableChanged(new javax.swing.event.TableModelEvent(this, firstRow, lastRow, javax.swing.event.TableModelEvent.ALL_COLUMNS, javax.swing.event.TableModelEvent.INSERT));
    }

    public void fireTableStructureChanged()
    {
        fireTableChanged(new javax.swing.event.TableModelEvent(this, javax.swing.event.TableModelEvent.HEADER_ROW));
    }

    public void fireTableDataChanged()
    {
        fireTableChanged(new javax.swing.event.TableModelEvent(this));
    }
}
