/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amonsoft;

/**
 *
 * @author Amon
 */
public class AU extends javax.swing.JFrame {

    public AU() {
        bt_Cancel = new javax.swing.JButton();
        bt_Cancel.setMnemonic('X');
        bt_Cancel.setText("退出(X)");
        bt_Cancel.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CancelActionPerformed(evt);
            }
        });

        bt_Manage = new javax.swing.JButton();
        bt_Manage.setMnemonic('S');
        bt_Manage.setText("暂停(S)");
        bt_Manage.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ManageActionPerformed(evt);
            }
        });

        ta_NoteInfo = new javax.swing.JTextArea();
//        ta_NoteInfo.setColumns(20);
        ta_NoteInfo.setEditable(false);
        ta_NoteInfo.setFocusable(false);
        ta_NoteInfo.setOpaque(false);
        ta_NoteInfo.setRows(3);

        ls_StepInfo = new javax.swing.JList();
        javax.swing.JScrollPane sp_StepInfo = new javax.swing.JScrollPane();
        sp_StepInfo.setViewportView(ls_StepInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(bt_Manage);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(bt_Cancel);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(sp_StepInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE);
        hpg1.addGroup(hsg1);
        hpg1.addComponent(ta_NoteInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addGroup(hpg1);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg2));

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(bt_Cancel);
        vpg1.addComponent(bt_Manage);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap();
        vsg1.addComponent(ta_NoteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(sp_StepInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg1);
        vsg1.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vsg1));

        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    private void bt_ManageActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void checkUpdate() {
    }

    private void downVersion() {
    }

    private void downFile() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AU().setVisible(true);
    }
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_Manage;
    private javax.swing.JList ls_StepInfo;
    private javax.swing.JTextArea ta_NoteInfo;
}
