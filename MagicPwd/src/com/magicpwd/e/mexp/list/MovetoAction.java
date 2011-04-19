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
package com.magicpwd.e.mexp.list;

import com.magicpwd.__a.mexp.AMexpAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.mpwd.Mkey;
import com.magicpwd._util.Char;
import com.magicpwd.x.CatDialog;

/**
 *
 * @author Amon
 */
public class MovetoAction extends AMexpAction implements IBackCall<String, String>
{

    public MovetoAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        CatDialog dat = new CatDialog(mexpPtn, this);
        dat.initView();
        dat.initLang();
        dat.initData();
        dat.setVisible(true);
    }

    @Override
    public void doInit(String value)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button, String value)
    {
    }

    @Override
    public boolean callBack(String options, String hash)
    {
        if (!"0".equals(hash) && !Char.isValidateHash(hash))
        {
            return false;
        }

        Object obj = mexpPtn.getSelectedListValue();
        if (obj instanceof Mkey)
        {
            ((Mkey) obj).setP30F0106(hash);
        }
        mexpPtn.changeKind(hash);

        return true;
    }
}