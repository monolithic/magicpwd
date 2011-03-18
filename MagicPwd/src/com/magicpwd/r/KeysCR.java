/*
 *  Copyright (C) 2010 Amon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.r;

import com.magicpwd._comn.Keys;
import com.magicpwd._util.Bean;
import com.magicpwd.v.mexp.MexpPtn;

/**
 *
 * @author Amon
 */
public class KeysCR extends javax.swing.JPanel implements javax.swing.ListCellRenderer
{

    private MexpPtn mainPtn;

    public KeysCR(MexpPtn mainPtn)
    {
        this.mainPtn = mainPtn;

        lb_Icon = new javax.swing.JLabel();
        lb_Text = new javax.swing.JLabel();
        lb_Major = new javax.swing.JLabel();
        lb_Label = new javax.swing.JLabel();
        lb_Rest = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(lb_Icon);
        hsg.addComponent(lb_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE);
        hsg.addComponent(lb_Label);
        hsg.addComponent(lb_Major);
        hsg.addComponent(lb_Rest);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(lb_Icon);
        vpg.addComponent(lb_Text);
        vpg.addComponent(lb_Label);
        vpg.addComponent(lb_Major);
        vpg.addComponent(lb_Rest);
        layout.setVerticalGroup(vpg);
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
            this.setBackground(list.getSelectionBackground());
            lb_Text.setForeground(list.getSelectionForeground());
        }
        else
        {
            this.setBackground(list.getBackground());
            lb_Text.setForeground(list.getForeground());
        }

        // 可编辑状态设置
        setEnabled(list.isEnabled());

        // 文字属性设置
        lb_Text.setFont(list.getFont());

//        lb_Icon.setIcon(Util.getNone());

        // 口令列表专用
        if (value instanceof Keys)
        {
            Keys keys = (Keys) value;
            lb_Text.setText(keys.getP30F0109());
            setToolTipText(com.magicpwd._util.Char.isValidate(keys.getP30F010A()) ? keys.getP30F010A() : keys.getP30F0109());
            lb_Text.setIcon(Bean.getDataIcon(keys.getP30F010B()));
            lb_Major.setIcon(mainPtn.getFavIcon("keys-major" + (keys.getP30F0103() > 0 ? "+" : "") + keys.getP30F0103()));
            lb_Label.setIcon(mainPtn.getFavIcon("keys-label" + keys.getP30F0102()));
        }
        // 其它
        else if (value != null)
        {
            lb_Text.setText(value.toString());
            lb_Label.setIcon(Bean.getNone());
            lb_Major.setIcon(Bean.getNone());
        }

//        lb_Rest.setIcon(Util.getNone());

        return this;
    }
    private javax.swing.JLabel lb_Icon;
    private javax.swing.JLabel lb_Major;
    private javax.swing.JLabel lb_Rest;
    private javax.swing.JLabel lb_Label;
    private javax.swing.JLabel lb_Text;
}
