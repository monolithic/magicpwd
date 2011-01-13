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
package com.magicpwd._comp;

import java.awt.event.InputEvent;
import javax.swing.Icon;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 * @author Amon
 * 
 */
public class IcoLabel extends JLabel
{

    private boolean selected;
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

    /**
     * @return the selected
     */
    public boolean isSelected()
    {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected)
    {
        this.selected = selected;
        if (selIcon != null)
        {
            if (selected)
            {
                setIcon(selIcon);
            }
            else
            {
                setIcon(defIcon);
            }
        }
    }

    @Override
    public void setIcon(Icon icon)
    {
        this.defIcon = icon;
        super.setIcon(icon);
    }

    public void setSelectedIcon(Icon icon)
    {
        selIcon = icon;
    }
    private Icon defIcon;
    private Icon selIcon;
}
