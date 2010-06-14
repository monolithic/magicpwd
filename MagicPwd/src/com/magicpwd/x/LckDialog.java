/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.x;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Logs;
import java.awt.Color;
import java.awt.event.ActionListener;

/**
 *
 * @author Awen
 */
public class LckDialog extends javax.swing.JDialog
{

    public LckDialog(javax.swing.JFrame form)
    {
        super(form, true);
        setUndecorated(true);
    }

    public boolean initView()
    {
        lb_BusyIcon = new javax.swing.JLabel();
        lb_BusyIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_BusyIcon.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        getContentPane().add(lb_BusyIcon, java.awt.BorderLayout.CENTER);
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
        try
        {
            java.io.InputStream stream = LckDialog.class.getResourceAsStream(ConsEnv.ICON_PATH + "wait.png");
            java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(stream);
            stream.close();
            waitIcons = new javax.swing.Icon[15];

            int x = 0;
            for (int i = 0; i < waitIcons.length; i++)
            {
                waitIcons[i] = new javax.swing.ImageIcon(image.getSubimage(x, 0, 16, 16));
                x += 16;
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        lb_BusyIcon.setIcon(waitIcons[waitIndex]);

        waitTimer = new javax.swing.Timer(30, new ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                waitIndex = (waitIndex + 1) % waitIcons.length;
                lb_BusyIcon.setIcon(waitIcons[waitIndex]);
            }
        });
        waitTimer.start();
        return true;
    }

    @Override
    public void dispose()
    {
        waitTimer.stop();
        super.dispose();
    }
    private javax.swing.Timer waitTimer;
    private int waitIndex = 0;
    private javax.swing.Icon waitIcons[];
    private javax.swing.JLabel lb_BusyIcon;
}
