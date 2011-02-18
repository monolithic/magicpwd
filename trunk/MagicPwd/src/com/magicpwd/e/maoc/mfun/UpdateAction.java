/*
 *  Copyright (C) 2011 Aven
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
package com.magicpwd.e.maoc.mfun;

import com.magicpwd.__a.maoc.AMaocAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.S1S3;
import com.magicpwd.x.maoc.MfunDlg;

/**
 *
 * @author Aven
 */
public class UpdateAction extends AMaocAction implements IBackCall<S1S3>
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        S1S3 item = maocPtn.getSelectedFun();
        if (item == null)
        {
            return;
        }

        MfunDlg dlg = new MfunDlg(maocPtn, this);
        dlg.initView();
        dlg.initLang();
        dlg.initData(item);
        dlg.setVisible(true);
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
    public boolean callBack(String options, S1S3 object)
    {
        if (!IBackCall.OPTIONS_APPLY.equals(options))
        {
            return true;
        }
        if (object == null)
        {
            return false;
        }
        maocPtn.updateFun(object);
        return true;
    }
}
