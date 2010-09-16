/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.data;

import com.magicpwd._comn.Kind;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Jcsv;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.r.KindTN;
import com.magicpwd.v.MainPtn;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public class ImportAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public ImportAction(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.tree.TreePath path = mainPtn.getSelectedPath();
        if (path == null)
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A02, "");
//            tr_GuidTree.requestFocus();
            return;
        }

        UserSign us = new UserSign(TrayPtn.getCurrForm());
        us.setBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return importData();
            }
        });
        us.initView(ConsEnv.INT_SIGN_RS);
        us.initLang();
        us.initData();
    }

    private boolean importData()
    {
        javax.swing.tree.TreePath path = mainPtn.getSelectedPath();
        KindTN node = (KindTN) path.getLastPathComponent();
        Kind kind = (Kind) node.getUserObject();

        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        int status = jfc.showOpenDialog(mainPtn);
        if (status != javax.swing.JFileChooser.APPROVE_OPTION)
        {
            return false;
        }
        java.io.File file = jfc.getSelectedFile();
        if (!file.exists())
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A03, "");
            return false;
        }
        if (!file.isFile())
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A04, "");
            return false;
        }
        if (!file.canRead())
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A05, "");
            return false;
        }

        try
        {
            Jcsv csv = new Jcsv(file);
            csv.setEe("");
            java.util.ArrayList<java.util.ArrayList<String>> data = csv.readFile();
            int size = coreMdl.getGridMdl().wImport(data, kind.getC2010103());
            coreMdl.getListMdl().listName(kind.getC2010103());
            Lang.showMesg(mainPtn, LangRes.P30F7A07, "成功导入数据个数：{0}", "" + size);

        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(mainPtn, LangRes.P30F7A08, "TXT文档格式解析出错，数据导入失败！");
        }
        return true;
    }
}
