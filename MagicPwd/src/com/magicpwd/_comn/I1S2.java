/**
 * 
 */
package com.magicpwd._comn;

/**
 * @author Amon
 */
public class I1S2
{

    /** 记录类别 */
    private int i;
    /** 记录名称 */
    private String k;
    /** 记录内容 */
    private String v;

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
        this.i = i;
        k = "";
        v = "";
    }

    /**
     * @param i
     * @param k
     * @param v
     */
    public I1S2(int i, String k, String v)
    {
        this.i = i;
        this.k = k;
        this.v = v;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (obj instanceof I1S2)
        {
            I1S2 tmp = (I1S2) obj;
            return k.equals(tmp.getK());
        }
        return k.equals(obj);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 73 * hash + (this.k != null ? this.k.hashCode() : 0);
        return hash;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return v;
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

    /**
     * @return the k
     */
    public String getK()
    {
        return k;
    }

    /**
     * @param k
     *            the k to set
     */
    public void setK(String k)
    {
        this.k = k;
    }

    /**
     * @return the v
     */
    public String getV()
    {
        return v;
    }

    /**
     * @param v
     *            the v to set
     */
    public void setV(String v)
    {
        this.v = v;
    }
}
