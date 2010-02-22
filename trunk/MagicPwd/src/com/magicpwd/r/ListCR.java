/**
 * 
 */
package com.magicpwd.r;

import com.magicpwd._comn.*;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;

import com.magicpwd._util.Util;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * @author Amon
 * 
 */
public class ListCR extends JLabel implements javax.swing.ListCellRenderer
{
    public ListCR()
    {
        this(JLabel.LEFT);
    }

    public ListCR(int horizontalAlignment)
    {
        setHorizontalAlignment(horizontalAlignment);
        setOpaque(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListCR#getListCellRendererComponent(javax.swing.JList,
     *      java.lang.Object, int, boolean, boolean)
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        // 前景及背景颜色设置
        if (isSelected)
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        // 文字属性设置
        setFont(list.getFont());

        // 可编辑状态设置
        setEnabled(list.isEnabled());

        setForeground(Color.red);

        // 显示文本及提示信息
        if (value instanceof Keys)
        {
            Keys keys = (Keys) value;
            setText(keys.getP30F0109());
            setToolTipText(Util.isValidate(keys.getP30F010A()) ? keys.getP30F010A() : keys.getP30F0109());
            setIcon(new ImageIcon(keys.getP30F010B()));
        }
        else if (value instanceof S1S2)
        {
            S1S2 item = (S1S2) value;
            setText(item.getV1());
            setToolTipText(Util.isValidate(item.getV2()) ? item.getV2() : item.getV1());
        }
        else if (value != null)
        {
            setText(value.toString());
        }

        return this;
    }
}
