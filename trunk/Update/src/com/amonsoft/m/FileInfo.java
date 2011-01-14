/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amonsoft.m;

/**
 *
 * @author yaoshangwen
 */
public class FileInfo
{

    private String version;
    private String strategy;
    private int operation;
    private String remotePath;
    private String remoteName;
    private String localPath;
    private String localName;

    /**
     * @return the version
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version)
    {
        this.version = version;
    }

    /**
     * @return the strategy
     */
    public String getStrategy()
    {
        return strategy;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(String strategy)
    {
        this.strategy = strategy;
    }

    /**
     * @return the operation
     */
    public int getOperation()
    {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(int operation)
    {
        this.operation = operation;
    }

    /**
     * @return the remotePath
     */
    public String getRemotePath()
    {
        return remotePath;
    }

    /**
     * @param remotePath the remotePath to set
     */
    public void setRemotePath(String remotePath)
    {
        this.remotePath = remotePath;
    }

    /**
     * @return the remoteName
     */
    public String getRemoteName()
    {
        return remoteName;
    }

    /**
     * @param remoteName the remoteName to set
     */
    public void setRemoteName(String remoteName)
    {
        this.remoteName = remoteName;
    }

    /**
     * @return the localPath
     */
    public String getLocalPath()
    {
        return localPath;
    }

    /**
     * @param localPath the localPath to set
     */
    public void setLocalPath(String localPath)
    {
        this.localPath = localPath;
    }

    /**
     * @return the localName
     */
    public String getLocalName()
    {
        return localName;
    }

    /**
     * @param localName the localName to set
     */
    public void setLocalName(String localName)
    {
        this.localName = localName;
    }
}
