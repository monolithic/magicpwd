/*
 * 
 */
package com.magicpwd._util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amon
 */
public class Jpng
{

    private int dt = 30;
    private int fi;
    private int ti;
    private int ci;
    private javax.swing.Timer timer;
    private javax.swing.JLabel label;
    private List<javax.swing.Icon> icons;

    public Jpng()
    {
        this(new ArrayList<javax.swing.Icon>());
    }

    public Jpng(List<javax.swing.Icon> icons)
    {
        this.icons = icons;
    }

    public boolean readIcons(String filePath, int iconWidth, int iconHeight) throws Exception
    {
        return readIcons(new java.io.File(filePath), iconWidth, iconHeight);
    }

    public boolean readIcons(java.io.File file, int iconWidth, int iconHeight) throws Exception
    {
        if (file != null && file.exists() && file.isFile() && file.canRead())
        {
            return readIcons(new java.io.FileInputStream(file), iconWidth, iconHeight);
        }
        return false;
    }

    public boolean readIcons(java.io.InputStream stream, int iconWidth, int iconHeight) throws Exception
    {
        fi = 0;
        ti = 0;
        if (icons.size() > 0)
        {
            icons.clear();
        }

        if (stream == null || iconWidth < 1 || iconHeight < 1)
        {
            return false;
        }

        java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(stream);
        int w = image.getWidth();
        int h = image.getHeight();
        if (w < iconWidth || h < iconHeight)
        {
            return false;
        }

        for (int y = 0; y < h; y += iconHeight)
        {
            for (int x = 0; x < w; x += iconWidth)
            {
                icons.add(new javax.swing.ImageIcon(image.getSubimage(x, y, iconWidth, iconHeight)));
            }
        }
        return true;
    }

    public void start()
    {
        if (timer == null)
        {
            timer = new javax.swing.Timer(dt, new java.awt.event.ActionListener()
            {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e)
                {
                    showIcon();
                }
            });
            timer.start();
        }
        if (ti < fi)
        {
            ti = icons.size() - 1;
        }
        timer.setDelay(dt);
        timer.setInitialDelay(dt);
    }

    public void stop()
    {
        if (timer.isRunning())
        {
            timer.stop();
        }
        ci = fi;
    }

    public void suspend()
    {
        if (timer.isRunning())
        {
            timer.stop();
        }
    }

    public void continve()
    {
        if (!timer.isRunning())
        {
            timer.start();
        }
    }

    /**
     * @return the dt
     */
    public int getDt()
    {
        return dt;
    }

    /**
     * @param dt the dt to set
     */
    public void setDt(int dt)
    {
        if (dt < 1)
        {
            dt = 1;
        }
        this.dt = dt;
    }

    /**
     * @return the fi
     */
    public int getFi()
    {
        return fi;
    }

    /**
     * @param fi the fi to set
     */
    public void setFi(int fi)
    {
        if (fi < 0 || fi >= icons.size() - 1)
        {
            fi = 0;
        }
        this.fi = fi;
    }

    /**
     * @return the ti
     */
    public int getTi()
    {
        return ti;
    }

    /**
     * @param ti the ti to set
     */
    public void setTi(int ti)
    {
        if (ti <= fi || ti >= icons.size())
        {
            ti = icons.size() - 1;
        }
        this.ti = ti;
    }

    /**
     * @return the label
     */
    public javax.swing.JLabel getLabel()
    {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(javax.swing.JLabel label)
    {
        this.label = label;
    }

    private void showIcon()
    {
        if (label != null)
        {
            ci = (ci + 1) % icons.size();
            label.setIcon(icons.get(ci));
        }
    }
}
