/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.list;

/**
 *
 * @author Amon
 */
public class SortDirAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        coreMdl.getUserCfg().setCfg(ConsCfg.CFG_VIEW_LIST_ASC, evt.getActionCommand());
        if (isSearch)
        {
            coreMdl.getListMdl().findName(queryKey);
        }
        else if (com.magicpwd._util.Char.isValidateHash(queryKey))
        {
            coreMdl.getListMdl().listName(queryKey);
        }
    }
}
