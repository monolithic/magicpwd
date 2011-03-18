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
package com.magicpwd.e.mpwd.data;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.prop.Kind;
import com.magicpwd._cons.LangRes;
import com.magicpwd._enum.AuthLog;
import com.magicpwd._user.UserDto;
import com.magicpwd._util.Lang;
import com.magicpwd.r.KindTN;

/**
 *
 * @author Amon
 */
public class ImportByKindAction extends AMpwdAction implements IBackCall<UserDto>
{

    public ImportByKindAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.tree.TreePath path = mpwdPtn.getSelectedKindValue();
        if (path == null)
        {
            Lang.showMesg(mpwdPtn, LangRes.P30F7A02, "");
            return;
        }

        trayPtn.getUserPtn(AuthLog.signRs, this);
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
    public boolean callBack(String options, UserDto object)
    {
        if (AuthLog.signRs.name().equalsIgnoreCase(options))
        {
            javax.swing.tree.TreePath path = mpwdPtn.getSelectedKindValue();
            KindTN node = (KindTN) path.getLastPathComponent();
            Kind kind = (Kind) node.getUserObject();
            if (!mpwdPtn.isKindValidate(kind))
            {
                Lang.showMesg(mpwdPtn, LangRes.P30F7A4A, "不能保存到任务列表中去！");
//                    tr_GuidTree.requestFocus();
                return false;
            }
            if (mpwdPtn.importByKind(kind.getC2010103()))
            {
                mpwdPtn.findLast();
            }
            return true;
        }
        return false;
    }
}
