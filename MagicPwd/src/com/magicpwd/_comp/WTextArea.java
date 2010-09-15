/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comp;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Amon
 */
public class WTextArea extends javax.swing.JTextArea implements java.awt.datatransfer.ClipboardOwner
{

    private UndoManager undo = new UndoManager();

    public void initView()
    {
        popMenu = new javax.swing.JPopupMenu();

        allItem = new javax.swing.JMenuItem();
        allItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                selectAll();
            }
        });
        popMenu.add(allItem);

        popMenu.addSeparator();

        cutItem = new javax.swing.JMenuItem();
        cutItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editCutsActionPerformed(evt);
            }
        });
        popMenu.add(cutItem);

        copyItem = new javax.swing.JMenuItem();
        copyItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editCopyActionPerformed(evt);
            }
        });
        popMenu.add(copyItem);

        pastItem = new javax.swing.JMenuItem();
        pastItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editPastActionPerformed(evt);
            }
        });
        popMenu.add(pastItem);

        popMenu.addSeparator();

        undoItem = new javax.swing.JMenuItem();
        undoItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editUndoActionPerformed(evt);
            }
        });
        popMenu.add(undoItem);

        redoItem = new javax.swing.JMenuItem();
        redoItem.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editRedoActionPerformed(evt);
            }
        });
        popMenu.add(redoItem);
//        ta_NoteData.getActionMap().put(ConsEnv.EVENT_NOTE_ALLS, new javax.swing.AbstractAction()
//        {
//
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt)
//            {
//                editAllsActionPerformed(evt);
//            }
//        });
//        ta_NoteData.getInputMap(javax.swing.JComponent.WHEN_FOCUSED).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK), ConsEnv.EVENT_NOTE_ALLS);
//
//        ta_NoteData.getActionMap().put(ConsEnv.EVENT_NOTE_CUTS, new javax.swing.AbstractAction()
//        {
//
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt)
//            {
//                editCutsActionPerformed(evt);
//            }
//        });
//        ta_NoteData.getInputMap(javax.swing.JComponent.WHEN_FOCUSED).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK), ConsEnv.EVENT_NOTE_CUTS);
//
//        ta_NoteData.getActionMap().put(ConsEnv.EVENT_NOTE_COPY, new javax.swing.AbstractAction()
//        {
//
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt)
//            {
//                editCopyActionPerformed(evt);
//            }
//        });
//        ta_NoteData.getInputMap(javax.swing.JComponent.WHEN_FOCUSED).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK), ConsEnv.EVENT_NOTE_COPY);
//
//        ta_NoteData.getActionMap().put(ConsEnv.EVENT_NOTE_PAST, new javax.swing.AbstractAction()
//        {
//
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt)
//            {
//                editPastActionPerformed(evt);
//            }
//        });
//        ta_NoteData.getInputMap(javax.swing.JComponent.WHEN_FOCUSED).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK), ConsEnv.EVENT_NOTE_PAST);
//
//        ta_NoteData.getActionMap().put(ConsEnv.EVENT_NOTE_UNDO, new javax.swing.AbstractAction()
//        {
//
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt)
//            {
//                editUndoActionPerformed(evt);
//            }
//        });
//        ta_NoteData.getInputMap(javax.swing.JComponent.WHEN_FOCUSED).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK), ConsEnv.EVENT_NOTE_UNDO);
//
//        ta_NoteData.getActionMap().put(ConsEnv.EVENT_NOTE_REDO, new javax.swing.AbstractAction()
//        {
//
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt)
//            {
//                editRedoActionPerformed(evt);
//            }
//        });
//        ta_NoteData.getInputMap(javax.swing.JComponent.WHEN_FOCUSED).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK), ConsEnv.EVENT_NOTE_REDO);
    }

    public void initLang()
    {
        Lang.setWText(allItem, LangRes.P30F7D34, "全选");
        Lang.setWText(cutItem, LangRes.P30F7D35, "剪切");
        Lang.setWText(copyItem, LangRes.P30F7D36, "复制");
        Lang.setWText(pastItem, LangRes.P30F7D37, "粘贴");
        Lang.setWText(undoItem, LangRes.P30F7D38, "撤消");
        Lang.setWText(redoItem, LangRes.P30F7D39, "重做");
    }

    public void initData()
    {
        getDocument().addUndoableEditListener(new javax.swing.event.UndoableEditListener()
        {

            @Override
            public void undoableEditHappened(javax.swing.event.UndoableEditEvent evt)
            {
                undo.addEdit(evt.getEdit());
                changeMenuStatus();
            }
        });

        changeMenuStatus();
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents)
    {
    }

    public void reset()
    {
        undo.discardAllEdits();
        changeMenuStatus();
    }

    private void editCutsActionPerformed(ActionEvent evt)
    {
        String copy = getSelectedText();
        if (copy != null)
        {
            java.awt.datatransfer.StringSelection sSelection = new java.awt.datatransfer.StringSelection(copy);
            getToolkit().getSystemClipboard().setContents(sSelection, this);
            replaceSelection("");
        }
        changeMenuStatus();
    }

    private void editCopyActionPerformed(ActionEvent evt)
    {
        String copy = getSelectedText();
        if (copy != null)
        {
            java.awt.datatransfer.StringSelection sSelection = new java.awt.datatransfer.StringSelection(copy);
            getToolkit().getSystemClipboard().setContents(sSelection, this);
        }
        changeMenuStatus();
    }

    private void editPastActionPerformed(ActionEvent evt)
    {
        java.awt.datatransfer.Transferable transfer = getToolkit().getSystemClipboard().getContents(this);
        if (transfer != null)
        {
            try
            {
                Object data = transfer.getTransferData(java.awt.datatransfer.DataFlavor.stringFlavor);
                if (data != null)
                {
                    replaceSelection(data.toString());
                }
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
        changeMenuStatus();
    }

    private void editUndoActionPerformed(ActionEvent evt)
    {
        if (undo.canUndo())
        {
            try
            {
                undo.undo();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
            changeMenuStatus();
        }
    }

    private void editRedoActionPerformed(ActionEvent evt)
    {
        if (undo.canRedo())
        {
            try
            {
                undo.redo();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
            changeMenuStatus();
        }
    }

    private void changeMenuStatus()
    {
        undoItem.setEnabled(undo.canUndo());
        redoItem.setEnabled(undo.canRedo());
    }
    private javax.swing.JPopupMenu popMenu;
    private javax.swing.JMenuItem allItem;
    private javax.swing.JMenuItem cutItem;
    private javax.swing.JMenuItem copyItem;
    private javax.swing.JMenuItem pastItem;
    private javax.swing.JMenuItem undoItem;
    private javax.swing.JMenuItem redoItem;
}
