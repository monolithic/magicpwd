/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.data;

import com.magicpwd.__a.APwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class ExportAction extends APwdAction
{

    public ExportAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.tree.TreePath path = mainPtn.getSelectedKindValue();
        if (path == null)
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A20, "请选择您要导出数据的类别信息！");
            return;
        }

        TrayPtn.getUserPtn(ConsEnv.INT_SIGN_RS, new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                if (params != null && params.length > 0)
                {
                    if (ConsEnv.STR_SIGN_RS.equals(params[0]))
                    {
                        return mainPtn.exportData();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(Object object)
    {
    }
}
