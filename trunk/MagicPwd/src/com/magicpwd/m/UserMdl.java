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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.magicpwd.r.KindTN;
import javax.crypto.Cipher;

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
    private static UserCfg uc_UserCfg;
    private static UserDat ud_UserDat;
    private static UserSec us_UserSec;
    private static GridMdl gridMdl;
    private static ListMdl listMdl;
    private static HintMdl hintMdl;
    private static NoteMdl noteMdl;
    private static TreeMdl treeMdl;
    private static CboxMdl cboxMdl;
    private static boolean charUpd;
    private static CharMdl charMdl;
    private static Cipher dCipher;
    private static Cipher eCipher;

    /**
     * @return the dCipher
     */
    public Cipher getDCipher()
    {
        if (dCipher == null)
        {
            try
            {
                dCipher = Cipher.getInstance(ConsEnv.NAME_CIPHER);
                dCipher.init(Cipher.DECRYPT_MODE, getSec());
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
    public Cipher getECipher()
    {
        if (eCipher == null)
        {
            try
            {
                eCipher = Cipher.getInstance(ConsEnv.NAME_CIPHER);
                eCipher.init(Cipher.ENCRYPT_MODE, getSec());
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

    private UserMdl()
    {
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
        us_UserSec = new UserSec(this);
        hintMdl = new HintMdl(this);
    }

    public void loadUserCfg()
    {
        uc_UserCfg = new UserCfg();
        uc_UserCfg.loadCfg();
    }

    public void saveCfg()
    {
        uc_UserCfg.saveCfg();
    }

    public UserCfg getUserCfg()
    {
        return uc_UserCfg;
    }

    public UserDat getDat()
    {
        return ud_UserDat;
    }

    /**
     * 用户登录
     * @param userName
     * @param userPwds
     * @param userSalt
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws Exception
     */
    public boolean signIn(String userName, String userPwds) throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        us_UserSec.setName(userName);
        us_UserSec.setPwds(userPwds);
        return us_UserSec.signIn();
    }

    public boolean signPb(String userName, String userPwds) throws Exception
    {
        us_UserSec.setName(userName);
        us_UserSec.setPwds(userPwds);
        return us_UserSec.signPb();
    }

    /**
     * 修改登录口令
     * @param oldPwds
     * @param userPwds
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws Exception
     */
    public boolean signPk(String oldPwds, String newPwds) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        if (us_UserSec == null)
        {
            return false;
        }
        return us_UserSec.signPk(oldPwds, newPwds);
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
        if (us_UserSec == null)
        {
            return false;
        }
        return us_UserSec.signFp(usrName, secPwds);
    }

    /**
     * 设置安全口令
     * @param secPwds
     * @return
     * @throws java.lang.Exception
     */
    public boolean signSk(String oldPwds, String secPwds) throws Exception
    {
        if (us_UserSec == null)
        {
            return false;
        }
        return us_UserSec.signSk(oldPwds, secPwds);
    }

    /**
     * 用户注销
     * @param userName
     * @param userPwds
     * @throws Exception
     */
    public boolean signOx(String userName, String userPwds)
    {
        return us_UserSec.signOx();
    }

    /**
     * 用户注册
     * @param userName
     * @param userPwds
     * @return
     * @throws java.security.InvalidKeyException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     */
    public boolean signUp(String userName, String userPwds) throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        us_UserSec.setName(userName);
        us_UserSec.setPwds(userPwds);
        return us_UserSec.signUp();
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
        return us_UserSec;
    }

    public String getUserCode()
    {
        return us_UserSec.getCode();
    }

    public String getUserName()
    {
        return us_UserSec.getName();
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
        return us_UserSec.hasSkey();
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
