/**
 * 用户配置信息
 * CopyRight: MagicPwd.com
 * Homepage:http://magicpwd.com/
 * Project:http://magicpwd.dev.java.net/
 * Email:Amon@amonsoft.cn
 */
package com.magicpwd.m;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Logs;

/**
 * @author Amon
 * 
 */
public final class UserCfg
{

    private Properties userCfg;
    private boolean viewTop;

    UserCfg()
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
        userCfg.setProperty(ConsCfg.CFG_VIEW_TOPS, ConsCfg.DEF_FAIL);
        userCfg.setProperty(ConsCfg.CFG_VIEW_MENU, ConsCfg.DEF_TRUE);
        userCfg.setProperty(ConsCfg.CFG_VIEW_TOOL, ConsCfg.DEF_FAIL);
        userCfg.setProperty(ConsCfg.CFG_VIEW_INFO, ConsCfg.DEF_FAIL);
        userCfg.setProperty(ConsCfg.CFG_VIEW_FIND, ConsCfg.DEF_TRUE);
        userCfg.setProperty(ConsCfg.CFG_VIEW_TOOL_LOC, "North");

        userCfg.setProperty(ConsCfg.CFG_STAY_TIME, ConsCfg.DEF_STAY_TIME);

        userCfg.setProperty(ConsCfg.CFG_PWDS_SIZE, ConsCfg.DEF_PWDS_SIZE);
        userCfg.setProperty(ConsCfg.CFG_PWDS_CHAR, ConsCfg.DEF_PWDS_HASH);

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
    public final boolean isMenuViw()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_MENU, ConsCfg.DEF_TRUE));
    }

    /**
     * @param menuViw
     *            the menuViw to set
     */
    public final void setMenuViw(boolean menuViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_MENU, menuViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FAIL);
    }

    /**
     * @return the toolViw
     */
    public final boolean isToolViw()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_TOOL));
    }

    /**
     * @param toolViw
     *            the toolViw to set
     */
    public final void setToolViw(boolean toolViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_TOOL, toolViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FAIL);
    }

    /**
     * @return the infoViw
     */
    public final boolean isInfoViw()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_INFO, ConsCfg.DEF_TRUE));
    }

    /**
     * @param infoViw
     *            the infoViw to set
     */
    public final void setInfoViw(boolean infoViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_INFO, infoViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FAIL);
    }

    /**
     * @return the findViw
     */
    public final boolean isFindViw()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_FIND, ConsCfg.DEF_TRUE));
    }

    /**
     * @param findViw
     *            the findViw to set
     */
    public final void setFindViw(boolean findViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_FIND, findViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FAIL);
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
     * @return the pwdsSet
     */
    public final String getPwdsSet()
    {
        return userCfg.getProperty(ConsCfg.CFG_PWDS_CHAR, ConsCfg.DEF_PWDS_HASH);
    }

    /**
     * @param pwdsSet
     *            the pwdsSet to set
     */
    public final void setPwdsSet(String pwdsSet)
    {
        userCfg.setProperty(ConsCfg.CFG_PWDS_CHAR, pwdsSet);
    }

    /**
     * @return the bakNum
     */
    public final int getBackNum()
    {
        int size;
        try
        {
            size = Integer.parseInt(userCfg.getProperty(ConsCfg.CFG_BACK_SIZE, "3"));
        }
        catch (NumberFormatException exp)
        {
            Logs.exception(exp);
            size = 3;
        }
        return size;
    }

    /**
     * @param bakNum
     *            the bakNum to set
     */
    public final void setBackNum(int backNum)
    {
        userCfg.setProperty(ConsCfg.CFG_BACK_SIZE, "" + backNum);
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

    /**
     * @return the editViw
     */
    public final boolean isEditViw()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_EDIT, ConsCfg.DEF_TRUE));
    }

    /**
     * @param editViw
     *            the editViw to set
     */
    public final void setEditViw(boolean editViw)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_EDIT, editViw ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FAIL);
    }

    /**
     * @return the propSpt
     */
    public final boolean isEditWnd()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_VIEW_EDIT_WND));
    }

    /**
     * @param editWnd
     *            the editWnd to set
     */
    public final void setEditWnd(boolean editWnd)
    {
        userCfg.setProperty(ConsCfg.CFG_VIEW_EDIT_WND, editWnd ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FAIL);
    }

    public final String getPwdsUpt()
    {
        return userCfg.getProperty(ConsCfg.CFG_PWDS_URPT, "");
    }

    /**
     * @return the pwdUpt
     */
    public final boolean isPwdsUpt()
    {
        return ConsCfg.DEF_TRUE.equalsIgnoreCase(userCfg.getProperty(ConsCfg.CFG_PWDS_URPT));
    }

    /**
     * @param pwdsUpt
     *            the pwdsUpt to set
     */
    public final void setPwdsUpt(boolean pwdsUpt)
    {
        userCfg.setProperty(ConsCfg.CFG_PWDS_URPT, pwdsUpt ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FAIL);
    }

    public final void setUserLang(String lang)
    {
        userCfg.setProperty(ConsCfg.CFG_LANG, lang);
    }

    public final String getUserLang()
    {
        return userCfg.getProperty(ConsCfg.CFG_LANG, System.getProperty("user.language") + '_' + System.getProperty("user.country"));
    }
}
