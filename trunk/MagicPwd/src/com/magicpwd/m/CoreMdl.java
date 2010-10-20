/**
 * 
 */
package com.magicpwd.m;

/**
 * @author Amon
 */
public class CoreMdl
{

    // 程序运行模式
    private static int runMode;
    // 用户配置数据模型
    private UserCfg userCfg;
    private CboxMdl cboxMdl;
    private CharMdl charMdl;
    private HintMdl hintMdl;

    public CoreMdl()
    {
    }

    public void loadPre()
    {
        hintMdl = new HintMdl(this);
    }

    public void loadCfg()
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

    public void setUserCfg(UserCfg userCfg)
    {
        this.userCfg = userCfg;
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
        CoreMdl.runMode = runMode;
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
     * @return the hintMdl
     */
    public HintMdl getHintMdl()
    {
        return hintMdl;
    }

    /**
     * @param hintMdl the hintMdl to set
     */
    public void setHintMdl(HintMdl hintMdl)
    {
        this.hintMdl = hintMdl;
    }
}
