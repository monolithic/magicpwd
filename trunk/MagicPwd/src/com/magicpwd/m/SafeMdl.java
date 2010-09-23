/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;

/**
 *
 * @author Amon
 */
public final class SafeMdl
{

    private javax.crypto.Cipher dCipher;
    private javax.crypto.Cipher eCipher;
    private SafeKey safeKey;

    public SafeMdl(UserCfg userCfg)
    {
        safeKey = new SafeKey(userCfg);
    }

    /**
     * @return the dCipher
     */
    public javax.crypto.Cipher getDCipher()
    {
        if (dCipher == null)
        {
            try
            {
                dCipher = javax.crypto.Cipher.getInstance(ConsEnv.NAME_CIPHER);
                dCipher.init(javax.crypto.Cipher.DECRYPT_MODE, getSafeKey());
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                Lang.showMesg(null, LangRes.P30FAA04, "系统错误：系统无法加载密码算法！");
                System.exit(0);
            }
        }
        return dCipher;
    }

    /**
     * @return the eCipher
     */
    public javax.crypto.Cipher getECipher()
    {
        if (eCipher == null)
        {
            try
            {
                eCipher = javax.crypto.Cipher.getInstance(ConsEnv.NAME_CIPHER);
                eCipher.init(javax.crypto.Cipher.ENCRYPT_MODE, getSafeKey());
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                Lang.showMesg(null, LangRes.P30FAA04, "系统错误：系统无法加载密码算法！");
                System.exit(0);
            }
        }
        return eCipher;
    }

    /**
     * 解密处理
     * @param text
     * @throws Exception
     */
    public String deCript(String text) throws Exception
    {
        return new String(getDCipher().doFinal(Util.stringToBytes(text, getSafeKey().getMask())), ConsEnv.FILE_ENCODING);
    }

    /**
     * 对文件进行加密或解密处理
     * @param c 密码算法
     * @param s 来源文件
     * @param d 写入文件
     * @throws Exception
     */
    public final void deCrypt(java.io.File s, java.io.File d) throws Exception
    {
        byte[] buf = new byte[1024];
        java.io.FileInputStream fis = new java.io.FileInputStream(s);
        java.io.FileOutputStream fos = new java.io.FileOutputStream(d);
        int len = fis.read(buf);
        while (len >= 0)
        {
            fos.write(getDCipher().update(buf, 0, len));
            len = fis.read(buf);
        }
        fos.write(getDCipher().doFinal());
        fos.flush();
        fos.close();
        fis.close();
    }

    /**
     * 加密处理
     * @param text
     * @throws Exception
     */
    public String enCrypt(String text) throws Exception
    {
        return Util.bytesToString(getECipher().doFinal(text.getBytes(ConsEnv.FILE_ENCODING)), getSafeKey().getMask());
    }

    /**
     * 对文件进行加密或解密处理
     * @param c 密码算法
     * @param s 来源文件
     * @param d 写入文件
     * @throws Exception
     */
    public final void enCrypt(java.io.File s, java.io.File d) throws Exception
    {
        byte[] buf = new byte[1024];
        java.io.FileInputStream fis = new java.io.FileInputStream(s);
        java.io.FileOutputStream fos = new java.io.FileOutputStream(d);
        int len = fis.read(buf);
        while (len >= 0)
        {
            fos.write(getECipher().update(buf, 0, len));
            len = fis.read(buf);
        }
        fos.write(getECipher().doFinal());
        fos.flush();
        fos.close();
        fis.close();
    }

    /**
     * 用户登录
     * @param userName
     * @param userPwds
     * @param userSalt
     * @throws Exception
     */
    public boolean signIn(String userName, String userPwds) throws Exception
    {
        safeKey.setName(userName);
        safeKey.setPwds(userPwds);
        return safeKey.signIn();
    }

    public boolean signPb(String userName, String userPwds) throws Exception
    {
        safeKey.setName(userName);
        safeKey.setPwds(userPwds);
        return safeKey.signPb();
    }

    /**
     * 修改登录口令
     * @param oldPwds
     * @param userPwds
     * @throws Exception
     */
    public boolean signPk(String oldPwds, String newPwds) throws Exception
    {
        if (safeKey == null)
        {
            return false;
        }
        return safeKey.signPk(oldPwds, newPwds);
    }

    /**
     * 口令找回
     *
     * @param secPwds
     * @return
     * @throws Exception
     */
    public boolean signFp(String usrName, StringBuffer secPwds) throws Exception
    {
        if (safeKey == null)
        {
            return false;
        }
        return safeKey.signFp(usrName, secPwds);
    }

    /**
     * 设置安全口令
     * @param secPwds
     * @return
     * @throws java.lang.Exception
     */
    public boolean signSk(String oldPwds, String secPwds) throws Exception
    {
        if (safeKey == null)
        {
            return false;
        }
        return safeKey.signSk(oldPwds, secPwds);
    }

    /**
     * 用户注销
     * @param userName
     * @param userPwds
     * @throws Exception
     */
    public boolean signOx(String userName, String userPwds)
    {
        return safeKey.signOx();
    }

    /**
     * 用户注册
     * @param userName
     * @param userPwds
     * @return
     * @throws Exception
     */
    public boolean signUp(String userName, String userPwds) throws Exception
    {
        safeKey.setName(userName);
        safeKey.setPwds(userPwds);
        return safeKey.signUp();
    }

    public SafeKey getSafeKey()
    {
        return safeKey;
    }

    public boolean hasSkey()
    {
        return safeKey.hasSkey();
    }
}
