/**
 * 
 */
package com.magicpwd._mail;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Bean;
import com.magicpwd.m.UserMdl;

/**
 * @author Amon
 * 
 */
public class MailMdl extends javax.swing.table.AbstractTableModel
{

    private java.util.ArrayList<Reader> messages;
    private java.text.SimpleDateFormat format;
    private UserMdl userMdl;

    public MailMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
        messages = new java.util.ArrayList<Reader>();
        format = new java.text.SimpleDateFormat("yyyy-MM-dd");
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
        return javax.swing.JLabel.class;
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
            return null;
        }

        javax.swing.JLabel label = new javax.swing.JLabel();
        Reader message = messages.get(rowIndex);
        if (!message.isReaded())
        {
            label.setFont(label.getFont().deriveFont(java.awt.Font.BOLD));
        }

        switch (columnIndex)
        {
            case 0:
                label.setIcon(message.hasAttachment() ? userMdl.readIcon(ConsEnv.FEEL_PATH + "mail-attach.png") : Bean.getNone());
                break;
            case 1:
                label.setText(message.getFrom());
                break;
            case 2:
                label.setText(message.getSubject());
                break;
            case 3:
                label.setText(format.format(message.getSentDate()));
                break;
            default:
                break;
        }
        return label;
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
        int i = 0;
        for (Reader tmp : messages)
        {
            if (mailInf.getSentDate().after(tmp.getSentDate()))
            {
                break;
            }
            i += 1;
        }
        messages.add(i, mailInf);
        fireTableDataChanged();
    }
}
