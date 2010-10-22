/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__a;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.m.SafeMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Amon
 */
public abstract class AFrame extends javax.swing.JFrame
{

    protected UserMdl userMdl;
    protected SafeMdl safeMdl;
    private java.util.HashMap<String, javax.swing.Icon> iconMap;

    public AFrame()
    {
        Bean.registerKeyStrokeAction(rootPane, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.CTRL_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK), null, "safe", javax.swing.JComponent.WHEN_FOCUSED);
    }

    /**
     * 用户数据保存
     * @return
     */
    public abstract boolean endSave();

    /**
     * 获取用户偏好图片
     * @param favHash
     * @return
     */
    public javax.swing.Icon getFavIcon(String favHash)
    {
        if (!Char.isValidate(favHash))
        {
            return Bean.getIcon(favHash);
        }

        javax.swing.Icon icon = getIconMap().get(favHash);
        if (icon == null)
        {
            icon = Bean.getIcon(favHash);
        }
        return icon;
    }

    /**
     * 缓存用户偏好图片
     * @param favHash
     * @param favIcon
     */
    public void setFavIcon(String favHash, javax.swing.Icon favIcon)
    {
        if (Char.isValidate(favHash))
        {
            getIconMap().put(favHash, favIcon);
        }
    }

    /**
     * 隐藏窗口
     */
    public void hideFrame()
    {
        setExtendedState(ICONIFIED);
        setVisible(false);
        TrayPtn.getInstance().showTips(Lang.getLang(LangRes.P30F9A01, "友情提示"), Lang.getLang(LangRes.P30F7A43, "魔方密码仍在运行中，您可以通过双击此处显示主窗口！"));
        endSave();
    }

    /**
     * 锁屏窗口
     */
    public void lockFrame()
    {
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e)
    {
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
        {
            if (safeMdl.isModified() && javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(this, LangRes.P30F7A42, "您的数据尚未保存，确认要退出吗？"))
            {
                return;
            }
            TrayPtn.endSave();
        }
        super.processWindowEvent(e);
    }

    protected java.util.HashMap<String, javax.swing.Icon> getIconMap()
    {
        if (iconMap == null)
        {
            iconMap = new java.util.HashMap<String, javax.swing.Icon>();
        }
        return iconMap;
    }

    /**
     * @return the userMdl
     */
    public UserMdl getUserMdl()
    {
        return userMdl;
    }

    /**
     * @param userMdl the userMdl to set
     */
    public void setUserMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }
}
