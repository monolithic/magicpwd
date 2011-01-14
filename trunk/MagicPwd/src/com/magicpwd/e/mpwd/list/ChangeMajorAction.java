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
package com.magicpwd.e.mpwd.list;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._comn.Keys;
import com.magicpwd._util.Char;

/**
 *
 * @author Amon
 */
public class ChangeMajorAction extends AMpwdAction
{

    public ChangeMajorAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        String command = e.getActionCommand();
        int val = Char.isValidateInteger(command) ? Integer.parseInt(command) : 0;

        Object obj = mpwdPtn.getSelectedListValue();
        if (obj instanceof Keys)
        {
            ((Keys) obj).setP30F0103(val);
        }
        mpwdPtn.changeMajor(val);
    }

    @Override
    public void doInit(String value)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button, String value)
    {
    }
}
