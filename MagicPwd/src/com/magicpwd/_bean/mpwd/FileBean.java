/**
 * 
 */
package com.magicpwd._bean.mpwd;

import com.magicpwd.__i.IEditItem;
import com.magicpwd.__i.mpwd.IMpwdBean;
import com.magicpwd._comp.WEditBox;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comn.item.EditItem;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Desk;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.v.mpwd.MainPtn;

/**
 * 属性：附件
 * 键值：ConsEnv.INDX_FILE
 * @author Amon
 */
public class FileBean extends javax.swing.JPanel implements IMpwdBean
{

    private WEditBox dataEdit;
    private IEditItem itemData;
    private MainPtn mainPtn;
    /**
     * 用户文件对象
     */
    private java.io.File filePath;
    private java.io.File amaPath;
    private String amaName;
    private WTextBox nameBox;
    private WTextBox dataBox;

    public FileBean(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    @Override
    public void initView()
    {
        dataEdit = new WEditBox(mainPtn.getUserMdl(), this, false);
        dataEdit.initView();

        lb_PropName = new javax.swing.JLabel();
        tf_PropName = new javax.swing.JTextField(14);
        nameBox = new WTextBox(tf_PropName, true);
        nameBox.initView();
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();
        tf_PropData = new javax.swing.JTextField();
        lb_PropData.setLabelFor(tf_PropData);

        lb_PropEdit = new javax.swing.JLabel();
        pl_PropEdit = new javax.swing.JPanel();
        pl_PropEdit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_FileView = new BtnLabel();
        bt_FileView.setIcon(mainPtn.getUserMdl().readIcon(ConsEnv.FEEL_PATH + "file-preview.png"));
        bt_FileView.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_FileViewActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_FileView);

        bt_FileApnd = new BtnLabel();
        bt_FileApnd.setIcon(mainPtn.getUserMdl().readIcon(ConsEnv.FEEL_PATH + "file-upload.png"));
        bt_FileApnd.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_FileApndActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_FileApnd);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropEdit);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        hpg2.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_PropName);
        vpg1.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_PropData);
        vpg2.addComponent(tf_PropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg3.addComponent(lb_PropEdit);
        vpg3.addComponent(pl_PropEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg3);
        vsg1.addContainerGap(14, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        vpg.addGroup(vsg1);
        vpg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    @Override
    public void initLang()
    {
        Lang.setWText(lb_PropName, LangRes.P30F1313, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F1314, "文件");

        Lang.setWText(bt_FileView, LangRes.P30F1515, "&V");
        Lang.setWTips(bt_FileView, LangRes.P30F1516, "打开附件(Alt + V)");

        Lang.setWText(bt_FileApnd, LangRes.P30F1517, "&P");
        Lang.setWTips(bt_FileApnd, LangRes.P30F1518, "添加附件(Alt + P)");

        nameBox.initLang();
        dataEdit.initLang();
    }

    @Override
    public void initData()
    {
        nameBox.initData();
    }

    @Override
    public void showData(IEditItem item)
    {
        itemData = item;
        String name = itemData.getName();
        if (com.magicpwd._util.Char.isValidate(name) && name.startsWith(ConsDat.SP_TPL_LS) && name.endsWith(ConsDat.SP_TPL_RS))
        {
            name = name.substring(1, name.length() - 1);
        }
        tf_PropName.setText(name);
        tf_PropData.setText(itemData.getData());
        amaName = itemData.getSpec(EditItem.SPEC_FILE_NAME);

        if (amaPath == null)
        {
            amaPath = new java.io.File(ConsEnv.DIR_DAT, ConsEnv.DIR_AMA);
            if (!amaPath.exists())
            {
                amaPath.mkdirs();
            }
        }
    }

    @Override
    public void requestFocus()
    {
        if (!com.magicpwd._util.Char.isValidate(tf_PropName.getText()))
        {
            tf_PropName.requestFocus();
            return;
        }
        tf_PropData.requestFocus();
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (Lang.showFirm(mainPtn, LangRes.P30F1A01, "确认要删除此属性数据么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            return;
        }

        try
        {
            new java.io.File(itemData.getSpec(EditItem.SPEC_FILE_NAME) + ConsEnv.FILE_ATTACHMENT).delete();

            mainPtn.removeSelected();
        }
        catch (Exception exp)
        {
            Lang.showMesg(mainPtn, null, exp.getLocalizedMessage());
            Logs.exception(exp);
        }
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_PropName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A2B, "请输入文件名称！");
            tf_PropName.requestFocus();
            return;
        }

        if (filePath != null)
        {
            if (!filePath.exists())
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A03, "");
                tf_PropData.requestFocus();
                return;
            }
            if (!filePath.isFile())
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A04, "");
                tf_PropData.requestFocus();
                return;
            }
            if (!filePath.canRead())
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A05, "");
                tf_PropData.requestFocus();
                return;
            }
            if (filePath.length() > 1048576)
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A06, "");
                tf_PropData.requestFocus();
                return;
            }

            if (!com.magicpwd._util.Char.isValidate(amaName))
            {
                amaName = com.magicpwd._util.Char.lPad(Long.toHexString(System.currentTimeMillis()), 16, '0');
            }
            try
            {
                java.io.File amaFile = new java.io.File(amaPath, amaName + ConsEnv.FILE_ATTACHMENT);
                if (!amaFile.exists())
                {
                    if (!amaFile.createNewFile())
                    {
                        Lang.showMesg(mainPtn, LangRes.P30F7A2C, "文件上传保存出错，请重试！");
                        return;
                    }
                }
                mainPtn.enCrypt(filePath, amaFile);
                //Keys.doCrypt(gridView.getCoreMdl().getECipher(), filePath, amaFile);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                Lang.showMesg(mainPtn, LangRes.P30F7A2C, "文件上传保存出错，请重试！");
                return;
            }
        }

        itemData.setName(name);
        itemData.setData(tf_PropData.getText());
        itemData.setSpec(EditItem.SPEC_FILE_NAME, amaName);

        mainPtn.updateSelected();
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        jfc.setMultiSelectionEnabled(false);
        if (filePath != null)
        {
            jfc.setSelectedFile(filePath);
        }
        int status = jfc.showSaveDialog(this);
        if (status != javax.swing.JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        filePath = jfc.getSelectedFile();
        if (filePath == null)
        {
            return;
        }
        if (!filePath.exists())
        {
            try
            {
                filePath.getParentFile().mkdirs();
                filePath.createNewFile();
            }
            catch (java.io.IOException exp)
            {
                Logs.exception(exp);
                Lang.showMesg(mainPtn, LangRes.P30F7A2E, "无法创建文件 {0}, 请确认您是否有足够的访问权限！", filePath.getPath());
                return;
            }
        }
        if (!filePath.canWrite())
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A2F, "无法保存数据到您指定的路径，请确认您是否有足够的权限！");
            return;
        }
        if (filePath.isDirectory())
        {
            filePath = new java.io.File(filePath, itemData.getData());
        }
        java.io.File tempFile = new java.io.File(amaPath, itemData.getSpec(EditItem.SPEC_FILE_NAME) + ConsEnv.FILE_ATTACHMENT);
        try
        {
            mainPtn.deCrypt(tempFile, filePath);
            //Keys.doCrypt(gridView.getCoreMdl().getDCipher(), tempFile, filePath);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(mainPtn, LangRes.P30F7A2D, "文件下载保存出错，请重试！");
        }
    }

    private void bt_FileViewActionPerformed(java.awt.event.ActionEvent evt)
    {
        try
        {
            java.io.File tmpPath = new java.io.File(System.getProperty("java.io.tmpdir"));
            if (!tmpPath.exists() || !tmpPath.canWrite())
            {
                tmpPath = new java.io.File("./tmp/");
                if (!tmpPath.exists())
                {
                    tmpPath.mkdir();
                }
            }

            java.io.File srcFile = new java.io.File(amaPath, itemData.getSpec(IEditItem.SPEC_FILE_NAME) + ConsEnv.FILE_ATTACHMENT);
            java.io.File tmpFile = new java.io.File(tmpPath, itemData.getData());
            mainPtn.deCrypt(srcFile, tmpFile);
            //Keys.doCrypt(gridView.getCoreMdl().getDCipher(), srcFile, tmpFile);
            if (!Desk.open(tmpFile))
            {
                Lang.showMesg(mainPtn, LangRes.P30F1A03, "打开文件错误，请尝试手动方式查看！");
            }
        }
        catch (Exception exp)
        {
            Lang.showMesg(mainPtn, null, exp.getLocalizedMessage());
            Logs.exception(exp);
        }
    }

    private void bt_FileApndActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        if (filePath != null)
        {
            jfc.setSelectedFile(filePath);
        }
        int status = jfc.showOpenDialog(this);
        if (status != javax.swing.JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        filePath = jfc.getSelectedFile();
        tf_PropData.setText(filePath.getName());
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropEdit;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JPanel pl_PropEdit;
    private javax.swing.JTextField tf_PropData;
    private javax.swing.JTextField tf_PropName;
    private BtnLabel bt_FileApnd;
    private BtnLabel bt_FileView;
}
