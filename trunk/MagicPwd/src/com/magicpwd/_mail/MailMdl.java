/**
 * 
 */
package com.magicpwd._mail;

import com.magicpwd._util.Logs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author Administrator
 * 
 */
public class MailMdl extends AbstractTableModel
{

    private List<MailInf> messages;

    public MailMdl()
    {
        messages = new ArrayList<MailInf>();
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
                return javax.swing.JPanel.class;
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
            MailInf message = messages.get(rowIndex);
            switch (columnIndex)
            {
                case 0:
                    return null;//message.getFlags().getSystemFlags()[0];
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

    public MailInf getMailInf(int rowIndex)
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

    public void append(MailInf mailInf)
    {
        messages.add(mailInf);
        fireTableDataChanged();
    }
}
