/**
 * 
 */
package com.magicpwd.r;

import com.magicpwd._comn.S1S2;
import com.magicpwd._util.Util;

/**
 * @author Amon
 * 
 */
public class ListCR extends javax.swing.JLabel implements javax.swing.ListCellRenderer
{
    public ListCR()
    {
        setOpaque(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListCR#getListCellRendererComponent(javax.swing.JList,
     *      java.lang.Object, int, boolean, boolean)
     */
    @Override
    public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
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

        // 下拉列表专用
        if (value instanceof S1S2)
        {
            S1S2 item = (S1S2) value;
            setText(item.getV());
            setToolTipText(Util.isValidate(item.getV2()) ? item.getV2() : item.getV());
        }
        // 其它
        else if (value != null)
        {
            setText(value.toString());
        }

        return this;
    }
}
