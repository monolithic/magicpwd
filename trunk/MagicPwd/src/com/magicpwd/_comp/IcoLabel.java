/**
 * 
 */
package com.magicpwd._comp;

import java.awt.event.InputEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 * @author amon
 * 
 */
public class IcoLabel extends JLabel
{
    private static final long serialVersionUID = -4049173100924829062L;

    public IcoLabel()
    {
    }

    public void addActionListener(final java.awt.event.ActionListener listener)
    {
        getActionMap().put("MagicPwdEvent", new javax.swing.AbstractAction()
        {
            private static final long serialVersionUID = -8595221430413134451L;

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                listener.actionPerformed(evt);
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                java.awt.event.ActionEvent e = new java.awt.event.ActionEvent(this, 0, "");
                listener.actionPerformed(e);
            }
        });
    }

    public void setMnemonic(char mnemonic)
    {
        int vk = (int) mnemonic;
        if (vk >= 'a' && vk <= 'z')
            vk -= ('a' - 'A');
        setMnemonic(vk);
    }

    public void setMnemonic(int mnemonic)
    {
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(mnemonic, InputEvent.ALT_MASK),
                "MagicPwdEvent");
    }
}
