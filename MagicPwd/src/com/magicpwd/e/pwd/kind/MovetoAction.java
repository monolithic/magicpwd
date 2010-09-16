/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.kind;

import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._util.Lang;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd.x.DatDialog;

/**
 *
 * @author Amon
 */
public class MovetoAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public MovetoAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        DatDialog dat = new DatDialog(coreMdl.getTreeMdl(), new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return changeKind(params[0]);
            }
        });
        dat.initView();
        dat.initLang();
        dat.setTitle(Lang.getLang(LangRes.P30F4206, "把记录迁移到..."));
        dat.setVisible(true);
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

    private boolean changeKind(String hash)
    {
//        GridMdl gm = coreMdl.getGridMdl();
//        if (hash == null || hash.equals(gm.getItemAt(ConsEnv.PWDS_HEAD_GUID).getData()))
//        {
//            return true;
//        }
//
//        gm.getItemAt(ConsEnv.PWDS_HEAD_GUID).setData(hash);
//        try
//        {
//            gm.saveData(true, true);
//            coreMdl.getListMdl().wRemove(ls_LastIndx);
//        }
//        catch (Exception exp)
//        {
//            Logs.exception(exp);
//            return false;
//        }
        return true;
    }
}
