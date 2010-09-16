/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.file;

import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class HideAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public HideAction()
    {
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
        GridMdl gridMdl = getCoreMdl().getGridMdl();
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

    /**
     * @return the mainPtn
     */
    public MainPtn getMainPtn()
    {
        return mainPtn;
    }

    /**
     * @param mainPtn the mainPtn to set
     */
    public void setMainPtn(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    /**
     * @return the coreMdl
     */
    public CoreMdl getCoreMdl()
    {
        return coreMdl;
    }

    /**
     * @param coreMdl the coreMdl to set
     */
    public void setCoreMdl(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }
}
