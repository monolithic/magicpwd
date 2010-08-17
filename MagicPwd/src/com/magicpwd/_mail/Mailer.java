/**
 * 
 */
package com.magicpwd._mail;

import com.magicpwd._util.Logs;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * @author Amon
 * 
 */
public class Mailer
{

    protected MimeMessage message;
    private String contentType = MailEnv.TEXT_HTML + ';' + MailEnv.CHARSET + "=UTF-8";
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private Date sentDate;

    public Mailer(MimeMessage message)
    {
        this.message = message;
    }

    public boolean initData() throws Exception
    {
        from = decodeAddress(message.getFrom());
        to = decodeAddress(message.getRecipients(Message.RecipientType.TO));
        cc = decodeAddress(message.getRecipients(Message.RecipientType.CC));
        bcc = decodeAddress(message.getRecipients(Message.RecipientType.BCC));
        subject = decodeMessage(message.getSubject());
        sentDate = message.getSentDate();
        return true;
    }

    public String getContentType()
    {
        return contentType;
    }

    /**
     * 获得发件人的地址和姓名
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * 获取收件人的地址和姓名
     * @return
     */
    public String getTo()
    {
        return to;
    }

    /**
     * 获取抄送人的地址和姓名
     * @return
     */
    public String getCc()
    {
        return cc;
    }

    /**
     * 获取密抄人的地址和姓名
     * @return
     */
    public String getBcc()
    {
        return bcc;
    }

    /**
     * 获得邮件主题
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * 获得邮件发送日期
     */
    public Date getSentDate()
    {
        return sentDate;
    }

    /**
     * 获得此邮件的Message-ID
     */
    public String getMessageId()
    {
        return "";//mimeMessage.getMessageID();
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
}
