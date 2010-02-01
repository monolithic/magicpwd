/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._mail;

import com.magicpwd._util.Desk;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import java.awt.Point;
import javax.mail.Folder;
import javax.mail.Store;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

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
        lb_MailHead.setText("标题：");
        lb_MailUser.setText("收件人：");
        bt_Delete.setText("删除(D)");
        bt_Delete.setEnabled(false);
        bt_Download.setText("下载(D)");
        bt_Download.setEnabled(false);
        bt_Replay.setText("回复(D)");
        bt_Replay.setEnabled(false);
    }

    public void initData()
    {
        rootNode = new DefaultMutableTreeNode("邮箱列表");
        treeModel = new DefaultTreeModel(rootNode);
        tr_MailBoxs.setModel(treeModel);
        tableMode = new MailMdl();
        tb_MailMsgs.setModel(tableMode);
    }

    public boolean append(Connect connect, String folder)
    {
        Store store = null;
        try
        {
            store = connect.getStore();
            Folder f = (Util.isValidate(folder) ? store.getFolder(folder) : store.getDefaultFolder());
            NodeMdl model = new NodeMdl(connect, f);
            rootNode.add(model);
            listFolders(connect, model, f);
            TreePath path = new TreePath(new DefaultMutableTreeNode[]
                    {
                        rootNode, model
                    });
            tr_MailBoxs.setSelectionPath(path);
//            tr_MailBoxs.fireTreeExpanded(path);
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            if (store != null)
            {
                try
                {
                    store.close();
                }
                catch (Exception ex)
                {
                    Logs.exception(ex);
                }
            }
        }
    }

    private static void listFolders(Connect connect, DefaultMutableTreeNode node, Folder folder) throws Exception
    {
        for (Folder sub : folder.list())
        {
            sub.open(Folder.READ_ONLY);
            NodeMdl temp = new NodeMdl(connect, sub);
            node.add(temp);
            System.out.println(sub.getFullName());
            if ((sub.getType() & Folder.HOLDS_FOLDERS) != 0)
            {
                listFolders(connect, temp, sub);
            }
            sub.close(false);
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
        ta_MailBody = new javax.swing.JEditorPane();

        lb_MailUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lb_MailHead.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        ta_MailBody.addHyperlinkListener(new javax.swing.event.HyperlinkListener()
        {

            @Override
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt)
            {
                ta_MailBodyHyperlinkUpdate(evt);
            }
        });
        ta_MailBody.setEditable(false);
        sp1.setViewportView(ta_MailBody);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_MailEdit);
        pl_MailEdit.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_MailHead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, 80);
        hpg1.addComponent(lb_MailUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, 80);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_MailHead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg2.addComponent(tf_MailUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
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
        sp2.setDividerLocation(180);
        sp2.setOneTouchExpandable(true);

        tr_MailBoxs.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
        tr_MailBoxs.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tr_MailBoxsMouseClicked(evt);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                tr_MailBoxsMouseReleased(evt);
            }
        });
        sp2.setLeftComponent(new javax.swing.JScrollPane(tr_MailBoxs));

        tb_MailMsgs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_MailMsgs.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                tb_MailMsgsMouseReleased(evt);
            }
        });
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

    private void tr_MailBoxsMouseClicked(java.awt.event.MouseEvent evt)
    {
        if (evt.getClickCount() < 2)
        {
            return;
        }

        TreePath path = tr_MailBoxs.getSelectionPath();
        if (path == null)
        {
            return;
        }
        Object obj = path.getLastPathComponent();
        if (!(obj instanceof NodeMdl))
        {
            return;
        }
        NodeMdl node = (NodeMdl) obj;
        try
        {
            Store store = node.getConnect().getStore();
            tableMode.loadMsg(store.getFolder(node.getKeyWord()));
            store.close();
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    private void tr_MailBoxsMouseReleased(java.awt.event.MouseEvent evt)
    {
        Point p = evt.getPoint();
        TreePath path = tr_MailBoxs.getPathForLocation(p.x, p.y);
//        tr_MailBoxs.setSelectionPath(path);
        if (path != null)
        {
            Object obj = path.getLastPathComponent();
            if ((obj instanceof NodeMdl) && evt.isPopupTrigger())
            {
            }
        }
    }

    private void tb_MailMsgsMouseReleased(java.awt.event.MouseEvent evt)
    {
        try
        {
            MailInf mail = tableMode.getMailInf(tb_MailMsgs.getSelectedRow());
            ta_MailBody.setContentType(mail.getContentType());
            tf_MailHead.setText(mail.getSubject());
            tf_MailUser.setText(mail.getMailAddress("TO"));
            ta_MailBody.setText(mail.getBodyText());
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    private void ta_MailBodyHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt)
    {
        if (evt.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED)
        {
            if (evt instanceof javax.swing.text.html.HTMLFrameHyperlinkEvent)
            {
                javax.swing.text.html.HTMLDocument doc = (javax.swing.text.html.HTMLDocument) ta_MailBody.getDocument();
                doc.processHTMLFrameHyperlinkEvent((javax.swing.text.html.HTMLFrameHyperlinkEvent) evt);
            }
            else
            {
                Desk.browse(evt.getURL().toString());
            }
        }
    }

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception exp)
        {
        }
        MailDlg md = new MailDlg();
        md.initView();
        md.initLang();
        md.initData();
        md.setVisible(true);
        Connect connect = new Connect("pop3", "Amon.CT@live.com", "c~9xJpa&");
        connect.setUsername("Amon.CT");
        connect.setHost("pop.live.com");
        connect.setPort(995);
        connect.setAuth(true);
        md.append(connect, "");
    }
    private javax.swing.JEditorPane ta_MailBody;
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
