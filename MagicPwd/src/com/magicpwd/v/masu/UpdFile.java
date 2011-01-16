/********************************************************************
 * 项目名称                ：rochoc<p>
 * 包名称                  ：com.rochoc.autoupdate<p>
 * 文件名称                ：UpdFile.java<p>
 * 编写者                 ：kfzx-luoc<p>
 * 编写日期                ：2008-12-23<p>
 * 程序功能（类）描述    ：<p>
 * 版本文件对像
 * 程序变更日期            ：
 * 变更作者                ：
 * 变更说明                ：
 ********************************************************************/
package com.magicpwd.v.masu;

import java.io.Serializable;

/**
 * @author Amon
 */
public class UpdFile implements Serializable
{

    private String name = "";//名称
    private String path = "";//路径
    private int type = 0;//类型 0.文件 1.目录
    private String version = "";//版本号

    public UpdFile()
    {
        super();
    }

    public UpdFile(String name)
    {
        this();
        this.name = name;
    }

    public UpdFile(String name, String path, int type, String version)
    {
        this(name);
        this.path = path;
        this.type = type;
        this.version = version;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the path.
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @param path The path to set.
     */
    public void setPath(String path)
    {
        this.path = path;
    }

    /**
     * @return Returns the type.
     */
    public int getType()
    {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(int type)
    {
        this.type = type;
    }

    /**
     * @return Returns the version.
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @param version The version to set.
     */
    public void setVersion(String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "Name:" + getName() + ",Path:" + getPath() + ",Type:" + getType() + ",Version:" + getVersion();
    }
}
