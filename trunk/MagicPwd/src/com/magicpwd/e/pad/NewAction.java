/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pad;

import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pad.MiniPtn;

/**
 *
 * @author Administrator
 */
public class NewAction extends javax.swing.AbstractAction implements IPadAction
{

    private MiniPtn miniPtn;
    private CoreMdl coreMdl;

    public NewAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
//        tf_NoteHead.setText("");
//        ta_NoteData.setText("");
//        infoLayout.show(pl_NoteInfo, "info");
//        lb_NoteInfo.setText("");
//        tf_NoteHead.requestFocus();
//        coreMdl.getNoteMdl().clear();
//
//        undo.discardAllEdits();
//        noteMenu.setNoteUndoEnabled(undo.canUndo());
//        noteMenu.setNoteRedoEnabled(undo.canRedo());
    }

    @Override
    public void setMiniPtn(MiniPtn miniPtn)
    {
        this.miniPtn = miniPtn;
    }

    @Override
    public void setCoreMdl(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }
}
