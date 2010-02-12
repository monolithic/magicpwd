/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._bean;

import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IGridView;

/**
 * 属性：图标
 * 键值：ConsEnv.INDX_ICON
 * @author Amon
 */
public class LogoBean extends javax.swing.JPanel implements IEditBean
{

    private IEditItem tpltData;
    private IGridView gridView;

    public LogoBean(IGridView gridView)
    {
        this.gridView = gridView;
    }

    @Override
    public void initView()
    {
    }

    @Override
    public void initLang()
    {
    }

    @Override
    public void initData(IEditItem tplt)
    {
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }
}
