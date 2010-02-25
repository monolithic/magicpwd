/**
 * 
 */
package com.magicpwd.r;

import com.magicpwd._comn.*;
import com.magicpwd._cons.ConsEnv;
import java.awt.Component;

import javax.swing.JList;

import com.magicpwd._util.Util;
import javax.swing.ImageIcon;

/**
 * @author Amon
 * 
 */
public class ListCR extends javax.swing.JPanel implements javax.swing.ListCellRenderer
{

    public ListCR()
    {
        lb_Image = new javax.swing.JLabel();
        lb_Title = new javax.swing.JLabel();
        lb_Major = new javax.swing.JLabel();
        lb_State = new javax.swing.JLabel();
        lb_Other = new javax.swing.JLabel();

        lb_Title.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        this.setOpaque(false);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(lb_Image);
        hsg.addComponent(lb_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE);
        hsg.addComponent(lb_Major);
        hsg.addComponent(lb_State);
        hsg.addComponent(lb_Other);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(lb_Image);
        vpg.addComponent(lb_Title);
        vpg.addComponent(lb_Major);
        vpg.addComponent(lb_State);
        vpg.addComponent(lb_Other);
        layout.setVerticalGroup(vpg);
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
            lb_Title.setBackground(list.getSelectionBackground());
            lb_Title.setForeground(list.getSelectionForeground());
        }
        else
        {
            lb_Title.setBackground(list.getBackground());
            lb_Title.setForeground(list.getForeground());
        }

        // 文字属性设置
        lb_Title.setFont(list.getFont());

        lb_Image.setIcon(Util.getNone());
        lb_State.setIcon(Util.getNone());
        lb_Major.setIcon(Util.getNone());
        lb_Other.setIcon(Util.getNone());

        // 可编辑状态设置
        setEnabled(list.isEnabled());

        // 显示文本及提示信息
        if (value instanceof Keys)
        {
            Keys keys = (Keys) value;
            lb_State.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_MOD0 + keys.getP30F0102()));
            lb_Title.setText(keys.getP30F0109());
            setToolTipText(Util.isValidate(keys.getP30F010A()) ? keys.getP30F010A() : keys.getP30F0109());
            if (Util.isValidateHash(keys.getP30F010B()))
            {
                lb_Title.setIcon(new ImageIcon(Util.format("ico/{0}.png", keys.getP30F010B())));
            }
        }
        else if (value instanceof S1S2)
        {
            S1S2 item = (S1S2) value;
            lb_Title.setText(item.getV1());
            setToolTipText(Util.isValidate(item.getV2()) ? item.getV2() : item.getV1());
        }
        else if (value != null)
        {
            lb_Title.setText(value.toString());
        }

        return this;
    }
    private javax.swing.JLabel lb_Image;
    private javax.swing.JLabel lb_Major;
    private javax.swing.JLabel lb_Other;
    private javax.swing.JLabel lb_State;
    private javax.swing.JLabel lb_Title;
}
