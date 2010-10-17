/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__a.mpwd;

import com.magicpwd.__i.mpwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;

/**
 *
 * @author Amon
 */
public abstract class APwdAction implements IPwdAction
{

    protected MainPtn mainPtn;
    protected CoreMdl coreMdl;
    protected boolean enabled = true;
    protected boolean visible = true;
    protected boolean selected = false;

    @Override
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener)
    {
    }

    @Override
    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener)
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

    @Override
    public void setMainPtn(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    @Override
    public void setCoreMdl(CoreMdl coreMdl)
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
}