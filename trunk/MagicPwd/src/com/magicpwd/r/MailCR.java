/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.r;

/**
 *
 * @author Amon
 */
public class MailCR implements javax.swing.table.TableCellRenderer
{

    public MailCR()
    {
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        javax.swing.JLabel label;
        if (value instanceof javax.swing.JLabel)
        {
            label = (javax.swing.JLabel) value;
        }
        else
        {
            label = new javax.swing.JLabel(value.toString());
        }

        label.setOpaque(true);
        // 前景及背景颜色设置
        if (isSelected)
        {
            label.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
        }
        else
        {
            label.setBackground(table.getBackground());
            label.setForeground(table.getForeground());
        }
        return label;
    }
}
