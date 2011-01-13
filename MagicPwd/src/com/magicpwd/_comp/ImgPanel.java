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
package com.magicpwd._comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Amon
 */
public class ImgPanel extends java.awt.Canvas
{

    private Paint paint;
    private BufferedImage image;

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (paint == null)
        {
            paint = Color.white;
        }
        g2d.setPaint(paint);
        int cw = this.getWidth();
        int ch = this.getHeight();
        g2d.fillRect(0, 0, cw, ch);

        if (image != null)
        {
            int iw = image.getWidth();
            int ih = image.getHeight();

            if (cw < iw || ch < ih)
            {
                double dw = (double) cw / iw;
                double dh = (double) ch / ih;
                double r = dw < dh ? dw : dh;
                iw = (int) (iw * r);
                ih = (int) (ih * r);
            }
            System.out.println("[" + cw + ',' + ch + "][" + iw + ',' + ih + ']');
            g2d.drawImage(image, (cw - iw) >> 1, (ch - ih) >> 1, iw, ih, this);
        }
    }

    /**
     * @return the image
     */
    public BufferedImage getImage()
    {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image)
    {
        this.image = image;
        this.repaint();
    }
}
