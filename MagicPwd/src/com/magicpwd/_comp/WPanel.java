package com.magicpwd._comp;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;

public class WPanel extends JComponent implements ActionListener
{

    private int frameIndex;
    private Timer timer;

    public WPanel()
    {
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        frameIndex++;
        if (frameIndex >= ANIMATION_FRAMES)
        {
            closeTimer();
            return;
        }

        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        if (isAnimating())
        {
            float alpha = (float) frameIndex / (float) ANIMATION_FRAMES;
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            super.paint(g2d);
            return;
        }

        frameIndex = 0;
        timer = new Timer(ANIMATION_INTERVAL, this);
        timer.start();
    }

    private boolean isAnimating()
    {
        return timer != null && timer.isRunning();
    }

    private void closeTimer()
    {
        if (isAnimating())
        {
            timer.stop();
            frameIndex = 0;
            timer = null;
        }
    }
    private static final int ANIMATION_FRAMES = 50;
    private static final int ANIMATION_INTERVAL = 10;
}
