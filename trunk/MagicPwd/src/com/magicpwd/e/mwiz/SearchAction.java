/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mwiz;

import com.magicpwd.__a.mwiz.AMwizAction;

/**
 *
 * @author Amon
 */
public class SearchAction extends AMwizAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (e.getSource() instanceof javax.swing.AbstractButton)
        {
            javax.swing.AbstractButton button = (javax.swing.AbstractButton) e.getSource();
            normPtn.setFindVisible(button.isSelected());
        }
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }
}
