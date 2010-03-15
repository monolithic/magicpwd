/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Logs;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author Amon
 */
public final class MailCfg
{

    private Properties mailCfg;

    MailCfg()
    {
    }

    public final void loadCfg()
    {
        mailCfg = new Properties();
        try
        {
            File file = new File(ConsEnv.DIR_DAT, "mail.config");
            if (!file.exists() || !file.canRead())
            {
                return;
            }
            FileInputStream fis = new FileInputStream(file);
            mailCfg.load(fis);
            fis.close();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    public final void saveCfg()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(new File(ConsEnv.DIR_DAT, "mail.config"));
            mailCfg.store(fos, "MagicPwd Mail Configure File!");
            fos.close();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    public final String getCfg(String key)
    {
        return mailCfg.getProperty(key);
    }

    public final String getCfg(String key, String def)
    {
        return mailCfg.getProperty(key, def);
    }

    public final void setCfg(String key, String value)
    {
        mailCfg.setProperty(key, value);
    }
}
