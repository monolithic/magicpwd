/**
 * 
 */
package com.magicpwd._mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

/**
 * @author Amon
 * 
 */
public abstract class Mailer
{

    private Connect connect;
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private Date sentDate;
    /**
     * 存放邮件内容
     */
    private StringBuffer subject;
    private String contentType = MailEnv.TEXT_HTML + ';' + MailEnv.CHARSET + "=UTF-8";

    public Mailer()
    {
        subject = new StringBuffer();
    }

    /**
     * 获得发件人的地址和姓名
     */
    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    /**
     * 获取收件人的地址和姓名
     * @return
     */
    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    /**
     * 获取抄送人的地址和姓名
     * @return
     */
    public String getCc()
    {
        return cc;
    }

    public void setCc(String cc)
    {
        this.cc = cc;
    }

    /**
     * 获取密抄人的地址和姓名
     * @return
     */
    public String getBcc()
    {
        return bcc;
    }

    public void setBcc(String bcc)
    {
        this.bcc = bcc;
    }

    /**
     * 获得邮件主题
     */
    public StringBuffer getSubject()
    {
        return subject;
    }

    public void setSubject(StringBuffer subject)
    {
        this.subject = subject;
    }

    public void appendSubject(String subject)
    {
        this.subject.append(subject);
    }

    /**
     * 获得邮件发送日期
     */
    public Date getSentDate()
    {
        return sentDate;
    }

    public void setSentDate(Date sentDate)
    {
        this.sentDate = sentDate;
    }

    /**
     * 获得此邮件的Message-ID
     */
    public abstract String getMessageId();

    public String getContentType()
    {
        return contentType;
    }

    /**
     * 【判断此邮件是否已读，如果未读返回返回 false,反之返回true】
     */
    public boolean isNew()
    {
        return false;
    }

    protected String decodeAddress(Address[] addresses) throws UnsupportedEncodingException
    {
        if (addresses == null || addresses.length < 1)
        {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        for (Address addr : addresses)
        {
            if (addr instanceof InternetAddress)
            {
                InternetAddress temp = (InternetAddress) addr;
                String personal = temp.getPersonal();
                String address = temp.getAddress();
                buffer.append(personal != null ? MimeUtility.decodeText(personal) : "");
                buffer.append('<').append(address != null ? MimeUtility.decodeText(address) : "").append(">,");
                continue;
            }
            buffer.append(addr.toString()).append(',');
        }
        return buffer.toString();
    }

    protected static String decodeMessage(String text) throws UnsupportedEncodingException
    {
        System.out.println(text);
        if (text == null)
        {
            return null;
        }
        if (text.toLowerCase().startsWith("=?gb"))
        {
            return MimeUtility.decodeText(text);
        }
        return text;//new String(text.getBytes("ISO8859_1"));
    }

    /**
     * @return the connect
     */
    public Connect getConnect()
    {
        return connect;
    }

    /**
     * @param connect the connect to set
     */
    public void setConnect(Connect connect)
    {
        this.connect = connect;
    }
}
