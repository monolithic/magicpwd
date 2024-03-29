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
 * $Id: SeaGlassDesktopPaneUI.java 466 2009-12-06 19:21:29Z kathryn@kathrynhuxtable.org $
 */
package com.seaglasslookandfeel.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

import javax.swing.DefaultDesktopManager;
import javax.swing.DesktopManager;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicDesktopPaneUI;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthStyle;

import sun.swing.plaf.synth.SynthUI;

import com.seaglasslookandfeel.SeaGlassContext;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;

/**
 * SeaGlassDesktopPaneUI implementation.
 * 
 * Based on Synth's implementation.
 * 
 * @see javax.swing.plaf.synth.SynthDesktopPaneUI
 */
public class SeaGlassDesktopPaneUI extends BasicDesktopPaneUI implements PropertyChangeListener, SynthUI {
    private SynthStyle     style;
    private TaskBar        taskBar;
    private DesktopManager oldDesktopManager;

    public static ComponentUI createUI(JComponent c) {
        return new SeaGlassDesktopPaneUI();
    }

    protected void installListeners() {
        super.installListeners();
        desktop.addPropertyChangeListener(this);
        if (taskBar != null) {
            // Listen for desktop being resized
            desktop.addComponentListener(taskBar);
            // Listen for frames being added to desktop
            desktop.addContainerListener(taskBar);
        }
    }

    protected void installDefaults() {
        updateStyle(desktop);

        if (UIManager.getBoolean("InternalFrame.useTaskBar")) {
            taskBar = new TaskBar();

            for (Component comp : desktop.getComponents()) {
                JInternalFrame.JDesktopIcon desktopIcon;

                if (comp instanceof JInternalFrame.JDesktopIcon) {
                    desktopIcon = (JInternalFrame.JDesktopIcon) comp;
                } else if (comp instanceof JInternalFrame) {
                    desktopIcon = ((JInternalFrame) comp).getDesktopIcon();
                } else {
                    continue;
                }
                // Move desktopIcon from desktop to taskBar
                if (desktopIcon.getParent() == desktop) {
                    desktop.remove(desktopIcon);
                }
                if (desktopIcon.getParent() != taskBar) {
                    taskBar.add(desktopIcon);
                    desktopIcon.getInternalFrame().addComponentListener(taskBar);
                }
            }
            taskBar.setBackground(desktop.getBackground());
            desktop.add(taskBar, new Integer(JLayeredPane.PALETTE_LAYER.intValue() + 1));
            if (desktop.isShowing()) {
                taskBar.adjustSize();
            }
        }
    }

    private void updateStyle(JDesktopPane c) {
        SynthStyle oldStyle = style;
        SeaGlassContext context = getContext(c, ENABLED);
        style = SeaGlassLookAndFeel.updateStyle(context, this);
        if (oldStyle != null) {
            uninstallKeyboardActions();
            installKeyboardActions();
        }
        context.dispose();
    }

    protected void uninstallListeners() {
        if (taskBar != null) {
            desktop.removeComponentListener(taskBar);
            desktop.removeContainerListener(taskBar);
        }
        desktop.removePropertyChangeListener(this);
        super.uninstallListeners();
    }

    protected void uninstallDefaults() {
        SeaGlassContext context = getContext(desktop, ENABLED);

        style.uninstallDefaults(context);
        context.dispose();
        style = null;

        if (taskBar != null) {
            for (Component comp : taskBar.getComponents()) {
                JInternalFrame.JDesktopIcon desktopIcon = (JInternalFrame.JDesktopIcon) comp;
                taskBar.remove(desktopIcon);
                desktopIcon.setPreferredSize(null);
                JInternalFrame f = desktopIcon.getInternalFrame();
                if (f.isIcon()) {
                    desktop.add(desktopIcon);
                }
                f.removeComponentListener(taskBar);
            }
            desktop.remove(taskBar);
            taskBar = null;
        }
    }

    protected void installDesktopManager() {
        if (UIManager.getBoolean("InternalFrame.useTaskBar")) {
            desktopManager = oldDesktopManager = desktop.getDesktopManager();
            if (!(desktopManager instanceof SeaGlassDesktopManager)) {
                desktopManager = new SeaGlassDesktopManager();
                desktop.setDesktopManager(desktopManager);
            }
        } else {
            super.installDesktopManager();
        }
    }

    protected void uninstallDesktopManager() {
        if (oldDesktopManager != null && !(oldDesktopManager instanceof UIResource)) {
            desktopManager = desktop.getDesktopManager();
            if (desktopManager == null || desktopManager instanceof UIResource) {
                desktop.setDesktopManager(oldDesktopManager);
            }
        }
        oldDesktopManager = null;
        super.uninstallDesktopManager();
    }

    static class TaskBar extends JPanel implements ComponentListener, ContainerListener {
        TaskBar() {
            setOpaque(true);
            setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0) {
                public void layoutContainer(Container target) {
                    // First shrink buttons to fit
                    Component[] comps = target.getComponents();
                    int n = comps.length;
                    if (n > 0) {
                        // Start with the largest preferred width
                        int prefWidth = 0;
                        for (Component c : comps) {
                            c.setPreferredSize(null);
                            Dimension prefSize = c.getPreferredSize();
                            if (prefSize.width > prefWidth) {
                                prefWidth = prefSize.width;
                            }
                        }
                        // Shrink equally to fit if needed
                        Insets insets = target.getInsets();
                        int tw = target.getWidth() - insets.left - insets.right;
                        int w = Math.min(prefWidth, Math.max(10, tw / n));
                        for (Component c : comps) {
                            Dimension prefSize = c.getPreferredSize();
                            c.setPreferredSize(new Dimension(w, prefSize.height));
                        }
                    }
                    super.layoutContainer(target);
                }
            });

            // PENDING: This should be handled by the painter
            setBorder(new BevelBorder(BevelBorder.RAISED) {
                protected void paintRaisedBevel(Component c, Graphics g, int x, int y, int w, int h) {
                    Color oldColor = g.getColor();
                    g.translate(x, y);
                    g.setColor(getHighlightOuterColor(c));
                    g.drawLine(0, 0, 0, h - 2);
                    g.drawLine(1, 0, w - 2, 0);
                    g.setColor(getShadowOuterColor(c));
                    g.drawLine(0, h - 1, w - 1, h - 1);
                    g.drawLine(w - 1, 0, w - 1, h - 2);
                    g.translate(-x, -y);
                    g.setColor(oldColor);
                }
            });
        }

        void adjustSize() {
            JDesktopPane desktop = (JDesktopPane) getParent();
            if (desktop != null) {
                int height = getPreferredSize().height;
                Insets insets = getInsets();
                if (height == insets.top + insets.bottom) {
                    if (getHeight() <= height) {
                        // Initial size, because we have no buttons yet
                        height += 21;
                    } else {
                        // We already have a good height
                        height = getHeight();
                    }
                }
                setBounds(0, desktop.getHeight() - height, desktop.getWidth(), height);
                revalidate();
                repaint();
            }
        }

        // ComponentListener interface

        public void componentResized(ComponentEvent e) {
            if (e.getSource() instanceof JDesktopPane) {
                adjustSize();
            }
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
            if (e.getSource() instanceof JInternalFrame) {
                adjustSize();
            }
        }

        public void componentHidden(ComponentEvent e) {
            if (e.getSource() instanceof JInternalFrame) {
                ((JInternalFrame) e.getSource()).getDesktopIcon().setVisible(false);
                revalidate();
            }
        }

        // ContainerListener interface

        public void componentAdded(ContainerEvent e) {
            if (e.getChild() instanceof JInternalFrame) {
                JInternalFrame f = (JInternalFrame) e.getChild();
                JInternalFrame.JDesktopIcon desktopIcon = f.getDesktopIcon();
                for (Component comp : getComponents()) {
                    if (comp == desktopIcon) {
                        // We have it already
                        return;
                    }
                }
                add(desktopIcon);
                f.addComponentListener(this);
                if (getComponentCount() == 1) {
                    adjustSize();
                }
            }
        }

        public void componentRemoved(ContainerEvent e) {
            if (e.getChild() instanceof JInternalFrame) {
                JInternalFrame f = (JInternalFrame) e.getChild();
                if (!f.isIcon()) {
                    // Frame was removed without using setClosed(true)
                    remove(f.getDesktopIcon());
                    f.removeComponentListener(this);
                    revalidate();
                    repaint();
                }
            }
        }
    }

    class SeaGlassDesktopManager extends DefaultDesktopManager implements UIResource {

        public void maximizeFrame(JInternalFrame f) {
            if (f.isIcon()) {
                try {
                    f.setIcon(false);
                } catch (PropertyVetoException e2) {
                }
            } else {
                f.setNormalBounds(f.getBounds());
                Component desktop = f.getParent();
                setBoundsForFrame(f, 0, 0, desktop.getWidth(), desktop.getHeight() - taskBar.getHeight());
            }

            try {
                f.setSelected(true);
            } catch (PropertyVetoException e2) {
            }
        }

        public void iconifyFrame(JInternalFrame f) {
            Container c = f.getParent();
            boolean findNext = f.isSelected();

            if (c == null) {
                return;
            }

            if (!f.isMaximum()) {
                f.setNormalBounds(f.getBounds());
            }
            c.remove(f);
            c.repaint(f.getX(), f.getY(), f.getWidth(), f.getHeight());
            try {
                f.setSelected(false);
            } catch (PropertyVetoException e2) {
            }

            // Get topmost of the remaining frames
            if (findNext) {
                for (Component comp : c.getComponents()) {
                    if (comp instanceof JInternalFrame) {
                        try {
                            ((JInternalFrame) comp).setSelected(true);
                        } catch (PropertyVetoException e2) {
                        }
                        ((JInternalFrame) comp).moveToFront();
                        return;
                    }
                }
            }
        }

        public void deiconifyFrame(JInternalFrame f) {
            JInternalFrame.JDesktopIcon desktopIcon = f.getDesktopIcon();
            Container c = desktopIcon.getParent();
            if (c != null) {
                c = c.getParent();
                if (c != null) {
                    c.add(f);
                    if (f.isMaximum()) {
                        int w = c.getWidth();
                        int h = c.getHeight() - taskBar.getHeight();
                        if (f.getWidth() != w || f.getHeight() != h) {
                            setBoundsForFrame(f, 0, 0, w, h);
                        }
                    }
                    if (f.isSelected()) {
                        f.moveToFront();
                    } else {
                        try {
                            f.setSelected(true);
                        } catch (PropertyVetoException e2) {
                        }
                    }
                }
            }
        }

        protected void removeIconFor(JInternalFrame f) {
            super.removeIconFor(f);
            taskBar.validate();
        }

        public void setBoundsForFrame(JComponent f, int newX, int newY, int newWidth, int newHeight) {
            super.setBoundsForFrame(f, newX, newY, newWidth, newHeight);
            if (taskBar != null && newY >= taskBar.getY()) {
                f.setLocation(f.getX(), taskBar.getY() - f.getInsets().top);
            }
        }
    }

    public SeaGlassContext getContext(JComponent c) {
        return getContext(c, getComponentState(c));
    }

    private SeaGlassContext getContext(JComponent c, int state) {
        return SeaGlassContext.getContext(SeaGlassContext.class, c, SeaGlassLookAndFeel.getRegion(c), style, state);
    }

    private int getComponentState(JComponent c) {
        return SeaGlassLookAndFeel.getComponentState(c);
    }

    public void update(Graphics g, JComponent c) {
        SeaGlassContext context = getContext(c);

        SeaGlassLookAndFeel.update(context, g);
        context.getPainter().paintDesktopPaneBackground(context, g, 0, 0, c.getWidth(), c.getHeight());
        paint(context, g);
        context.dispose();
    }

    public void paint(Graphics g, JComponent c) {
        SeaGlassContext context = getContext(c);

        paint(context, g);
        context.dispose();
    }

    protected void paint(SeaGlassContext context, Graphics g) {
    }

    public void paintBorder(SynthContext context, Graphics g, int x, int y, int w, int h) {
        ((SeaGlassContext) context).getPainter().paintDesktopPaneBorder(context, g, x, y, w, h);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (SeaGlassLookAndFeel.shouldUpdateStyle(evt)) {
            updateStyle((JDesktopPane) evt.getSource());
        }
        if (evt.getPropertyName() == "ancestor" && taskBar != null) {
            taskBar.adjustSize();
        }
    }
}
