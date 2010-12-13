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
package com.magicpwd.r;

import com.magicpwd._comn.prop.Kind;
import com.magicpwd._comn.*;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;

/**
 * @author Amon
 * 
 */
public class TreeCR extends javax.swing.JLabel implements javax.swing.tree.TreeCellRenderer
{

    private javax.swing.Icon leafIcon;
    private javax.swing.Icon openIcon;
    private javax.swing.Icon closedIcon;

    public TreeCR()
    {
        this(JLabel.LEFT);
    }

    public TreeCR(int horizontalAlignment)
    {
        setHorizontalAlignment(horizontalAlignment);
        setOpaque(true);
        leafIcon = javax.swing.UIManager.getDefaults().getIcon("Tree.leafIcon");
        closedIcon = javax.swing.UIManager.getDefaults().getIcon("Tree.closedIcon");
        openIcon = javax.swing.UIManager.getDefaults().getIcon("Tree.openIcon");
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.tree.TreeCR#getTreeCellRendererComponent(javax.swing.JTree,
     *      java.lang.Object, boolean, boolean, boolean, int, boolean)
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        setFont(tree.getFont());
        setEnabled(tree.isEnabled());

//        if (leaf)
//        {
//            setIcon(leafIcon);
//        }
//        else
//        {
        if (isSelected)
        {
            setBackground(Color.GRAY);
            setIcon(openIcon);
        }
        else
        {
            setBackground(tree.getBackground());
            setForeground(tree.getForeground());
            setIcon(closedIcon);
        }
//        }

        setFocusable(hasFocus);

        if (value instanceof KindTN)
        {
            value = ((KindTN) value).getUserObject();
            Kind kind = (Kind) value;
            setText(kind.getC2010105());
            setToolTipText(kind.getC2010106());
            return this;
        }

        if (value instanceof S1S2)
        {
            S1S2 kvItem = (S1S2) value;
            setText(kvItem.getV());
            setToolTipText(kvItem.getV2());
            return this;
        }

        if (value != null)
        {
            setText(value.toString());
            setToolTipText(value.toString());
            return this;
        }

        return this;
    }
}
