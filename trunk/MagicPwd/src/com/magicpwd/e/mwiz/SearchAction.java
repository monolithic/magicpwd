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
        Object object = e.getSource();
        javax.swing.AbstractButton button;
        if (object instanceof javax.swing.AbstractButton)
        {
            button = (javax.swing.AbstractButton) object;
            selected = button.isSelected();
        }
        else
        {
            button = normPtn.getMenuPtn().getButton("search");
            selected = !button.isSelected();
            button.setSelected(selected);
        }

        normPtn.setFindVisible(selected);
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
