/**
 * 
 */
package com.magicpwd.m;

import com.magicpwd._comn.Kind;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.r.KindTN;

/**
 * @author Amon
 * 
 */
public final class UserMdl
{

    /**
     * 程序运行模式
     */
    private static int runMode;
    private static boolean charUpd;
    private static UserCfg userCfg;
    private static UserSec userSec;
    private static GridMdl gridMdl;
    private static ListMdl listMdl;
    private static HintMdl hintMdl;
    private static NoteMdl noteMdl;
    private static TreeMdl treeMdl;
    private static CboxMdl cboxMdl;
    private static CharMdl charMdl;
    private static javax.crypto.Cipher dCipher;
    private static javax.crypto.Cipher eCipher;

    private UserMdl()
    {
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
                dCipher.init(javax.crypto.Cipher.DECRYPT_MODE, getSec());
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
                eCipher.init(javax.crypto.Cipher.ENCRYPT_MODE, getSec());
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
     * 获取运行模式
     * @return
     */
    public int getRunMode()
    {
        return runMode;
    }

    /**
     * 设置运行模式
     * @param runMode
     */
    public void setRunMode(int runMode)
    {
        UserMdl.runMode = runMode;
    }

    public void preLoad()
    {
        gridMdl = new GridMdl(this);
        listMdl = new ListMdl();
        Kind kind = new Kind();
        kind.setC2010103(ConsDat.HASH_ROOT);
        kind.setC2010105("魔方密码");
        kind.setC2010106("魔方密码");
        treeMdl = new TreeMdl(new KindTN(kind));
        userSec = new UserSec(this);
        hintMdl = new HintMdl(this);
    }

    public void loadUserCfg()
    {
        userCfg = new UserCfg();
        userCfg.loadCfg();
    }

    public void saveCfg()
    {
        userCfg.saveCfg();
    }

    public UserCfg getUserCfg()
    {
        return userCfg;
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
        userSec.setName(userName);
        userSec.setPwds(userPwds);
        return userSec.signIn();
    }

    public boolean signPb(String userName, String userPwds) throws Exception
    {
        userSec.setName(userName);
        userSec.setPwds(userPwds);
        return userSec.signPb();
    }

    /**
     * 修改登录口令
     * @param oldPwds
     * @param userPwds
     * @throws Exception
     */
    public boolean signPk(String oldPwds, String newPwds) throws Exception
    {
        if (userSec == null)
        {
            return false;
        }
        return userSec.signPk(oldPwds, newPwds);
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
        if (userSec == null)
        {
            return false;
        }
        return userSec.signFp(usrName, secPwds);
    }

    /**
     * 设置安全口令
     * @param secPwds
     * @return
     * @throws java.lang.Exception
     */
    public boolean signSk(String oldPwds, String secPwds) throws Exception
    {
        if (userSec == null)
        {
            return false;
        }
        return userSec.signSk(oldPwds, secPwds);
    }

    /**
     * 用户注销
     * @param userName
     * @param userPwds
     * @throws Exception
     */
    public boolean signOx(String userName, String userPwds)
    {
        return userSec.signOx();
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
        userSec.setName(userName);
        userSec.setPwds(userPwds);
        return userSec.signUp();
    }

    /**
     * @return the gridMdl
     */
    public GridMdl getGridMdl()
    {
        return gridMdl;
    }

    /**
     * @return the listMdl
     */
    public ListMdl getListMdl()
    {
        return listMdl;
    }

    /**
     * @return
     */
    public NoteMdl getNoteMdl()
    {
        if (noteMdl == null)
        {
            noteMdl = new NoteMdl(this);
        }
        return noteMdl;
    }

    /**
     * @return the treeMdl
     */
    public TreeMdl getTreeMdl()
    {
        return treeMdl;
    }

    /**
     * @return
     */
    public CboxMdl getCboxMdl()
    {
        if (cboxMdl == null)
        {
            cboxMdl = new CboxMdl();
            cboxMdl.initData();
        }
        return cboxMdl;
    }

    /**
     * @return the us_UserSec
     */
    UserSec getSec()
    {
        return userSec;
    }

    public String getUserCode()
    {
        return userSec.getCode();
    }

    public String getUserName()
    {
        return userSec.getName();
    }

    /**
     * @return the charMdl
     */
    public CharMdl getCharMdl()
    {
        if (charMdl == null)
        {
            charMdl = new CharMdl();
        }
        return charMdl;
    }

    /**
     * @return the charUpd
     */
    public boolean isCharUpd()
    {
        return charUpd;
    }

    /**
     * @param charUpd
     *            the charUpd to set
     */
    public void setCharUpd(boolean charUpd)
    {
        UserMdl.charUpd = charUpd;
    }

    public boolean hasSkey()
    {
        return userSec.hasSkey();
    }

    /**
     * @return the hintMdl
     */
    public HintMdl getHintMdl()
    {
        return hintMdl;
    }

    /**
     * @param aHintMdl the hintMdl to set
     */
    public void setHintMdl(HintMdl aHintMdl)
    {
        hintMdl = aHintMdl;
    }
}
