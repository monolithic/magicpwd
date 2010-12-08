/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.safe;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.S1S1;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.x.DatDialog;

/**
 * 从数据文件恢复
 * @author Amon
 */
public class NativeResumeAction extends AMpwdAction implements IBackCall
{

    public NativeResumeAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (!Char.isValidate(mainPtn.getUserMdl().getCfg(ConsCfg.CFG_SAFE_BACK_LOC)))
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A54, "您还没有配置本地备份目录！");
            return;
        }

        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(mainPtn, LangRes.P30F7A53, "确认要执行恢复操作吗？"))
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
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }

    @Override
    public boolean callBack(Object sender, java.util.EventListener event, String... params)
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

        mainPtn.setLocked(true);
        mainPtn.showProgress();
        doResume(params[1]);
        return true;
    }

    private void doResume()
    {
        mainPtn.setLocked(true);
        mainPtn.showProgress();

        java.util.List<S1S1> list = new java.util.ArrayList<S1S1>();
        try
        {
            mainPtn.nativeDetect(list);
        }
        catch (Exception exp)
        {
            mainPtn.hideProgress();
            mainPtn.setLocked(false);

            Logs.exception(exp);
            Lang.showMesg(mainPtn, null, exp.getLocalizedMessage());
            return;
        }

        if (list.size() < 1)
        {
            mainPtn.hideProgress();
            mainPtn.setLocked(false);

            Lang.showMesg(mainPtn, LangRes.P30F7A55, "没有发现可用的备份数据！");
            return;
        }

        if (list.size() == 1)
        {
            doResume(list.get(0).getK());
            return;
        }

        mainPtn.hideProgress();
        mainPtn.setLocked(false);

        DatDialog datDialog = new DatDialog(mainPtn, this);
        datDialog.initView();
        datDialog.initLang();
        datDialog.initData();
        datDialog.showData(list);
        datDialog.setVisible(true);
    }

    private void doResume(String file)
    {
        try
        {
            boolean b = mainPtn.nativeResume(file, null);
            mainPtn.hideProgress();
            mainPtn.setLocked(false);

            if (b)
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A3F, "恭喜，数据恢复成功，您需要重新启动本程序！");
            }
            else
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A3E, "数据恢复失败，请重启软件后重试！");
            }
        }
        catch (Exception exp)
        {
            mainPtn.hideProgress();
            mainPtn.setLocked(false);

            Logs.exception(exp);
            Lang.showMesg(mainPtn, null, exp.getLocalizedMessage());
        }
        System.exit(0);
    }
}
