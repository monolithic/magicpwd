/**
 * 
 */
package com.magicpwd._mail;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;

/**
 * @author Amon
 * 
 */
public class MailMdl extends javax.swing.table.AbstractTableModel
{

    private java.util.ArrayList<Reader> messages;

    public MailMdl()
    {
        messages = new java.util.ArrayList<Reader>();
    }

    @Override
    public int getColumnCount()
    {
        return 4;
    }

    @Override
    public int getRowCount()
    {
        return messages.size();
    }

    @Override
    public Class getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0:
                return javax.swing.ImageIcon.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return java.util.Date.class;
            default:
                return String.class;
        }
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0:
                return "";
            case 1:
                return "发送人";
            case 2:
                return "标题";
            case 3:
                return "时间";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if (rowIndex < 0 || rowIndex >= messages.size())
        {
            return "";
        }
        try
        {
            Reader message = messages.get(rowIndex);
            switch (columnIndex)
            {
                case 0:
                    return message.hasAttachment() ? Util.getIcon(ConsEnv.ICON_MAIL_FILE) : Util.getNone();
                case 1:
                    return message.getFrom();
                case 2:
                    return message.getSubject();
                case 3:
                    return message.getSentDate();
                default:
                    return null;
            }
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            return "";
        }
    }

    @Override
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }

    public Reader getMailInf(int rowIndex)
    {
        if (rowIndex < 0 || rowIndex >= messages.size())
        {
            return null;
        }
        return messages.get(rowIndex);
    }

    public void clear()
    {
        messages.clear();
    }

    public void append(Reader mailInf)
    {
        messages.add(mailInf);
        fireTableDataChanged();
    }
}
