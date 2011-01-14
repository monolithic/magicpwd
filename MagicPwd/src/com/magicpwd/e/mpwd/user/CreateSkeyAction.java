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
package com.magicpwd.e.mpwd.user;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._user.UserDto;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;

/**
 *
 * @author Amon
 */
public class CreateSkeyAction extends AMpwdAction implements IBackCall<UserDto>
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

        trayPtn.getUserPtn(ConsEnv.INT_SIGN_SK, this);
    }

    @Override
    public void doInit(String value)
    {
        setEnabled(!Char.isValidate(mpwdPtn.getUserMdl().getCfg(ConsCfg.CFG_USER_SKEY), 224));
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
        button.setEnabled(isEnabled());
    }

    @Override
    public boolean callBack(String options, UserDto object)
    {
        if (ConsEnv.STR_SIGN_SK.equalsIgnoreCase(options))
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
