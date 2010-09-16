/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.kind;

import com.magicpwd._comn.Kind;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.r.KindTN;
import com.magicpwd.v.MainPtn;

/**
 *
 * @author Amon
 */
public class AppendAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public AppendAction(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.tree.TreePath path = mainPtn.getSelectedPath();
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
}
