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
package com.magicpwd.e.maoc.mnum;

import com.magicpwd.__a.maoc.AMaocAction;
import com.magicpwd.v.maoc.MaocPtn;
import com.magicpwd.x.maoc.EditDlg;

/**
 *
 * @author Aven
 */
public class AppendAction extends AMaocAction
{

    @Override
    public void setMaocPtn(MaocPtn maocPtn)
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        EditDlg dlg = new EditDlg();
        dlg.initView();
        dlg.initLang();
        dlg.initData();
        int status = javax.swing.JOptionPane.showConfirmDialog(maocPtn, dlg, "", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.INFORMATION_MESSAGE);
        if (javax.swing.JOptionPane.OK_OPTION != status)
        {
            return;
        }
        maocPtn.appendNum(dlg.getInputName(), dlg.getInputValue(), dlg.getInputRemark());
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
