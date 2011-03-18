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
package com.magicpwd.e.mexp.user;

import com.magicpwd.__a.mexp.AMexpAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.LangRes;
import com.magicpwd._enum.AuthLog;
import com.magicpwd._user.UserDto;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;

/**
 *
 * @author Amon
 */
public class CreateSkeyAction extends AMexpAction implements IBackCall<UserDto>
{

    public CreateSkeyAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (Char.isValidate(mpwdPtn.getUserMdl().getCfg(ConsCfg.CFG_USER_SKEY), 224))
        {
            Lang.showMesg(mpwdPtn, LangRes.P30F7A28, "您已经设置过安全口令！");
            return;
        }

        trayPtn.getUserPtn(AuthLog.signSk, this);
    }

    @Override
    public void doInit(String value)
    {
        setEnabled(!Char.isValidate(mpwdPtn.getUserMdl().getCfg(ConsCfg.CFG_USER_SKEY), 224));
    }

    @Override
    public void reInit(javax.swing.AbstractButton button, String value)
    {
        button.setEnabled(isEnabled());
    }

    @Override
    public boolean callBack(String options, UserDto object)
    {
        if (AuthLog.signSk.name().equalsIgnoreCase(options))
        {
            javax.swing.AbstractButton button = mpwdPtn.getMenuPtn().getButton("mpwd-skey");
            if (button != null)
            {
                button.setEnabled(false);
            }
            return true;
        }
        return false;
    }
}
