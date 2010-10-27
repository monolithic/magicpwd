/*
 *  Copyright (C) 2010 Amon
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd._bean.mwiz;

import com.magicpwd.__i.mwiz.IMwizBean;
import com.magicpwd._bean.AListBean;
import com.magicpwd.m.mwiz.KeysMdl;
import com.magicpwd.v.mwiz.NormPtn;
import javax.swing.JLabel;

/**
 * Application: MagicPwd
 * Author     : Amon
 * Encoding   : UTF-8
 * Created    : 2010-10-27 21:28:18
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class ListBean extends AListBean implements IMwizBean
{

    public ListBean(NormPtn normPtn)
    {
        super(normPtn);
    }

    @Override
    public void showData(KeysMdl keysMdl)
    {
    }

    @Override
    public void setLabelFor(JLabel label)
    {
    }

    @Override
    public void initView()
    {
    }

    @Override
    public void initLang()
    {
    }

    @Override
    public void initData()
    {
    }
}
