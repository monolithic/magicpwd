package com.magicpwd.v;

import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IFormView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.c.FindEvt;
import com.magicpwd.c.MPayEvt;

/**
 * 正常模式：账簿
 * 
 * @author Amon
 * 
 */
public class NormPtn extends javax.swing.JFrame implements IFormView, MPayEvt, FindEvt
{

    public void initView()
    {
        this.pack();
        this.setIconImage(Util.getLogo(16));
        Util.centerForm(this, null);
    }

    public void initLang()
    {
        setTitle(Lang.getLang(LangRes.P30F5201, "全能搜索"));
    }

    public void initData()
    {
    }

    @Override
    public void setVisible(boolean visible)
    {
        super.setVisible(visible);
    }

    @Override
    public javax.swing.JFrame getForm()
    {
        return this;
    }
}
