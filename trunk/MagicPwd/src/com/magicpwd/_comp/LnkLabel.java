/**
 * 
 */
package com.magicpwd._comp;

import com.magicpwd._util.Desk;
import com.magicpwd._util.Util;

/**
 * @author shangwen.yao
 * 
 */
public class LnkLabel extends javax.swing.JLabel
{

    public LnkLabel()
    {
        setForeground(java.awt.Color.BLUE);
        setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    }

    /**
     * @return the linkUrl
     */
    public String getLinkUrl()
    {
        return linkUrl;
    }

    /**
     * @param linkUrl
     *            the linkUrl to set
     */
    public void setLinkUrl(String linkUrl)
    {
        this.linkUrl = linkUrl;
    }

    /**
     * @param autoOpen
     */
    public void setAutoOpenLink(boolean autoOpen)
    {
        if (autoOpen)
        {
            if (listener == null)
            {
                listener = new java.awt.event.MouseAdapter()
                {

                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt)
                    {
                        openLinkUrl(linkUrl);
                    }
                };
            }
            this.addMouseListener(listener);
        }
        else if (listener != null)
        {
            this.removeMouseListener(listener);
        }
    }

    /**
     * @param linkUrl
     * @return
     */
    public boolean openLinkUrl(String linkUrl)
    {
        if (!com.magicpwd._util.Char.isValidate(linkUrl))
        {
            return false;
        }

        if (linkUrl.toLowerCase().startsWith("mailto:"))
        {
            Desk.mail(linkUrl);
        }
        else
        {
            Desk.browse(linkUrl);
        }
        return true;
    }
    /** 链接地址 */
    private String linkUrl;
    /**  */
    private java.awt.event.MouseListener listener;
}
