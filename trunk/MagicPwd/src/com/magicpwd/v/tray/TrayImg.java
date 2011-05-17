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

    /**
     * 窗口大小
     */
    private int wndW = 64;
    private int wndH = 64;
    /**
     * 默认图标大小
     */
    private int defImgW = 16;
    private int defImgH = 16;
    /**
     * 选中图标大小
     */
    private int selImgW = 28;
    private int selImgH = 28;
    private int selPadW = 2;
    private int selPadH = 2;
    /**
     * 背景图像偏移值
     */
    private int wndOffX = 8;
    private int wndOffY = 8;
    private int imgOffX;
    private int imgOffY;
    private java.awt.image.BufferedImage bigBg;
    private java.awt.image.BufferedImage curBg;
    private java.awt.image.BufferedImage icoBg;
    private java.awt.image.BufferedImage[][] bgArr;
    private java.util.HashMap<String, java.awt.image.BufferedImage> locMap;

    public void init()
    {
        imgOffX = (selImgW - defImgW) >> 1;
        imgOffY = (selImgH - defImgH) >> 1;

        icoBg = readImage("logo\\logo24.png");

        locMap = new java.util.HashMap<String, java.awt.image.BufferedImage>();
        locMap.put("1,1", readImage("skin\\feel\\default\\mexp.png"));
        locMap.put("0,0", readImage("skin\\feel\\default\\mwiz.png"));
        locMap.put("2,0", readImage("skin\\feel\\default\\mpad.png"));
        locMap.put("0,2", readImage("skin\\feel\\default\\mgtd.png"));
        locMap.put("2,2", readImage("skin\\feel\\default\\maoc.png"));

        bigBg = readImage("skin\\feel\\default\\guid.png");
        bgArr = new java.awt.image.BufferedImage[3][3];
        bgArr[0][0] = readImage("skin\\feel\\default\\guid-tl.png");
        bgArr[0][1] = readImage("skin\\feel\\default\\guid-ml.png");
        bgArr[0][2] = readImage("skin\\feel\\default\\guid-bl.png");
        bgArr[1][0] = readImage("skin\\feel\\default\\guid-tm.png");
        bgArr[1][1] = readImage("skin\\feel\\default\\guid-mm.png");
        bgArr[1][2] = readImage("skin\\feel\\default\\guid-bm.png");
        bgArr[2][0] = readImage("skin\\feel\\default\\guid-tr.png");
        bgArr[2][1] = readImage("skin\\feel\\default\\guid-mr.png");
        bgArr[2][2] = readImage("skin\\feel\\default\\guid-br.png");

        curBg = new java.awt.image.BufferedImage(bigBg.getWidth(), bigBg.getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB);
        deActive();
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

    public void deActive()
    {
        java.awt.Graphics2D g2d = curBg.createGraphics();
        g2d.drawImage(bigBg, 0, 0, this);
        g2d.drawImage(icoBg, (wndW - selImgW) >> 1, (wndH - selImgH) >> 1, this);
    }

    public void enActive(java.awt.Point p)
    {
        java.awt.Graphics2D g2d = curBg.createGraphics();
        g2d.drawImage(bigBg, 0, 0, this);
        for (String key : locMap.keySet())
        {
            String[] arr = key.split(",");
            g2d.drawImage(locMap.get(key), wndOffX + Integer.parseInt(arr[0]) * defImgW, wndOffY + Integer.parseInt(arr[1]) * defImgW, this);
        }

        int x = x2GridIndex(p.x);
        int y = y2GridIndex(p.y);
        java.awt.image.BufferedImage bi = bgArr[x][y];
        String xy = x + "," + y;

        x = x2GridPoint(p.x) - selPadW;
        y = y2GridPoint(p.y) - selPadH;
        if (x > wndOffX)
        {
            x -= imgOffX;
        }
        if (y > wndOffY)
        {
            y -= imgOffY;
        }
        if (x + selImgW > wndW - wndOffX)
        {
            x -= selPadW;
        }
        if (y + selImgH > wndH - wndOffY)
        {
            y -= selPadH;
        }
        if (locMap.containsKey(xy))
        {
            g2d.drawImage(bi, x, y, this);
            g2d.drawImage(locMap.get(xy), x + imgOffX, y + imgOffY, this);
        }
        g2d.dispose();

        repaint();
    }

    private int x2GridIndex(int x)
    {
        if (x - wndOffX < defImgW)
        {
            return 0;
        }
        if (x + defImgW >= wndW - wndOffX)
        {
            return 2;
        }
        return 1;
    }

    private int x2GridPoint(int x)
    {
        x -= wndOffX;
        return x - x % defImgW + wndOffX;
    }

    private int y2GridIndex(int y)
    {
        if (y - wndOffY < defImgH)
        {
            return 0;
        }
        if (y + defImgW >= wndH - wndOffY)
        {
            return 2;
        }
        return 1;
    }

    private int y2GridPoint(int y)
    {
        y -= wndOffY;
        return y - y % defImgH + wndOffY;
    }
}
