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
    private SafeMdl safeMdl;
    // 魔方密码数据模型
    private GridMdl gridMdl;
    private ListMdl listMdl;
    private HintMdl hintMdl;
    private TreeMdl treeMdl;
    private CboxMdl cboxMdl;
    private CharMdl charMdl;
    // 记事便签数据模型
    private NoteMdl noteMdl;

    private CoreMdl()
    {
    }

    public void preLoad()
    {
        gridMdl = new GridMdl(this);
        listMdl = new ListMdl(this);
        Kind kind = new Kind();
        kind.setC2010103(ConsDat.HASH_ROOT);
        kind.setC2010105("魔方密码");
        kind.setC2010106("魔方密码");
        treeMdl = new TreeMdl(new KindTN(kind));
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

    /**
     * @return the safeMdl
     */
    public SafeMdl getSafeMdl()
    {
        return safeMdl;
    }

    /**
     * @param aSafeMdl the safeMdl to set
     */
    public void setSafeMdl(SafeMdl aSafeMdl)
    {
        safeMdl = aSafeMdl;
    }
}
