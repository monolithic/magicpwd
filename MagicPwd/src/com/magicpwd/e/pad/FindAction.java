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
public class FindAction extends javax.swing.AbstractAction implements IPadAction
{

    private MiniPtn miniPtn;
    private CoreMdl coreMdl;

    public FindAction()
    {
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
