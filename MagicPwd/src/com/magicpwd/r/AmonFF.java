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
public class AmonFF implements java.io.FileFilter
{

    private Pattern pattern;

    public AmonFF(String regex, boolean igoreCase)
    {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean accept(File pathName)
    {
        if (pathName.isDirectory())
        {
            return true;
        }
        String fileName = pathName.getName();
        return fileName != null ? pattern.matcher(fileName).matches() : false;
//        if (fileName == null)
//        {
//            return false;
//        }
//        if (!fileName.startsWith("amon_") || !fileName.endsWith(".backup"))
//        {
//            return false;
//        }
//        return true;
    }
}
