/*
 *  Copyright (C) 2011 Amon
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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;

/**
 *
 * @author Amon
 */
public class WDialog extends javax.swing.JComponent
{

    private JFrame owner;

    public WDialog(JFrame frame)
    {
        this.owner = frame;
    }

    public void init()
    {
        this.setName("amonPanel");
        this.setOpaque(false);
        this.addMouseListener(new MouseAdapter()
        {
        });
        this.addComponentListener(new ComponentAdapter()
        {

            @Override
            public void componentResized(ComponentEvent e)
            {
            }
        });
        super.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    public void dispose()
    {
        owner.setResizable(lastResizable);

        if (lastComponent != null && lastComponent.isShowing())
        {
            lastComponent.requestFocus();
        }
    }

    public void pack()
    {
        lastComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();

        this.fixSize();
        this.validate();
        this.requestFocus();
    }

    /**
     * @param resizable the resizable to set
     */
    public void setResizable(boolean resizable)
    {
        lastResizable = owner.isResizable();
        if (!lastResizable)
        {
            return;
        }
        owner.setResizable(resizable);
    }

    private void fixSize()
    {
        Dimension dim = owner.getContentPane().getSize();
        this.setBounds(0, 0, dim.width, dim.height);
    }
    private boolean lastResizable;
    private Component lastComponent;
}
