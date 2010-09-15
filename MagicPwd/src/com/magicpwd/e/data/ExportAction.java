/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.data;

/**
 *
 * @author Administrator
 */
public class ExportAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        if (path == null)
        {
            Lang.showMesg(this, LangRes.P30F7A20, "请选择您要导出数据的类别信息！");
            tr_GuidTree.requestFocus();
            return;
        }

        UserSign us = new UserSign(TrayPtn.getCurrForm());
        us.setBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return exportData();
            }
        });
        us.initView(ConsEnv.INT_SIGN_RS);
        us.initLang();
        us.initData();
    }
}
