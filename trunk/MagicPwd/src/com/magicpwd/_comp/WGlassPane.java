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

import com.magicpwd._util.Logs;
import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.util.EventObject;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Amon
 */
public class WGlassPane extends JComponent implements AWTEventListener
{

    private Window parentWindow;
    private Component lastFocusOwner;
    private final Toolkit toolkit;

    public WGlassPane()
    {
        setOpaque(false);
        toolkit = Toolkit.getDefaultToolkit();
    }

    @Override
    public void setVisible(boolean visible)
    {
        if (visible)
        {
            if (parentWindow == null)
            {
                parentWindow = SwingUtilities.windowForComponent(this);
            }
            Component focusOwner = parentWindow.getFocusOwner();
            if (focusOwner != this)
            {
                lastFocusOwner = focusOwner;
            }
            toolkit.addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);
            toolkit.addAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK);
            requestFocus();
        }
        else
        {
            toolkit.removeAWTEventListener(this);
            if (lastFocusOwner != null)
            {
                lastFocusOwner.requestFocus();
                lastFocusOwner = null;
            }
        }
        super.setVisible(visible);
    }

    @Override
    public void eventDispatched(AWTEvent e)
    {
        Object source = e.getSource();
        if (e instanceof EventObject && source instanceof Component)
        {
            Component src = (Component) source;
            EventObject obj = (EventObject) e;
            if (SwingUtilities.windowForComponent(src) == parentWindow)
            {
                try
                {
                    Class[] cls =
                    {
                    };
                    Object[] args =
                    {
                    };
                    obj.getClass().getMethod("consume", cls).invoke(obj, args);
                }
                catch (Exception ex)
                {
                    Logs.exception(ex);
                }
            }
        }
    }
}
