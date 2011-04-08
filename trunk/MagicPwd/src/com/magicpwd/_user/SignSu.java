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
public class SignSu extends javax.swing.JPanel implements IUserView
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
//    private void signSu()
//    {
//        // 登录名称检测
//        String un = tf_UserName.getText();
//        if (un == null)
//        {
//            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
//            tf_UserName.requestFocus();
//            return;
//        }
//        un = un.trim();
//        if (un.length() < 1)
//        {
//            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
//            tf_UserName.requestFocus();
//            return;
//        }
//        String ul = userMdl.getCfg(ConsCfg.CFG_USER, "");
//        if (ul.indexOf(un + ',') >= 0)
//        {
//            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
//        }
//
//        if (backCall != null)
//        {
//            backCall.callBack(AuthLog.signSu.name(), null);
//        }
//    }
}
