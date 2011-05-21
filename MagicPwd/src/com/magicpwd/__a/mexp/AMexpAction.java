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
package com.magicpwd.__a.mexp;

import com.magicpwd.__a.AAction;
import com.magicpwd.__i.mexp.IMexpAction;
import com.magicpwd.v.gui.mexp.MexpPtn;

/**
 *
 * @author Amon
 */
public abstract class AMexpAction extends AAction implements IMexpAction
{

    protected MexpPtn mexpPtn;

    @Override
    public void setMexpPtn(MexpPtn mexpPtn)
    {
        this.mexpPtn = mexpPtn;
    }
}
