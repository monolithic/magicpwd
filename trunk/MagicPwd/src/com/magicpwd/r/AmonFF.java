/**
 * 
 */
package com.magicpwd.r;

import java.io.File;
import java.util.regex.Pattern;

/**
 * @author Amon
 * 
 */
public class AmonFF extends javax.swing.filechooser.FileFilter implements java.io.FileFilter
{

    private Pattern pattern;
    private boolean hasFolder;
    private String description;

    public AmonFF(String regex, boolean igoreCase)
    {
        this.pattern = igoreCase ? Pattern.compile(regex, Pattern.UNICODE_CASE) : Pattern.compile(regex);
    }

    @Override
    public boolean accept(File pathName)
    {
        if (pathName.isDirectory())
        {
            return hasFolder;
        }
        String fileName = pathName.getName();
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
     * @return the hasFolder
     */
    public boolean isHasFolder()
    {
        return hasFolder;
    }

    /**
     * @param hasFolder the hasFolder to set
     */
    public void setHasFolder(boolean hasFolder)
    {
        this.hasFolder = hasFolder;
    }
}
