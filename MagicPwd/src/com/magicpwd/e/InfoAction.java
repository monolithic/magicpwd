/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e;

import com.magicpwd.__a.AAction;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd.v.TrayPtn;

/**
 *
 * @author Administrator
 */
public class InfoAction extends AAction
{

    public InfoAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        StringBuilder buf = new StringBuilder();
        buf.append(Lang.getLang(LangRes.P30F7201, "魔方密码")).append("\n");
        buf.append(ConsEnv.VERSIONS).append(" Build ").append(ConsEnv.BUILDER);
        javax.swing.JFrame form = null;
        if (TrayPtn.getCurrForm().isVisible())
        {
            form = TrayPtn.getCurrForm();
        }
        javax.swing.JOptionPane.showMessageDialog(form, buf.toString(), Lang.getLang(LangRes.P30F1208, "关于软件"), javax.swing.JOptionPane.INFORMATION_MESSAGE, new javax.swing.ImageIcon(Bean.getLogo(32)));
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
