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