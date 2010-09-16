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
public class SaveAction extends javax.swing.AbstractAction
{

    private MiniPtn miniPtn;
    private UserMdl coreMdl;

    public SaveAction(MiniPtn miniPtn, UserMdl coreMdl)
    {
        this.miniPtn = miniPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
//        String head = tf_NoteHead.getText();
//        if (!com.magicpwd._util.Char.isValidate(head))
//        {
//            Lang.showMesg(this, LangRes.P30F5A01, "请输入记事标题！");
//            tf_NoteHead.requestFocus();
//            return;
//        }
//
//        String data = ta_NoteData.getText();
//        if (!com.magicpwd._util.Char.isValidate(data))
//        {
//            if (Lang.showFirm(this, LangRes.P30F5A02, "记事内容并没有实际意义的文字，确认要保存么？") != javax.swing.JOptionPane.NO_OPTION)
//            {
//                return;
//            }
//        }
//
//        NoteMdl noteMdl = coreMdl.getNoteMdl();
//        if (noteMdl.getSize() < 1)
//        {
//            // Guid
//            noteMdl.initGuid();
//            // Meta
//            noteMdl.initMeta();
//            // Logo
//            noteMdl.initLogo();
//            // Hint
//            noteMdl.initHint();
//            // Note
//            noteMdl.initNote();
//        }
//
//        noteMdl.setNote(head, data);
//
//        try
//        {
//            noteMdl.saveData(true);
//            infoLayout.show(pl_NoteInfo, "info");
//            Lang.setWText(lb_NoteInfo, LangRes.P30F5A03, "");
//        }
//        catch (Exception exp)
//        {
//            Logs.exception(exp);
//            Lang.showMesg(this, LangRes.P30F5A04, "");
//        }
    }
}
