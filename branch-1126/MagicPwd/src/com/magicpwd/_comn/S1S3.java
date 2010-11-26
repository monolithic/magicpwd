/**
 * 
 */
package com.magicpwd._comn;

/**
 * @author Amon
 * 
 */
public class S1S3 extends S1S2
{
    private String _v3;

    public S1S3()
    {
    }

    public S1S3(String k, String v1, String v2, String v3)
    {
        super(k, v1, v2);
        _v3 = v3;
    }

    /**
     * @return the v3
     */
    public String getV3()
    {
        return _v3;
    }

    /**
     * @param v3
     *            the v3 to set
     */
    public void setV3(String v3)
    {
        _v3 = v3;
    }
}
