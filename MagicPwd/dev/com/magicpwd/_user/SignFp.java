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
public class SignFp {

//    private void signFp()
//    {
//        String name = tf_UserName.getText();
//        if (!com.magicpwd._util.Char.isValidate(name))
//        {
//            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
//            tf_UserName.requestFocus();
//            return;
//        }
//        String pwds = new String(pf_UserKey0.getPassword());
//        if (!com.magicpwd._util.Char.isValidate(pwds))
//        {
//            Lang.showMesg(this, LangRes.P30FAA14, "请输入安全口令！");
//            pf_UserKey0.requestFocus();
//            return;
//        }
//
//        StringBuffer sb = new StringBuffer(pwds);
//        try
//        {
//            boolean b = userMdl.signFp(name, sb);
//            if (!b)
//            {
//                Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
//                tf_UserName.setText("");
//                pf_UserKey0.setText("");
//                tf_UserName.requestFocus();
//                errCount += 1;
//                if (errCount > 2)
//                {
//                    System.exit(0);
//                }
//                return;
//            }
//        }
//        catch (java.security.NoSuchAlgorithmException exp)
//        {
//            Logs.exception(exp);
//            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
//            System.exit(0);
//            return;
//        }
//        catch (javax.crypto.NoSuchPaddingException exp)
//        {
//            Logs.exception(exp);
//            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
//            System.exit(0);
//            return;
//        }
//        catch (Exception exp)
//        {
//            Logs.exception(exp);
//            Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
//            tf_UserName.setText("");
//            pf_UserKey0.setText("");
//            errCount += 1;
//            if (errCount > 2)
//            {
//                System.exit(0);
//            }
//            return;
//        }
//
//        if (backCall != null)
//        {
//            backCall.callBack(AuthLog.signFp.name(), new UserDto(sb.toString()));
//        }
//        Lang.showMesg(null, LangRes.P30FAA18, "您的新口令是：{0}\n为了您的安全，请登录软件后尽快修改您的口令。", sb.toString());
//        this.initView(AuthLog.signIn);
//        this.initLang();
//        this.initData();
//    }
}
