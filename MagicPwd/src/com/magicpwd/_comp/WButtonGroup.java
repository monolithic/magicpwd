/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comp;

/**
 *
 * @author Administrator
 */
public class WButtonGroup extends javax.swing.ButtonGroup
{

    private java.util.HashMap<String, javax.swing.ButtonModel> buttonModel;

    public WButtonGroup()
    {
        buttonModel = new java.util.HashMap<String, javax.swing.ButtonModel>();
    }

    public void add(String actionCommand, javax.swing.AbstractButton button)
    {
        buttonModel.put(actionCommand, button.getModel());
        add(button);
    }

    public void setSelected(String actionCommand, boolean selected)
    {
        javax.swing.ButtonModel model = buttonModel.get(actionCommand);
        if (model != null)
        {
            setSelected(model, selected);
        }
    }
}
