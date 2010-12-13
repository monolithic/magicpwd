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
package com.magicpwd.e.mpwd.safe;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;

/**
 *
 * @author Amon
 */
public class RemoteConfigAction extends AMpwdAction implements IBackCall
{

    public RemoteConfigAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        trayPtn.getUserPtn(ConsEnv.INT_SIGN_CS, this);
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }

    @Override
    public boolean callBack(Object sender, java.util.EventListener event, String... params)
    {
        if (params == null || params.length < 4 || !ConsEnv.STR_SIGN_CS.equals(params[0]))
        {
            return false;
        }

        try
        {
            if (!Char.isValidateMail(params[1]))
            {
                params[1] = params[2] + '@' + params[1];
            }
            mainPtn.setCfgText("pop_mail", params[1] + '\n' + params[2] + '\n' + params[3]);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            Lang.showMesg(mainPtn, null, ex.getLocalizedMessage());
        }
        return true;
    }
}
