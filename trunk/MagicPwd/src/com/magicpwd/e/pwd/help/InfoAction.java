/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.help;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd.x.MdiDialog;

/**
 *
 * @author Amon
 */
public class InfoAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public InfoAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        MdiDialog mdiDialog = MdiDialog.getInstance();
        if (mdiDialog == null)
        {
            MdiDialog.newInstance(mainPtn);
            mdiDialog = MdiDialog.getInstance();
        }
        mdiDialog.showProp(ConsEnv.PROP_INFO, true);
    }

    @Override
    public void setMainPtn(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    @Override
    public void setCoreMdl(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    @Override
    public void doUpdate(Object object)
    {
    }
}
