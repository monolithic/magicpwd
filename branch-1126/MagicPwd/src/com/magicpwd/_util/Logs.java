/**
 * 
 */
package com.magicpwd._util;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd.m.UserMdl;

/**
 * @author shangwen.yao
 * 
 */
public class Logs
{

    private static java.io.PrintWriter writer;

    public static void dev(String log)
    {
        System.out.println(log);
    }

    public static void log(String log)
    {
        if (UserMdl.getRunMode() == ConsEnv.RUN_MODE_DEV)
        {
            System.out.println(log);
        }
    }

    public static void exception(Exception exp)
    {
        if (UserMdl.getRunMode() == ConsEnv.RUN_MODE_DEV)
        {
            exp.printStackTrace();
        }
        getWriter().println(exp.toString());
    }

    public static void end()
    {
        if (writer != null)
        {
            writer.flush();
            writer.close();
        }
    }

    private static java.io.PrintWriter getWriter()
    {
        if (writer == null)
        {
            try
            {
                java.io.File logs = new java.io.File(ConsEnv.DIR_LOG);
                if (!logs.exists())
                {
                    logs.mkdirs();
                }
                logs = new java.io.File(logs, "amon.log");
                if (!logs.exists())
                {
                    logs.createNewFile();
                }
                writer = new java.io.PrintWriter(logs);
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        return writer;
    }
}
