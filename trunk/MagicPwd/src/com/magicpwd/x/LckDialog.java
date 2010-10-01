/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.x;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Jpng;
import com.magicpwd._util.Logs;

/**
 * 运行等待对话框
 * @author Awen
 */
public class LckDialog extends javax.swing.JWindow
{

    private Jpng jpng;

    public LckDialog(javax.swing.JFrame form)
    {
        super(form);
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

        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
        setPreferredSize(new java.awt.Dimension(200, 82));
        pack();
        return true;
    }

    public boolean initLang()
    {
        return true;
    }

    public boolean initData()
    {
        if (jpng == null)
        {
            jpng = new Jpng();
            try
            {
                java.io.InputStream stream = LckDialog.class.getResourceAsStream(ConsEnv.ICON_PATH + "wait.png");
                jpng.readIcons(stream, 16, 16);
                stream.close();
                jpng.setLabel(lb_BusyIcon);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
        jpng.start();
        return true;
    }

    @Override
    public void dispose()
    {
        jpng.stop();
        super.dispose();
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent evt)
    {
        if (evt.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
        {
            return;
        }
        super.processWindowEvent(evt);
    }
    private javax.swing.JLabel lb_BusyIcon;
}
