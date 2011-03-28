/*
 *  Copyright (C) 2011 Amon
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.m;

import com.magicpwd._enum.AppView;
import com.magicpwd._enum.RunMode;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Logs;

/**
 *
 * @author Amon
 */
public final class MpwdMdl
{

    private static RunMode runMode = RunMode.app;
    private static AppView appView = AppView.mwiz;
    private java.util.Properties mpwdCfg;
    private String datPath;
    private String bakPath;

    public void loadCfg()
    {
        mpwdCfg = new java.util.Properties();

        java.io.FileInputStream fis = null;
        try
        {
            java.io.File cfgFile = new java.io.File("magicpwd.cfg");
            if (cfgFile.exists() && cfgFile.isFile() && cfgFile.canRead())
            {
                fis = new java.io.FileInputStream(cfgFile);
                mpwdCfg.load(fis);
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            Bean.closeStream(fis);
        }

        setDatPath(mpwdCfg.getProperty("path.dat"));
        setBakPath(mpwdCfg.getProperty("path.bak"));
        setAppView(mpwdCfg.getProperty("view.last", "mwiz"));
    }

    public void saveCfg()
    {
        java.io.FileOutputStream fis = null;
        try
        {
            java.io.File cfgFile = new java.io.File("magicpwd.cfg");
            if (!cfgFile.exists())
            {
                cfgFile.createNewFile();
            }
            if (cfgFile.isFile() && cfgFile.canWrite())
            {
                fis = new java.io.FileOutputStream(cfgFile);
                mpwdCfg.setProperty("view.last", appView.name());
                mpwdCfg.store(fis, "");
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            Bean.closeStream(fis);
        }
    }

    /**
     * @return the runMode
     */
    public static RunMode getRunMode()
    {
        return runMode;
    }

    /**
     * @param runMode the runMode to set
     */
    public static void setRunMode(RunMode runMode)
    {
        MpwdMdl.runMode = runMode;
    }

    public static void setRunMode(String runMode)
    {
        if ("webstart".equalsIgnoreCase(runMode) || "web".equalsIgnoreCase(runMode))
        {
            MpwdMdl.runMode = RunMode.web;
        }
        else if ("command".equalsIgnoreCase(runMode) || "cmd".equalsIgnoreCase(runMode))
        {
            MpwdMdl.runMode = RunMode.cmd;
        }
        else if ("dev".equalsIgnoreCase(runMode))
        {
            MpwdMdl.runMode = RunMode.dev;
        }
    }

    /**
     * @return the appView
     */
    public static AppView getAppView()
    {
        return appView;
    }

    /**
     * @param appView the appView to set
     */
    public static void setAppView(AppView appView)
    {
        MpwdMdl.appView = appView;
    }

    public static void setAppView(String appView)
    {
        if (Char.isValidate(appView, 4))
        {
            appView = appView.toLowerCase();
            try
            {
                MpwdMdl.appView = AppView.valueOf(appView);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                MpwdMdl.appView = AppView.mwiz;
            }
        }
    }

    public static void setAppModule(String appModule)
    {
        if (appModule != null)
        {
            MpwdMdl.appView = AppView.valueOf(appModule.toLowerCase());
        }
    }

    /**
     * @return the datPath
     */
    public String getDatPath()
    {
        return datPath;
    }

    /**
     * @param datPath the datPath to set
     */
    public void setDatPath(String datPath)
    {
        if (!Char.isValidate(datPath))
        {
            datPath = "dat";
        }
        if (datPath.endsWith("/"))
        {
            datPath = datPath.substring(0, datPath.length() - 1);
        }
        this.datPath = datPath;
    }

    /**
     * @return the bakPath
     */
    public String getBakPath()
    {
        return bakPath;
    }

    /**
     * @param bakPath the bakPath to set
     */
    public void setBakPath(String bakPath)
    {
        if (!Char.isValidate(datPath))
        {
            datPath = "dat";
        }
        if (datPath.endsWith("/"))
        {
            datPath = datPath.substring(0, datPath.length() - 1);
        }
        this.bakPath = bakPath;
    }

    public String getViewList()
    {
        return mpwdCfg.getProperty("view.list", "");
    }

    public String getViewLast()
    {
        return mpwdCfg.getProperty("view.last", "");
    }
}
