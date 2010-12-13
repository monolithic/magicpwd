package com.magicpwd.v.maoc;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;

/**
 * 代数运算
 * 
 * @author Amon
 * 
 */
public class MaocPtn extends javax.swing.JFrame
{

    public void initView()
    {
        this.pack();
        this.setIconImage(Bean.getLogo(16));
        Bean.centerForm(this, null);
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
