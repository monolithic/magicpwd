/**
 * 
 */
package com.magicpwd._bean;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comn.Item;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._comn.Keys;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.EditBox;

/**
 * 属性：附件
 * 键值：ConsEnv.INDX_FILE
 * @author Amon
 */
public class FileBean extends javax.swing.JPanel implements IEditBean
{

    private IEditItem tpltData;
    private IGridView gridView;
    private java.io.File filePath;
    private String fileName;
    private EditBox dataEdit;

    public FileBean(IGridView view)
    {
        gridView = view;
    }

    @Override
    public void initView()
    {
        dataEdit = new EditBox(this, false);
        dataEdit.initView();

        lb_PropName = new javax.swing.JLabel();

        tf_PropName = new javax.swing.JTextField();
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();

        tf_PropData = new javax.swing.JTextField();
        lb_PropData.setLabelFor(tf_PropData);

        lb_PropEdit = new javax.swing.JLabel();

        pl_PropEdit = new javax.swing.JPanel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropEdit);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg2.addComponent(tf_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE);
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
        vpg3.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg3);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        vpg4.addComponent(dataEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vpg4.addGroup(vsg);
        layout.setVerticalGroup(vpg4);

        bt_FileView = new BtnLabel();

        lb_PropName.setLabelFor(tf_PropName);

        tf_PropName.setColumns(14);
        tf_PropName.addFocusListener(new java.awt.event.FocusAdapter()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                tf_PropName.selectAll();
            }
        });

        lb_PropData.setLabelFor(tf_PropData);

        bt_FileView.setMnemonic('O');
        bt_FileView.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_FILE_OPEN)));
        bt_FileView.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_FileViewActionPerformed(evt);
            }
        });
    }

    @Override
    public void initLang()
    {
        Lang.setWText(lb_PropName, LangRes.P30F1313, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F1314, "文件");
        Lang.setWTips(bt_FileView, LangRes.P30F150B, "添加附件");

        dataEdit.initLang();
    }

    @Override
    public void initData(IEditItem tplt)
    {
        tpltData = tplt;
        String name = tpltData.getName();
        if (Util.isValidate(name) && name.startsWith(ConsDat.SP_TPL_LS) && name.endsWith(ConsDat.SP_TPL_RS))
        {
            name = name.substring(1, name.length() - 1);
        }
        tf_PropName.setText(name);
        tf_PropData.setText(tpltData.getData());
        fileName = tpltData.getSpec(Item.SPEC_FILE_NAME);
    }

    @Override
    public void requestFocus()
    {
        if (!Util.isValidate(tf_PropName.getText()))
        {
            tf_PropName.requestFocus();
            return;
        }
        tf_PropData.requestFocus();
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (Lang.showFirm(this, LangRes.P30F1A01, "") == javax.swing.JOptionPane.YES_OPTION)
        {
            UserMdl.getGridMdl().wRemove(tpltData);
            gridView.selectNext(false);
            try
            {
                new java.io.File(tpltData.getSpec(Item.SPEC_FILE_NAME)).delete();
            }
            catch (Exception exp)
            {
            }
        }
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_PropName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, "", "请输入文件名称！");
            tf_PropName.requestFocus();
            return;
        }

        if (filePath != null)
        {
            if (!filePath.exists())
            {
                Lang.showMesg(this, LangRes.P30F7A03, "");
                tf_PropData.requestFocus();
                return;
            }
            if (!filePath.isFile())
            {
                Lang.showMesg(this, LangRes.P30F7A04, "");
                tf_PropData.requestFocus();
                return;
            }
            if (!filePath.canRead())
            {
                Lang.showMesg(this, LangRes.P30F7A05, "");
                tf_PropData.requestFocus();
                return;
            }
            if (filePath.length() > 1048576)
            {
                Lang.showMesg(this, LangRes.P30F7A06, "");
                tf_PropData.requestFocus();
                return;
            }

            if (!Util.isValidate(fileName))
            {
                fileName = Util.lPad(Long.toHexString(System.currentTimeMillis()), 16, '0');
            }
            try
            {
                java.io.File tempFile = new java.io.File(ConsEnv.DIR_DAT, fileName);
                if (!tempFile.exists())
                {
                    if (!tempFile.createNewFile())
                    {
                        Lang.showMesg(this, "", "文件上传保存出错，请重试！");
                        return;
                    }
                }
                Keys.doCrypt(UserMdl.getECipher(), filePath, tempFile);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                Lang.showMesg(this, "", "文件上传保存出错，请重试！");
                return;
            }
        }

        tpltData.setName(name);
        tpltData.setData(tf_PropData.getText());
        tpltData.setSpec(Item.SPEC_FILE_NAME, fileName);
        UserMdl.getGridMdl().setModified(true);

        gridView.selectNext(!UserMdl.getGridMdl().isUpdate());
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
                Lang.showMesg(this, "", "无法创建文件 {0}, 请确认您是否有足够的访问权限！", filePath.getPath());
                return;
            }
        }
        if (!filePath.canWrite())
        {
            Lang.showMesg(this, "", "无法保存数据到您指定的路径，请确认您是否有足够的权限！");
            return;
        }
        if (filePath.isDirectory())
        {
            filePath = new java.io.File(filePath, tpltData.getData());
        }
        java.io.File tempFile = new java.io.File(ConsEnv.DIR_DAT, tpltData.getSpec(Item.SPEC_FILE_NAME));
        try
        {
            Keys.doCrypt(UserMdl.getDCipher(), tempFile, filePath);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, "", "文件下载保存出错，请重试！");
        }
    }

    private void bt_FileViewActionPerformed(java.awt.event.ActionEvent evt)
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
    private BtnLabel bt_FileView;
}
