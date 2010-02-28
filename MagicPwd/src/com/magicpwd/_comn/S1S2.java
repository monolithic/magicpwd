/**
 * 
 */
package com.magicpwd._comn;

/**
 * @author Amon
 * 
 */
public class S1S2 extends S1S1
{

    private String _v2;

    public S1S2()
    {
        this("", "", "");
    }

    public S1S2(String k, String v1, String v2)
    {
        super(k, v1);
        _v2 = v2;
    }

    public String getV2()
    {
        return _v2;
    }

    public void setV2(String v2)
    {
        this._v2 = v2;
    }
}
