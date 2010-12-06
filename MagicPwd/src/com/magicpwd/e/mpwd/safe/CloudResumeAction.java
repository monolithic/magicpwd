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
import com.magicpwd.x.DatDialog;
import java.util.EventListener;

/**
 *
 * @author Amon
 */
public class CloudResumeAction extends AMpwdAction implements IBackCall
{

    public CloudResumeAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(mainPtn, LangRes.P30F7A41, "确认要执行从云端数据恢复的操作吗，此操作将需要一定的时间？"))
        {
            return;
        }

//        mainPtn.showProcessDialog();
        java.util.List<S1S1> list = new java.util.ArrayList<S1S1>();
        if (mainPtn.checkResume(list) && list.size() < 1)
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A3E, "无法从POP邮箱读取备份数据！");
            return;
        }

        if (list.size() == 1)
        {
            resume(list.get(0).getK());
            return;
        }

        DatDialog datDialog = new DatDialog(mainPtn, this);
        datDialog.initView();
        datDialog.initLang();
        datDialog.initData();
        datDialog.showData();
        datDialog.setVisible(true);

        mainPtn.hideProcessDialog();
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
        resume(params[0]);
        return true;
    }

    private void resume(final String sign)
    {
        new Thread()
        {

            @Override
            public void run()
            {
                mainPtn.cloudResume(sign, null);
            }
        }.start();
    }
}
