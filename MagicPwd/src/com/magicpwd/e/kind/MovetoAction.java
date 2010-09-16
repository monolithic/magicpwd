/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.kind;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MainPtn;
import com.magicpwd.x.DatDialog;

/**
 *
 * @author Amon
 */
public class MovetoAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public MovetoAction(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
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
