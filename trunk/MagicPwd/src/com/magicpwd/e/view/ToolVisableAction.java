/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.view;

/**
 *
 * @author Administrator
 */
public class ToolVisableAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        boolean b = !coreMdl.getUserCfg().isToolViw();
        mainTool.setVisible(b);
        this.pack();

        mainMenu.setViewToolSelected(b);
        coreMdl.getUserCfg().setToolViw(b);
    }
}
