/*
 *  Copyright (C) 2010 Amon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.m;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._enum.AppView;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Logs;

/**
 *
 * @author Amon
 */
public final class UserMdl
{

    private MpwdMdl mpwdMdl;
    private java.util.Properties userCfg;
    private boolean incBack = true;
    private boolean topMost;
    private TpltMdl tpltMdl;
    private CharMdl charMdl;
    private HintMdl hintMdl;
    static SafeKey safeKey;

    public UserMdl()
    {
    }

    public void loadCfg()
    {
        userCfg = new java.util.Properties();
        java.io.FileInputStream fis = null;
        try
        {
            java.io.File file = new java.io.File(ConsEnv.DIR_DAT, ConsEnv.FILE_DATA + ".config");
            if (file.exists() && file.canRead())
            {
                fis = new java.io.FileInputStream(file);
                userCfg.load(fis);
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            Bean.closeStream(fis);
        }

        safeKey = new SafeKey(this);
    }

    public void loadDef()
    {
        userCfg.clear();
        incBack = true;
        topMost = false;
    }

    public void saveCfg()
    {
        java.io.FileOutputStream fos = null;
        try
        {
            fos = new java.io.FileOutputStream(new java.io.File(ConsEnv.DIR_DAT, ConsEnv.FILE_DATA + ".config"));
            userCfg.store(fos, "MagicPwd User Configure File!");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            Bean.closeStream(fos);
        }
    }

    public String getCfg(String key)
    {
        return getCfg(key, null);
    }

    public String getCfg(String key, String def)
    {
        if (!Char.isValidate(key))
        {
            return def;
        }
        return userCfg.getProperty(Char.format(key, safeKey.getName()), def);
    }

    public void setCfg(String key, String value)
    {
        if (Char.isValidate(key))
        {
            userCfg.setProperty(Char.format(key, safeKey.getName()), value);
        }
    }

    /**
     * @return the menuViw
     */
    public boolean isMenuVisible()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_MENU, ConsCfg.DEF_TRUE));
    }

    /**
     * @param visible
     *            the menuViw to set
     */
    public void setMenuVisible(boolean visible)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_MENU, visible ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    /**
     * @return the toolViw
     */
    public boolean isToolVisible(AppView view)
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(Char.format(ConsCfg.CFG_VIEW_TOOL, view.name())));
    }

    /**
     * @param visible
     *            the toolViw to set
     */
    public void setToolVisible(AppView view, boolean visible)
    {
        userCfg.setProperty(Char.format(ConsCfg.CFG_VIEW_TOOL, view.name()), visible ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    /**
     * @return the infoViw
     */
    public boolean isInfoVisible()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_INFO, ConsCfg.DEF_TRUE));
    }

    /**
     * @param visible
     *            the infoViw to set
     */
    public void setInfoVisible(boolean visible)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_INFO, visible ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    /**
     * @return the findViw
     */
    public boolean isFindVisible()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_FIND, ConsCfg.DEF_TRUE));
    }

    /**
     * @param visible
     *            the findViw to set
     */
    public void setFindVisible(boolean visible)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_FIND, visible ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    /**
     * @return the toolLoc
     */
    public String getToolLoc(AppView view)
    {
        return userCfg.getProperty(Char.format(ConsCfg.CFG_VIEW_TOOL_LOC, view.name()), "North");
    }

    /**
     * @param toolLoc
     *            the toolLoc to set
     */
    public void setToolLoc(AppView view, String toolLoc)
    {
        userCfg.setProperty(Char.format(ConsCfg.CFG_VIEW_TOOL_LOC, view.name()), toolLoc);
    }

    /**
     * @return the clipDlt
     */
    public int getClipDlt()
    {
        return Integer.parseInt(userCfg.getProperty(ConsCfg.CFG_SAFE_CLIP_DLT, ConsCfg.DEF_CLIP_DLT));
    }

    /**
     * @param clipDlt
     *            the clipDlt to set
     */
    public void setClipDlt(int clipDlt)
    {
        userCfg.setProperty(ConsCfg.CFG_SAFE_CLIP_DLT, Integer.toString(clipDlt));
    }

    /**
     * @return the pwdsLen
     */
    public String getPwdsLen()
    {
        return userCfg.getProperty(ConsCfg.CFG_PWDS_SIZE, ConsCfg.DEF_PWDS_SIZE);
    }

    /**
     * @param pwdsLen
     *            the pwdsLen to set
     */
    public void setPwdsLen(int pwdsLen)
    {
        userCfg.setProperty(ConsCfg.CFG_PWDS_SIZE, "" + pwdsLen);
    }

    /**
     * @return the pwdsKey
     */
    public String getPwdsKey()
    {
        return userCfg.getProperty(ConsCfg.CFG_PWDS_HASH, ConsCfg.DEF_PWDS_HASH);
    }

    /**
     * @param pwdsKey
     *            the pwdsKey to set
     */
    public void setPwdsKey(String pwdsKey)
    {
        userCfg.setProperty(ConsCfg.CFG_PWDS_HASH, pwdsKey);
    }

    public int getHintInt()
    {
        String txt = userCfg.getProperty(ConsCfg.CFG_HINT_INT, "60");
        if (Char.isValidatePositiveInteger(txt))
        {
            return Integer.parseInt(txt);
        }
        return 60;
    }

    public void setHintInt(int hintInt)
    {
        userCfg.setProperty(ConsCfg.CFG_HINT_INT, "" + hintInt);
    }

    public int getHintPre()
    {
        String txt = userCfg.getProperty(ConsCfg.CFG_HINT_PRE, "300");
        if (Char.isValidatePositiveInteger(txt))
        {
            return Integer.parseInt(txt);
        }
        return 300;
    }

    public void setHintPre(int hintPre)
    {
        userCfg.setProperty(ConsCfg.CFG_HINT_PRE, "" + hintPre);
    }

    public String getDataDir()
    {
        return userCfg.getProperty(ConsCfg.CFG_SAFE_DATA_DIR, ConsCfg.DEF_DATA_PATH);
    }

    public void setDataDir(String dataDir)
    {
        userCfg.setProperty(ConsCfg.CFG_SAFE_DATA_DIR, dataDir);
    }

    /**
     * @return the bakNum
     */
    public int getDumpCnt()
    {
        String txt = userCfg.getProperty(ConsCfg.CFG_SAFE_DUMP_CNT, "3");
        if (Char.isValidatePositiveInteger(txt))
        {
            return Integer.parseInt(txt);
        }
        return 3;
    }

    /**
     * @param dumpCnt
     *            the dumpCnt to set
     */
    public void setDumpCnt(int dumpCnt)
    {
        userCfg.setProperty(ConsCfg.CFG_SAFE_DUMP_CNT, "" + dumpCnt);
    }

    /**
     * @return the bakDir
     */
    public String getDumpDir()
    {
        return userCfg.getProperty(ConsCfg.CFG_SAFE_DUMP_DIR, ConsCfg.DEF_DUMP_PATH);
    }

    /**
     * @param dumpDir
     *            the backDir to set
     */
    public void setDumpDir(String dumpDir)
    {
        userCfg.setProperty(ConsCfg.CFG_SAFE_DUMP_DIR, dumpDir);
    }

    /**
     * @return the editViw
     */
    public boolean isEditVisible()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_EDIT_VIW, ConsCfg.DEF_TRUE));
    }

    /**
     * @param editViw
     *            the editViw to set
     */
    public void setEditVisible(boolean editViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_EDIT_VIW, editViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    public boolean isEditIsolate()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_EDIT_WND, ConsCfg.DEF_FALSE));
    }

    /**
     * @param isolate
     *            the editWnd to set
     */
    public void setEditIsolate(boolean isolate)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_EDIT_WND, isolate ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    public String getPwdsLoop()
    {
        return userCfg.getProperty(ConsCfg.CFG_PWDS_LOOP, ConsCfg.DEF_TRUE);
    }

    /**
     * @return the pwdUpt
     */
    public boolean isPwdsLoop()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(getPwdsLoop());
    }

    /**
     * @param pwdsLoop
     *            the pwdsUpt to set
     */
    public void setPwdsLoop(boolean pwdsLoop)
    {
        userCfg.setProperty(ConsCfg.CFG_PWDS_LOOP, pwdsLoop ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    public String getSkin()
    {
        return userCfg.getProperty(ConsCfg.CFG_SKIN, ConsCfg.DEF_SKIN_DEF);
    }

    public void setSkin(String skin)
    {
        userCfg.setProperty(ConsCfg.CFG_SKIN, skin);
    }

    public String getLook()
    {
        return userCfg.getProperty(ConsCfg.CFG_SKIN_LOOK, "jgoodies.Plastic3D");
        //return userCfg.getProperty(ConsCfg.CFG_SKIN_LOOK, ConsCfg.DEF_SKIN_LOOK_SYS);
    }

    public void setLook(String look)
    {
        userCfg.setProperty(ConsCfg.CFG_SKIN_LOOK, look);
    }

    public String getFeel()
    {
        return userCfg.getProperty(ConsCfg.CFG_SKIN_FEEL, ConsCfg.DEF_SKIN_FEEL_DEF);
    }

    public void setFeel(String feel)
    {
        userCfg.setProperty(ConsCfg.CFG_SKIN_FEEL, feel);
    }

    public String getLang()
    {
        String lang = userCfg.getProperty(ConsCfg.CFG_LANG);
        if (!Char.isValidate(lang))
        {
            lang = System.getProperty("user.language");//Locale.getDefault().getLanguage();
            String country = System.getProperty("user.country");//Locale.getDefault().getCountry();
            if (Char.isValidate(country))
            {
                lang += '_' + country;
            }
        }
        return lang;
    }

    public void setLang(String lang)
    {
        setCfg(ConsCfg.CFG_LANG, lang);
    }

    public String getCode()
    {
        return getCfg(ConsCfg.CFG_USER_CODE, "");
    }

    public String getName()
    {
        return getCfg(ConsCfg.CFG_USER_NAME, "");
    }

    public javax.swing.ImageIcon readIcon(String path)
    {
        return Bean.readIcon(path.replace(ConsEnv.FEEL_ARGS, getFeel()));
    }

    public java.awt.image.BufferedImage readImage(String path)
    {
        java.io.File file = new java.io.File(path.replace(ConsEnv.FEEL_ARGS, getFeel()));
        if (!file.exists() || !file.isFile() || !file.canRead())
        {
            return null;
        }

        try
        {
            return javax.imageio.ImageIO.read(file);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return null;
        }
    }

    /**
     * @return the incBack
     */
    public boolean isIncBack()
    {
        return incBack;
    }

    /**
     * @param incBack the incBack to set
     */
    public void setIncBack(boolean incBack)
    {
        this.incBack = incBack;
    }

    /**
     * @return the viewTop
     */
    public boolean isTopMost()
    {
        return topMost;
    }

    /**
     * @param topMost
     *            the viewTop to set
     */
    public void setTopMost(boolean topMost)
    {
        this.topMost = topMost;
    }

    /**
     * @return the tpltMdl
     */
    public TpltMdl getTpltMdl()
    {
        if (tpltMdl == null)
        {
            tpltMdl = new TpltMdl();
            tpltMdl.initData();
        }
        return tpltMdl;
    }

    /**
     * @return the charMdl
     */
    public CharMdl getCharMdl()
    {
        if (charMdl == null)
        {
            charMdl = new CharMdl();
            charMdl.initData();
        }
        return charMdl;
    }

    /**
     * @return the hintMdl
     */
    public HintMdl getHintMdl()
    {
        if (hintMdl == null)
        {
            hintMdl = new HintMdl(this);
            hintMdl.initData();
        }
        return hintMdl;
    }

    /**
     * @return the userName
     */
    public String getUserName()
    {
        return safeKey.getName();
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

    public boolean signLs(String userPwds) throws Exception
    {
        safeKey.setPwds(userPwds);
        return safeKey.signIn();
    }

    public boolean signRs(String userName, String userPwds) throws Exception
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

    public boolean hasSkey()
    {
        return safeKey.hasSkey();
    }

    public int getTrayHintCnt()
    {
        String txt = userCfg.getProperty(ConsCfg.CFG_TRAY_HINT_CNT);
        if (Char.isValidatePositiveInteger(txt))
        {
            return Integer.parseInt(txt);
        }
        return -1;
    }

    public void setTrayHintCnt(int cnt)
    {
        userCfg.setProperty(ConsCfg.CFG_TRAY_HINT_CNT, "" + cnt);
    }

    /**
     * @return the mpwdMdl
     */
    public MpwdMdl getMpwdMdl()
    {
        return mpwdMdl;
    }

    /**
     * @param mpwdMdl the mpwdMdl to set
     */
    public void setMpwdMdl(MpwdMdl mpwdMdl)
    {
        this.mpwdMdl = mpwdMdl;
    }
}
