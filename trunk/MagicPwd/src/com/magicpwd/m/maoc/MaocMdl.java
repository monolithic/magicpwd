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
package com.magicpwd.m.maoc;

import com.magicpwd.m.UserMdl;
import java.text.DecimalFormat;

/**
 * Application: MagicPwd
 * Author     : Amon
 * Encoding   : UTF-8
 * Created    : 2010-12-14 16:13:15
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class MaocMdl
{

    private UserMdl userMdl;
    private MnumMdl mnumMdl;
    private MfunMdl mfunMdl;
    private MexpMdl mexpMdl;
    private DecimalFormat format;

    public MaocMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void init()
    {
        mnumMdl = new MnumMdl(this);
        mfunMdl = new MfunMdl(this);
        mexpMdl = new MexpMdl(this);
        format = new DecimalFormat();
    }

    /**
     * @return the mnumMdl
     */
    public MnumMdl getMnumMdl()
    {
        return mnumMdl;
    }

    /**
     * @return the mfunMdl
     */
    public MfunMdl getMfunMdl()
    {
        return mfunMdl;
    }

    /**
     * @return the gridMdl
     */
    public MexpMdl getMexpMdl()
    {
        return mexpMdl;
    }

    public DecimalFormat getFormat()
    {
        return format;
    }

    public void setPrecision(int precision)
    {
        format.setMaximumFractionDigits(precision);
    }
}