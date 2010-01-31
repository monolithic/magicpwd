/**
 * 
 */
package com.magicpwd._mail;

import com.magicpwd._util.Util;
import java.util.Properties;
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
    private String username;
    private String password;

    public Connect(String protocol, String mail, String pwds)
    {
        this.protocol = protocol;
        this.mail = mail;
        this.username = "Amon.CT";
        this.password = "bMjbmaG0zm";
    }

    /**
     * @return the host
     */
    String getHost()
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
    String getMail()
    {
        return mail;
    }

    /**
     * @return the password
     */
    String getPassword()
    {
        return password;
    }

    /**
     * @return the port
     */
    int getPort()
    {
        return port;
    }

    /**
     * @return the protocol
     */
    String getProtocol()
    {
        return protocol;
    }

    /**
     * @return the username
     */
    String getUsername()
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
    boolean isAuth()
    {
        return auth;
    }

    /**
     * @param auth
     *            the auth to set
     */
    void setAuth(boolean auth)
    {
        this.auth = auth;
    }

    /**
     * @param host
     *            the host to set
     */
    void setHost(String host)
    {
        this.host = host;
    }

    /**
     * @param password
     *            the password to set
     */
    void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @param port
     *            the port to set
     */
    void setPort(int port)
    {
        this.port = port;
    }

    /**
     * @param protocol
     *            the protocol to set
     */
    void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    /**
     * @param username
     *            the username to set
     */
    void setUsername(String username)
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
        prop.put(Util.format("mail.{0}.host", getProtocol()), getHost());
        prop.put(Util.format("mail.{0}.auth", getProtocol()), isAuth() ? "true" : "false");
        return prop;
    }

    public URLName getURLName()
    {
        return new URLName(getProtocol(), getHost(), getPort(), null, getUsername(), getPassword());
    }

    public Store getStore() throws Exception
    {
        Session session = Session.getDefaultInstance(getProperties());
        Store store = session.getStore(getURLName());
        if (!store.isConnected())
        {
            store.connect();
        }
        return store;
//        if (!f.isOpen())
//        {
//            f.open(Folder.READ_WRITE);
//        }
//        return f;
    }
}
