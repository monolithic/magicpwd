/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mwiz;

import com.magicpwd._comn.Keys;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Bean;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;

/**
 *
 * @author Amon
 */
public final class MwizMdl implements javax.swing.table.TableModel, java.io.Serializable
{

    private UserMdl userMdl;
    private KeysMdl keysMdl;
    private java.util.List<Keys> ls_KeysList;
    private javax.swing.event.EventListenerList listenerList;

    public MwizMdl(UserMdl userMdl)
    {
        keysMdl = new KeysMdl(userMdl);
        this.userMdl = userMdl;
    }

    public void init()
    {
        ls_KeysList = new java.util.ArrayList<Keys>();
        listenerList = new javax.swing.event.EventListenerList();
    }

    @Override
    public int getRowCount()
    {
        return ls_KeysList != null ? ls_KeysList.size() : 0;
    }

    @Override
    public int getColumnCount()
    {
        return ConsEnv.PWDS_HEAD_SIZE;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0:
                return "..";
            case 1:
                return "徽标";
            case 2:
                return "标题";
            case 3:
                return "提示";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if (columnIndex == 0)
        {
            return Integer.class;
        }
        if (columnIndex == 1)
        {
            return javax.swing.Icon.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        // 公共属性
        if (ls_KeysList == null || rowIndex < -1 || rowIndex >= ls_KeysList.size())
        {
            return null;
        }

        Keys keys = ls_KeysList.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return Integer.toString(rowIndex + 1);
            case 1:
                return Bean.getDataIcon(keys.getP30F010B());
            case 2:
                return keys.getP30F0109();
            case 3:
                return keys.getP30F010D();
            case 4:
                return keys.getP30F010E();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
    }

    public boolean listKeysByKind(String kindHash)
    {
        ls_KeysList.size();
        ls_KeysList.clear();
        boolean b = DBA3000.readKeysList(userMdl, kindHash, ls_KeysList);
        int s = ls_KeysList.size();
        fireTableDataChanged();
        return b & (s > 0);
    }

    public boolean listKeysByMeta(String keysMeta)
    {
        int s = ls_KeysList.size();
        ls_KeysList.clear();
        boolean b = DBA3000.findUserData(userMdl, keysMeta, ls_KeysList);
        s = ls_KeysList.size();
        fireTableDataChanged();
        return b & (s > 0);
    }

    public Keys getKeysAt(int index)
    {
        return ls_KeysList.get(index);
    }

    /**
     * @return the keysMdl
     */
    public KeysMdl getKeysMdl()
    {
        return keysMdl;
    }

    /**
     * @param keysMdl the keysMdl to set
     */
    public void setKeysMdl(KeysMdl keysMdl)
    {
        this.keysMdl = keysMdl;
    }

    public void wDelete(int index)
    {
        DBA3000.deletePwdsData(ls_KeysList.get(index).getP30F0104());
        ls_KeysList.remove(index);
        fireTableDataChanged();
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
