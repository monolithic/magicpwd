/*
 *  Copyright (C) 2011 Amon
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
package com.magicpwd.x;

import com.magicpwd.__a.ADialog;
import com.magicpwd.__a.AFrame;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.ImgPanel;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Logs;
import java.awt.event.ActionEvent;

/**
 *
 * @author Amon
 */
public class ImgViewer extends ADialog
{

    private AFrame formPtn;

    public ImgViewer(AFrame formPtn)
    {
        super(formPtn, true);
        this.formPtn = formPtn;
    }

    public void initView()
    {
        ip_ImgView = new ImgPanel();
        pl_Control = new javax.swing.JPanel();
        pl_Control.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        bl_ExitLbl = new BtnLabel();
        bl_ExitLbl.setIcon(formPtn.readFavIcon("file-close", true));
        bl_ExitLbl.setPreferredSize(new java.awt.Dimension(17, 17));
        pl_Control.add(bl_ExitLbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(ip_ImgView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, Short.MAX_VALUE);
        hpg1.addComponent(pl_Control, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addContainerGap();
        hsg1.addGroup(hpg1);
        hsg1.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg1));

        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap();
        vsg1.addComponent(ip_ImgView, javax.swing.GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(pl_Control, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vsg1));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
    }

    public void initLang()
    {
        this.setTitle("图片预览");

        pack();
        Bean.centerForm(this, formPtn);
    }

    public void initData()
    {
        bl_ExitLbl.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                hideDialog();
            }
        });

        this.addComponentListener(new java.awt.event.ComponentAdapter()
        {

            @Override
            public void componentResized(java.awt.event.ComponentEvent e)
            {
                ip_ImgView.repaint();
            }
        });

        this.processEscape();
    }

    public boolean showData(java.io.File file)
    {
        if (file == null || !file.exists() || !file.isFile() || !file.canRead())
        {
            return false;
        }

        try
        {
            java.io.FileInputStream fis = new java.io.FileInputStream(file);
            java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(fis);
            fis.close();

            if (image == null)
            {
                return false;
            }
            ip_ImgView.setPreferredSize(new java.awt.Dimension(image.getWidth(), image.getHeight()));
            ip_ImgView.setImage(image);
            pack();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    @Override
    protected boolean hideDialog()
    {
        setVisible(false);
        return true;
    }
    private BtnLabel bl_ExitLbl;
    private ImgPanel ip_ImgView;
    private javax.swing.JPanel pl_Control;
}
