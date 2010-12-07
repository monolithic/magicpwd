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
    public boolean callBack(Object sender, java.util.EventListener event, String... params)
    {
        if (params != null && params.length == 2)
        {
            Lang.showMesg(mainPtn, null, params[1]);
        }
        return true;
    }

    private void doResume()
    {
        mainPtn.localResume(mainPtn.getUserMdl().getCfg(ConsCfg.CFG_SAFE_BACK_LOC), this);
    }
}
