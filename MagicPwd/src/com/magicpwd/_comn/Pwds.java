/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._comn;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Util;
import javax.crypto.Cipher;

/**
 * 口令数据
 * @author Amon
 */
public class Pwds
{

    /**
     * 内容索引
     */
    private int P30F0201;
    /**
     * 口令索引
     */
    private String P30F0202;
    /**
     * 口令内容
     */
    private StringBuffer P30F0203 = new StringBuffer();

    public Pwds()
    {
        setDefault();
    }

    public boolean loadPwds()
    {
        return true;
    }

    public boolean savePwds()
    {
        return true;
    }

    public void setDefault()
    {
        P30F0202 = "";
        P30F0203.delete(0, P30F0203.length());
    }

    /**
     * @return the P30F0201
     */
    public int getP30F0201()
    {
        return P30F0201;
    }

    /**
     * @param P30F0201 the P30F0201 to set
     */
    public void setP30F0201(int P30F0201)
    {
        this.P30F0201 = P30F0201;
    }

    /**
     * @return the P30F0202
     */
    public String getP30F0202()
    {
        return P30F0202;
    }

    /**
     * @param P30F0202 the P30F0202 to set
     */
    public void setP30F0202(String P30F0202)
    {
        this.P30F0202 = P30F0202;
    }

    /**
     * 口令内容
     * @return the P30F0203
     */
    public StringBuffer getP30F0203()
    {
        return P30F0203;
    }

    /**
     * 解密处理
     */
    public void deCript(Cipher c, char[] m) throws Exception
    {
        String t = P30F0203.toString();
        P30F0203.delete(0, t.length()).append(new String(c.doFinal(Util.stringToBytes(t, m)), ConsEnv.FILE_ENCODING));
    }

    /**
     * 加密处理
     */
    public void enCrypt(Cipher c, char[] m) throws Exception
    {
        String t = P30F0203.toString();
        P30F0203.delete(0, t.length()).append(Util.bytesToString(c.doFinal(t.getBytes(ConsEnv.FILE_ENCODING)), m));
    }
}
