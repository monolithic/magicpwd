/*
 *  Copyright (C) 2010 Amon
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.m.mgtd;

import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.m.UserMdl;

/**
 * @author Amon
 */
public class GridMdl extends javax.swing.table.DefaultTableModel
{

//    private KeysMdl keysMdl;
    private java.util.List<Mgtd> lsMgtdList;

    GridMdl(UserMdl userMdl)
    {
    }

    void init()
    {
//        keysMdl = new KeysMdl(userMdl);
        lsMgtdList = new java.util.ArrayList<Mgtd>();
        DBA4000.listMgtdData(lsMgtdList);
    }

    @Override
    public int getRowCount()
    {
        return lsMgtdList != null ? lsMgtdList.size() : 0;
    }

    @Override
    public int getColumnCount()
    {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0:
                return "..";
            case 1:
                return "标题";
            case 2:
                return "完成度";
            case 3:
                return "优先级";
            case 4:
                return "提醒周期";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnIndex == 0 ? Integer.class : String.class;
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
        if (lsMgtdList == null || rowIndex < -1 || rowIndex >= lsMgtdList.size())
        {
            return null;
        }

        Mgtd temp = lsMgtdList.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return rowIndex + 1;
            case 1:
                return temp.getP30F070B();
            case 2:
                return temp.getP30F0707();
            case 3:
                return temp.getP30F0703();
            case 4:
                return temp.getP30F0704();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
    }

    public Mgtd getKeysAt(int index)
    {
        return lsMgtdList.get(index);
    }

    public void wDelete(int index)
    {
//        DBA4000.deletePwdsData(lsMgtdList.get(index).getP30F0104());
        lsMgtdList.remove(index);
        fireTableDataChanged();
    }
}
