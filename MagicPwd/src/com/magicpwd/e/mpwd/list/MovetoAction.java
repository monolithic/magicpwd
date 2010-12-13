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
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.Keys;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.x.CatDialog;

/**
 *
 * @author Amon
 */
public class MovetoAction extends AMpwdAction
{

    public MovetoAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        CatDialog dat = new CatDialog(mainPtn, new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return changeKind(params[0]);
            }
        });
        dat.initView();
        dat.initLang();
        dat.initData();
        dat.setVisible(true);
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }

    private boolean changeKind(String hash)
    {
        if (!"0".equals(hash) && !Char.isValidateHash(hash))
        {
            return false;
        }

        Object obj = mainPtn.getSelectedListValue();
        if (obj instanceof Keys)
        {
            ((Keys) obj).setP30F0106(hash);
        }
        mainPtn.changeKind(hash);

        return true;
    }
}
