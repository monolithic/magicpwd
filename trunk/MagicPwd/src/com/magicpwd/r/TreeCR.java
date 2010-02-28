/**
 * 
 */
package com.magicpwd.r;

import com.magicpwd._comn.*;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Util;

/**
 * @author Amon
 * 
 */
public class TreeCR extends JLabel implements javax.swing.tree.TreeCellRenderer
{

    public TreeCR()
    {
        this(JLabel.LEFT);
    }

    public TreeCR(int horizontalAlignment)
    {
        setHorizontalAlignment(horizontalAlignment);
        setOpaque(true);
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

        if (isSelected)
        {
            setBackground(Color.GRAY);
        }
        else
        {
            setBackground(tree.getBackground());
            setForeground(tree.getForeground());
        }

        setFocusable(hasFocus);
        setIcon(expanded ? Util.getIcon(ConsEnv.ICON_KIND_XPND) : Util.getIcon(ConsEnv.ICON_KIND_CLPS));

        if (value instanceof KindTN)
        {
            value = ((KindTN) value).getUserObject();
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
        }

        return this;
    }
}
