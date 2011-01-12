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
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
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
        javax.swing.tree.TreePath path = mainPtn.getSelectedKindValue();
        if (path == null)
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A02, "");
            return;
        }

        trayPtn.getUserPtn(ConsEnv.INT_SIGN_RS, this);
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
    public boolean callBack(String options, UserDto object)
    {
        if (ConsEnv.STR_SIGN_RS.equalsIgnoreCase(options))
        {
            javax.swing.tree.TreePath path = mainPtn.getSelectedKindValue();
            KindTN node = (KindTN) path.getLastPathComponent();
            Kind kind = (Kind) node.getUserObject();
            if (!mainPtn.isKindValidate(kind))
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A4A, "不能保存到任务列表中去！");
//                    tr_GuidTree.requestFocus();
                return false;
            }
            if (mainPtn.importByKind(kind.getC2010103()))
            {
                mainPtn.findLast();
            }
            return true;
        }
        return false;
    }
}
