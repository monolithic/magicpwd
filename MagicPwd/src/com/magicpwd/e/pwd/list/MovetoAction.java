/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.list;

import com.magicpwd.__a.mpwd.APwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.Keys;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.x.DatDialog;

/**
 *
 * @author Amon
 */
public class MovetoAction extends APwdAction
{

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
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(Object object)
    {
    }

    private boolean changeKind(String hash)
    {
        if (!"0".equals(hash) && !Char.isValidateHash(hash))
        {
            return false;
        }

        Object obj = mainPtn.getSelectedListValue();
        if (obj instanceof Keys)
        {
            ((Keys) obj).setP30F0106(hash);
        }
        mainPtn.changeKind(hash);

        return true;
    }
}
