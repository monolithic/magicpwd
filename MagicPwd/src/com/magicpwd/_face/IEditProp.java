/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._face;

/**
 *
 * @author Amon
 */
public interface IEditProp
{

    /**
     * 界面布局初始化
     */
    void initView();

    /**
     * 界面语言初始化
     */
    void initLang();

    /**
     * 界面数据初始化
     * @param tplt
     */
    void initData();

    /**
     * 界面焦点初始化
     */
    void requestFocus();

    void copyDataActionPerformed(java.awt.event.ActionEvent evt);

    void saveDataActionPerformed(java.awt.event.ActionEvent evt);

    void dropDataActionPerformed(java.awt.event.ActionEvent evt);

    void sortUActionPerformed(java.awt.event.ActionEvent evt);

    void sortDActionPerformed(java.awt.event.ActionEvent evt);

    void sortLActionPerformed(java.awt.event.ActionEvent evt);

    void sortRActionPerformed(java.awt.event.ActionEvent evt);
}
