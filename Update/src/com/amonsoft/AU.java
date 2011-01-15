/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amonsoft;

import com.amonsoft.m.FileInfo;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Amon
 */
public class AU extends javax.swing.JFrame
{

    private boolean updating;
    private static String currVer = "1.0.0.1";
    private static String infoUri;
    private static String fileUri;
    private javax.swing.DefaultListModel lm_StepInfo;

    public AU()
    {
    }

    private void init()
    {
        bt_Cancel = new javax.swing.JButton();
        bt_Cancel.setMnemonic('X');
        bt_Cancel.setText("退出(X)");
        bt_Cancel.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CancelActionPerformed(evt);
            }
        });

        bt_Manage = new javax.swing.JButton();
        bt_Manage.setMnemonic('S');
        bt_Manage.setText("开始(S)");
        bt_Manage.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ManageActionPerformed(evt);
            }
        });

        ta_NoteInfo = new javax.swing.JTextArea();
        ta_NoteInfo.setEditable(false);
        ta_NoteInfo.setFocusable(false);
        ta_NoteInfo.setOpaque(false);
        ta_NoteInfo.setRows(3);

        lm_StepInfo = new javax.swing.DefaultListModel();
        ls_StepInfo = new javax.swing.JList();
        ls_StepInfo.setModel(lm_StepInfo);
        ls_StepInfo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
        pack();
        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    private void bt_ManageActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (updating)
        {
            return;
        }

        new Thread()
        {

            @Override
            public void run()
            {
                startAU();
            }
        }.start();
    }

    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt)
    {
        System.exit(0);
    }

    private boolean parseUri(String arg)
    {
        return true;
    }

    private void startAU()
    {
        if (updating)
        {
            return;
        }

        updating = true;
        lm_StepInfo.clear();

        try
        {
            java.util.List<FileInfo> infoList = downInfo();
            if (infoList != null && overRide(infoList))
            {
                showStep("系统升级成功！");
            }
            else
            {
                showStep("系统升级失败！");
            }
        }
        catch (Exception exp)
        {
            javax.swing.JOptionPane.showMessageDialog(this, exp.getLocalizedMessage());
            showStep("系统升级异常！");
        }
        updating = false;
    }

    private java.util.List<FileInfo> downInfo() throws Exception
    {
        showStep("服务器连接中……");

        // 属性读取
        Document document = new SAXReader().read(new URL(infoUri));
        Node node = document.selectSingleNode("/amonsoft");
        if (node == null)
        {
            showStep("升级信息读取出错！");
            return null;
        }

        Node temp = node.selectSingleNode("version");
        if (temp == null)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "读取软件版本信息出错！");
            return null;
        }
        if (!checkVer(currVer, temp.getText()))
        {
            javax.swing.JOptionPane.showMessageDialog(this, "没有可用的升级信息！");
            return null;
        }

        // 文件服务器路径
        temp = node.selectSingleNode("server/url");
        if (temp == null)
        {
            showStep("无法获取文件下载路径！");
            return null;
        }
        fileUri = temp.getText();

        temp = node.selectSingleNode("strategy");
        if (temp != null)
        {
            String tmp = temp.getText().trim().toLowerCase();
            if (!"publish".equals(tmp))
            {
                if (javax.swing.JOptionPane.YES_OPTION != javax.swing.JOptionPane.showConfirmDialog(this, "新版本策略为" + tmp + "，确认要升级吗？"))
                {
                    showStep("用户取消升级！");
                    return null;
                }
            }
        }

        java.util.List nodeList = node.selectNodes("files/file");
        if (nodeList == null || nodeList.size() < 1)
        {
            showStep("没有可用的文件更新！");
            return null;
        }

        // 获取用户IP地址
        String inet = null;
        try
        {
            inet = InetAddress.getLocalHost().getHostAddress();
        }
        catch (Exception exp)
        {
            inet = "0.0.0.0";
        }

        int size = nodeList.size();
        java.util.List<FileInfo> infoList = new java.util.ArrayList<FileInfo>(size);

        byte[] buf = new byte[1024];
        FileInfo info;
        for (int i = 0; i < size; i += 1)
        {
            info = new FileInfo();
            infoList.add(info);

            node = (Node) nodeList.get(i);
            // 发行版本
            temp = node.selectSingleNode("version");
            if (temp != null)
            {
                info.setVersion(temp.getText());
            }
            // 发行方案
            temp = node.selectSingleNode("strategy");
            if (temp != null)
            {
                info.setStrategy(temp.getText());
            }
            // 升级方式
            temp = node.selectSingleNode("operation");
            if (temp != null)
            {
                info.setOperation(readInt(temp.getText(), 0));
            }
            // 远程目录
            temp = node.selectSingleNode("remote-path");
            if (temp != null)
            {
                info.setRemotePath(temp.getText());
            }
            // 远程名称
            temp = node.selectSingleNode("remote-name");
            if (temp != null)
            {
                info.setRemoteName(temp.getText());
            }
            // 本地目录
            temp = node.selectSingleNode("local-path1");
            if (temp != null)
            {
                info.setLocalPath1(temp.getText());
            }
            // 本地名称
            temp = node.selectSingleNode("local-name1");
            if (temp != null)
            {
                info.setLocalName1(temp.getText());
            }
            // 本地目录
            temp = node.selectSingleNode("local-path2");
            if (temp != null)
            {
                info.setLocalPath2(temp.getText());
            }
            // 本地名称
            temp = node.selectSingleNode("local-name2");
            if (temp != null)
            {
                info.setLocalName2(temp.getText());
            }
            // 文件下载
            if (!downFile(buf, info, inet))
            {
                return null;
            }
        }
        showStep("文件下载完成！");
        return infoList;
    }

    private boolean downFile(byte[] buf, FileInfo info, String ip)
    {
        try
        {
            showStep("正在下载文件：" + info.getRemoteName());

            URL url = new URL(fileUri.replace("{path}", info.getRemotePath()).replace("{file}", info.getRemoteName()).replace("{newVer}", info.getVersion()).replace("{oldVer}", currVer).replace("{ip}", ip));
            URLConnection con = url.openConnection();//建立连接
            java.io.File file = new java.io.File("tmp", info.getLocalName1());
            if (!file.exists() || !file.isFile())
            {
                file.createNewFile();
            }

            java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
            java.io.BufferedInputStream bis = new java.io.BufferedInputStream(con.getInputStream());
            int len = bis.read(buf, 0, buf.length);
            while (len >= 0)
            {
                fos.write(buf, 0, len);
                len = bis.read(buf, 0, buf.length);
            }
            bis.close();
            fos.close();

            return true;
        }
        catch (Exception exp)
        {
            javax.swing.JOptionPane.showMessageDialog(this, exp.getLocalizedMessage());
            showStep("文件 " + info.getRemoteName() + " 下载失败！");
            return false;
        }
    }

    private boolean overRide(java.util.List<FileInfo> infoList)
    {
        java.io.File file;
        for (FileInfo info : infoList)
        {
            showStep("正在升级文件：" + info.getLocalName1());

            if (info.getOperation() == -1)
            {
                file = new java.io.File(info.getLocalPath1(), info.getLocalName1());
                if (file.exists())
                {
                    if (file.canWrite())
                    {
                        file.delete();
                        info.setOperation(10);
                    }
                    else
                    {
                        showStep("无法移除文件 " + info.getLocalName1() + " ！");
                        return false;
                    }
                }
                continue;
            }
            if (info.getOperation() == 1)
            {
                file = new java.io.File("tmp", info.getLocalName1());
                if (!file.canWrite())
                {
                    showStep("文件 " + info.getLocalName1() + " 升级失败！");
                    return false;
                }
                copyTo(file, new java.io.File(info.getLocalPath1(), info.getLocalName1()));
                info.setOperation(10);
                continue;
            }
        }
        return true;
    }

    private boolean checkVer(String oldVer, String newVer)
    {
        // 版本标记处理
        if (!isValidate(oldVer) || !isValidate(newVer))
        {
            return false;
        }

        newVer = newVer.toUpperCase().replaceAll("\\s+", "");
        if (newVer.charAt(0) == 'V')
        {
            newVer = newVer.substring(1);
        }
        String[] newArr = newVer.split("\\.");
        if (newArr == null || newArr.length < 1)
        {
            return false;
        }

        // 老版本标记处理
        oldVer = oldVer.toUpperCase().replaceAll("\\s+", "");
        if (oldVer.charAt(0) == 'V')
        {
            oldVer = oldVer.substring(1);
        }
        String[] oldArr = oldVer.split("\\.");
        if (oldVer == null || newArr.length != oldArr.length)
        {
            return false;
        }

        for (int i = 0; i < newArr.length; i += 1)
        {
            if (oldArr[i].length() < newArr[i].length())
            {
                return true;
            }
            if (oldArr[i].compareTo(newArr[i]) < 0)
            {
                return true;
            }
        }
        return false;
    }

    private synchronized void showStep(String info)
    {
        lm_StepInfo.addElement(info);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (args != null && args.length == 1)
        {
            infoUri = args[0];
        }
        if (!isValidate(infoUri))
        {
            infoUri = readUri();
            if (!isValidate(infoUri))
            {
                return;
            }
        }

        new AU().init();
    }

    private static String readUri()
    {
        java.io.File file = new java.io.File("tmp", "ver.amu");
        if (!file.exists() || !file.isFile() || !file.canRead())
        {
            return "";
        }

        java.util.Properties prop = new java.util.Properties();
        try
        {
            java.io.FileInputStream fis = new java.io.FileInputStream(file);
            prop.load(fis);
            fis.close();
        }
        catch (Exception ex)
        {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            return "";
        }
        return prop.getProperty("uri");
    }

    private static int readInt(String t, int d)
    {
        if (t == null)
        {
            return d;
        }
        t = t.trim();
        if (java.util.regex.Pattern.matches("^\\d+$", t))
        {
            return Integer.parseInt(t);
        }
        return d;
    }

    public static boolean isValidate(String t)
    {
        return t != null ? t.trim().length() > 0 : false;
    }

    private static boolean copyTo(java.io.File srcFile, java.io.File dstFile)
    {
        java.io.FileInputStream fis = null;
        java.io.FileOutputStream fos = null;

        byte[] buf = new byte[4096];
        try
        {
            fis = new java.io.FileInputStream(srcFile);
            fos = new java.io.FileOutputStream(dstFile);

            int len = fis.read(buf);
            while (len > 0)
            {
                fos.write(buf, 0, len);
                len = fis.read(buf);
            }
        }
        catch (Exception exp)
        {
            return false;
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.flush();
                }
                catch (Exception exp)
                {
                }
                try
                {
                    fos.close();
                }
                catch (Exception exp)
                {
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (Exception exp)
                {
                }
            }
        }
        return true;
    }
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_Manage;
    private javax.swing.JList ls_StepInfo;
    private javax.swing.JTextArea ta_NoteInfo;
}
