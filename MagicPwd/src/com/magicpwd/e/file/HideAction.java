/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.file;

import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class HideAction extends javax.swing.AbstractAction
{

    private UserMdl coreMdl;

    public HideAction(MainPtn mainPtn, UserMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        TrayPtn.getCurrForm().setVisible(false);

        TrayPtn.getInstance().displayMessage(Lang.getLang(LangRes.P30F9A01, "友情提示"), Lang.getLang(LangRes.P30F7A43, "魔方密码仍在运行中，您可以通过双击此处显示主窗口！"), java.awt.TrayIcon.MessageType.INFO);

        hideWindow();
    }

    private void hideWindow()
    {
        GridMdl gridMdl = coreMdl.getGridMdl();
        // Save Temperary Data
        if (gridMdl.isModified())
        {
            gridMdl.setInterim(true);
            gridMdl.getItemAt(ConsEnv.PWDS_HEAD_GUID).setData(ConsDat.HASH_ROOT);
            try
            {
                gridMdl.saveData(true, false);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
    }
}