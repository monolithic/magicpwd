/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.r;

import com.magicpwd._mail.Reader;

/**
 *
 * @author Amon
 */
public class MailCR extends javax.swing.JLabel implements javax.swing.ListCellRenderer
{

    public MailCR()
    {
        setOpaque(true);
    }

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

        // 可编辑状态设置
        setEnabled(list.isEnabled());

        // 下拉列表专用
        if (value instanceof Reader)
        {
            Reader item = (Reader) value;
            setText(item.getSubject());

            // 文字属性设置
            java.awt.Font font = list.getFont();
            if (item.isNew())
            {
                font = font.deriveFont(java.awt.Font.BOLD);
            }
            setFont(font);
        }
        // 其它
        else if (value != null)
        {
            setText(value.toString());
            // 文字属性设置
            setFont(list.getFont());
        }

        return this;
    }
}
