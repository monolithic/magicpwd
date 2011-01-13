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
import com.magicpwd._comp.IcoLabel;
import com.magicpwd._comp.ImgPanel;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Logs;
import java.awt.event.ActionEvent;

/**
 * 图片预览
 * @author Amon
 */
public class ImgViewer extends ADialog
{

    private AFrame formPtn;
    private java.io.File imgFile;

    public ImgViewer(AFrame formPtn, java.io.File imgFile)
    {
        super(formPtn, true);
        this.formPtn = formPtn;
        this.imgFile = imgFile;
    }

    public void initView()
    {
        java.awt.image.BufferedImage image = null;
        if (imgFile != null && imgFile.exists() && imgFile.isFile() && imgFile.canRead())
        {
            try
            {
                java.io.FileInputStream fis = new java.io.FileInputStream(imgFile);
                image = javax.imageio.ImageIO.read(fis);
                fis.close();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }

        ip_ImgView = new ImgPanel();
        if (image != null)
        {
            ip_ImgView.setImage(image);
            ip_ImgView.setPreferredSize(new java.awt.Dimension(image.getWidth(), image.getHeight()));
        }

        pl_Control = new javax.swing.JPanel();
        pl_Control.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        bt_ClearBtn = new IcoLabel();
        bt_ClearBtn.setIcon(formPtn.readFavIcon("file-close", true));
        bt_ClearBtn.setFocusable(false);
        pl_Control.add(bt_ClearBtn);

        bt_MLineBtn = new IcoLabel();
        bt_MLineBtn.setIcon(formPtn.readFavIcon("file-close", true));
        bt_MLineBtn.setFocusable(false);
        pl_Control.add(bt_MLineBtn);

        bt_PLineBtn = new IcoLabel();
        bt_PLineBtn.setIcon(formPtn.readFavIcon("file-close", true));
        bt_PLineBtn.setFocusable(false);
        pl_Control.add(bt_PLineBtn);

        bt_CloseBtn = new IcoLabel();
        bt_CloseBtn.setIcon(formPtn.readFavIcon("file-close", true));
        bt_CloseBtn.setFocusable(false);
        pl_Control.add(bt_CloseBtn);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(ip_ImgView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, image.getWidth(), Short.MAX_VALUE);
        hpg1.addComponent(pl_Control, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, image.getWidth(), Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addContainerGap();
        hsg1.addGroup(hpg1);
        hsg1.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg1));

        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap();
        vsg1.addComponent(ip_ImgView, javax.swing.GroupLayout.PREFERRED_SIZE, image.getHeight(), Short.MAX_VALUE);
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
        bt_ClearBtn.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ip_ImgView.clearLine();
            }
        });

        bt_MLineBtn.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ip_ImgView.setShowMLine(bt_MLineBtn.isSelected());
            }
        });
        bt_MLineBtn.setSelected(true);
        ip_ImgView.setShowMLine(true);

        bt_PLineBtn.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ip_ImgView.setShowPLine(bt_PLineBtn.isSelected());
            }
        });
        bt_PLineBtn.setSelected(true);
        ip_ImgView.setShowPLine(true);

        bt_CloseBtn.addActionListener(new java.awt.event.ActionListener()
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
                ip_ImgView.resize();
                ip_ImgView.repaint();
            }
        });

        this.processEscape();
    }

    @Override
    protected boolean hideDialog()
    {
        setVisible(false);
        return true;
    }
    private IcoLabel bt_ClearBtn;
    private IcoLabel bt_MLineBtn;
    private IcoLabel bt_PLineBtn;
    private IcoLabel bt_CloseBtn;
    private ImgPanel ip_ImgView;
    private javax.swing.JPanel pl_Control;
}
