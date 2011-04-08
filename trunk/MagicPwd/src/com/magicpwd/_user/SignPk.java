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

package com.magicpwd._user;

/**
 *
 * @author Aven
 */
public class SignPk {

//    private void signPk()
//    {
//        String p1 = new String(pf_UserKey1.getPassword());
//        String p2 = new String(pf_UserKey2.getPassword());
//        if (!p1.equals(p2))
//        {
//            Lang.showMesg(this, LangRes.P30FAA08, "新输入的两次口令不匹配，请重新输入！");
//            pf_UserKey1.setText("");
//            pf_UserKey2.setText("");
//            pf_UserKey1.requestFocus();
//            return;
//        }
//
//        String p0 = new String(pf_UserKey0.getPassword());
//        if (!com.magicpwd._util.Char.isValidate(p0))
//        {
//            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
//            pf_UserKey0.requestFocus();
//            return;
//        }
//        try
//        {
//            boolean b = userMdl.signPk(p0, p1);
//            if (!b)
//            {
//                Lang.showMesg(this, LangRes.P30FAA09, "口令更改失败，请检查您输入的口令是否与原口令相同！");
//                pf_UserKey0.setText("");
//                pf_UserKey1.setText("");
//                pf_UserKey2.setText("");
//                pf_UserKey0.requestFocus();
//                return;
//            }
//        }
//        catch (Exception exp)
//        {
//            Logs.exception(exp);
//            Lang.showMesg(this, LangRes.P30FAA09, "口令更改失败，请检查您输入的口令是否与原口令相同！");
//            return;
//        }
//
//        dispoze();
//        if (backCall != null)
//        {
//            backCall.callBack(AuthLog.signPk.name(), null);
//        }
//        Lang.showMesg(this, LangRes.P30FAA0A, "登录口令修改成功，您可以使用新口令登录了！");
//    }
}
