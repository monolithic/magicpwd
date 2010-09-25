/**
 * 
 */
package com.magicpwd.m;

import com.magicpwd._comn.Kind;
import com.magicpwd._cons.ConsDat;
import com.magicpwd.r.KindTN;

/**
 * @author Amon
 * 
 */
public final class CoreMdl
{

    // 程序运行模式
    private static int runMode;
    // 用户配置数据模型
    private UserCfg userCfg;
    private ListMdl listMdl;
    private HintMdl hintMdl;
    private TreeMdl treeMdl;
    private CboxMdl cboxMdl;
    private CharMdl charMdl;

    public CoreMdl()
    {
    }

    public void preLoad()
    {
        listMdl = new ListMdl(this);
        Kind kind = new Kind();
        kind.setC2010103(ConsDat.HASH_ROOT);
        kind.setC2010105("魔方密码");
        kind.setC2010106("魔方密码");
        treeMdl = new TreeMdl(new KindTN(kind));
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
     * @return the listMdl
     */
    public ListMdl getListMdl()
    {
        return listMdl;
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
     * @param aHintMdl the hintMdl to set
     */
    public void setHintMdl(HintMdl aHintMdl)
    {
        hintMdl = aHintMdl;
    }
}
