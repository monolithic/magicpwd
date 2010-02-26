/**
 * 
 */
package com.magicpwd._face;

import com.magicpwd._comn.EditItem;

/**
 * @author Amon
 * 
 */
public interface IEditBean
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
    void initData(IEditItem tplt);

    /**
     * 界面焦点初始化
     */
    void requestFocus();

    void copyDataActionPerformed(java.awt.event.ActionEvent evt);

    void saveDataActionPerformed(java.awt.event.ActionEvent evt);

    void dropDataActionPerformed(java.awt.event.ActionEvent evt);
}
