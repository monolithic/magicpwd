/**
 * 用户配置信息
 * CopyRight: MagicPwd.com
 * Homepage:http://magicpwd.com/
 * Project:http://magicpwd.dev.java.net/
 * Email:Amon@magicpwd.com
 */
package com.magicpwd.m;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Char;
import com.magicpwd._util.Logs;

/**
 * @author Amon
 * 
 */
public final class UserCfg
{

    private Properties userCfg;
    private boolean viewTop;

    public UserCfg()
    {
    }

    public final void loadCfg()
    {
        userCfg = new Properties();
        try
        {
            File file = new File(ConsEnv.DIR_DAT, ConsEnv.FILE_DATA + ".config");
            if (!file.exists() || !file.canRead())
            {
                return;
            }
            FileInputStream fis = new FileInputStream(file);
            userCfg.load(fis);
            fis.close();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    public final void saveCfg()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(new File(ConsEnv.DIR_DAT, ConsEnv.FILE_DATA + ".config"));
            userCfg.store(fos, "MagicPwd User Configure File!");
            fos.close();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    public final void loadDef()
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_TOPS, ConsCfg.DEF_FALSE);
        userCfg.setProperty(ConsCfg.CFG_VIEW_MENU, ConsCfg.DEF_TRUE);
        userCfg.setProperty(ConsCfg.CFG_VIEW_TOOL, ConsCfg.DEF_FALSE);
        userCfg.setProperty(ConsCfg.CFG_VIEW_INFO, ConsCfg.DEF_FALSE);
        userCfg.setProperty(ConsCfg.CFG_VIEW_FIND, ConsCfg.DEF_TRUE);
        userCfg.setProperty(ConsCfg.CFG_VIEW_TOOL_LOC, "North");

        userCfg.setProperty(ConsCfg.CFG_STAY_TIME, ConsCfg.DEF_STAY_TIME);

        userCfg.setProperty(ConsCfg.CFG_PWDS_SIZE, ConsCfg.DEF_PWDS_SIZE);
        userCfg.setProperty(ConsCfg.CFG_PWDS_HASH, ConsCfg.DEF_PWDS_HASH);

        userCfg.setProperty(ConsCfg.CFG_BACK_SIZE, "3");
        userCfg.setProperty(ConsCfg.CFG_BACK_PATH, ConsEnv.DIR_BAK);
    }

    public final String getCfg(String key)
    {
        return userCfg.getProperty(key);
    }

    public final String getCfg(String key, String def)
    {
        return userCfg.getProperty(key, def);
    }

    public final void setCfg(String key, String value)
    {
        userCfg.setProperty(key, value);
    }

    /**
     * @return the viewTop
     */
    public final boolean isViewTop()
    {
        return viewTop;
    }

    /**
     * @param viewTop
     *            the viewTop to set
     */
    public final void setViewTop(boolean viewTop)
    {
        this.viewTop = viewTop;
    }

    /**
     * @return the menuViw
     */
    public final boolean isMenuVisible()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_MENU, ConsCfg.DEF_TRUE));
    }

    /**
     * @param menuViw
     *            the menuViw to set
     */
    public final void setMenuViw(boolean menuViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_MENU, menuViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    /**
     * @return the toolViw
     */
    public final boolean isToolVisible()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_TOOL));
    }

    /**
     * @param toolViw
     *            the toolViw to set
     */
    public final void setToolViw(boolean toolViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_TOOL, toolViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    /**
     * @return the infoViw
     */
    public final boolean isInfoVisible()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_INFO, ConsCfg.DEF_TRUE));
    }

    /**
     * @param infoViw
     *            the infoViw to set
     */
    public final void setInfoViw(boolean infoViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_INFO, infoViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    /**
     * @return the findViw
     */
    public final boolean isFindVisible()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_FIND, ConsCfg.DEF_TRUE));
    }

    /**
     * @param findViw
     *            the findViw to set
     */
    public final void setFindViw(boolean findViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_FIND, findViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    /**
     * @return the toolLoc
     */
    public final String getToolLoc()
    {
        return userCfg.getProperty(ConsCfg.CFG_VIEW_TOOL_LOC, "North");
    }

    /**
     * @param toolLoc
     *            the toolLoc to set
     */
    public final void setToolLoc(String toolLoc)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_TOOL_LOC, toolLoc);
    }

    /**
     * @return the clnClp
     */
    public final int getStayTime()
    {
        return Integer.parseInt(userCfg.getProperty(ConsCfg.CFG_STAY_TIME, ConsCfg.DEF_STAY_TIME));
    }

    /**
     * @param clnClp
     *            the clnClp to set
     */
    public final void setStayTime(int clnClp)
    {
        userCfg.setProperty(ConsCfg.CFG_STAY_TIME, Integer.toString(clnClp));
    }

    /**
     * @return the pwdsLen
     */
    public final String getPwdsLen()
    {
        return userCfg.getProperty(ConsCfg.CFG_PWDS_SIZE, ConsCfg.DEF_PWDS_SIZE);
    }

    /**
     * @param pwdsLen
     *            the pwdsLen to set
     */
    public final void setPwdsLen(int pwdsLen)
    {
        userCfg.setProperty(ConsCfg.CFG_PWDS_SIZE, "" + pwdsLen);
    }

    /**
     * @return the pwdsKey
     */
    public final String getPwdsKey()
    {
        return userCfg.getProperty(ConsCfg.CFG_PWDS_HASH, ConsCfg.DEF_PWDS_HASH);
    }

    /**
     * @param pwdsKey
     *            the pwdsKey to set
     */
    public final void setPwdsKey(String pwdsKey)
    {
        userCfg.setProperty(ConsCfg.CFG_PWDS_HASH, pwdsKey);
    }

    /**
     * @return the bakNum
     */
    public final int getBackNum()
    {
        String txt = userCfg.getProperty(ConsCfg.CFG_BACK_SIZE, "3");
        if (Char.isValidatePositiveInteger(txt))
        {
            return Integer.parseInt(txt);
        }
        return 3;
    }

    /**
     * @param bakNum
     *            the bakNum to set
     */
    public final void setBackNum(int backNum)
    {
        userCfg.setProperty(ConsCfg.CFG_BACK_SIZE, "" + backNum);
    }

    public final int getHintInt()
    {
        String txt = userCfg.getProperty(ConsCfg.CFG_HINT_INT, "60");
        if (Char.isValidatePositiveInteger(txt))
        {
            return Integer.parseInt(txt);
        }
        return 60;
    }

    public final void setHintInt(int hintInt)
    {
        userCfg.setProperty(ConsCfg.CFG_HINT_INT, "" + hintInt);
    }

    public final int getHintPre()
    {
        String txt = userCfg.getProperty(ConsCfg.CFG_HINT_PRE, "300");
        if (Char.isValidatePositiveInteger(txt))
        {
            return Integer.parseInt(txt);
        }
        return 300;
    }

    public final void setHintPre(int hintPre)
    {
        userCfg.setProperty(ConsCfg.CFG_HINT_PRE, "" + hintPre);
    }

    /**
     * @return the bakDir
     */
    public final String getBackDir()
    {
        return userCfg.getProperty(ConsCfg.CFG_BACK_PATH, ConsCfg.DEF_BACK_PATH);
    }

    /**
     * @param backDir
     *            the backDir to set
     */
    public final void setBackDir(String backDir)
    {
        userCfg.setProperty(ConsCfg.CFG_BACK_PATH, backDir);
    }

    public final String getDataDir()
    {
        return userCfg.getProperty(ConsCfg.CFG_DATA_PATH, ConsCfg.DEF_DATA_PATH);
    }

    public final void setDataDir(String dataDir)
    {
        userCfg.setProperty(ConsCfg.CFG_BACK_PATH, dataDir);
    }

    /**
     * @return the editViw
     */
    public final boolean isEditVisible()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_EDIT_VIW, ConsCfg.DEF_TRUE));
    }

    /**
     * @param editViw
     *            the editViw to set
     */
    public final void setEditVisible(boolean editViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_EDIT_VIW, editViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    public final boolean isEditIsolate()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_EDIT_WND, ConsCfg.DEF_TRUE));
    }

    /**
     * @param isolate
     *            the editWnd to set
     */
    public final void setEditIsolate(boolean isolate)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_EDIT_WND, isolate ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    public final String getPwdsLoop()
    {
        return userCfg.getProperty(ConsCfg.CFG_PWDS_LOOP, ConsCfg.DEF_TRUE);
    }

    /**
     * @return the pwdUpt
     */
    public final boolean isPwdsLoop()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(getPwdsLoop());
    }

    /**
     * @param pwdsLoop
     *            the pwdsUpt to set
     */
    public final void setPwdsLoop(boolean pwdsLoop)
    {
        userCfg.setProperty(ConsCfg.CFG_PWDS_LOOP, pwdsLoop ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
    }

    public final void setUserLang(String lang)
    {
        userCfg.setProperty(ConsCfg.CFG_LANG, lang);
    }

    public final String getUserLang()
    {
        return userCfg.getProperty(ConsCfg.CFG_LANG, System.getProperty("user.language") + '_' + System.getProperty("user.country"));
    }

    public String getUserCode()
    {
        return "";//userSec.getCode();
    }

    public String getUserName()
    {
        return "";//userSec.getName();
    }
}
