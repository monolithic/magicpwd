/*
 * Copyright (c) 2009 Kathryn Huxtable and Kenneth Orr.
 *
 * This file is part of the SeaGlass Pluggable Look and Feel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Id: ScrollPanePainter.java 1408 2010-03-12 22:40:25Z kathryn@kathrynhuxtable.org $
 */
package com.seaglasslookandfeel.painter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import com.seaglasslookandfeel.painter.AbstractRegionPainter.PaintContext.CacheMode;

/**
 * Nimbus's ScrollPanePainter.
 */
public final class ScrollPanePainter extends AbstractRegionPainter {

    /**
     * DOCUMENT ME!
     *
     * @author  $author$
     * @version $Revision$, $Date$
     */
    public static enum Which {
        BACKGROUND_ENABLED, BORDER_ENABLED, BORDER_ENABLED_FOCUSED, CORNER_ENABLED,
    }

    private Which        state;
    private PaintContext ctx;

    private Color borderColor = decodeColor("nimbusBorder");

    private Color cornerBorder = new Color(192, 192, 192);
    private Color cornerColor1 = new Color(240, 240, 240);
    private Color cornerColor2 = new Color(212, 212, 212);

    /**
     * Creates a new ScrollPanePainter object.
     *
     * @param state DOCUMENT ME!
     */
    public ScrollPanePainter(Which state) {
        super();
        this.state = state;
        this.ctx   = new PaintContext(CacheMode.FIXED_SIZES);
    }

    /**
     * @see com.seaglasslookandfeel.painter.AbstractRegionPainter#doPaint(java.awt.Graphics2D,
     *      javax.swing.JComponent, int, int, java.lang.Object[])
     */
    protected void doPaint(Graphics2D g, JComponent c, int width, int height, Object[] extendedCacheKeys) {
        switch (state) {

        case BORDER_ENABLED:
            paintBorderEnabled(g, width, height);
            break;

        case BORDER_ENABLED_FOCUSED:
            paintBorderFocused(g, width, height);
            break;

        case CORNER_ENABLED:
            paintCornerEnabled(g, width, height);
            break;
        }
    }

    /**
     * @see com.seaglasslookandfeel.painter.AbstractRegionPainter#getPaintContext()
     */
    protected PaintContext getPaintContext() {
        return ctx;
    }

    /**
     * DOCUMENT ME!
     *
     * @param g      DOCUMENT ME!
     * @param width  DOCUMENT ME!
     * @param height DOCUMENT ME!
     */
    private void paintBorderEnabled(Graphics2D g, int width, int height) {
        g.setPaint(borderColor);
        g.drawLine(3, 2, width - 4, 2);
        g.drawLine(2, 2, 2, height - 3);
        g.drawLine(width - 3, 2, width - 3, height - 3);
        g.drawLine(3, height - 3, width - 4, height - 3);
    }

    /**
     * DOCUMENT ME!
     *
     * @param g      DOCUMENT ME!
     * @param width  DOCUMENT ME!
     * @param height DOCUMENT ME!
     */
    private void paintBorderFocused(Graphics2D g, int width, int height) {
        paintBorderEnabled(g, width, height);

        Shape s = shapeGenerator.createRectangle(0, 0, width - 1, height - 1);

        g.setPaint(getFocusPaint(s, FocusType.OUTER_FOCUS, false));
        g.draw(s);
        s = shapeGenerator.createRectangle(1, 1, width - 3, height - 3);
        g.setPaint(getFocusPaint(s, FocusType.INNER_FOCUS, false));
        g.draw(s);
    }

    /**
     * DOCUMENT ME!
     *
     * @param g      DOCUMENT ME!
     * @param width  DOCUMENT ME!
     * @param height DOCUMENT ME!
     */
    private void paintCornerEnabled(Graphics2D g, int width, int height) {
        Shape s = decodeCornerBorder(width, height);

        g.setPaint(cornerBorder);
        g.fill(s);
        s = decodeCornerInside(width, height);
        g.setPaint(decodeCornerGradient(s));
        g.fill(s);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  width  DOCUMENT ME!
     * @param  height DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private Shape decodeCornerBorder(int width, int height) {
        return shapeGenerator.createRectangle(0, 0, width, height);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  width  DOCUMENT ME!
     * @param  height DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private Shape decodeCornerInside(int width, int height) {
        return shapeGenerator.createRectangle(1, 1, width - 2, height - 2);
    }

    /**
     * DOCUMENT ME!
     *
     * @param  s DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private Paint decodeCornerGradient(Shape s) {
        Rectangle2D bounds = s.getBounds2D();
        float       w      = (float) bounds.getWidth();
        float       h      = (float) bounds.getHeight();

        return createGradient(1, 1, w - 2, h - 2, new float[] { 0f, 1f }, new Color[] { cornerColor1, cornerColor2 });
    }
}
