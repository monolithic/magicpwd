/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.file;

import com.magicpwd._comn.item.GuidItem;
import com.magicpwd._comn.Kind;
import com.magicpwd._comn.item.MetaItem;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.r.KindTN;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public class SaveAction extends javax.swing.AbstractAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public SaveAction(MainPtn mainPtn, CoreMdl coreMdl)
    {
        this.mainPtn = mainPtn;
        this.coreMdl = coreMdl;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
//        // 是否需要保存
//        if (coreMdl.getGridMdl().getRowCount() < ConsEnv.PWDS_HEAD_SIZE)
//        {
//            return;
//        }
//
//        GridMdl gm = coreMdl.getGridMdl();
//
//        // 数据未被修改
//        if (!gm.isModified())
//        {
//            //Lang.showMesg(this, LangRes.P30F7A27, "您未曾修改过数据，不需要保存！");
//            return;
//        }
//
//        // 口令类别检测
//        GuidItem guid = (GuidItem) gm.getItemAt(ConsEnv.PWDS_HEAD_GUID);
//        if (!com.magicpwd._util.Char.isValidate(guid.getData()))
//        {
//            javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
//            if (path == null)
//            {
//                Lang.showMesg(mainPtn, LangRes.P30F7A0D, "请选择口令类别信息！");
//                tr_GuidTree.requestFocus();
//                return;
//            }
//
//            KindTN node = (KindTN) path.getLastPathComponent();
//            Kind kind = (Kind) node.getUserObject();
//            gm.getItemAt(ConsEnv.PWDS_HEAD_GUID).setData(kind.getC2010103());
//        }
//
//        // 标题为空检测
//        MetaItem meta = (MetaItem) gm.getItemAt(ConsEnv.PWDS_HEAD_META);
//        if (!com.magicpwd._util.Char.isValidate(meta.getName()))
//        {
//            Lang.showMesg(mainPtn, LangRes.P30F7A0C, "请输入口令标题！");
//            tb_KeysView.setRowSelectionInterval(1, 1);
//            showPropEdit(meta, true);
//            return;
//        }
//
//        try
//        {
//            ls_LastIndx = -1;
//            tb_LastIndx = -1;
//            gm.saveData(mainTool.isHistBackSelected(), true);
//        }
//        catch (Exception exp)
//        {
//            Logs.exception(exp);
//            Lang.showMesg(mainPtn, LangRes.P30F7A0E, "口令数据保存失败，请重新启动本程序后再次尝试！");
//        }
//
//        // 数据新增的情况下，需要重新显示列表信息
//        if (gm.isUpdate())
//        {
//            coreMdl.getListMdl().updtName(ls_GuidList.getSelectedIndex(), gm.getItemAt(ConsEnv.PWDS_HEAD_META).getName());
//        }
//        else
//        {
//            if (isSearch)
//            {
//                coreMdl.getListMdl().findName(queryKey);
//            }
//            else if (com.magicpwd._util.Char.isValidateHash(queryKey))
//            {
//                coreMdl.getListMdl().listName(queryKey);
//            }
//        }
//
//        mainPtn.showPropEdit();
//        mainInfo.initData();
    }
}
