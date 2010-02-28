/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.r;

import com.magicpwd._comn.S1S1;
import com.magicpwd._util.Util;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author amon
 */
public class LogoCR extends javax.swing.JLabel implements javax.swing.ListCellRenderer
{

    private static java.util.Map<String, javax.swing.ImageIcon> mp_LogoList;

    public LogoCR()
    {
        setOpaque(true);
        mp_LogoList = new java.util.HashMap<String, javax.swing.ImageIcon>();
    }

    public static ImageIcon getLogo(String name)
    {
        return mp_LogoList != null ? mp_LogoList.get(name) : null;
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
        if (value instanceof S1S1)
        {
            setIcon(getLogo((S1S1) value));
        }
        // 其它
        else if (value != null)
        {
            setText(value.toString());
        }

        return this;
    }

    private static javax.swing.ImageIcon getLogo(S1S1 item)
    {
        if (!mp_LogoList.containsKey(item.getK()))
        {
            try
            {
                java.io.FileInputStream stream = new java.io.FileInputStream(item.getV());
                javax.swing.ImageIcon logo = new javax.swing.ImageIcon(ImageIO.read(stream));
                stream.close();
                mp_LogoList.put(item.getK(), logo);
            }
            catch (Exception exp)
            {
                mp_LogoList.put(item.getK(), Util.getNone());
            }
        }
        return mp_LogoList.get(item.getK());
    }
}
