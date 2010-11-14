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

import com.magicpwd.__i.IEditItem;
import com.magicpwd.__i.mwiz.IMwizBean;
import com.magicpwd._bean.ADataBean;
import com.magicpwd._comp.WButtonGroup;
import com.magicpwd.v.mwiz.NormPtn;

/**
 *
 * @author Amon
 */
public class DataBean extends ADataBean implements IMwizBean
{

    public DataBean(NormPtn normPtn)
    {
        super(normPtn);
    }

    @Override
    public void initView()
    {
    }

    @Override
    public void initLang()
    {
        initConfLang();
    }

    @Override
    public void initData()
    {
        initConfData();
    }

    @Override
    public void showData(IEditItem itemData)
    {
        this.itemData = itemData;

        tf_PropData.setText(itemData.getData());

        showConfData();
    }

    @Override
    public void setLabelFor(javax.swing.JLabel label)
    {
        if (label != null)
        {
            label.setLabelFor(tf_PropData);
        }
    }

    @Override
    public javax.swing.JComponent getComponent()
    {
        return this;
    }

    @Override
    public boolean saveData()
    {
        saveName();
        return processData();
    }

    @Override
    public void requestFocus()
    {
        tf_PropData.requestFocus();
    }
}
