/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.kind;

import com.magicpwd._comn.prop.Kind;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.$i.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.r.KindTN;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public class AppendAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public AppendAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.tree.TreePath path = mainPtn.getSelectedKindValue();
        if (path == null)
        {
            return;
        }

        Object obj = path.getLastPathComponent();
        if (obj == null || !(obj instanceof KindTN))
        {
            return;
        }

        String kindName = javax.swing.JOptionPane.showInputDialog(Lang.getLang(LangRes.P30F7A15, "请输入类别名称："));
        if (kindName == null)
        {
            return;
        }
        if (!com.magicpwd._util.Char.isValidate(kindName))
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A16, "");
            return;
        }

        KindTN p = (KindTN) obj;
        Kind c = new Kind();
        c.setC2010101(p.getChildCount());
        c.setC2010105(kindName);
        c.setC2010106(kindName);
        coreMdl.getTreeMdl().wAppend(path, c);
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
