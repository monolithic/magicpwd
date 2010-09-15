/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.kind;

/**
 *
 * @author Administrator
 */
public class AppendAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
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
            Lang.showMesg(this, LangRes.P30F7A16, "");
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
