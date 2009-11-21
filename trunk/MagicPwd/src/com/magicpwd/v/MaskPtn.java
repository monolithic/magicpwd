package com.magicpwd.v;

import javax.swing.JPanel;

public class MaskPtn extends JPanel
{
    public MaskPtn()
    {
    }

    public void initView()
    {
        bt_Login = new javax.swing.JButton();
        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        ta_Login = new javax.swing.JTextArea();

        bt_Login.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_LoginActionPerformed(evt);
            }
        });

        sp1.setViewportView(ta_Login);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap().addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(sp1,
                                javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300,
                                Short.MAX_VALUE).addComponent(bt_Login)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap().addComponent(sp1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_Login).addContainerGap()));
    }

    public void initLang()
    {
    }

    /**
     * @param evt
     */
    private void bt_LoginActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private javax.swing.JButton bt_Login;
    private javax.swing.JTextArea ta_Login;

    private static final long serialVersionUID = 2407167168950836402L;

}
