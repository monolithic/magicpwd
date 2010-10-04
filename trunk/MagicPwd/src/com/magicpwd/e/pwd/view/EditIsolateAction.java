/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.view;

import com.magicpwd._util.Char;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public class EditIsolateAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public EditIsolateAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isEditIsolate();

        String cmd = e.getActionCommand();
        if (Char.isValidate(cmd))
        {
            javax.swing.AbstractButton button = mainPtn.getMenuPtn().getButton(cmd);
            if (button != null)
            {
                button.setSelected(b);
            }
        }

        mainPtn.setEditIsolate(b);
        //mainPtn.pack();
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
//        if (coreMdl.getUserCfg().isEditVisible())
//        {
        putValue("selected", coreMdl.getUserCfg().isEditIsolate());
//        }
//        else
//        {
//            putValue("enabled", false);
//        }
    }
}
