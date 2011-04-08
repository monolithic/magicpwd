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

import com.magicpwd.__i.IUserView;

/**
 *
 * @author Aven
 */
public class SignSk extends javax.swing.JPanel implements IUserView
{

    @Override
    public void initView()
    {
    }

    @Override
    public void initLang()
    {
    }

    @Override
    public void initData()
    {
    }

    @Override
    public javax.swing.JPanel getPanel()
    {
        return this;
    }

    @Override
    public void btApplyActionPerformed(java.awt.event.ActionEvent e)
    {
    }

    @Override
    public void btAbortActionPerformed(java.awt.event.ActionEvent e)
    {
    }
    /**
     *
     */
//    private void signSk()
//    {
//        String p0 = new String(pf_UserKey0.getPassword());
//        if (!com.magicpwd._util.Char.isValidate(p0))
//        {
//            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
//            pf_UserKey0.requestFocus();
//            return;
//        }
//
//        String p1 = new String(pf_UserKey1.getPassword());
//        if (!com.magicpwd._util.Char.isValidate(p1))
//        {
//            Lang.showMesg(this, LangRes.P30FAA14, "请输入安全口令！");
//            pf_UserKey1.setText("");
//            pf_UserKey1.requestFocus();
//            return;
//        }
//        String p2 = new String(pf_UserKey2.getPassword());
//
//        if (!p1.equals(p2))
//        {
//            Lang.showMesg(this, LangRes.P30FAA15, "您输入的两次口令不匹配，请重新输入！");
//            pf_UserKey1.setText("");
//            pf_UserKey2.setText("");
//            pf_UserKey1.requestFocus();
//            return;
//        }
//
//        try
//        {
//            boolean b = userMdl.signSk(p0, p1);
//            if (!b)
//            {
//                Lang.showMesg(this, LangRes.P30FAA16, "安全口令设定失败，请稍后重新尝试！");
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
//            Lang.showMesg(this, LangRes.P30FAA16, "安全口令设定失败，请稍后重新尝试！");
//            return;
//        }
//
//        dispoze();
//        if (backCall != null)
//        {
//            backCall.callBack(AuthLog.signSk.name(), null);
//        }
//        pf_UserKey0.setText("");
//        pf_UserKey1.setText("");
//        pf_UserKey2.setText("");
//        Lang.showMesg(this, LangRes.P30FAA17, "安全口令设定成功！");
//    }
}
