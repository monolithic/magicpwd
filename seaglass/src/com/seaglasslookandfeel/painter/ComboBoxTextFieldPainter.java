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
 * $Id: ComboBoxTextFieldPainter.java 1120 2010-02-09 23:26:32Z kathryn@kathrynhuxtable.org $
 */
package com.seaglasslookandfeel.painter;

import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JComponent;

import com.seaglasslookandfeel.effect.SeaGlassInternalShadowEffect;
import com.seaglasslookandfeel.painter.AbstractRegionPainter.PaintContext.CacheMode;

/**
 * ComboBoxTextFieldPainter implementation.
 */
public final class ComboBoxTextFieldPainter extends AbstractCommonColorsPainter {
    public static enum Which {
        BACKGROUND_DISABLED, BACKGROUND_ENABLED, BACKGROUND_SELECTED,
    }

    private SeaGlassInternalShadowEffect internalShadow = new SeaGlassInternalShadowEffect();

    private PaintContext                 ctx;
    private CommonControlState            type;

    public ComboBoxTextFieldPainter(Which state) {
        super();
        this.ctx = new PaintContext(CacheMode.FIXED_SIZES);
        this.type = (state == Which.BACKGROUND_DISABLED) ? CommonControlState.DISABLED : CommonControlState.ENABLED;
    }

    @Override
    protected void doPaint(Graphics2D g, JComponent c, int width, int height, Object[] extendedCacheKeys) {
        Shape s = shapeGenerator.createRectangle(3, 3, width - 3, height - 6);
        g.setColor(c.getBackground());
        g.fill(s);

        s = shapeGenerator.createRectangle(3, 3, width - 3, height - 6);
        internalShadow.fill(g, s, false, false);

        s = shapeGenerator.createOpenRectangle(2, 2, width - 3, height - 5);
        g.setPaint(getTextBorderPaint(type, false));
        g.draw(s);
    }

    @Override
    protected PaintContext getPaintContext() {
        return ctx;
    }
}
