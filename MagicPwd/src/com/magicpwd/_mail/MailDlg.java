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
import javax.mail.Message;
import javax.mail.Store;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * 
 * @author Amon
 */
public class MailDlg extends javax.swing.JFrame implements Runnable
{

    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;
    private MailMdl tableMode;
    private Folder folder;

    public MailDlg()
    {
    }

    @Override
    public void run()
    {
        loadMsg();
    }

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
        tb_MailMsgs.setRowSorter(new TableRowSorter<MailMdl>(tableMode));
    }

    public void append(Connect connect, String folder)
    {
        showNotice("正在检测邮件信息，请稍候……");
        Store store = null;
        try
        {
            store = connect.getStore();
            Folder f = (com.magicpwd._util.Char.isValidate(folder) ? store.getFolder(folder) : store.getDefaultFolder());
            NodeMdl model = new NodeMdl(connect, f);
            listFolders(connect, model, f);

            // 已有节点
            String display = model.toString();
            boolean exists = false;
            for (int i = 0, j = rootNode.getChildCount(); i < j; i += 1)
            {
                if (display.equalsIgnoreCase(rootNode.getChildAt(i).toString()))
                {
                    model = (NodeMdl) rootNode.getChildAt(i);
                    treeModel.nodeChanged(model);
                    exists = true;
                    break;
                }
            }

            // 新增节点
            if (!exists)
            {
                rootNode.add(model);
                treeModel.nodeStructureChanged(rootNode);
            }
            tr_MailBoxs.setSelectionPath(new TreePath(new DefaultMutableTreeNode[]
                    {
                        rootNode, model
                    }));
            showNotice("邮件信息检测完毕！");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            showNotice("邮件信息检测异常:(");
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

        java.awt.Container container = getContentPane();
        setTitle("邮件检测");
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(container);
        container.setLayout(layout);
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

        pack();
        setIconImage(Util.getLogo(16));
    }

    /**
     * 消息加载线程专用
     * @return
     */
    private boolean loadMsg()
    {
        try
        {
            if (folder != null)
            {
                showNotice("正在获取邮件列表信息……");
                Message[] msgs = folder.getMessages();
//                FetchProfile profile = new FetchProfile();
//                profile.add(FetchProfile.Item.ENVELOPE);
//                folder.fetch(msgs, profile);

                MailInf mail;
                for (int i = 1, j = msgs.length; i <= j; i += 1)
                {
                    showNotice("正处理第" + i + "封邮件……");
                    mail = new MailInf();
                    mail.loadMsg(msgs[i - 1]);
                    tableMode.append(mail);
                }

                folder.close(true);
                folder = null;

                showNotice("邮件列表读取完毕！");
                return true;
            }
            return false;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    private void tr_MailBoxsMouseClicked(java.awt.event.MouseEvent evt)
    {
        if (evt.getClickCount() < 2)
        {
            return;
        }

        showNotice("");
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
        String name = node.getKeyWord();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            return;
        }

        try
        {
            showNotice("正在处理与服务器通讯……");
            Store store = node.getConnect().getStore();
            folder = store.getFolder(node.getKeyWord());
            if (folder == null)
            {
                return;
            }
            if ((folder.getType() & Folder.HOLDS_MESSAGES) == 0)
            {
                return;
            }
            showNotice("正在读取邮件列表，请稍候……");
            if (!folder.isOpen())
            {
                folder.open(Folder.READ_WRITE);
            }
            tableMode.clear();

            Thread thread = new Thread(this);
            thread.start();
//            SwingUtilities.invokeLater(thread);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            showNotice("邮件列表读取异常:(");
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
            showNotice("正在加载邮件内容……");
            MailInf mail = tableMode.getMailInf(tb_MailMsgs.getSelectedRow());
            ta_MailBody.setContentType(mail.getContentType());
            tf_MailHead.setText(mail.getSubject());
            tf_MailUser.setText(mail.getTo());
            ta_MailBody.setText(mail.getBodyText());
            showNotice("邮件内容加载完毕！");
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            showNotice("邮件内容加载异常:(");
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

    private synchronized void showNotice(String notice)
    {
        lb_MailInfo.setText(notice);
    }

    private javax.swing.JEditorPane ta_MailBody;
    private javax.swing.JTable tb_MailMsgs;
    private javax.swing.JTree tr_MailBoxs;
    private javax.swing.JPanel pl_MailEdit;
    private javax.swing.JLabel lb_MailHead;
    private javax.swing.JLabel lb_MailUser;
    private javax.swing.JLabel lb_MailInfo;
    private javax.swing.JTextField tf_MailHead;
    private javax.swing.JTextField tf_MailUser;
    private javax.swing.JPanel pl_MailCtrl;
    private javax.swing.JButton bt_Delete;
    private javax.swing.JButton bt_Download;
    private javax.swing.JButton bt_Replay;
}
