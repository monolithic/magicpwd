package com.magicpwd.v;

import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IFormView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.c.FindEvt;
import com.magicpwd.c.MPayEvt;

/**
 * 正常模式：账簿
 * 
 * @author Amon
 * 
 */
public class NormPtn extends javax.swing.JFrame implements IFormView, MPayEvt, FindEvt
{

    public void initView()
    {
        this.pack();
        this.setIconImage(Util.getLogo(16));
        Util.centerForm(this, null);
    }

    public void initLang()
    {
        setTitle(Lang.getLang(LangRes.P30F5201, "全能搜索"));
    }

    public void initData()
    {
    }

    @Override
    public void setVisible(boolean visible)
    {
        super.setVisible(visible);
    }

    @Override
    public javax.swing.JFrame getForm()
    {
        return this;
    }

    @Override
    public void fileCopyActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileApndActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileSaveActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileOpenActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileDeltActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileHideActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileExitActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void viewTop1ActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpHelpActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpSiteActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpMailActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpUpdtActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpJavaActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpInfoActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void findActionPerformed(java.awt.event.ActionEvent evt)
    {
    }
}
