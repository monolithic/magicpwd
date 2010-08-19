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

    private Connect connnect;
    private String contentType = MailEnv.TEXT_HTML + ';' + MailEnv.CHARSET + "=UTF-8";

    public Mailer()
    {
    }

    /**
     * 获得发件人的地址和姓名
     */
    public abstract String getFrom();

    public abstract boolean setFrom(String from);

    /**
     * 获取收件人的地址和姓名
     * @return
     */
    public abstract String getTo();

    public abstract boolean setTo(String to);

    /**
     * 获取抄送人的地址和姓名
     * @return
     */
    public abstract String getCc();

    public abstract boolean addCc(String cc);

    /**
     * 获取密抄人的地址和姓名
     * @return
     */
    public abstract String getBcc();

    public abstract boolean addBcc(String bcc);

    /**
     * 获得邮件主题
     */
    public abstract String getSubject();

    public abstract void setSubject(String subject);

    /**
     * 获得邮件发送日期
     */
    public abstract Date getSentDate();

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
     * @return the connnect
     */
    public Connect getConnnect()
    {
        return connnect;
    }

    /**
     * @param connnect the connnect to set
     */
    public void setConnnect(Connect connnect)
    {
        this.connnect = connnect;
    }
}
