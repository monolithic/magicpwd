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

import com.magicpwd._comn.KFManager;
import com.magicpwd._cons.ConsEnv;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
        super.setVisible(false);
        super.setName(ConsEnv.WDIALOG_NAME);
        super.setLayout(new FlowLayout(FlowLayout.CENTER));
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
    }

    public void demo()
    {
        javax.swing.JTextField field = new javax.swing.JTextField(20);
        this.add(field);
        javax.swing.JButton button = new javax.swing.JButton("Test");
        button.setMnemonic('T');
        button.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                demoAactionPerformed();
            }
        });
        this.add(button);
    }

    private void demoAactionPerformed()
    {
        setVisible(false);
    }

    @Override
    public void setVisible(boolean visible)
    {
        if (visible)
        {
            if (!super.isVisible())
            {
                System.out.println("Visible");
                KFManager kfm = (KFManager) java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager();
                kfm.setContainerName(ConsEnv.WDIALOG_NAME);
                lastComponent = kfm.getFocusOwner();
                lastResizable = owner.isResizable();
                owner.setResizable(lastResizable & nextResizable);
                fixSize();
                System.out.println((getComponentCount() > 0 ? getComponents()[0] : this));
                (getComponentCount() > 0 ? getComponents()[0] : this).requestFocus();
            }
        }
        else
        {
            if (super.isVisible())
            {
                System.out.println("unVisible");
                KFManager kfm = (KFManager) java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager();
                kfm.setContainerName(null);
                owner.setResizable(lastResizable);
                if (lastComponent != null && lastComponent.isShowing())
                {
                    lastComponent.requestFocus();
                }
            }
        }

        super.setVisible(visible);
    }

    public void pack()
    {
    }

    /**
     * @param resizable the resizable to set
     */
    public void setResizable(boolean resizable)
    {
        nextResizable = resizable;
    }

    private void fixSize()
    {
        Dimension dim = owner.getContentPane().getSize();
        this.setBounds(0, 0, dim.width, dim.height);
    }
    private boolean lastResizable;
    private boolean nextResizable;
    private Component lastComponent;
}
