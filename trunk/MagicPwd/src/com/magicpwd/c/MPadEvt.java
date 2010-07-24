/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.c;

/**
 *
 * @author aven
 */
public interface MPadEvt extends MenuEvt
{

    void editAllsActionPerformed(java.awt.event.ActionEvent evt);

    void editCutsActionPerformed(java.awt.event.ActionEvent evt);

    void editCopyActionPerformed(java.awt.event.ActionEvent evt);

    void editPastActionPerformed(java.awt.event.ActionEvent evt);

    void editUndoActionPerformed(java.awt.event.ActionEvent evt);

    void editRedoActionPerformed(java.awt.event.ActionEvent evt);
}
