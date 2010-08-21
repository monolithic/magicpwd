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

    static
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    }

    public Connect()
    {
        oldProp = new java.util.Properties();
        newProp = new java.util.Properties();
    }

    public Connect(String protocol, String mail, String pwds)
    {
        this.protocol = protocol;
        this.mail = mail;
        this.password = pwds;

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
            getDefaultUserHost();
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
            getDefaultUserHost();
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

    private void getDefaultUserHost()
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
        return Session.getDefaultInstance(getProperties(), isAuth() ? new Authenticator()
        {

            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(getUsername(), getPassword());
            }
        } : null);
    }

    public Store getStore() throws Exception
    {
        Store store = getSession().getStore(getURLName());
        if (!store.isConnected())
        {
            store.connect();
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
        newProp.setProperty(messageId, readed ? ConsCfg.DEF_TRUE : ConsCfg.DEF_FAIL);
        return true;
    }

    public boolean isMailReaded(String messageId)
    {
        if (com.magicpwd._util.Char.isValidate(messageId))
        {
            return ConsCfg.DEF_TRUE.equals(oldProp.getProperty(messageId));
        }
        return false;
    }
}
