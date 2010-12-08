/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.x;

import com.magicpwd.__a.AFrame;

/**
 * 运行等待对话框
 * @author Awen
 */
public class LckDialog extends javax.swing.JPanel
{

    public LckDialog(AFrame form)
    {
    }

    public boolean initView()
    {
        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setLayout(new java.awt.BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));

        lb_BusyIcon = new javax.swing.JLabel();
        lb_BusyIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_BusyIcon.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray));
        panel.add(lb_BusyIcon, java.awt.BorderLayout.CENTER);

        add(panel, java.awt.BorderLayout.CENTER);
        return true;
    }

    public boolean initLang()
    {
        return true;
    }

    public boolean initData()
    {
        return true;
    }
    private javax.swing.JLabel lb_BusyIcon;
}
