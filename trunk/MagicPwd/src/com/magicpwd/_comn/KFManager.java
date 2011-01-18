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
package com.magicpwd._comn;

import java.awt.Component;
import java.awt.Container;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.event.KeyEvent;

/**
 *
 * @author Amon
 */
public class KFManager extends DefaultKeyboardFocusManager
{

    private String containerName;

    public void setContainerName(String name)
    {
        if (name != null)
        {
            name = name.trim();
            if (name.length() < 1)
            {
                name = null;
            }
        }
        containerName = name;
    }

    @Override
    public void processKeyEvent(Component focusedComponent, KeyEvent e)
    {
        if (containerName != null)
        {
            Container c = (focusedComponent instanceof Container) ? (Container) focusedComponent : focusedComponent.getParent();
            while (c != null)
            {
                System.out.println(c.getName());
                if (containerName.equalsIgnoreCase(c.getName()))
                {
                    e.consume();
                    return;
                }
                c = c.getParent();
            }
        }

        super.processKeyEvent(focusedComponent, e);
    }
}
