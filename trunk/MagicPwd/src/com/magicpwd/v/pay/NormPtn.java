package com.magicpwd.v.pay;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
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
public class NormPtn extends javax.swing.JFrame implements MPayEvt, FindEvt
{

    public void initView()
    {
        this.pack();
        this.setIconImage(Bean.getLogo(16));
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

    public void endSave()
    {
    }
}
