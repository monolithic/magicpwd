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
package com.magicpwd.e.mpwd.safe;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.S1S1;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.x.DatDialog;

/**
 *
 * @author Amon
 */
public class RemoteResumeAction extends AMpwdAction implements IBackCall<String>
{

    public RemoteResumeAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(mpwdPtn, LangRes.P30F7A41, "确认要执行从云端数据恢复的操作吗，此操作将需要一定的时间？"))
        {
            return;
        }

        new Thread()
        {

            @Override
            public void run()
            {
                doResume();
            }
        }.start();
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
    public boolean callBack(String options, String object)
    {
        if (IBackCall.OPTIONS_ABORT.equalsIgnoreCase(options))
        {
            return true;
        }
        if (!IBackCall.OPTIONS_APPLY.equalsIgnoreCase(options))
        {
            return false;
        }

        mpwdPtn.showProgress();
        doResume(object);
        return true;
    }

    private void doResume()
    {
        mpwdPtn.showProgress();

        java.util.List<S1S1> list = new java.util.ArrayList<S1S1>();
        try
        {
            mpwdPtn.remoteDetect(list);
        }
        catch (Exception exp)
        {
            mpwdPtn.hideProgress();

            Logs.exception(exp);
            Lang.showMesg(mpwdPtn, null, exp.getLocalizedMessage());
            return;
        }

        if (list.size() < 1)
        {
            mpwdPtn.hideProgress();

            Lang.showMesg(mpwdPtn, LangRes.P30F7A55, "没有发现可用的备份数据！");
            return;
        }

        if (list.size() == 1)
        {
            doResume(list.get(0).getK());
            return;
        }

        mpwdPtn.hideProgress();

        DatDialog datDialog = new DatDialog(mpwdPtn, this);
        datDialog.initView();
        datDialog.initLang();
        datDialog.initData();
        datDialog.showData(list);
        datDialog.setVisible(true);
    }

    private void doResume(String sign)
    {
        try
        {
            boolean b = mpwdPtn.remoteResume(sign, null);
            mpwdPtn.hideProgress();

            if (b)
            {
                Lang.showMesg(mpwdPtn, LangRes.P30F7A3F, "恭喜，数据恢复成功，您需要重新启动本程序！");
            }
            else
            {
                Lang.showMesg(mpwdPtn, LangRes.P30F7A3E, "数据恢复失败，请重启软件后重试！");
            }
        }
        catch (Exception ex)
        {
            mpwdPtn.hideProgress();

            Logs.exception(ex);
            Lang.showMesg(mpwdPtn, null, ex.getLocalizedMessage());
        }
        System.exit(0);
    }
}
