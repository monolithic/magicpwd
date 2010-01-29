/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

/**
 *
 * @author Amon
 */
public class MailDlg extends javax.swing.JFrame
{
    public void initView()
    {
        tr_MailBoxs = new javax.swing.JTree();
        tb_MailMsgs = new javax.swing.JTable();
        ta_MailBody = new javax.swing.JTextArea();
        lp_Operate = new javax.swing.JPanel();

        javax.swing.JSplitPane sp1 = new javax.swing.JSplitPane();
        sp1.setDividerLocation(120);
        sp1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        sp1.setOneTouchExpandable(true);

        javax.swing.JSplitPane sp2 = new javax.swing.JSplitPane();
        sp2.setDividerLocation(120);
        sp2.setOneTouchExpandable(true);
        
        sp2.setLeftComponent(new javax.swing.JScrollPane(tr_MailBoxs));
        sp2.setRightComponent(new javax.swing.JScrollPane(tb_MailMsgs));

        sp1.setTopComponent(sp2);

        //tb_MailMsgs.setModel(null);

        sp1.setRightComponent(new javax.swing.JScrollPane(ta_MailBody));

        lp_Operate.setLayout(new java.awt.FlowLayout());
        lp_Operate.add(new javax.swing.JButton("回复"));
        lp_Operate.add(new javax.swing.JButton("删除"));
        lp_Operate.add(new javax.swing.JButton("下载"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(lp_Operate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_Operate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        this.pack();
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public void initLang()
    {
    }

    public void initData()
    {
    }

    public static void main(String[] args)
    {
        MailDlg md = new MailDlg();
        md.initView();
        md.initLang();
        md.initData();
        md.setVisible(true);
    }
    private javax.swing.JPanel lp_Operate;
    private javax.swing.JTextArea ta_MailBody;
    private javax.swing.JTable tb_MailMsgs;
    private javax.swing.JTree tr_MailBoxs;
}
