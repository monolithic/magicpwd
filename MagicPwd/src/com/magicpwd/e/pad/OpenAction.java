/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pad;

import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.MiniPtn;

/**
 *
 * @author Administrator
 */
public class OpenAction extends javax.swing.AbstractAction
{

    private MiniPtn miniPtn;
    private CoreMdl coreMdl;

    public OpenAction(MiniPtn miniPtn, CoreMdl coreMdl)
    {
        this.miniPtn = miniPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
//        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
//        jfc.setMultiSelectionEnabled(false);
//        jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
//        int status = jfc.showOpenDialog(miniPtn);
//        if (status != javax.swing.JFileChooser.APPROVE_OPTION)
//        {
//            return;
//        }
//        java.io.File file = jfc.getSelectedFile();
//        if (!file.exists())
//        {
//            Lang.showMesg(miniPtn, LangRes.P30F7A03, "", "");
//            return;
//        }
//        if (!file.isFile())
//        {
//            Lang.showMesg(miniPtn, LangRes.P30F7A04, "", "");
//            return;
//        }
//        if (!file.canRead())
//        {
//            Lang.showMesg(miniPtn, LangRes.P30F7A05, "");
//            return;
//        }
//        if (file.length() > 1048576)
//        {
//            Lang.showMesg(miniPtn, LangRes.P30F7A06, "");
//            return;
//        }
//        try
//        {
//            byte[] buf = new byte[(int) file.length()];
//            java.io.FileInputStream fis = new java.io.FileInputStream(file);
//            int len = fis.read(buf);
//            fis.close();
//            ta_NoteData.setText(new String(buf, 0, len));
//            String path = file.getName();
//            if (path.length() > 20)
//            {
//                path = "..." + path.substring(path.length() - 20);
//            }
//            infoLayout.show(pl_NoteInfo, "info");
//            lb_NoteInfo.setText(path);
//        }
//        catch (Exception exp)
//        {
//            Logs.exception(exp);
//        }
//
//        undo.discardAllEdits();
//        noteMenu.setNoteUndoEnabled(undo.canUndo());
//        noteMenu.setNoteRedoEnabled(undo.canRedo());
    }
}
