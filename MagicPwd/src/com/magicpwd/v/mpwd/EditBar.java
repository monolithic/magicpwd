/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v.mpwd;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;

/**
 *
 * @author aven
 */
public class EditBar extends javax.swing.JPanel
{

    private javax.swing.border.TitledBorder border;

    public EditBar()
    {
    }

    public void initView()
    {
        setLayout(new java.awt.BorderLayout());
        border = javax.swing.BorderFactory.createTitledBorder(Lang.getLang(LangRes.P30F7305, ""));
        setBorder(border);
    }

    public void initLang()
    {
    }

    public void initData()
    {
    }

    public void setPropView(javax.swing.JPanel prop)
    {
        add(prop);
    }

    public void setTitle(String title)
    {
        border.setTitle(title);
    }
}
