/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.safe;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;

/**
 * 备份到数据文件
 * @author Amon
 */
public class NativeBackupAction extends AMpwdAction implements IBackCall
{

    public NativeBackupAction()
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

        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(mainPtn, LangRes.P30F7A52, "确认要执行备份操作吗？"))
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
    public boolean callBack(Object sender, java.util.EventListener event, String... params)
    {
        return true;
    }

    private void doBackup()
    {
        mainPtn.setLocked(true);
        mainPtn.showProgress();

        try
        {
            boolean b = mainPtn.nativeBackup(mainPtn.getUserMdl().getCfg(ConsCfg.CFG_SAFE_BACK_LOC), this);
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
