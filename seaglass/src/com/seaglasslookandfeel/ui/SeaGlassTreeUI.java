package com.seaglasslookandfeel.ui;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTreeUI;

public class SeaGlassTreeUI extends BasicTreeUI {

    public static ComponentUI createUI(JComponent c) {
        return new SeaGlassTreeUI();
    }
}
