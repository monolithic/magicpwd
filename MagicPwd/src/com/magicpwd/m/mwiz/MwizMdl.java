/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m.mwiz;

import com.magicpwd.m.SafeMdl;
import com.magicpwd.m.UserMdl;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Amon
 */
public class MwizMdl extends SafeMdl implements javax.swing.table.TableModel, java.io.Serializable
{

    public MwizMdl(UserMdl userMdl)
    {
        super(userMdl);
    }

    @Override
    public void initHead()
    {
    }

    @Override
    public void initBody(String tpltHash)
    {
    }

    @Override
    public void clear()
    {
    }

    @Override
    public int getRowCount()
    {
        return 0;
    }

    @Override
    public int getColumnCount()
    {
        return 0;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
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
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
    }

    @Override
    public void addTableModelListener(TableModelListener l)
    {
    }

    @Override
    public void removeTableModelListener(TableModelListener l)
    {
    }
}
