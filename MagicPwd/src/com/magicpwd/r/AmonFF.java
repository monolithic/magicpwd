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
    private HashMap<String, String> exceptFiles;

    public AmonFF(boolean dirOnly, String... excepts)
    {
        this.dirOnly = dirOnly;
        processExcepts(excepts);
    }

    public AmonFF(String regex, boolean igoreCase, String... excepts)
    {
        this.igoreCase = igoreCase;
        pattern = igoreCase ? Pattern.compile(regex, Pattern.UNICODE_CASE) : Pattern.compile(regex);
        processExcepts(excepts);
    }

    private void processExcepts(String... excepts)
    {
        if (excepts == null || excepts.length < 1)
        {
            exceptFile = false;
            exceptFiles = new HashMap<String, String>(1);
            return;
        }

        exceptFile = true;
        exceptFiles = new HashMap<String, String>(excepts.length + 2);
        for (String file : excepts)
        {
            exceptFiles.put(igoreCase ? file.toLowerCase() : file, "");
        }
    }

    @Override
    public boolean accept(File file)
    {
        String fileName = file.getName();
        if (exceptFile && exceptFiles.containsKey(igoreCase ? fileName.toLowerCase() : fileName))
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
