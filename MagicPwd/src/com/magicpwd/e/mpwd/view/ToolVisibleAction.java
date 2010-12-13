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
package com.magicpwd.e.mpwd.view;

import com.magicpwd.__a.mpwd.AMpwdAction;

/**
 *
 * @author Amon
 */
public class ToolVisibleAction extends AMpwdAction
{

    public ToolVisibleAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !mainPtn.getUserMdl().isToolVisible("mpwd");
        mainPtn.setToolVisible(b);
        mainPtn.pack();
    }

    @Override
    public void doInit(Object object)
    {
        setSelected(mainPtn.getUserMdl().isToolVisible("mpwd"));
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
        button.setSelected(isSelected());
    }
}
