/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pad;

import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MiniPtn;

/**
 *
 * @author Administrator
 */
public class FindAction extends javax.swing.AbstractAction
{

    private MiniPtn miniPtn;
    private UserMdl coreMdl;

    public FindAction(MiniPtn miniPtn, UserMdl coreMdl)
    {
        this.miniPtn = miniPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
//        String noteName = tf_NoteHead.getText();
//        if (!com.magicpwd._util.Char.isValidate(noteName))
//        {
//            return;
//        }
//
//        noteList.clear();
//        boolean b = DBA3000.findUserNote(coreMdl.getUserCfg(), noteName, noteList);
//        b &= noteList.size() > 0;
//        if (!b || noteList.size() < 1)
//        {
//            Lang.showMesg(this, LangRes.P30F7A1F, "搜索不到与标题相匹配的记事内容，请修改查询条件后重试！");
//            tf_NoteHead.requestFocus();
//            return;
//        }
//
//        lastHash = "";
//        infoLayout.show(pl_NoteInfo, "list");
//        cb_NoteInfo.removeAllItems();
//        for (S1S2 item : noteList)
//        {
//            cb_NoteInfo.addItem(item);
//        }
//
//        undo.discardAllEdits();
//        noteMenu.setNoteUndoEnabled(undo.canUndo());
//        noteMenu.setNoteRedoEnabled(undo.canRedo());
    }
}
