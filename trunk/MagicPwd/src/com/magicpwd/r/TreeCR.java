/**
 * 
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
public class TreeCR extends JLabel implements javax.swing.tree.TreeCellRenderer
{

    // Icons
    /** Icon used to show non-leaf nodes that aren't expanded. */
    transient protected javax.swing.Icon closedIcon;
    /** Icon used to show leaf nodes. */
    transient protected javax.swing.Icon leafIcon;
    /** Icon used to show non-leaf nodes that are expanded. */
    transient protected javax.swing.Icon openIcon;

    public TreeCR()
    {
        this(JLabel.LEFT);
    }

    public TreeCR(int horizontalAlignment)
    {
        setHorizontalAlignment(horizontalAlignment);
        setOpaque(true);
        leafIcon = sun.swing.DefaultLookup.getIcon(this, ui, "Tree.leafIcon");
        closedIcon = sun.swing.DefaultLookup.getIcon(this, ui, "Tree.closedIcon");
        openIcon = sun.swing.DefaultLookup.getIcon(this, ui, "Tree.openIcon");
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

        if (leaf)
        {
            setIcon(leafIcon);
        }
        else
        {
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
        }

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
