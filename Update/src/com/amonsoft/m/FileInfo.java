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

    private String name;
    private String version;
    private String strategy;
    private int operation;
    private String remotePath;
    private String localePath;

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

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
     * @return the localePath
     */
    public String getLocalePath()
    {
        return localePath;
    }

    /**
     * @param localePath the localePath to set
     */
    public void setLocalePath(String localePath)
    {
        this.localePath = localePath;
    }
}
