/**
 * 
 */
package com.magicpwd.r;

import java.io.File;

/**
 * @author Amon
 * 
 */
public class AmonFF implements java.io.FileFilter
{
    @Override
    public boolean accept(File pathname)
    {
        if (pathname.isDirectory())
        {
            return true;
        }
        String fileName = pathname.getName();
        if (fileName == null)
        {
            return false;
        }
        fileName = fileName.toLowerCase();
        if (!fileName.startsWith("amon_") || !fileName.endsWith(".backup"))
        {
            return false;
        }
        return true;
    }
}
