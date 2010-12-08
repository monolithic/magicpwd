/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.safe;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import java.util.EventListener;

/**
 *
 * @author Amon
 */
public class RemoteBackupAction extends AMpwdAction implements IBackCall
{

    public RemoteBackupAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(mainPtn, LangRes.P30F7A40, "确认要执行备份数据到云端的操作吗，此操作将需要一定的时间？"))
        {
            return;
        }

        new Thread()
        {

            @Override
            public void run()
            {
                doBackup();
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
        return true;
    }

    private void doBackup()
    {
        mainPtn.setLocked(true);
        mainPtn.showProgress();

        try
        {
            boolean b = mainPtn.remoteBackup(this);
            mainPtn.hideProgress();
            mainPtn.setLocked(false);

            if (b)
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A3D, "恭喜，数据备份成功！");
            }
            else
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A3C, "数据备份失败，请重启软件后重试！");
            }
        }
        catch (Exception exp)
        {
            mainPtn.hideProgress();
            mainPtn.setLocked(false);

            Logs.exception(exp);
            Lang.showMesg(mainPtn, null, exp.getLocalizedMessage());
        }
    }
}
