/*
 *  Copyright (C) 2010 yaoshangwen
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author yaoshangwen
 */
public class Shape extends javax.swing.JWindow
{

    private static int minWidth = 24;
    private static int maxWidth = 72;
    private static int arcWidth = 48;
    private int aniTime = 100;
    private int aniStep = 10;
    private boolean full = true;

    public Shape()
    {
        JButton b = new JButton("Demo");
        b.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (full)
                {
                    stepS();
                }
                else
                {
                    stepB();
                }
            }
        });
        this.setLayout(new BorderLayout());
        this.add(b);

        this.setSize(new Dimension(maxWidth, maxWidth));
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void stepS()
    {
        try
        {
            int c1 = (maxWidth - minWidth) / aniStep;
            int c2 = arcWidth / aniStep;
            int t = 1000 / aniStep;
            int w = maxWidth;
            int r = arcWidth;
            int p;
            while (w >= minWidth)
            {
                w -= c1;
                r -= c2;
                p = (maxWidth - w) >> 1;
                com.sun.awt.AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(p, p, w, w, r, r));
                Thread.sleep(t);
            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        full = false;
    }

    public void stepB()
    {
        try
        {
            // 矩形步增量
            int c1 = (maxWidth - minWidth) / aniStep;
            // 圆角步增量
            int c2 = arcWidth / aniStep;
            // 线程休息时长
            int t = aniTime / aniStep;

            int w = minWidth;
            int r = 0;
            int p;
            while (w <= maxWidth)
            {
                w += c1;
                r += c2;
                p = (maxWidth - w) >> 1;
                com.sun.awt.AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(p, p, w, w, r, r));
                Thread.sleep(t);
            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        full = true;
    }

    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        try
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                Window w = new Shape();
                w.setVisible(true);
                com.sun.awt.AWTUtilities.setWindowShape(w, new RoundRectangle2D.Double(0, 0, maxWidth, maxWidth, arcWidth, arcWidth));
                com.sun.awt.AWTUtilities.setWindowOpacity(w, 0.93f);
            }
        });
    }
}
