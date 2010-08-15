/**
 * 
 */
package com.magicpwd.r;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * @author Amon
 * 
 */
public class AmonFF extends javax.swing.filechooser.FileFilter implements java.io.FileFilter
{

    private Pattern pattern;
    private boolean dirOnly;
    private boolean includeDir;
    private boolean igoreCase;
    private boolean exceptFile;
    private String description;
    private HashMap<String, Boolean> exceptFiles;

    public AmonFF(boolean dirOnly, String... excepts)
    {
        this.dirOnly = dirOnly;
        if (excepts != null && excepts.length > 0)
        {
            exceptFile = true;
            exceptFiles = new HashMap<String, Boolean>(excepts.length + 2);
            for (String file : excepts)
            {
                exceptFiles.put(igoreCase ? file.toLowerCase() : file, true);
            }
        }
        else
        {
            exceptFiles = new HashMap<String, Boolean>(1);
        }
    }

    public AmonFF(String regex, boolean igoreCase, String... excepts)
    {
        this.igoreCase = igoreCase;
        pattern = igoreCase ? Pattern.compile(regex, Pattern.UNICODE_CASE) : Pattern.compile(regex);
        if (excepts != null && excepts.length > 0)
        {
            exceptFile = true;
            exceptFiles = new HashMap<String, Boolean>(excepts.length + 2);
            for (String file : excepts)
            {
                exceptFiles.put(igoreCase ? file.toLowerCase() : file, true);
            }
        }
        else
        {
            exceptFiles = new HashMap<String, Boolean>(1);
        }
    }

    @Override
    public boolean accept(File file)
    {
        String fileName = file.getName();
        if (exceptFile && exceptFiles.get(igoreCase ? fileName.toLowerCase() : fileName))
        {
            return false;
        }

        if (dirOnly)
        {
            return file.isDirectory();
        }

        if (file.isDirectory())
        {
            return includeDir;
        }

        return fileName != null ? pattern.matcher(fileName).matches() : false;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the includeDir
     */
    public boolean isIncludeDir()
    {
        return includeDir;
    }

    /**
     * @param includeDir the includeDir to set
     */
    public void setIncludeDir(boolean includeDir)
    {
        this.includeDir = includeDir;
    }
}
