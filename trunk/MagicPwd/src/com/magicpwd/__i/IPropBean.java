/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__i;

/**
 *
 * @author Amon
 */
public interface IPropBean
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
     * 显示数据
     */
    void showData();

    /**
     * 界面数据保存
     */
    void saveData();

    /**
     * 界面焦点初始化
     */
    void requestFocus();

    javax.swing.JPanel getPanel();
}
