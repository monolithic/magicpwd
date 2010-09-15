/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.list;

import com.magicpwd._comn.Keys;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MainPtn;
import com.magicpwd.x.MdiDialog;

/**
 *
 * @author Amon
 */
public class HistoryAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private UserMdl coreMdl;

    public HistoryAction(MainPtn mainPtn, UserMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        Object obj = mainPtn.getSelectedItem();
        if (obj == null || !(obj instanceof Keys))
        {
            return;
        }
        Keys item = (Keys) obj;

        MdiDialog mdiDialog = MdiDialog.getInstance();
        if (mdiDialog == null)
        {
            MdiDialog.newInstance(mainPtn);
            mdiDialog = MdiDialog.getInstance();
        }
        mdiDialog.showProp(ConsEnv.PROP_HIST, false);
    }
}