/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._mail;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.BorderFactory;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 * 
 * @author Amon
 */
public class MailDlg extends javax.swing.JFrame
{

    private TreeModel treeModel;
    private DefaultMutableTreeNode rootNode;
    private MailMdl tableMode;

    public void initView()
    {
        initCtrlView();
        initEditView();
        initBaseView();
    }

    public void initLang()
    {
        lb_MailHead.setText("Subject:");
        lb_MailUser.setText("From:");
        bt_Delete.setText("Delete");
        bt_Download.setText("DownLoad");
        bt_Replay.setText("Replay");
    }

    public void initData()
    {
        rootNode = new DefaultMutableTreeNode("邮箱列表");
        treeModel = new DefaultTreeModel(rootNode);
        tr_MailBoxs.setModel(treeModel);
        tableMode = new MailMdl();
        tb_MailMsgs.setModel(tableMode);
    }

    public boolean append(Connect connect)
    {
        try
        {
            Session session = Session.getDefaultInstance(connect.getProperties(), null);
            Store store = session.getStore(connect.getURLName());
            store.connect();

            Folder folder = store.getDefaultFolder();
            rootNode.add(new NodeMdl(folder));
            listFolders(rootNode, folder);

            return true;
        }
        catch (Exception exp)
        {
            return false;
        }
    }

    private static void listFolders(DefaultMutableTreeNode node, Folder folder) throws Exception
    {
        for (Folder sub : folder.list())
        {
            NodeMdl temp = new NodeMdl(sub);
            node.add(temp);
            if ((sub.getType() & Folder.HOLDS_FOLDERS) != 0)
            {
                listFolders(temp, sub);
            }
        }
    }

    private void initCtrlView()
    {
        pl_MailCtrl = new javax.swing.JPanel();
        bt_Download = new javax.swing.JButton();
        bt_Replay = new javax.swing.JButton();
        bt_Delete = new javax.swing.JButton();
        lb_MailInfo = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_MailCtrl);
        pl_MailCtrl.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(lb_MailInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(bt_Delete);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(bt_Replay);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(bt_Download);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(bt_Download);
        vpg.addComponent(bt_Replay);
        vpg.addComponent(bt_Delete);
        vpg.addComponent(lb_MailInfo);
        layout.setVerticalGroup(vpg);
    }

    private void initEditView()
    {
        pl_MailEdit = new javax.swing.JPanel();
        lb_MailHead = new javax.swing.JLabel();
        lb_MailUser = new javax.swing.JLabel();
        tf_MailHead = new javax.swing.JTextField();
        tf_MailUser = new javax.swing.JTextField();
        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        ta_MailBody = new javax.swing.JTextArea();

        ta_MailBody.setRows(5);
        sp1.setViewportView(ta_MailBody);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_MailEdit);
        pl_MailEdit.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_MailHead);
        hpg1.addComponent(lb_MailUser);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_MailHead, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE);
        hpg2.addComponent(tf_MailUser, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        javax.swing.GroupLayout.ParallelGroup hvg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hvg.addGroup(javax.swing.GroupLayout.Alignment.LEADING, hsg);
        hvg.addComponent(sp1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE);
        layout.setHorizontalGroup(hvg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_MailHead);
        vpg1.addComponent(tf_MailHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_MailUser);
        vpg2.addComponent(tf_MailUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(sp1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE);
        layout.setVerticalGroup(vsg);
    }

    private void initBaseView()
    {
        tr_MailBoxs = new javax.swing.JTree();
        tb_MailMsgs = new javax.swing.JTable();

        javax.swing.JSplitPane sp1 = new javax.swing.JSplitPane();
        sp1.setBorder(BorderFactory.createEmptyBorder());
        sp1.setDividerLocation(120);
        sp1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        sp1.setOneTouchExpandable(true);

        javax.swing.JSplitPane sp2 = new javax.swing.JSplitPane();
        sp2.setBorder(BorderFactory.createEmptyBorder());
        sp2.setDividerLocation(120);
        sp2.setOneTouchExpandable(true);

        sp2.setLeftComponent(new javax.swing.JScrollPane(tr_MailBoxs));
        sp2.setRightComponent(new javax.swing.JScrollPane(tb_MailMsgs));

        sp1.setTopComponent(sp2);
        sp1.setBottomComponent(pl_MailEdit);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg.addComponent(sp1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE);
        hpg.addComponent(pl_MailCtrl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(sp1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
        vsg.addComponent(pl_MailCtrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);

        this.pack();
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        MailDlg md = new MailDlg();
        md.initView();
        md.initLang();
        md.initData();
        md.setVisible(true);
    }
    private javax.swing.JTextArea ta_MailBody;
    private javax.swing.JTable tb_MailMsgs;
    private javax.swing.JTree tr_MailBoxs;
    private javax.swing.JPanel pl_MailEdit;
    private javax.swing.JLabel lb_MailHead;
    private javax.swing.JLabel lb_MailUser;
    private javax.swing.JTextField tf_MailHead;
    private javax.swing.JTextField tf_MailUser;
    private javax.swing.JPanel pl_MailCtrl;
    private javax.swing.JButton bt_Delete;
    private javax.swing.JButton bt_Download;
    private javax.swing.JButton bt_Replay;
    private javax.swing.JLabel lb_MailInfo;
}
