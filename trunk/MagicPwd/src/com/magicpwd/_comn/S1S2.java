/**
 * 
 */
package com.magicpwd._comn;

/**
 * @author Amon
 * 
 */
public class S1S2
{
    private String _k;
    private String _v1;
    private String _v2;

    public S1S2()
    {
        this("", "", "");
    }

    public S1S2(String k, String v1, String v2)
    {
        _k = k;
        _v1 = v1;
        _v2 = v2;
    }

    public String getK()
    {
        return _k;
    }

    public void setK(String k)
    {
        this._k = k;
    }

    public String getV1()
    {
        return _v1;
    }

    public void setV1(String v1)
    {
        this._v1 = v1;
    }

    public String getV2()
    {
        return _v2;
    }

    public void setV2(String v2)
    {
        this._v2 = v2;
    }

    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }

        if (o instanceof S1S2)
        {
            S1S2 t = (S1S2) o;
            if (_k == null)
            {
                return t.getK() == null;
            }
            return _k.equals(t.getK());
        }

        return _k.equals(o);
    }

    public String toString()
    {
        return _v1;
    }
}