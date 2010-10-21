/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__a;

import com.magicpwd.__i.IAction;
import com.magicpwd.m.UserMdl;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Amon
 */
public abstract class AAction implements IAction
{

    protected UserMdl coreMdl;
    protected boolean enabled = true;
    protected boolean visible = true;
    protected boolean selected = false;

    @Override
    public void setCoreMdl(UserMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    @Override
    public boolean isVisible()
    {
        return visible;
    }

    @Override
    public boolean isSelected()
    {
        return selected;
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
    }

    @Override
    public Object getValue(String key)
    {
        return null;
    }

    @Override
    public void putValue(String key, Object value)
    {
    }
}
