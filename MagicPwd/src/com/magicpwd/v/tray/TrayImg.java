/*
 *  Copyright (C) 2011 Aven
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
package com.magicpwd.v.tray;

/**
 * Application: MagicPwd
 * Author     : Aven
 * Encoding   : UTF-8
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class TrayImg extends javax.swing.JPanel
{

    private int wndDim = 64;
    private int imageDim = 16;
    private int offset = 8;
    private java.awt.image.BufferedImage bigBg;
    private java.awt.image.BufferedImage curBg;
    private java.awt.image.BufferedImage[][] bglt;//左上角背景
    private java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, imageDim, imageDim);

    public void init()
    {
        bigBg = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid.png");
        bglt = new java.awt.image.BufferedImage[3][3];
        bglt[0][0] = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid-tl.png");
        bglt[0][1] = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid-ml.png");
        bglt[0][2] = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid-bl.png");
        bglt[1][0] = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid-tm.png");
        bglt[1][1] = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid-mm.png");
        bglt[1][2] = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid-bm.png");
        bglt[2][0] = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid-tr.png");
        bglt[2][1] = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid-mr.png");
        bglt[2][2] = readImage("F:\\app\\MagicPwd\\skin\\feel\\default\\guid-br.png");
        curBg = new java.awt.image.BufferedImage(bigBg.getWidth(), bigBg.getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB);
    }

    private java.awt.image.BufferedImage readImage(String path)
    {
        try
        {
            return javax.imageio.ImageIO.read(new java.io.File(path));
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
            return null;
        }
    }

    @Override
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        if (curBg != null)
        {
            g.drawImage(curBg, 0, 0, this);
        }
    }

    public void active(java.awt.Point p)
    {
        int x = getLoc(p.x);
        int y = getLoc(p.y);
        java.awt.Graphics2D g2d = curBg.createGraphics();
        g2d.drawImage(bigBg, 0, 0, this);
        java.awt.image.BufferedImage tmpImg = bglt[x][y];
        x *= 16;
        y *= 16;
        if (x > 0)
        {
            x -= 8;
        }
        if (y > 0)
        {
            y -= 8;
        }
        if (x + 16 >= wndDim)
        {
            x -= 16;
        }
        if (y + 16 >= wndDim)
        {
            y -= 16;
        }
        System.out.println(p.x + "," + p.y + ":" + x + "," + y);
        g2d.drawImage(tmpImg, offset + x, offset + y, this);
        g2d.dispose();
        repaint();
    }

    private int getLoc(int p)
    {
        if (p - offset < imageDim)
        {
            return 0;
        }
        if (p + imageDim >= wndDim - offset)
        {
            return 2;
        }
        return 1;
    }
}
