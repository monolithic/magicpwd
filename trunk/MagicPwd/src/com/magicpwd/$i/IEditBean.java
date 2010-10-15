/**
 * 
 */
package com.magicpwd.$i;

/**
 * @author Amon
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
     */
    void initData();

    /**
     * 显示指定数据
     * @param item
     */
    void showData(IEditItem item);

    /**
     * 界面焦点初始化
     */
    void requestFocus();

    void copyDataActionPerformed(java.awt.event.ActionEvent evt);

    void saveDataActionPerformed(java.awt.event.ActionEvent evt);

    void dropDataActionPerformed(java.awt.event.ActionEvent evt);
}
