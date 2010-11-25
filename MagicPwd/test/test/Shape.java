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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author yaoshangwen
 */
public class Shape extends javax.swing.JFrame
{

    public Shape()
    {
        super("不规则窗体");
        this.setLayout(new FlowLayout());
        this.add(new JButton("按钮"));
        this.add(new JCheckBox("复选按钮"));
        this.add(new JRadioButton("单选按钮"));
        this.add(new JProgressBar(0, 100));

        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                Window w = new Shape();
                w.setVisible(true);
                com.sun.awt.AWTUtilities.setWindowShape(w, new Ellipse2D.Double(0, 0, w.getWidth(), w.getHeight()));
                com.sun.awt.AWTUtilities.setWindowOpacity(w, 0.93f);
            }
        });
    }
}
