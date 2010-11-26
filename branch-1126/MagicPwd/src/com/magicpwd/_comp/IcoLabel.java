/**
 * 
 */
package com.magicpwd._comp;

import java.awt.event.InputEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 * @author Amon
 * 
 */
public class IcoLabel extends JLabel
{

    private String actionCommand;

    public IcoLabel()
    {
        setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    }

    public void addActionListener(final java.awt.event.ActionListener listener)
    {
        getActionMap().put("MagicPwdEvent", new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                listener.actionPerformed(evt);
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                listener.actionPerformed(new java.awt.event.ActionEvent(evt.getSource(), evt.getID(), getActionCommand()));
            }
        });
    }

    public void setMnemonic(char mnemonic)
    {
        int vk = (int) mnemonic;
        if (vk >= 'a' && vk <= 'z')
        {
            vk -= ('a' - 'A');
        }
        setMnemonic(vk);
    }

    public void setMnemonic(int mnemonic)
    {
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(mnemonic, InputEvent.ALT_MASK), "MagicPwdEvent");
    }

    /**
     * @return the actionCommand
     */
    public String getActionCommand()
    {
        return actionCommand;
    }

    /**
     * @param actionCommand the actionCommand to set
     */
    public void setActionCommand(String actionCommand)
    {
        this.actionCommand = actionCommand;
    }
}
