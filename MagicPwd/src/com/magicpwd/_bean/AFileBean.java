/*
 *  Copyright (C) 2010 Amon
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd._bean;

import com.magicpwd.__a.AFrame;
import com.magicpwd.__i.IEditItem;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Desk;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;

/**
 * Application: MagicPwd
 * Author     : Amon
 * Encoding   : UTF-8
 * Created    : 2010-10-27 20:22:02
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public abstract class AFileBean extends javax.swing.JPanel
{

    protected AFrame formPtn;
    protected IEditItem itemData;
    protected java.io.File filePath;
    protected java.io.File amaPath;
    private WTextBox dataBox;

    public AFileBean(AFrame formPtn)
    {
        this.formPtn = formPtn;
    }

    protected void initConfView()
    {
        tf_PropData = new javax.swing.JTextField();
        dataBox = new WTextBox(tf_PropData, true);
        dataBox.initView();

        pl_PropConf = new javax.swing.JPanel();
        pl_PropConf.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_FileView = new BtnLabel();
        bt_FileView.setIcon(formPtn.getUserMdl().readIcon(ConsEnv.FEEL_PATH + "file-preview.png"));
        bt_FileView.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_FileViewActionPerformed(evt);
            }
        });
        pl_PropConf.add(bt_FileView);

        bt_FileApnd = new BtnLabel();
        bt_FileApnd.setIcon(formPtn.getUserMdl().readIcon(ConsEnv.FEEL_PATH + "file-upload.png"));
        bt_FileApnd.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_FileApndActionPerformed(evt);
            }
        });
        pl_PropConf.add(bt_FileApnd);
    }

    protected void initConfLang()
    {
        Lang.setWText(bt_FileView, LangRes.P30F1515, "&V");
        Lang.setWTips(bt_FileView, LangRes.P30F1516, "打开附件(Alt + V)");

        Lang.setWText(bt_FileApnd, LangRes.P30F1517, "&P");
        Lang.setWTips(bt_FileApnd, LangRes.P30F1518, "添加附件(Alt + P)");

        dataBox.initLang();
    }

    protected void initConfData()
    {
        dataBox.initData();
    }

    protected abstract void deCrypt(java.io.File src, java.io.File dst) throws Exception;

    protected abstract void enCrypt(java.io.File src, java.io.File dst) throws Exception;

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
            deCrypt(srcFile, tmpFile);
            if (!Desk.open(tmpFile))
            {
                Lang.showMesg(formPtn, LangRes.P30F1A03, "打开文件错误，请尝试手动方式查看！");
            }
        }
        catch (Exception exp)
        {
            Lang.showMesg(formPtn, null, exp.getLocalizedMessage());
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
    protected javax.swing.JTextField tf_PropData;
    // 配置信息
    protected javax.swing.JPanel pl_PropConf;
    private BtnLabel bt_FileApnd;
    private BtnLabel bt_FileView;
}
