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
package com.magicpwd._comn;

/**
 * @author Amon
 */
public class I1S2 extends S1S1
{

    /** 记录类别 */
    private int i;

    /**
     * 
     */
    public I1S2()
    {
    }

    /**
     * @param i
     */
    public I1S2(int i)
    {
        super("", "");
        this.i = i;
    }

    /**
     * @param i
     * @param k
     * @param v
     */
    public I1S2(int i, String k, String v)
    {
        super(k, v);
        this.i = i;
    }

    /**
     * @return the i
     */
    public int getI()
    {
        return i;
    }

    /**
     * @param i
     *            the i to set
     */
    public void setI(int i)
    {
        this.i = i;
    }

    public void addI(int i)
    {
        this.i += i;
    }
}
