/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.help;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Desk;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MainPtn;

/**
 *
 * @author Amon
 */
public class UpdtAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public UpdtAction(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        try
        {
            boolean b = Util.checkUpdate(ConsEnv.SOFTCODE, ConsEnv.VERSIONS);
            if (b)
            {
                if (Lang.showFirm(mainPtn, LangRes.P30F7A12, "检测到新版本，现在要下载吗？") == javax.swing.JOptionPane.YES_OPTION)
                {
                    Desk.browse(ConsEnv.HOMEPAGE);
                }
            }
            else
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A13, "您使用的已是最新版本。");
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(mainPtn, LangRes.P30F7A14, "");
        }
    }
}
