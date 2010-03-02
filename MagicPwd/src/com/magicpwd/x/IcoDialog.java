/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.x;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._face.IBackCall;
import com.magicpwd._util.Util;
import com.magicpwd.r.AmonFF;

/**
 *
 * @author amon
 */
public class IcoDialog extends javax.swing.JDialog
{

    private java.io.File icoPath;
    private java.util.Map<String, javax.swing.ImageIcon> iconList;
    private IBackCall backCall;

    public IcoDialog(IBackCall backCall)
    {
        this.backCall = backCall;
    }

    public void initView()
    {
    }

    public void initLang()
    {
    }

    public void initData()
    {
    }

    private synchronized void getIcon()
    {
        icoPath = new java.io.File(ConsEnv.DIR_DAT, ConsEnv.DIR_ICO);
        if (!icoPath.exists())
        {
            icoPath.mkdirs();
        }

        java.io.File[] fileList = icoPath.listFiles(new AmonFF("[0-9a-z]{16}\\.png", false));
        if (fileList == null)
        {
            return;
        }

        for (java.io.File file : fileList)
        {
            iconList.put(file.getName().split("\\.")[0], new javax.swing.ImageIcon(Util.getImage(file.getAbsolutePath())));
        }
    }

    public javax.swing.ImageIcon getLogo(String name)
    {
        if (iconList == null)
        {
            iconList = new java.util.HashMap<String, javax.swing.ImageIcon>();
            getIcon();
        }
        return iconList.get(name);
    }
}
