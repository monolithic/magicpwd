/*
 *  Copyright (C) 2011 Amon
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
package com.magicpwd.r.mexp;

import com.magicpwd._cons.ConsEnv;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;

/**
 *
 * @author Amon
 */
public class MexpCR extends javax.swing.table.DefaultTableCellRenderer
{

    public MexpCR()
    {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (row < ConsEnv.PWDS_HEAD_SIZE)
        {
            this.setBackground(Color.red);
        }
        else if (column == 0)
        {
            this.setBackground(Color.cyan);
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
