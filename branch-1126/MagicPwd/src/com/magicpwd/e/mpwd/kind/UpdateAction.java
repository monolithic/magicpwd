/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.kind;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._comn.prop.Kind;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.d.DBA3000;
import com.magicpwd.r.KindTN;

/**
 *
 * @author Amon
 */
public class UpdateAction extends AMpwdAction
{

    public UpdateAction()
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

        KindTN node = (KindTN) obj;
        if (node.isRoot())
        {
            return;
        }

        Kind c = (Kind) node.getUserObject();

        String name = javax.swing.JOptionPane.showInputDialog(Lang.getLang(LangRes.P30F7A15, "请输入类别名称："), c.getC2010105());
        if (name == null)
        {
            return;
        }
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A17, "更新失败：您输入的类别名称无任何意义！");
            return;
        }
        c.setC2010105(name);
        c.setC2010106(name);
        DBA3000.updateKindData(c);
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }
}
