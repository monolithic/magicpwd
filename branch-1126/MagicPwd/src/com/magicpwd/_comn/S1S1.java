/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comn;

/**
 *
 * @author amon
 */
public class S1S1 implements java.io.Serializable, Comparable<S1S1>
{

    private String _k;
    private String _v;

    public S1S1()
    {
        this("", "");
    }

    public S1S1(String k, String v)
    {
        this._k = k;
        this._v = v;
    }

    /**
     * @return the _k
     */
    public String getK()
    {
        return _k;
    }

    /**
     * @param k the _k to set
     */
    public void setK(String k)
    {
        this._k = k;
    }

    /**
     * @return the _v
     */
    public String getV()
    {
        return _v;
    }

    /**
     * @param v the _v to set
     */
    public void setV(String v)
    {
        this._v = v;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }

        if (o instanceof S1S1)
        {
            S1S1 t = (S1S1) o;
            if (getK() == null)
            {
                return t.getK() == null;
            }
            return getK().equals(t.getK());
        }

        return getK().equals(o);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 29 * hash + (this._k != null ? this._k.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return getV();
    }

	@Override
	public int compareTo(S1S1 o)
    {
		if (o == null)
        {
			return 1;
		}
		return this.getK().compareTo(o.getK());
	}
}
