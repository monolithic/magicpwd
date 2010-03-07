/**
 *
 */
package com.magicpwd.r;

import com.magicpwd._comn.Keys;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Util;

/**
 *
 * @author Amon
 */
public class KeysCR extends javax.swing.JPanel implements javax.swing.ListCellRenderer
{

    public KeysCR()
    {
        lb_Icon = new javax.swing.JLabel();
        lb_Text = new javax.swing.JLabel();
        lb_Note = new javax.swing.JLabel();
        lb_Mode = new javax.swing.JLabel();
        lb_Rest = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(lb_Icon);
        hsg.addComponent(lb_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE);
        hsg.addComponent(lb_Note);
        hsg.addComponent(lb_Mode);
        hsg.addComponent(lb_Rest);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(lb_Icon);
        vpg.addComponent(lb_Text);
        vpg.addComponent(lb_Note);
        vpg.addComponent(lb_Mode);
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

        lb_Icon.setIcon(Util.getNone());

        // 口令列表专用
        if (value instanceof Keys)
        {
            Keys keys = (Keys) value;
            lb_Text.setText(keys.getP30F0109());
            setToolTipText(Util.isValidate(keys.getP30F010A()) ? keys.getP30F010A() : keys.getP30F0109());
            lb_Text.setIcon(Util.isValidateHash(keys.getP30F010B()) ? Util.getIcon(keys.getP30F010B()) : Util.getNone());
            lb_Note.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_NOTE + keys.getP30F0103()));
            lb_Mode.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_MOD0 + keys.getP30F0102()));
        }
        // 其它
        else if (value != null)
        {
            lb_Text.setText(value.toString());
            lb_Mode.setIcon(Util.getNone());
            lb_Note.setIcon(Util.getNone());
        }

        lb_Rest.setIcon(Util.getNone());

        return this;
    }
    private javax.swing.JLabel lb_Icon;
    private javax.swing.JLabel lb_Note;
    private javax.swing.JLabel lb_Rest;
    private javax.swing.JLabel lb_Mode;
    private javax.swing.JLabel lb_Text;
}
