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
package com.magicpwd.m.mruc;

import com.magicpwd._comn.S1S2;

/**
 *
 * @author Amon
 */
public class KeysMdl extends javax.swing.DefaultComboBoxModel
{

    private java.util.ArrayList<S1S2> unitList;

    public KeysMdl()
    {
    }

    public void init()
    {
        unitList = new java.util.ArrayList<S1S2>();
    }

    @Override
    public void setSelectedItem(Object anItem)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getSelectedItem()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSize()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getElementAt(int index)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
