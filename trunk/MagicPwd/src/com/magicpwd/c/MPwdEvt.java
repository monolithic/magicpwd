/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.c;

/**
 *
 * @author aven
 */
public interface MPwdEvt extends MenuEvt
{

    void editFindActionPerformed(java.awt.event.ActionEvent evt);

    void editPrevActionPerformed(java.awt.event.ActionEvent evt);

    void editNextActionPerformed(java.awt.event.ActionEvent evt);

    void keysModeActionPerformed(java.awt.event.ActionEvent evt);

    void keysNoteActionPerformed(java.awt.event.ActionEvent evt);

    void helpSKeyActionPerformed(java.awt.event.ActionEvent evt);
}
