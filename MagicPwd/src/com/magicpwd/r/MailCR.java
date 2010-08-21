/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.r;

import java.text.SimpleDateFormat;

/**
 *
 * @author Amon
 */
public class MailCR extends javax.swing.JLabel implements javax.swing.table.TableCellRenderer
{

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public MailCR()
    {
        setOpaque(true);
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        // 前景及背景颜色设置
        if (isSelected)
        {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        }
        else
        {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        // 其它
        if (value != null)
        {
            // 下拉列表专用
            if (value instanceof javax.swing.Icon)
            {
                setIcon((javax.swing.Icon) value);
            }
            if (value instanceof java.util.Date)
            {
                setText(format.format((java.util.Date) value));
            }

            // 文字属性设置
//            java.awt.Font font = list.getFont();
//            font = font.deriveFont(java.awt.Font.BOLD);
//            setFont(font);
            setText(value.toString());
        }

        return this;
    }
}
