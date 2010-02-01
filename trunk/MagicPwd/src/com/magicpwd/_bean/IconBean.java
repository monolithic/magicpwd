/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._bean;

import com.magicpwd._comn.Item;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IGridView;

/**
 * 属性：图标
 * 键值：ConsEnv.INDX_ICON
 * @author Amon
 */
public class IconBean extends javax.swing.JPanel implements IEditBean
{

    private Item tpltData;
    private IGridView gridView;

    public IconBean(IGridView gridView)
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
    public void initData(Item tplt)
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
