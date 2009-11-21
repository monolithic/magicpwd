package com.magicpwd._comn;

import java.util.Calendar;

public class S1SD extends S1S2
{
    private Calendar _v3;

    public S1SD()
    {
    }

    public S1SD(String k, String v1, String v2, Calendar v3)
    {
        super(k, v1, v2);
        _v3 = v3;
    }

    /**
     * @return the v3
     */
    public Calendar getV3()
    {
        return _v3;
    }

    /**
     * @param v3
     *            the v3 to set
     */
    public void setV3(Calendar v3)
    {
        _v3 = v3;
    }
}
