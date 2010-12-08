/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.safe;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.S1S1;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.x.DatDialog;
import java.util.EventListener;

/**
 *
 * @author Amon
 */
public class RemoteResumeAction extends AMpwdAction implements IBackCall
{

    public RemoteResumeAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(mainPtn, LangRes.P30F7A41, "确认要执行从云端数据恢复的操作吗，此操作将需要一定的时间？"))
        {
            return;
        }

        mainPtn.showProcessDialog();
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
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }

    @Override
    public boolean callBack(Object sender, EventListener event, String... params)
    {
        if (params == null || params.length < 1)
        {
            return false;
        }
        if ("cancel".equals(params[0]))
        {
            return true;
        }
        if (!"select".equals(params[0]))
        {
            return false;
        }

        doResume(params[1]);
        return true;
    }

    private void doResume()
    {
        java.util.List<S1S1> list = new java.util.ArrayList<S1S1>();
        try
        {
            mainPtn.remoteDetect(list);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            mainPtn.hideProcessDialog();
            Lang.showMesg(mainPtn, null, exp.getLocalizedMessage());
            return;
        }

        if (list.size() < 1)
        {
            mainPtn.hideProcessDialog();
            Lang.showMesg(mainPtn, LangRes.P30F7A55, "没有发现可用的备份数据！");
            return;
        }

        if (list.size() == 1)
        {
            doResume(list.get(0).getK());
            return;
        }

        DatDialog datDialog = new DatDialog(mainPtn, this);
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
            boolean b = mainPtn.remoteResume(sign, null);
            mainPtn.hideProcessDialog();
            if (b)
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A3F, "恭喜，数据恢复成功，您需要重新启动本程序！");
            }
            else
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A3E, "数据恢复失败，请重启软件后重试！");
            }
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            Lang.showMesg(mainPtn, null, ex.getLocalizedMessage());
        }
        System.exit(0);
    }
}
