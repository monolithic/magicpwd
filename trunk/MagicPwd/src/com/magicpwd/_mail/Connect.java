/**
 * 
 */
package com.magicpwd._mail;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Logs;
import java.security.Security;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

/**
 * @author Amon
 * 
 */
public class Connect
{

    private String mail;
    private String protocol;
    private String host;
    private int port = -1;
    private boolean auth;
    private boolean jssl;
    private String username;
    private String password;
    private java.util.Properties oldProp;
    private java.util.Properties newProp;
    private static java.util.Properties mailCfg;
    private Session session;
    private Store store;

    static
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    }

    public Connect()
    {
        this(null, null);
    }

    public Connect(String mail, String pwds)
    {
        this(mail, pwds, null);
    }

    public Connect(String mail, String pwds, String protocol)
    {
        this.mail = mail;
        this.password = pwds;
        this.protocol = protocol;

        oldProp = new java.util.Properties();
        newProp = new java.util.Properties();
    }

    /**
     * @return the host
     */
    public String getHost()
    {
        if (host == null)
        {
            getDefaultHost();
        }
        return host;
    }

    /**
     * @return the mail
     */
    public String getMail()
    {
        return mail;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @return the port
     */
    public int getPort()
    {
        return port;
    }

    /**
     * @return the protocol
     */
    public String getProtocol()
    {
        return protocol;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        if (username == null)
        {
            getDefaultHost();
        }
        return username;
    }

    /**
     * @return the auth
     */
    public boolean isAuth()
    {
        return auth;
    }

    /**
     * @param auth
     *            the auth to set
     */
    public void setAuth(boolean auth)
    {
        this.auth = auth;
    }

    /**
     * @param host
     *            the host to set
     */
    public void setHost(String host)
    {
        this.host = host;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(int port)
    {
        this.port = port;
    }

    /**
     * @param protocol
     *            the protocol to set
     */
    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    public boolean useDefault()
    {
        if (!com.magicpwd._util.Char.isValidateEmail(mail))
        {
            return false;
        }

        java.util.Properties prop = getMailCfg();

        // 服务器地址
        host = mail.substring(mail.indexOf('@') + 1);
        String cfg = null;
        if (com.magicpwd._util.Char.isValidate(protocol))
        {
            cfg = prop.getProperty(protocol + '.' + host);
        }
        if (!com.magicpwd._util.Char.isValidate(cfg))
        {
            String type = prop.getProperty(host + ".type");
            if (!com.magicpwd._util.Char.isValidate(type))
            {
                return false;
            }
            protocol = type;
            cfg = prop.getProperty(protocol + '.' + host);
        }
        if (!com.magicpwd._util.Char.isValidate(cfg))
        {
            return false;
        }

        // 服务器地址
        String[] arr = (cfg + ":::false").split("[:]");
        host = arr[0];

        // 服务器端口
        cfg = arr[1].trim();
        if (com.magicpwd._util.Char.isValidatePositiveInteger(cfg))
        {
            port = Integer.parseInt(cfg);
        }

        // 是否需要身份认证
        auth = "true".equalsIgnoreCase(arr[2].trim().toLowerCase());
        // 是否需要安全认证
        jssl = "true".equalsIgnoreCase(arr[3].trim().toLowerCase());

        return true;
    }

    private void getDefaultHost()
    {
        int at = mail.indexOf('@');
        if (at > 0)
        {
            this.username = mail.substring(0, at);
        }
        this.host = getProtocol() + '.' + mail.substring(at + 1);
    }

    public Properties getProperties()
    {
        Properties prop = new Properties();
        //prop.put("mail.debug", "true");
        prop.put("username", getUsername());
        prop.put("password", getPassword());
        prop.put(com.magicpwd._util.Char.format("mail.{0}.user", getProtocol()), getUsername());
        prop.put(com.magicpwd._util.Char.format("mail.{0}.host", getProtocol()), getHost());
        prop.put(com.magicpwd._util.Char.format("mail.{0}.port", getProtocol()), getPort());
        prop.put(com.magicpwd._util.Char.format("mail.{0}.auth", getProtocol()), isAuth() ? "true" : "false");
        prop.put(com.magicpwd._util.Char.format("mail.{0}.rsetbeforequit", getProtocol()), "true");
        prop.put("mail.store.protocol", isJssl() ? getProtocol() + 's' : getProtocol());

        if (isJssl())
        {
            prop.put(com.magicpwd._util.Char.format("mail.{0}.starttls.enable", getProtocol()), "true");// 使用SSL验证
            prop.put(com.magicpwd._util.Char.format("mail.{0}.socketFactory.port", getProtocol()), getPort());//重新设定端口
            prop.put(com.magicpwd._util.Char.format("mail.{0}.socketFactory.class", getProtocol()), "javax.net.ssl.SSLSocketFactory");
            prop.put(com.magicpwd._util.Char.format("mail.{0}.socketFactory.fallback", getProtocol()), "false");
        }
        return prop;
    }

    public URLName getURLName()
    {
        return new URLName(getProtocol(), getHost(), getPort(), null, getUsername(), getPassword());
    }

    public Session getSession()
    {
        if (session == null)
        {
            session = Session.getDefaultInstance(getProperties(), isAuth() ? new Authenticator()
            {

                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(getUsername(), getPassword());
                }
            } : null);
        }
        return session;
    }

    public Store getStore() throws Exception
    {
        if (store == null)
        {
            store = getSession().getStore(getURLName());
            if (!store.isConnected())
            {
                store.connect();
            }
        }
        return store;
    }

    /**
     * @return the jssl
     */
    public boolean isJssl()
    {
        return jssl;
    }

    /**
     * @param jssl the jssl to set
     */
    public void setJssl(boolean jssl)
    {
        this.jssl = jssl;
    }

    public boolean loadMailInfo()
    {
        oldProp.clear();

        java.io.File file = new java.io.File(ConsEnv.DIR_MAIL, getMail() + ".amm");
        if (file.exists() && file.isFile() && file.canRead())
        {
            java.io.FileInputStream fis = null;
            try
            {
                fis = new java.io.FileInputStream(file);
                oldProp.load(fis);
                fis.close();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
            finally
            {
                if (fis != null)
                {
                    try
                    {
                        fis.close();
                    }
                    catch (Exception exp)
                    {
                        Logs.exception(exp);
                    }
                }
            }
        }

        return true;
    }

    public boolean saveMailInfo()
    {
        java.io.File file = new java.io.File(ConsEnv.DIR_MAIL, getMail() + ".amm");
        java.io.FileOutputStream fos = null;
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
            }
            if (!file.isFile() || !file.canWrite())
            {
                return false;
            }
            fos = new java.io.FileOutputStream(file);
            newProp.store(fos, host);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (Exception exp)
                {
                    Logs.exception(exp);
                }
            }
        }
        return true;
    }

    public boolean appendMailInfo(String messageId, boolean readed)
    {
        if (com.magicpwd._util.Char.isValidate(messageId))
        {
            newProp.setProperty(messageId, readed ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FALSE);
            return true;
        }
        return false;
    }

    public boolean isMailExists(String messageId)
    {
        if (com.magicpwd._util.Char.isValidate(messageId))
        {
            return ConsCfg.DEF_TRUE.equals(oldProp.getProperty(messageId));
        }
        return false;
    }

    public boolean isMailReaded(String messageId)
    {
        if (com.magicpwd._util.Char.isValidate(messageId))
        {
            return ConsCfg.DEF_TRUE.equals(newProp.getProperty(messageId));
        }
        return false;
    }

    public static java.util.Properties getMailCfg()
    {
        if (mailCfg == null)
        {
            mailCfg = new Properties();
            try
            {
                java.io.File file = new java.io.File(ConsEnv.DIR_DAT, "mail.config");
                if (!file.exists() || !file.isFile() || !file.canRead())
                {
                    return null;
                }
                java.io.FileInputStream fis = new java.io.FileInputStream(file);
                mailCfg.load(fis);
                fis.close();
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
        return mailCfg;
    }
}
