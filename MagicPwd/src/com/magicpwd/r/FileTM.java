/*
 * 
 */
package com.magicpwd.r;

import java.util.regex.Pattern;

/**
 * 功能：文件监视
 * @author Amon
 */
public class FileTM implements java.io.FileFilter
{

    private boolean hasFolder;
    private int lastSize;
    private long lastTime;
    private java.io.File filePath;
    private Pattern pattern;

    public FileTM(String path, Pattern pattern, boolean hasFolder)
    {
        this(new java.io.File(path), pattern, hasFolder);
    }

    public FileTM(java.io.File file, Pattern pattern, boolean hasFolder)
    {
        this.filePath = file;
        this.hasFolder = hasFolder;
        this.pattern = pattern;
    }

    @Override
    public boolean accept(java.io.File file)
    {
        if (file == null || !file.exists() || !file.canRead())
        {
            return false;
        }
        if (file.isDirectory())
        {
            return hasFolder;
        }
        if (!file.isFile())
        {
            return false;
        }

        return pattern.matcher(file.getName()).matches();
    }

    public boolean checkModified()
    {
        fileList = filePath.listFiles(this);
        lastTime = System.currentTimeMillis();
        if (fileList != null)
        {
            if (fileList.length != lastSize)
            {
                return true;
            }

            for (java.io.File file : fileList)
            {
                if (file.lastModified() > lastTime)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the fileList
     */
    public java.io.File[] getFileList()
    {
        return fileList;
    }

    /**
     * @param fileList the fileList to set
     */
    public void setFileList(java.io.File[] fileList)
    {
        this.fileList = fileList;
    }
    private java.io.File[] fileList;
}
