/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.grid;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.TrayPtn;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author aven
 */
public class DropAction extends javax.swing.AbstractAction implements IPwdAction
{

    public MainPtn mainPtn;
    private CoreMdl coreMdl;

    public DropAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30F1A01, "确认要删除此属性数据么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            mainPtn.removeSelected();
        }
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
